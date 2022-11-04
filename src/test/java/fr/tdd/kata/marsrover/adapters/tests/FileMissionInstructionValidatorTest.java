package fr.tdd.kata.marsrover.adapters.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import fr.tdd.kata.marsrover.adapters.FileMissionInstructionValidator;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class FileMissionInstructionValidatorTest {
	
	private static final String FILE_LOCATION = "src/test/resources/rover.txt";
	private FileMissionInstructionValidator missionInstructionValidator;

	@Before
	public void initialise() {
		missionInstructionValidator = new FileMissionInstructionValidator();
	}
	
	@Test
	public void 
	should_validate_a_valid_commands() {
		String commands = "MMRMMRMRRM";
		missionInstructionValidator.validateCommands(commands);
	}
	
	@Test
	@Parameters({ "0", "1", "2"})
	public void 
	should_validate_a_positive_natural_number_as_coordinate(String coordinate) {
		missionInstructionValidator.validateCoordinate(coordinate);
	}
	
	@Test
	@Parameters({ "0", "1", "2"})
	public void 
	should_validate_coordinate_lower_than_max_coordinate(String coordinate) {
		String maxCoordinate = "10";
		missionInstructionValidator.validateCoordinate(coordinate, maxCoordinate);
	}
	
	@Test
	@Parameters({ "N", "S", "E", "W"})
	public void 
	should_validate_valid_direction(String direction) {
		missionInstructionValidator.validateDirection(direction);
	}
	
	@Test
	@Parameters({ 
		"1 2 N, 10, 10", 
		"3 3 E, 10, 10"})
	public void 
	should_validate_valid_position(String direction, String maxWidthCoordinate, String maxHeightCoordinate) {
		missionInstructionValidator.validatePosition(direction, maxWidthCoordinate, maxHeightCoordinate);
	}
	
	@Test
	@Parameters({ 
		"3 3",
		"5 2",
		"5 5"
	})
	public void 
	should_validate_valid_grid_size_line(String gridSizeLine) {
		missionInstructionValidator.validateGridSize(gridSizeLine);
	}
	
	@Test
	@Parameters
	public void 
	should_validate_valid_mission_instructions(String instructions) throws Exception {
		missionInstructionValidator.validateMissionInstructions(instructions, FILE_LOCATION);
	}
	
	@Test
	public void 
	should_validate_passed_argements()  {
		String[] args = new String[] {FILE_LOCATION};
		missionInstructionValidator.validateArgements(args);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void 
	should_thrown_exception_when_command_is_unknown() {
		String commands = "FMMRMMRMRRM";
		missionInstructionValidator.validateCommands(commands);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void 
	should_thrown_exception_when_coordinate_is_not_a_number() {
		String coordinate = "M";
		missionInstructionValidator.validateCoordinate(coordinate);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void 
	should_thrown_exception_when_coordinate_is_a_negative_number() {
		String coordinate = "-1";
		missionInstructionValidator.validateCoordinate(coordinate);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void
	should_thrown_exception_when_coordinate_exceed_max_coordinate() {
		String coordinate = "11";
		String maxCoordinate = "10";
		missionInstructionValidator.validateCoordinate(coordinate, maxCoordinate);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void 
	should_thrown_exception_when_direction_is_unknown() {
		String direction = "R";
		missionInstructionValidator.validateDirection(direction);
	}
	
	@Test(expected = IllegalArgumentException.class)
	@Parameters({ 
		"1 2 M, 10, 10", 
		"1 2, 10, 10", 
		"1 M, 10, 10",
		"10 0 E, 5, 10",
		"0 10 E, 10, 5"})
	public void 
	should_thrown_exception_when_rover_position_is_invalid(String position, String maxWidthCoordinate, String maxHeightCoordinate) {
		missionInstructionValidator.validatePosition(position, maxWidthCoordinate, maxHeightCoordinate);
	}
	
	@Test(expected = IllegalArgumentException.class)
	@Parameters({ 
		"M M",
		"1 A",
		"0 0",
		"-1 3"
	})
	public void 
	should_thrown_exception_when_grid_size_line_is_invalid(String gridSizeLine) {
		missionInstructionValidator.validateGridSize(gridSizeLine);
	}
	
	@Test(expected = IllegalArgumentException.class)
	@Parameters
	public void 
	should_thrown_exception_when_mission_instructions_are_not_valide(String instructions) throws Exception {
		missionInstructionValidator.validateMissionInstructions(instructions, FILE_LOCATION);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void 
	should_thrown_exception_when_no_argements_is_passed()  {
		String[] args = new String[0];
		missionInstructionValidator.validateArgements(args);
	}
	
	@SuppressWarnings("unused")
	private String[] parametersForShould_thrown_exception_when_mission_instructions_are_not_valide() {
		String newLine = System.getProperty("line.separator");
		return new String [] {
				String.join(newLine, "5 5", "1 2 N", "LMLMLMLMM", "MMRMMRMRRM"),
				String.join(newLine, "5 5", "1 2", "LMLMLMLMM", "3 3 E", "MMRMMRMRRM"),
				String.join(newLine, "5 5", "1 2  ", "LMLMLMLMM", "3 3 E", "MMRMMRMRRM"),
				String.join(newLine, "5 5", "1 8 N", "LMLMLMLMM", "3 3 E", "MMRMMRMRRM"),
				"",
				null
		};
	}
	
	@SuppressWarnings("unused")
	private String[] parametersForShould_validate_valid_mission_instructions() {
		String newLine = System.getProperty("line.separator");
		return new String [] {
				String.join(System.getProperty("line.separator"), "5 5", "1 2 N", "LMLMLMLMM", "3 3 E", "MMRMMRMRRM"),
				String.join(System.getProperty("line.separator"), "5 5", "1 0 N", "LMLMLMLMM", "3 3 E", "MMRMMRMRRM"),
		};
	}

}