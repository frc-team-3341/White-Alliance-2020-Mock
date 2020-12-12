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
public class Intake extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private int intakeToggle = 0;

  private PWMTalonSRX intakeMotor = new PWMTalonSRX(RobotMap.intake);
  public static Intake intake;

  public static Intake getInstance() {
    if (intake == null) {
      intake = new Intake();
    }
    return intake;
  }

  public void acquireToggle(double speed) {
    //start time
    //long startTime = System.currentTimeMillis();
    
    //how long it takes to intake one ball at set speed (0.3)
    //while(System.currentTimeMillis() - startTime < 200){
    //  intakeMotor.set(speed);
    //}
    
    if(intakeToggle == 0){
      intakeMotor.set(speed);
      intakeToggle = 1;
    } else if(intakeToggle == 1){
      intakeMotor.set(0);
      intakeToggle = 0;
    }
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  @Override
  public void periodic() {
      if (OI.returnXbox().getRawButton(RobotMap.aButton)){
        acquireToggle(0.3); 
      } 
  }
}