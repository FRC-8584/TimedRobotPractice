package frc.robot;

import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.hal.simulation.SpiReadAutoReceiveBufferCallback;
import edu.wpi.first.wpilibj.motorcontrol.Spark;

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

    static SparkMaxConfig SetConfig(boolean invert){
        SparkMaxConfig TheConfig;
        TheConfig = new SparkMaxConfig();
        TheConfig.inverted(invert);
        return TheConfig;
    }
    
    public static enum Levels{
        L1(00,00),
        L2(16,15),
        L3(37,15),
        L4(75,55);
        private double Height;
        private double Angle;
        private Levels(double Height,double Angle){
        this.Height=Height;
        this.Angle=Angle;
        }
    public double GetHeight(){
        return Height;
    }
    public double GetAngle(){
        return Angle;
    }
  }
    
    public static boolean IsInRange(double value,double theheight){
      double max,min;
      max=value+3;
      min=value+(-3);
      if(max>=theheight && theheight>=min){
        return true;
      }
      else{
        return false;
      }
    }
    

}
