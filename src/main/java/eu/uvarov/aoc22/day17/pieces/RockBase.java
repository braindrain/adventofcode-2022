package eu.uvarov.aoc22.day17.pieces;


import eu.uvarov.aoc22.day17.Board;
import eu.uvarov.aoc22.day17.types.MoveType;
import eu.uvarov.aoc22.day17.types.RockType;

import java.util.ArrayList;
import java.util.List;

public abstract class RockBase implements Rock {
    protected RockType type;
    protected Board board;
    protected List<Point> points = new ArrayList<>();

    public RockBase(RockType type, Board board) {
        this.type = type;
        this.board = board;
    }

    public static Rock getInstance(RockType ptype, int xPos, int yPos, Board board2) {
        switch (ptype) {
            case HORIZONTAL:
                return new HorizontalRock(ptype, xPos, yPos, board2);
            case VERTICAL:
                return new VerticalRock(ptype, xPos, yPos, board2);
            case LSHAPE:
                return new LShapeRock(ptype, xPos, yPos, board2);
            case CROSS:
                return new CrossRock(ptype, xPos, yPos, board2);
            case SQUARE:
                return new SquareRock(ptype, xPos, yPos, board2);
        }
        return null;
    }

    @Override
    public void draw() {
        for (int i = 0; i < points.size(); i++) {
            this.board.set(points.get(i).x, points.get(i).y);
        }
    }

    @Override
    public void clear() {
        for (int i = 0; i < points.size(); i++) {
            this.board.clear(points.get(i).x, points.get(i).y);
        }
    }

    @Override
    public void performMove(MoveType mtype) {
        switch (mtype) {
            case LEFT:
                moveLeft(points);
                break;
            case RIGHT:
                moveRight(points);
                break;
            case DOWN:
                moveDown(points);
                break;

        }
    }

    private void moveLeft(List<Point> pts) {
        for (int i = 0; i < pts.size(); i++) {
            pts.get(i).x--;
        }
    }

    private void moveRight(List<Point> pts) {
        for (int i = 0; i < pts.size(); i++) {
            pts.get(i).x++;
        }
    }

    private void moveDown(List<Point> pts) {
        for (int i = 0; i < points.size(); i++) {
            pts.get(i).y++;
        }
    }

    @Override
    public boolean canMove(MoveType mtype) {
        List<Point> newpts = new ArrayList<>(points.size());
        for (int i = 0; i < points.size(); i++) {
            newpts.add(new Point(points.get(i).x, points.get(i).y));
        }
        switch (mtype) {
            case LEFT:
                moveLeft(newpts);
                break;
            case RIGHT:
                moveRight(newpts);
                break;
            case DOWN:
                moveDown(newpts);
                break;
        }
        for (int i = 0; i < newpts.size(); i++) {
            if (!this.board.isClear(newpts.get(i).x, newpts.get(i).y, this)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean contains(int x, int y) {
        for (int i = 0; i < points.size(); i++) {
            if (points.get(i).x == x && points.get(i).y == y) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getMaxY() {
        int max = 0;
        for (int i = 0; i < points.size(); i++) {
            if (points.get(i).y > max) {
                max = points.get(i).y;
            }
        }
        return max;
    }
}
