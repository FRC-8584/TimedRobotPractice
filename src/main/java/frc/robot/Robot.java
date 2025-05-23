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
  private SparkMax CoralIntakeMotor;

  public Robot() {
    joystick = new Joystick(0);
    left_front_motor  = new SparkMax(1, MotorType.kBrushed);
    right_front_motor = new SparkMax(2, MotorType.kBrushed);
    right_back_motor  = new SparkMax(3, MotorType.kBrushed);
    left_back_motor   = new SparkMax(4, MotorType.kBrushed);

    CoralIntakeMotor = new SparkMax(10, MotorType.kBrushed);
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {}

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    double x, y, turn ;
    x = joystick.getX();
    y = -joystick.getY();
    turn = joystick.getRawAxis(4);
    move(x, y, turn);

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
