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
        double height;

        public double current_position;
        double round = 0.7;

        public Elevator(){
            left_motor = new SparkMax(5, MotorType.kBrushed);
            right_motor = new SparkMax(6, MotorType.kBrushed);

            left_motor.configure(setElevatorConfig(false), ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
            right_motor.configure(setElevatorConfig(true), ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
            
            this.height = 0.0;
        }

        public SparkMaxConfig setElevatorConfig(boolean isRight) {
             SparkMaxConfig config;
            config = new SparkMaxConfig();
            config
                .inverted(isRight)
                .idleMode(IdleMode.kBrake).closedLoop
                .outputRange(-0.5, 0.5);
            config.closedLoop
                .pid(0.1, 1e-4, 0.4)
                .iZone(0.1);
            return config;
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
