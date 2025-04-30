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
        private SparkMaxConfig config;
        double height;
        public double current_position;
        double round = 0.7;

        public Elevator(){
            left_motor = new SparkMax(5, MotorType.kBrushed);
            right_motor = new SparkMax(6, MotorType.kBrushed);
            config = new SparkMaxConfig();
            this.height = 0;
            config.inverted(true);
            right_motor.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        }
        
        void SetPosition(){
            left_motor.getClosedLoopController().setReference(height/round, ControlType.kPosition);
            right_motor.getClosedLoopController().setReference(height/round, ControlType.kPosition);
            current_position = (left_motor.getEncoder().getPosition()+right_motor.getEncoder().getPosition())/2.0;
        }
        void SetHeight(double height_cm){
            this.height = height_cm;
        }
    }

