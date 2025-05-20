// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

/** Add your docs here. */
public class Claw {
    private final SparkMax LeftClawMotor;
    private final SparkMax RightClawMotor;
    public Claw(){
        LeftClawMotor = new SparkMax(7, MotorType.kBrushless);
        RightClawMotor = new SparkMax(8, MotorType.kBrushless);
        LeftClawMotor.configure(Tool.SetClawConfig(true), ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        RightClawMotor.configure(Tool.SetClawConfig(false), ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }
    public void SetAngle(double angle){
        angle=angle/360*36;
        LeftClawMotor.getClosedLoopController().setReference(angle, ControlType.kPosition);
    }
    public void SetPower(double power){
        RightClawMotor.set(power);
    }
}
