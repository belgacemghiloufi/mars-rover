package fr.tdd.kata.marsrover.domain.model;

import java.util.List;

import fr.tdd.kata.marsrover.domain.ports.MissionInstructionReader;

public class MissionControl {

	private MissionInstructionReader missionInstructionReader;

	public MissionControl(MissionInstructionReader missionInstructionReader) {
		this.missionInstructionReader = missionInstructionReader;
	}

	public String startMissions(String fileLocation) throws Exception {
		final StringBuilder result = new StringBuilder();
		String newLine = System.getProperty("line.separator");
		List<Mission> missions = missionInstructionReader.getMissions(fileLocation);
		for (int index = 0; index < missions.size(); index++) {
			if (index != 0) {
				result.append(newLine);
			}
			String missionResult = missions.get(index).start();
			result.append(missionResult);
		}
		return result.toString();
	}

}
