package frc.robot;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkLowLevel.MotorType;


public class Claw {
    private SparkMax catch_motor;
    private SparkMax angle_motor;
    public double angle=0;
    public boolean catch_power;

    public Claw(){
        catch_motor = new SparkMax(9, MotorType.kBrushed);
        angle_motor = new SparkMax(8, MotorType.kBrushed);
      
        }
        
    public void Anglepower(){
        angle_motor.getClosedLoopController().setReference(angle, ControlType.kPosition);
            if(catch_power = true){
            catch_motor.set(0);
            }
            else if(catch_power = false){
            catch_motor.set(0.5);
        }
    }
    public void SetAngle(double angleunit){
        angle = angleunit/10;
    }

    public void SetCatch(boolean catchunit){
        catch_power = catchunit;
    }



}
