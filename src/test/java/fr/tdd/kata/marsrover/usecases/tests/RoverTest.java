package fr.tdd.kata.marsrover.usecases.tests;

import static fr.tdd.kata.marsrover.domain.model.Direction.NORTH;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import fr.tdd.kata.marsrover.domain.model.Coordinate;
import fr.tdd.kata.marsrover.domain.model.Direction;
import fr.tdd.kata.marsrover.domain.model.Grid;
import fr.tdd.kata.marsrover.domain.model.Rover;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class RoverTest {
	
	private static final Direction INITIAL_DIRECTION =NORTH;	
	private static final int INITIAL_Y_POSITION = 0;
	private static final int INITIAL_X_POSITION = 0;
	private static final int MAX_HEIGHT = 10;
	private static final int MAX_WIDTH = 10;

	private Rover rover;
	
	@Before
	public void initialise() {
		Grid grid = new Grid(MAX_WIDTH, MAX_HEIGHT);
		Coordinate coordinate = new Coordinate(INITIAL_X_POSITION, INITIAL_Y_POSITION);
		rover = new Rover(grid, coordinate, INITIAL_DIRECTION);
	}
	
	@Test
	@Parameters({ 
		"R, 0 0 E", 
		"RR, 0 0 S", 
		"RRR, 0 0 W", 
		"RRRR, 0 0 N" })
	public void 
	should_rotate_right(String commands, String position) {
		assertThat(rover.execute(commands), is(position));
	}
		

	@Test
	@Parameters({
		"L, 0 0 W", 
		"LL, 0 0 S", 
		"LLL, 0 0 E", 
		"LLLL, 0 0 N" })
	public void 
	should_rotate_left(String commands, String position) {
		assertThat(rover.execute(commands), is(position));
	}

	@Test
	@Parameters({ 
		"M, 0 1 N", 
		"MMM, 0 3 N" })
	public void 
	should_move_up(String commands, String position) {
		assertThat(rover.execute(commands), is(position));
	}

	@Test
	@Parameters({ 
		"RM, 1 0 E", 
		"RMMMMM, 5 0 E" })
	public void 
	should_move_right(String commands, String position) {
		assertThat(rover.execute(commands), is(position));
	}

	@Test
	@Parameters({ 
		"LM, 9 0 W", 
		"LMM, 8 0 W" })
	public void 
	should_move_left(String commands, String position) {
		assertThat(rover.execute(commands), is(position));
	}

	@Test
	@Parameters({ 
		"LLM, 0 9 S", 
		"LLMM, 0 8 S" })
	public void 
	should_move_south(String commands, String position) {
		assertThat(rover.execute(commands), is(position));
	}

	@Test
	@Parameters({ 
		"MMMMMMMMMM, 0 0 N", 
		"MMMMMMMMMMMMM, 0 3 N" })
	public void 
	should_wrap_from_top_to_bottom_when_moving_north(String commands, String position) {
		assertThat(rover.execute(commands), is(position));
	}

	@Test
	@Parameters({ 
		"RMMMMMMMMMM, 0 0 E",
		"RMMMMMMMMMMMMM, 3 0 E"})
	public void 
	should_wrap_from_right_to_left_when_moving_east(String commands, String position) {
		assertThat(rover.execute(commands), is(position));
	}
	
}
