package fr.tdd.kata.marsrover.acceptance.tests;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import fr.tdd.kata.marsrover.adapters.FileMissionInstructionReader;
import fr.tdd.kata.marsrover.adapters.FileMissionInstructionValidator;
import fr.tdd.kata.marsrover.domain.model.MissionControl;
import fr.tdd.kata.marsrover.domain.ports.MissionInstructionReader;

public class MarsRoverAcceptanceTest {
	
	private static final String FILE_LOCATION = "src/test/resources/rover.txt";
	
	private MissionControl missionControl;
	private MissionInstructionReader missionInstructionReader;
	private FileMissionInstructionValidator instructionValidator;
	
	@Before
	public void initialise() {
		instructionValidator = new FileMissionInstructionValidator();
		missionInstructionReader = new FileMissionInstructionReader(instructionValidator);
		missionControl = new MissionControl(missionInstructionReader);
	}
	
	@Test
	public void
	should_run_mission_in_order() throws Exception {
		final String missionsResult = String.join(System.getProperty("line.separator"), "1 3 N", "5 1 E");
		assertThat(missionControl.startMissions(FILE_LOCATION), is(missionsResult));
	}
}
