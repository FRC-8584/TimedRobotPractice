package frc.robot;


import com.revrobotics.spark.config.SparkMaxConfig;

public class Tool {
    static double deadband ( double a){
        if (Math.abs(a) < 0.1){
            return 0 ;
        }
        else{
            return a ;
        }
    }
    static double bounding (double a, double MaxOutput, double MinOutput) {
        if (a > MaxOutput){
            return MaxOutput ;
        }
        else if (a < MinOutput){
            return MinOutput ;
        }
        else {
            return a ; 
        }       
    }

    static boolean IsInRange(double value, double Max, double min){
        return (value>=min&&value<=Max) ? true : false;
    }

    static SparkMaxConfig setConfig(boolean invert) {
        SparkMaxConfig theConfig;
        theConfig = new SparkMaxConfig();
        theConfig.inverted(invert);
        return theConfig;
    }

    public static enum Levels{

        L1 (0.0, 0.0),
        L2 (25.0,15.0),
        L3 (45.0,15.0),
        L4 (70.0,35.0),
        Default(0.0,0.0);

        private double ElevatorHeight, ClawAngle;
        
        private Levels(double ElevatorHeight, double ClawAngle) {
            this.ElevatorHeight = ElevatorHeight;
            this.ClawAngle = ClawAngle;    
        }

        public double GetHeight() {
            return this.ElevatorHeight;
        }

        public double GetAngle() {
            return this.ClawAngle;
        }

    }

}
