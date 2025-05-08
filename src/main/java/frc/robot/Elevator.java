// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.servohub.ServoHub.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.units.measure.Angle;
import frc.robot.Tool.Levels;

/** Add your docs here. */
public class Elevator {
    private final SparkMax LeftElevatorMotor;
    private final SparkMax RightElevatorMotor;
    private double height=0.0;
    private double SafeAngle=25;
    private Tool.Levels TheLevel;

    public Elevator(){
      LeftElevatorMotor = new SparkMax(5, MotorType.kBrushless);
      RightElevatorMotor = new SparkMax(6, MotorType.kBrushless);
      LeftElevatorMotor.configure(Tool.SetConfig(true), com.revrobotics.spark.SparkBase.ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
      RightElevatorMotor.configure(Tool.SetConfig(false), com.revrobotics.spark.SparkBase.ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
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
      height=TheLevel.GetHeight();
      claw.SetAngle(SafeAngle);
      if(Tool.IsInRange(GetTheHeight(),TheLevel.GetHeight())==false){
        SetPosition();
        claw.SetAngle(TheLevel.GetAngle());
      }
    }
    public void SetLevel(Levels level){
      this.TheLevel=level;
    }
  }

