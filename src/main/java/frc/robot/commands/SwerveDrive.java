// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.SwerveConstants;
import frc.robot.subsystems.Swerve;

public class SwerveDrive extends CommandBase {
  private DoubleSupplier xSpeed;
  private DoubleSupplier ySpeed;
  private DoubleSupplier angleSpeed;

  private double maxTranslationalSpeed;
  private double maxAngularSpeed;

  private Swerve swerve;

  /** Creates a new SwerveDrive. */
  public SwerveDrive(DoubleSupplier xSpeedSupplier, DoubleSupplier ySpeedSupplier, DoubleSupplier angleSpeedSupplier,
      Swerve swerve, double maxTranslationalSpeed, double maxAngularSpeed) {
    this.xSpeed = xSpeedSupplier;
    this.ySpeed = ySpeedSupplier;
    this.angleSpeed = angleSpeedSupplier;

    this.maxTranslationalSpeed = maxTranslationalSpeed;
    this.maxAngularSpeed = maxAngularSpeed;

    this.swerve = swerve;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(swerve);
  }

  public SwerveDrive(DoubleSupplier xSpeedSupplier, DoubleSupplier ySpeedSupplier, DoubleSupplier angleSpeedSupplier,
      Swerve swerve) {
    this(xSpeedSupplier, ySpeedSupplier, angleSpeedSupplier, swerve, SwerveConstants.maxTranslationalSpeed,
        SwerveConstants.maxAngularSpeed);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double xMetersPerSecond = xSpeed.getAsDouble() * maxTranslationalSpeed;
    double yMetersPerSecond = -ySpeed.getAsDouble() * maxTranslationalSpeed;
    double angularRadiansPerSecond = angleSpeed.getAsDouble() * maxAngularSpeed;

    swerve.driveFieldOriented(xMetersPerSecond, yMetersPerSecond, angularRadiansPerSecond);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
