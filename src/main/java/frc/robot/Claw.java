package frc.robot;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

public class Claw {
    private SparkMax angle_motor;
    private SparkMax grab_motor;
    double angle;
    private Claw claw;

    public Claw(){
        angle_motor = new SparkMax(7, MotorType.kBrushed);
        grab_motor  = new SparkMax(8, MotorType.kBrushed);
    
        angle_motor.configure(SetClawConfig(true), ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        grab_motor.configure(SetClawConfig(false), ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        this.angle = 20;
    }

    public static SparkMaxConfig SetClawConfig(boolean isAngle) {
        SparkMaxConfig Config;
        Config = new SparkMaxConfig();
        Config
        .inverted(isAngle)
        .idleMode(IdleMode.kBrake);
        Config.closedLoop.pid(0.1, 0, 0.6);
        return Config;
    }
      public void SetAngle(double angle) {
        angle_motor.getClosedLoopController().setReference(angle, ControlType.kPosition);
    }
    public void SetPower(double power) {
        grab_motor.set(power);
    }
    
    public double GetPosition() {
        return this.angle_motor.getEncoder().getPosition();
    }
    public void SetClawPower(double power) {
        claw.SetPower(power);
    }
    
}
