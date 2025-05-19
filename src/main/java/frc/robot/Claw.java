package frc.robot;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

public class Claw {
    private SparkMax angle_motor;
    private SparkMax grab_motor;
    private SparkMaxConfig config;

    public Claw(){
        angle_motor = new SparkMax(7, MotorType.kBrushed);
        grab_motor  = new SparkMax(8, MotorType.kBrushed);
        config = new SparkMaxConfig();
    }
}
