package fr.tdd.kata.marsrover;

import fr.tdd.kata.marsrover.adapters.FileMissionInstructionReader;
import fr.tdd.kata.marsrover.adapters.FileMissionInstructionValidator;
import fr.tdd.kata.marsrover.domain.model.MissionControl;
import fr.tdd.kata.marsrover.domain.ports.MissionInstructionReader;

public class Application {

	public static void main(String[] args) throws Exception {
		FileMissionInstructionValidator instructionValidator = new FileMissionInstructionValidator();
		instructionValidator.validateArgements(args);
		MissionInstructionReader missionInstructionReader = new FileMissionInstructionReader(instructionValidator);
		MissionControl missionControl = new MissionControl(missionInstructionReader);
		String result = missionControl.startMissions(args[0]);
		System.out.println(result);
	}
}
