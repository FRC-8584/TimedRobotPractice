package frc.robot;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

public class Elevator {
    private final SparkMax Left;
    private final SparkMax Right;

    Elevator() {
        Left  = new SparkMax(5, MotorType.kBrushless);
        Right = new SparkMax(6, MotorType.kBrushless);

        Left.configure(Tool.setConfig(true), ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        Right.configure(Tool.setConfig(false), ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }
    public void SetPoweer(double power) {
        Left.set(power);
        Right.set(power);
        return;
    }
}
