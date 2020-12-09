/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.OI;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.PWMTalonSRX;

/**
 * Add your docs here.
 */
public class shooterSubsystem extends Subsystem {
  private PWMTalonSRX shooterMotor = new PWMTalonSRX(6);
  private PWMTalonSRX hoodMotor = new PWMTalonSRX(5);

  public static shooterSubsystem shooter;
  public shooterSubsystem() {
  }

  public static shooterSubsystem getInstance() {
    if (shooter == null) {
      shooter = new shooterSubsystem();
    }
    return shooter;
  }

  public void setShooterPow(double speed) {
    shooterMotor.set(speed);
  }

  public void setHoodPow(double speed) {
    hoodMotor.set(speed);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  
  @Override
  public void periodic() {
    if (OI.returnXbox().getRawButton(RobotMap.rightBumper)) {
      setHoodPow(0.2);
    } else if (OI.returnXbox().getRawButton(RobotMap.leftBumper)) {
      setHoodPow(-0.2);
    } else {
      setHoodPow(0);
    }
    setShooterPow(OI.returnXbox().getRawAxis(RobotMap.triggers));
  }
}
