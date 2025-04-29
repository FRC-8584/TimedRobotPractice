package frc.robot;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import frc.robot.Tool.Levels;

public class Elevator {
    private final SparkMax Left;
    private final SparkMax Right;
    private double height;
    private Claw claw;

    private final double SafeAngle  = 25.0;
    private final double SafeHeight = 90.0;
    
    private Tool.Levels m_level;

    Elevator() {

        Left  = new SparkMax(5, MotorType.kBrushless);
        Right = new SparkMax(6, MotorType.kBrushless);

        claw = new Claw();

        Left.configure(Tool.setConfig(true), ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        Right.configure(Tool.setConfig(false), ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        height = 0.0;

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

        Left.getClosedLoopController() .setReference(this.height / Constants.Stat.Rotation_Rate, ControlType.kPosition);
        Right.getClosedLoopController().setReference(this.height / Constants.Stat.Rotation_Rate, ControlType.kPosition);

    }

    public void Setpoint() {

        claw.SetPosition(SafeAngle);
        
        height = m_level.GetHeight();

        while(!Tool.IsInRange(height -3.0, this.GetPosition(), height +3.0)) {
            
            SetPosition();

        }

        claw.SetPosition(m_level.GetAngle());

    }

    public double GetPosition() {

        return (Left.getEncoder().getPosition()+Right.getEncoder().getPosition())/2.0;

    }
    
    public double GetClawAngle() {

        return this.claw.GetPosition();

    }

    public void SetLevel(Levels l) {
        m_level = l;
    }
    
}
