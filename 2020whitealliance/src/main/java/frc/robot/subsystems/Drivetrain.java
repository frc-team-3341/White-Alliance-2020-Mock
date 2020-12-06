/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import frc.robot.RobotMap;
import frc.robot.OI;

/**
 * Add your docs here.
 */
public class Drivetrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private PWMTalonSRX left = new PWMTalonSRX(RobotMap.leftDrive), right = new PWMTalonSRX(RobotMap.rightDrive);
  public static Drivetrain drive;

  public Drivetrain() {
    right.setInverted(true);
  }

  public static Drivetrain getInstance() {
    if (drive == null) {
      drive = new Drivetrain();
    }
    return drive;
  }

  public void tankDrive(double leftPow, double rightPow) {
    //minimizing error from small inputs
    if (leftPow < 0.05 && leftPow > -0.05) {
      leftPow = 0;
    } 
    if (rightPow < 0.05 && rightPow > -0.05) {
      rightPow = 0;
    }

    left.set(leftPow);
    right.set(rightPow);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  @Override
  public void periodic() {
    tankDrive(OI.returnXbox().getRawAxis(RobotMap.leftJoystick) * -0.3, OI.returnXbox().getRawAxis(RobotMap.rightJoystick) * -0.3);
  }
}