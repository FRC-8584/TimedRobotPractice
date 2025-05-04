package frc.robot;

import com.fasterxml.jackson.databind.type.ResolvedRecursiveType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Tool.Levels;

public class Elevator {
    private final SparkMax Left;
    private final SparkMax Right;
    private double height;
    private Claw claw;

    private final double SafeAngle  = 2.0;
    
    private Tool.Levels m_level;

    Elevator() {
        Left  = new SparkMax(Constants.ID.Elevator_Left_ID , MotorType.kBrushless);
        Right = new SparkMax(Constants.ID.Elevator_Right_ID, MotorType.kBrushless);

        claw = new Claw();

        Left.configure(Tool.setElevatorConfig(false), ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        Right.configure(Tool.setElevatorConfig(true), ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        height = 0.0;
        m_level = Levels.L1;
    }

    public double GetPosition() {
        return (Left.getEncoder().getPosition()+Right.getEncoder().getPosition())/2.0;
    }

    public void SetPosition(double value) {
        Right.getClosedLoopController().setReference(value, ControlType.kPosition);
        Left.getClosedLoopController().setReference(value, ControlType.kPosition);
        return;
    }

    public void SetPoint() {

        SmartDashboard.putNumber("The_Height", GetPosition());
        SmartDashboard.putNumber("Where", m_level.GetHeight());
        if(Tool.IsInRange(GetPosition(), m_level.GetHeight()+3.0, m_level.GetHeight()-3.0)) {
            claw.SetPosition(m_level.GetAngle());
        }
        else {
            claw.SetPosition(SafeAngle);
        }
        SetPosition(m_level.GetHeight());
    }

    public void SetLevel(Levels l) {
        claw.SetPosition(SafeAngle);
        m_level = l;
    }

    public void SetClawPower(double power) {
        claw.SetPower(power);
    }

    public void SetClawPos(double p) {
        claw.SetPosition(p);
    }

    public void ClawForTime(double s){}
}
