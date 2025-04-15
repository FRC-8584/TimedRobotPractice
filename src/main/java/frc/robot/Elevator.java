package frc.robot;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

public class Elevator {
    private final SparkMax Left;
    private final SparkMax Right;
    private double height = 0.0;
    private Claw claw;

    private final double SafeAngle = 25.0;

    Elevator() {
        Left  = new SparkMax(5, MotorType.kBrushless);
        Right = new SparkMax(6, MotorType.kBrushless);

        claw = new Claw();

        Left.configure(Tool.setConfig(true), ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        Right.configure(Tool.setConfig(false), ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }
    public void SetPoweer(double power) {
        Left.set(power);
        Right.set(power);
        return;
    }
    public void SetTheHeight(double value){
        claw.SetPosition(SafeAngle);
        height = value;
    }
    public void SetPosition() {
        Left.getClosedLoopController().setReference(this.height, ControlType.kPosition);
        Right.getClosedLoopController().setReference(this.height, ControlType.kPosition);
    }
    public void Setpoint(double angle, double power) {
        this.claw.SetPosition(angle);
        if(this.GetPosition()==this.height) {
            this.claw.SetPower(power);
        }
    }
    public double GetPosition() {
        return (Left.getEncoder().getPosition()+Right.getEncoder().getPosition())/2.0;
    }
}
