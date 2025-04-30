package frc.robot;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.Joystick;

public class Elevator {
    private SparkMax left_motor;
    private SparkMax right_motor;
    private SparkMaxConfig config;
    double current_position;
    double round = 0.7;
    double height=0;
    
    public Elevator(){
        left_motor = new SparkMax(5, MotorType.kBrushed);
        right_motor = new SparkMax(6, MotorType.kBrushed);
        config = new SparkMaxConfig();
        
        left_motor.configure(config,ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        
        
        left_motor.getClosedLoopController().setReference(height/round, ControlType.kPosition);
        right_motor.getClosedLoopController().setReference(height/round, ControlType.kPosition);
        current_position = (left_motor.getEncoder().getPosition()+right_motor.getEncoder().getPosition())/2;
    }
   void SetHeight(double height_cm){
        height = height_cm;
    }
}
