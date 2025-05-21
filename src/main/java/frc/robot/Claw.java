package frc.robot;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;


public class Claw {
    private SparkMax catch_motor;
    private SparkMax angle_motor;
    public double angle=0;
    public boolean catch_power;

    public Claw(){
        catch_motor = new SparkMax(9, MotorType.kBrushed);
        angle_motor = new SparkMax(8, MotorType.kBrushed);
        
        catch_motor.configure(SetClawConfig(false), ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        angle_motor.configure(SetClawConfig(true), ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
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
    public static SparkMaxConfig SetClawConfig(boolean Clawinvert){
        SparkMaxConfig theConfig;
        theConfig = new SparkMaxConfig();
        theConfig.inverted(Clawinvert).idleMode(IdleMode.kBrake);
        theConfig.closedLoop.pid(0.1, 0, 0.6);
        return theConfig;
    }
    public void SetAngle(double angleunit){
        angle = angleunit/10;
    }

    public void SetCatch(boolean catchunit){
        catch_power = catchunit;
    }



}
