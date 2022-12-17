package eu.uvarov.aoc22.day17.types;

public enum MoveType {
	LEFT, RIGHT, DOWN;

	public static MoveType getType(int i) {
		return values()[i];
	}
}
