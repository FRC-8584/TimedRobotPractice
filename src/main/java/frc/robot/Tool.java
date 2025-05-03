package frc.robot;


import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

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

    static SparkMaxConfig setElevatorConfig(boolean invert) {
        SparkMaxConfig theConfig;
        theConfig = new SparkMaxConfig();
        theConfig.inverted(invert).idleMode(IdleMode.kBrake);
        theConfig.closedLoop.pid(0.1, 1e-4, 0.4).iZone(0.1);
        return theConfig;
    }

    static SparkMaxConfig SetClawConfig(boolean invert) {
        SparkMaxConfig theConfig;
        theConfig = new SparkMaxConfig();
        theConfig.inverted(invert).idleMode(IdleMode.kBrake);
        theConfig.closedLoop.pid(0.1, 0, 0.6);
        return theConfig;
    }

    public static enum Levels{

        L1 (0.0, 0.0),
        AL1(25.0,25.0),
        L2 (10.5,1.5),
        L3 (39,1.5),
        L4 (85,3.5),
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
