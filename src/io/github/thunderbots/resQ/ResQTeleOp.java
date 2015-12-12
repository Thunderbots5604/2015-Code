/* Copyright (C) 2015-2016 Thunderbots Robotics
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package io.github.thunderbots.resQ;

import io.github.thunderbots.lightning.Lightning;
import io.github.thunderbots.lightning.annotation.Active;
import io.github.thunderbots.lightning.annotation.OpMode;
import io.github.thunderbots.lightning.control.JoystickListener;
import io.github.thunderbots.lightning.opmode.TeleOp;
import io.github.thunderbots.resQ.ResQRobot;

@OpMode(name="Tele Op", type="TeleOp")
@Active
public class ResQTeleOp extends TeleOp implements JoystickListener {

	@Override
	protected ResQRobot getRobot() {
		return (ResQRobot) super.getRobot();
	}

	@Override
	protected void initializeOpMode() {
		super.initializeOpMode();
		this.setRobot(new ResQRobot());
		Lightning.getJoystickMonitor(2).registerJoystickListener(this);
	}
	
	protected void mainLoop() {
		// Set the leg motors
		if (Lightning.getJoystick(1).rightBumper()) {
			this.getRobot().walkForward();
		} else if (Lightning.getJoystick(1).leftBumper()) {
			this.getRobot().walkBackward();
		} else {
			this.getRobot().stopWalking();
		}
		
		if (Math.abs(Lightning.getJoystick(2).leftStickY()) < .1)
			this.getRobot().moveScoringArm(Lightning.getJoystick(2).leftStickY());
		else 
			this.getRobot().moveScoringArm(0);
		
		if (Lightning.getJoystick(2).aButton())
			this.getRobot().moveBucket(0);
		else if (Lightning.getJoystick(2).xButton())
			this.getRobot().moveBucket(-1);
		else if (Lightning.getJoystick(2).bButton())
			this.getRobot().moveBucket(1);
		
		if (Lightning.getJoystick(2).upButton())
			this.getRobot().moveClimberArm(1);
		if (Lightning.getJoystick(2).downButton())
			this.getRobot().moveClimberArm(0);
	}
	
}
