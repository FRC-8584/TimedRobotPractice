// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;


/**
 * The methods in this class are called automatically corresponding to each mode, as described in
 * the TimedRobot documentation. If you change the name of this class or the package after creating
 * this project, you must also update the Main.java file in the project.
 */
public class Robot extends TimedRobot {
  private Joystick joystick;
  private SparkMax left_front_motor;
  private SparkMax left_back_motor;
  private SparkMax right_front_motor;
  private SparkMax right_back_motor;

  private SparkMax climber_motor;
  private SparkMax CoralIntakeMotor;
  private Elevator elevator;
  private Claw claw;

  public Robot() {
    
    joystick = new Joystick(0);

    left_front_motor  = new SparkMax(1, MotorType.kBrushed);
    right_front_motor = new SparkMax(2, MotorType.kBrushed);
    right_back_motor  = new SparkMax(3, MotorType.kBrushed);
    left_back_motor   = new SparkMax(4, MotorType.kBrushed);
    climber_motor = new SparkMax(7, MotorType.kBrushed);  
    CoralIntakeMotor = new SparkMax(10, MotorType.kBrushed);
    elevator = new Elevator();
    claw = new Claw();

  }
  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
    
  }

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    double x, y, turn ;
    double pov = joystick.getPOV();
    x = joystick.getX();
    y = -joystick.getY();
    turn = joystick.getRawAxis(4);
    move(x, y, turn);

    if(pov == 0){
      climber_motor.set(0.5);
    }
    else if(pov == 180){
      climber_motor.set(-0.5);
    }
    else{
      climber_motor.set(0);
    }

    if(joystick.getRawButton(2)){
      elevator.SetHeight(16);
      claw.SetAngle(1.5);
    
    }
    else if(joystick.getRawButton(3)){
      elevator.SetHeight(37);
      claw.SetAngle(1.5);
    }
    else if(joystick.getRawButton(4)){
      elevator.SetHeight(75);
      claw.SetAngle(3.5);
    }

    elevator.SetPosition();

    if(joystick.getRawButton(5)){
      claw.SetClawPower(0.5);
    }
    else if(joystick.getRawButton(6)){
      claw.SetClawPower(-0.5);
    }
    else{
      claw.SetClawPower(0);
    }

    if(joystick.getTriggerPressed()){}

    if(joystick.getRawButton(5)){
      CoralIntakeMotor.set(0.5);
    }
    else{
      CoralIntakeMotor.set(0);
    }
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}

  private void move(double x, double y, double turn) {
    double LF_power = Tool.bounding(x+y+turn, 1.0, -1.0);
    left_front_motor.set(LF_power);
    double RF_power = Tool.bounding(y-x-turn, 1.0, -1.0);
    right_front_motor.set(RF_power);
    double RB_power = Tool.bounding(x+y-turn, 1.0, -1.0); 
    right_back_motor.set(RB_power);
    double LB_power = Tool.bounding(y-x+turn, 1.0, -1.0);
    left_back_motor.set(LB_power);
  }
}
