package org.usfirst.frc4678.Cybercavs2016Code.commands;

import edu.wpi.first.wpilibj.command.Command;

public class AbstractCommand extends Command{
	
	public AbstractCommand(){
		System.out.println(this.getClass().getName());
	}
	
	@Override
	protected void end() {
		
	}

	@Override
	protected void execute() {
		
	}

	@Override
	protected void initialize() {
		
	}

	@Override
	protected void interrupted() {
		
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
