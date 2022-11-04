package fr.tdd.kata.marsrover.domain.ports;

import java.util.List;

import fr.tdd.kata.marsrover.domain.model.Grid;
import fr.tdd.kata.marsrover.domain.model.Mission;

public interface MissionInstructionReader {

	public String getMissionInstructions(String fileLocation) throws Exception;
	
	public Grid getGrid(String fileLocation) throws Exception;

	public List<Mission> getMissions(String fileLocation) throws Exception;

}
