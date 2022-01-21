/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.pixy2java;

/**
 * Add your docs here.
 */
public class PixyCamVector {
    public byte x0, x1, y0, y1, index, flags, errorCode;

    public double deltaX;
    public double deltaY;
    public double tanget;

    public PixyCamVector (byte x0, byte x1, byte y0, byte y1, byte index, byte flags, byte errorCode)
    {
        this.x0 = x0 ;
        this.x1 = x1 ;
        this.y0 = y0 ;
        this.y1 = y1 ;
        this.index = index;
        this.flags = flags;
        this.errorCode = errorCode;
    }

    public double getTheta(){
        deltaY = y0 - y1;
        deltaX = x1 - x0; 
        tanget = deltaY / deltaX; 
        return(Math.atan(tanget));
    }
    public double getPhi(){
        int y = 51 - y1;
        int x = x1 - 39;
        return(Math.atan(x / y));
    }
}