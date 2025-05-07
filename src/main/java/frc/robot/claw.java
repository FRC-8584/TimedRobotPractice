package frc.robot;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

public class Claw {
    private SparkMax grab_motor;
    private SparkMax angle_motor;
    
    public Claw(){
        grab_motor = new SparkMax(8, MotorType.kBrushed);
        angle_motor = new SparkMax(9, MotorType.kBrushed);
    }
    

}
