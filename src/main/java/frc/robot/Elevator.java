package frc.robot;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

public class Elevator {
    private SparkMax left_motor;
    private SparkMax right_motor;
    public double current_position;
    public double round = 0.7;
    public double height=0.0;
    
    public Elevator(){
        left_motor = new SparkMax(5, MotorType.kBrushed);
        right_motor = new SparkMax(6, MotorType.kBrushed);
        

        left_motor.configure(setElevatorConfig(false),ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        right_motor.configure(setElevatorConfig(true),ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        
    }    
    public static SparkMaxConfig setElevatorConfig(boolean setElevator){
        SparkMaxConfig thisConfig;
        thisConfig = new SparkMaxConfig();
        thisConfig.inverted(setElevator).idleMode(IdleMode.kBrake);
        thisConfig.closedLoop.pid(.1, 1e-4, 0.4).iZone(0.1);
        return thisConfig;

    }
    public void ElevatorPower(){
         left_motor.getClosedLoopController().setReference(height/round, ControlType.kPosition);
        right_motor.getClosedLoopController().setReference(height/round, ControlType.kPosition);
        current_position = (left_motor.getEncoder().getPosition()+right_motor.getEncoder().getPosition())/2;
    }
    public void SetHeight(double height_cm){
        height = height_cm;
    }
}
