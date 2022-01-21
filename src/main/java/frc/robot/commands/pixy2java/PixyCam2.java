/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.pixy2java;

import edu.wpi.first.wpilibj.I2C;

/**
 * Add your docs here.
 */
public class PixyCam2 {

    public PixyCam2(int portNum) {
        mI2C = new I2C(I2C.Port.kOnboard, portNum) ;
   }
    I2C mI2C;

   private byte[] sendAndRecieve(int type, byte[]outbytes, int MaxExpectedReturnPayload){
    // Find length of desired output data packet
    // If no packet supplied, then use zero 

    int length ;

    if (outbytes == null){
        length = 0;
    }else{
        length = outbytes.length ;
    }

    //System.out.println( "sr out length =" + length) ;
    // Prepare initial 4 bytes of outgoing data packet

    byte[] myOut = new byte[length + 4];
    myOut[0] = (byte)174;
    myOut[1] = (byte)193;
    myOut[2] = (byte)type;
    myOut[3] = (byte)length;

    if(length > 0){
        for(int i = 0; i < length; i++){
            myOut[4 + i] = outbytes[i];
        }
    }
   
   // byte[] getver = new byte[]  {(byte) 174, (byte) 193, 14, 0};   
   // boolean bok = mI2C.writeBulk(getver, 4) ;

    boolean bok = mI2C.writeBulk(myOut, myOut.length) ;

    //System.out.println("write result" + bok + "myout.length =" + myOut.length) ;
    byte [] retval = new byte[ MaxExpectedReturnPayload + 6];

    bok = mI2C.readOnly(retval, retval.length) ;

    //System.out.println ("Read result " + bok + " retval length" + retval.length) ;

    //mI2C.close(); 

    return retval ;
    }

    public String getVersion(){   

        // Send and received packet for operation 14, GetVersion.
        // No input data required

        byte [] retval = sendAndRecieve(14, null, 32) ;

        // If operation succeeds, then majot version is in output array 
        // element 8 and minor is in output array element 9

        if(retval != null){
            int major = retval[8];
            int minor = retval[9];
            return("FW  Major: " + major + " Minor: " + minor );
        }else{
            return("Get Version Failed");
        }
    }

    private String bytesToHex1(byte[] hashInBytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hashInBytes.length; i++) {
            sb.append(Integer.toString((hashInBytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    public byte [] SetLamps (Boolean Upper, boolean Lower) {
        byte[] lamps = new byte[2];

        if (Upper == true){
         lamps[0] = 1;
        }else{
            lamps [0] = 0 ;}

        if (Lower == true){
            lamps [1] = 1;
        }else{
            lamps[1] = 0 ;
        }

        byte[] retvect = sendAndRecieve(22, lamps, 32); 

        return retvect ;
    }

    public PixyCamBlock [] getBlocks(){
        // Send and received packet for operation 32, GetBlocks.
        byte[] blockParam = new byte[2];

        blockParam[0] = (byte)255;
        blockParam[1] = (byte)255;

        byte [] retval = sendAndRecieve(32, blockParam, 64) ;

        // Check return success value

        if (retval[2] != 33)
        {
            System.out.println ("Getblocks error:" + Byte.toUnsignedInt(retval[2]));
            return null ;
        }
       
        int nBlocks = retval [3] /14 ;

        if (nBlocks > 2)
        {
            System.out.println("nBlocks = " + nBlocks) ;
            nBlocks = 0 ;
        }

        PixyCamBlock [] retblocks = new PixyCamBlock [nBlocks] ;

        for (int i = 0 ; i < nBlocks; i++)
        {
            int offset = 14 * i ;

            //Signature Color of block
            int SignatureNumber = Byte.toUnsignedInt ( retval [7 + offset] );
            SignatureNumber <<= 8 ;
            SignatureNumber += Byte.toUnsignedInt (retval [6 + offset]) ;

            //X value of Center of block
            int XCenter = Byte.toUnsignedInt(retval [9 + offset]) ;
            XCenter <<= 8 ;
            XCenter += Byte.toUnsignedInt(retval [8 + offset]) ;

            //Y value of Center of Block
            int YCenter = Byte.toUnsignedInt(retval [11 + offset]) ;
            YCenter <<= 8 ;
            YCenter += Byte.toUnsignedInt(retval [10 + offset]) ;

            //Width of block
            int Width = Byte.toUnsignedInt(retval [13 + offset]) ;
            Width <<= 8 ;
            Width += Byte.toUnsignedInt(retval [12 + offset]) ;
            
            //Height of block
            int Height = Byte.toUnsignedInt(retval [15 + offset]) ;
            Height <<= 8 ;
            Height += Byte.toUnsignedInt(retval [14 + offset]) ;
        
            //Angle of Color in degrees
            int AngleOfColor = Byte.toUnsignedInt(retval [17 + offset]) ;
            AngleOfColor <<= 8 ;
            AngleOfColor += Byte.toUnsignedInt(retval [16 + offset]) ;

            //Tracking index of block
            int TrackingIndex = Byte.toUnsignedInt(retval [18 + offset]) ;

            //Number of frames this block has been traced
            int Age = Byte.toUnsignedInt(retval [19 + offset]) ;

            // Allocate new pixyCamBlock, place in output array

            retblocks [i] = new PixyCamBlock (SignatureNumber, XCenter, YCenter, 
                Width, Height, AngleOfColor, TrackingIndex, Age) ;

                
        }
  

        return retblocks ;
        
    }

    public PixyCamBlock GetCentermostBlock ()
    {
        PixyCamBlock [] AllBlocks = getBlocks() ;
     

        if (AllBlocks == null){
          return null ;
        }

        if (AllBlocks.length == 0)
        {
            System.out.println("AllBlocks.length = 0") ;
            return null ;

        }
          int minDistanceFromCenter = 160;
          int centerMostIndex = 0;
        
            for(int i = 0; i < AllBlocks.length; i++){
                int thisDistance = Math.abs(160 - AllBlocks[i].xCenter);
                if(thisDistance < minDistanceFromCenter){
                    centerMostIndex = i;
                    minDistanceFromCenter = thisDistance;
                }
            } 
    

        return AllBlocks [centerMostIndex] ;
    }

   
}