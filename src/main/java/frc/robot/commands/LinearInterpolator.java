package frc.robot.commands;



public class LinearInterpolator {
    

    //range in inches, power in RPM
    static double[][] powerPerRange = {//range first then power
        {60,1000},        //inches to goal, rpm
        {120,2000},
        {180,3000},
        {240,4000}
    };


    public static double calcRPM(double inchesFromTarget)
    {
        double currentRange = inchesFromTarget;//inches
        double RPM = 0;
        int closeLower = -1;
        int closeHigher = -1;

        if(currentRange < 120){
            closeLower = 0;
            closeHigher = 1;
        }else if(currentRange < 180){
            closeLower = 1;
            closeHigher = 2;
        }else if(currentRange <= 240){
            closeLower = 2;
            closeHigher = 3;
        }

        if(closeLower == -1 && closeHigher == -1)
        {
            return 0;
        }else{
            double slope = (powerPerRange[closeHigher][1] - powerPerRange[closeLower][1])/(powerPerRange[closeHigher][0] - powerPerRange[closeLower][0]);
            double yIntercept = powerPerRange[closeLower][1] - (slope * powerPerRange[closeLower][0]);

            RPM = (slope * currentRange) + yIntercept;
        }

        return RPM; 
    }

}
