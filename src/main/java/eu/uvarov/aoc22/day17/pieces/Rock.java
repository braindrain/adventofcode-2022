package eu.uvarov.aoc22.day17.pieces;

import eu.uvarov.aoc22.day17.types.MoveType;

import java.util.List;

public interface Rock {

	public abstract void draw();

	public abstract void clear();

	public abstract void performMove(MoveType mtype);

	public abstract boolean canMove(MoveType mtype);

	public abstract boolean contains(int i, int j);

	public abstract int getMaxY();

}