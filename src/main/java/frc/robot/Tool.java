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
}
