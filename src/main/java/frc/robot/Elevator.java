package frc.robot;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

public class Elevator {
    private SparkMax left_motor;
    private SparkMax right_motor;
    private SparkMaxConfig config1;
    private SparkMaxConfig config2;
    public double current_position;
    public double round = 0.7;
    public double height=0.0;
    
    public Elevator(){
        left_motor = new SparkMax(5, MotorType.kBrushed);
        right_motor = new SparkMax(6, MotorType.kBrushed);
        config1 = new SparkMaxConfig();
        config2 = new SparkMaxConfig();

        left_motor.configure(config1,ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        right_motor.configure(config2,ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        
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
