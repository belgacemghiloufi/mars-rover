package fr.tdd.kata.marsrover.usecases.tests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import fr.tdd.kata.marsrover.domain.model.Coordinate;
import fr.tdd.kata.marsrover.domain.model.Direction;
import fr.tdd.kata.marsrover.domain.model.Grid;
import fr.tdd.kata.marsrover.domain.model.Mission;
import fr.tdd.kata.marsrover.domain.model.MissionControl;
import fr.tdd.kata.marsrover.domain.model.Rover;
import fr.tdd.kata.marsrover.domain.ports.MissionInstructionReader;

@RunWith(MockitoJUnitRunner.class)
public class MissionControlTest {
	
	private static final String FILE_LOCATION = "src/test/resources/rover.txt";
	private static final Grid GRID = new Grid(6,6);
	private MissionControl missionControl;
	@Mock private MissionInstructionReader  missionInstructionReader;
	
	@Before
	public void initialise() {
		missionControl = new MissionControl(missionInstructionReader);
	}
	
	@Test
	public void
	should_run_mission_in_order() throws Exception {
		String missionsResult = String.join(System.getProperty("line.separator"), "1 3 N", "5 1 E");
		Rover rover_1 = new Rover(GRID, new Coordinate(1, 2), Direction.NORTH);
		String mission_instructions_1 = "LMLMLMLMM";
		Rover rover_2 = new Rover(GRID, new Coordinate(3, 3), Direction.EAST);
		String mission_instructions_2 = "MMRMMRMRRM";
		List<Mission> missions = List.of(new Mission(rover_1, mission_instructions_1), new Mission(rover_2, mission_instructions_2));
		when(missionInstructionReader.getMissions(FILE_LOCATION)).thenReturn(missions);
		assertEquals(missionsResult, missionControl.startMissions(FILE_LOCATION));
	}
}
