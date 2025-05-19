// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.servohub.ServoHub.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

/** Add your docs here. */
public class Elevator {
    private final SparkMax LeftElevatorMotor;
    private final SparkMax RightElevatorMotor;
    private final Claw claw;
    private double height;
    private double SafeAngle;
    private Tool.Levels TheLevel;

    public Elevator(){
      LeftElevatorMotor = new SparkMax(5, MotorType.kBrushless);
      RightElevatorMotor = new SparkMax(6, MotorType.kBrushless);
      claw = new Claw();
      LeftElevatorMotor.configure(Tool.SetElevatorConfig(false), com.revrobotics.spark.SparkBase.ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
      RightElevatorMotor.configure(Tool.SetElevatorConfig(true), com.revrobotics.spark.SparkBase.ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
      height=0.0;
      SafeAngle=25;
      TheLevel=Tool.Levels.L1;

    }



     public void SetPosition(){
      height=height/11.43*15;
      LeftElevatorMotor.getClosedLoopController().setReference(height, ControlType.kPosition);
      RightElevatorMotor.getClosedLoopController().setReference(height, ControlType.kPosition);
    }
    public double GetTheHeight(){
      return (LeftElevatorMotor.getEncoder().getPosition()+RightElevatorMotor.getEncoder().getPosition())/2.0;
    }
    public void SetPoint(Claw claw){
      claw.SetAngle(SafeAngle);
      if(Tool.IsInRange(GetTheHeight(),TheLevel.GetHeight())==false){
        height=TheLevel.GetHeight();
        SetPosition();
        }
      claw.SetAngle(TheLevel.GetAngle());
    }   
      public void SetLevel(Tool.Levels level){
        this.TheLevel=level;
      }
  }
    
    
