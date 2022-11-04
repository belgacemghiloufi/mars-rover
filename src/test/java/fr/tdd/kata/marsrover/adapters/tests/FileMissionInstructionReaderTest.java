package fr.tdd.kata.marsrover.adapters.tests;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.nio.file.NoSuchFileException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.tdd.kata.marsrover.adapters.FileMissionInstructionReader;
import fr.tdd.kata.marsrover.domain.model.Coordinate;
import fr.tdd.kata.marsrover.domain.model.Direction;
import fr.tdd.kata.marsrover.domain.model.Grid;
import fr.tdd.kata.marsrover.domain.model.Mission;
import fr.tdd.kata.marsrover.domain.model.Rover;
import fr.tdd.kata.marsrover.domain.ports.MissionInstructionReader;


public class FileMissionInstructionReaderTest {
	
	private static final String FILE_LOCATION = "src/test/resources/rover.txt";
	private static final Grid GRID = new Grid(6,6);
	
	private MissionInstructionReader  missionInstructionReader;
	private String missionInstructions;
	
	@Before
	public void initialise() {
		missionInstructionReader = new FileMissionInstructionReader();
		missionInstructions = String.join(System.getProperty("line.separator"),
		        "5 5",
		        "1 2 N",
		        "LMLMLMLMM",
		        "3 3 E",
		        "MMRMMRMRRM");
	}
	
	@Test
	public void
	should_return_mission_instructions() throws Exception {
		assertEquals(missionInstructions, missionInstructionReader.getMissionInstructions(FILE_LOCATION));
	}
	
	@Test(expected = NoSuchFileException.class)
	public void
	should_thrown_exception_when_file_is_not_found() throws Exception {
		String fileLocation = "src/test/resources/rover";
		missionInstructionReader.getMissionInstructions(fileLocation);
	}
	
	@Test
	public void
	should_return_a_grid_of_5_width_5_height() throws Exception {
		assertThat(missionInstructionReader.getGrid(FILE_LOCATION), is(GRID));
	}
	
	@Test
	public void
	should_return_list_of_missions() throws Exception {
		Rover rover_1 = new Rover(GRID, new Coordinate(1, 2), Direction.NORTH);
		String mission_instructions_1 = "LMLMLMLMM";
		Rover rover_2 = new Rover(GRID, new Coordinate(3, 3), Direction.EAST);
		String mission_instructions_2 = "MMRMMRMRRM";
		List<Mission> missions = List.of(new Mission(rover_1, mission_instructions_1), new Mission(rover_2, mission_instructions_2));
		assertThat(missionInstructionReader.getMissions(FILE_LOCATION), is(missions));
	}

}