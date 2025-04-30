// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.servohub.ServoHub.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

/** Add your docs here. */
public class Elevator {
    private final SparkMax LeftElevatorMotor;
    private final SparkMax RightElevatorMotor;
    private final Claw claw;
    private double height=0.0;
    private double SafeAngle;

    public Elevator(){
      LeftElevatorMotor = new SparkMax(5, MotorType.kBrushless);
      RightElevatorMotor = new SparkMax(6, MotorType.kBrushless);
      claw = new Claw();
      LeftElevatorMotor.configure(Tool.SetConfig(true), com.revrobotics.spark.SparkBase.ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
      RightElevatorMotor.configure(Tool.SetConfig(false), com.revrobotics.spark.SparkBase.ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }
    public void SetTheHeight(double height){
         claw.SetAngle(SafeAngle);
         this.height=height;
    }
    public void SetPosition(){
      
    }
}
