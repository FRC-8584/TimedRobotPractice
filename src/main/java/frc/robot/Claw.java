package frc.robot;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

public class Claw {
    private SparkMax ShaftMotor, ClawMotor;
    public Claw() {
        ShaftMotor = new SparkMax(7, MotorType.kBrushless);
        ClawMotor  = new SparkMax(8, MotorType.kBrushless);

        ShaftMotor.configure(Tool.SetClawConfig(true), ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        ClawMotor.configure(Tool.SetClawConfig(false), ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    public void SetPosition(double angle) {
        ShaftMotor.getClosedLoopController().setReference(angle, ControlType.kPosition);
    }

    public void SetPower(double power) {
        ClawMotor.set(power);
    }

    public double GetPosition() {
        return this.ShaftMotor.getEncoder().getPosition();
    }
}
