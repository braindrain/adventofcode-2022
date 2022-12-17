package eu.uvarov.aoc22.day17.types;

public enum RockType {
	HORIZONTAL, CROSS, LSHAPE, VERTICAL, SQUARE;

	public static RockType getType(int i) {
		return values()[i];
	}
}
