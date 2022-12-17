package eu.uvarov.aoc22.day17;

import eu.uvarov.aoc22.day17.pieces.Rock;
import eu.uvarov.aoc22.day17.pieces.RockBase;
import eu.uvarov.aoc22.day17.types.MoveType;
import eu.uvarov.aoc22.day17.types.RockType;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;


public class Board {

    private static  int gridSizeX = 7;
    private static  int gridSizeY = 0;
    private static Rock activeRock;

    int highestRock = 0;

    //private boolean grid[][];
    private List<List<AtomicBoolean>> grid = new ArrayList<>();

    int countPiece = 0;

    public Board() {
        initGrid(gridSizeX, gridSizeY);
    }

    public void initGrid(int x, int y) {
        grid.clear();
        for (int i = 0; i < x; i++) {
            List<AtomicBoolean> row = new ArrayList<>();
            for (int j = 0; j < y; j++) {
                row.add(new AtomicBoolean(false));
            }
            grid.add(row);
        }
    }

    public void growGrid(int increaseY) {
        List<List<AtomicBoolean>> oldGrid = cloneGrid();
        gridSizeY = gridSizeY + increaseY;
        initGrid(gridSizeX, gridSizeY);

        for (int i = 0; i < oldGrid.size(); i++) {
            for (int j = 0; j < oldGrid.get(0).size(); j++) {
                if (oldGrid.get(i).get(j).get()) {
                    grid.get(i).get(j+increaseY).set(true);
                }
            }
        }
    }

    public List<List<AtomicBoolean>> cloneGrid() {
        List<List<AtomicBoolean>> oldGrid = new ArrayList<>();
        for (List<AtomicBoolean> atomicBooleans : grid) {
            List<AtomicBoolean> oldRow = new ArrayList<>();
            oldRow.addAll(atomicBooleans);
            oldGrid.add(oldRow);
        }
        return oldGrid;
    }

    private void addNewPiece() {
        int xPos = 2;
        activeRock = RockBase
                .getInstance(RockType.getType(countPiece++ % 5), xPos, 0, this);
        int maxY = activeRock.getMaxY() +1;
        int highestRock = getHighestRock();
        int resize = highestRock + maxY + 3;

        growGrid(resize - gridSizeY);

        activeRock.draw();
    }

    public void movePiece(MoveType type) {
        if (activeRock.canMove(type)) {
            activeRock.clear();
            activeRock.performMove(type);
            activeRock.draw();
            update();
        }
    }

    public void update() {
      /*  clearConsole();
        for (int i = 0; i < gridSizeY; i++) {
            System.out.print("|");
            for (int j = 0; j < gridSizeX; j++) {
                if (grid.get(j).get(i).get()) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println("|");
        }
        for (int i = 0; i < gridSizeX + 2; i++) {
            System.out.print("_");
        }
        System.out.println();*/
    }

    public final static void clearConsole() {
        for (int i = 0; i < 1; i++) {
            System.out.println();
        }
    }

    public int play(String input) {
        char[] chars = input.toCharArray();
        int i = chars.length;
        for (int j = 0; j < 2022; j++) {
            addNewPiece();
            update();
            while (true) {
                switch (chars[i++ % chars.length]) {
                    case '<':
                        movePiece(MoveType.LEFT);
                        break;
                    case '>':
                        movePiece(MoveType.RIGHT);
                        break;
                }
                if (activeRock.canMove(MoveType.DOWN)) {
                    activeRock.clear();
                    activeRock.performMove(MoveType.DOWN);
                    activeRock.draw();
                    update();
                } else {
                    break;
                }
            }
        }
        return getHighestRock();
    }

    public int getHighestRock() {
        for (int j = 0; j < gridSizeY; j++) {
            for (int i = 0; i < gridSizeX; i++) {
                if (grid.get(i).get(j).get()) {
                    return gridSizeY - j;
                }
            }
        }
        return 0;
    }

    public void set(int x, int y) {
        if (x >= 0 && x < gridSizeX && y >= 0 && y < gridSizeY) {
            this.grid.get(x).get(y).set(true);
        }
    }

    public void clear(int x, int y) {
        if (x >= 0 && x < gridSizeX && y >= 0 && y < gridSizeY) {
            this.grid.get(x).get(y).set(false);
        }
    }

    public boolean isClear(int i, int j, Rock p) {
        if (i < 0 || i > gridSizeX - 1 || j < 0 || j > gridSizeY - 1) {
            return false;
        }
        return !grid.get(i).get(j).get() || p.contains(i, j);
    }
}
