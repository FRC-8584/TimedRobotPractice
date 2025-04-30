package frc.robot;

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
 }

