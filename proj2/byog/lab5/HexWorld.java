package byog.lab5;

//import org.junit.Test;

import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;


import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private Random randomGenerator = new Random(System.currentTimeMillis());
    private int WIDTH;
    private int HEIGHT;
    private TETile[][] data;

    public HexWorld(int w, int h) {
        WIDTH = w;
        HEIGHT = h;
        this.data = new TETile[w][h];
        for (int i = 0; i < w; i++) {
            for (int k = 0; k < h; k++) {
                data[i][k] = Tileset.NOTHING;
            }
        }
    }

    public HexWorld() {
        int w = 20;
        int h = 20;
        WIDTH = w;
        HEIGHT = h;
        this.data = new TETile[w][h];
        for (int i = 0; i < w; i++) {
            for (int k = 0; k < h; k++) {
                data[i][k] = Tileset.NOTHING;
            }
        }
    }

    public void draw() {
        TERenderer render = new TERenderer();
        render.initialize(WIDTH, HEIGHT);
        render.renderFrame(data);
    }

    private boolean positionInBound(int x, int y) {
        return x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT;
    }


    private TETile getRandomTile() {

        int randomIndex = randomGenerator.nextInt(5);

        switch (randomIndex) {
            case 0:
                return Tileset.FLOWER;
            case 1:
                return Tileset.MOUNTAIN;
            case 2:
                return Tileset.SAND;
            case 3:
                return Tileset.TREE;
            case 4:
                return Tileset.GRASS;
            default:
                return Tileset.WATER;
        }
    }

    // The position stands for the left and upper corner TILE of the middle four TILEs.
    public void addHexagon(int x, int y, int size) {

        TETile current = getRandomTile();
        if (size % 2 == 0) {
            int halves = (size - 2) / 2;
            for (int h = y + 1; h <= y + size; h++) {
                int heightDiff = h - (y + 1);
                int delta = size - 1 - heightDiff;
                int paintX = delta + halves;
                for (int w = x - paintX; w <= x + 1 + paintX; w++) {
                    if (positionInBound(w, h)) {
                        data[w][h] = current;
                    }
                }
            }
            for (int h = y; h >= y - size + 1; h--) {
                int heightDiff = y - h;
                int delta = size - 1 - heightDiff;
                int paintX = delta + halves;
                for (int w = x - paintX; w <= x + 1 + paintX; w++) {
                    if (positionInBound(w, h)) {
                        data[w][h] = current;
                    }
                }
            }
        } else {
            int halves = (size - 2) / 2;
            for (int h = y + 1; h <= y + size; h++) {
                int heightDiff = h - (y + 1);
                int delta = size - 1 - heightDiff;
                int paintX = delta + halves;
                for (int w = x - paintX; w <= x + 1 + paintX + 1; w++) {
                    if (positionInBound(w, h)) {
                        data[w][h] = current;
                    }
                }
            }
            for (int h = y; h >= y - size + 1; h--) {
                int heightDiff = y - h;
                int delta = size - 1 - heightDiff;
                int paintX = delta + halves;
                for (int w = x - paintX; w <= x + 1 + paintX + 1; w++) {
                    if (positionInBound(w, h)) {
                        data[w][h] = current;
                    }
                }
            }
        }

    }


    private class position {
        position(int x0, int y0) {
            x = x0;
            y = y0;
        }

        public int x;
        public int y;

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            position other = (position) obj;
            return this.x == other.x && this.y == other.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }


    private Deque<position> middleFinder(int mx, int my, int items) {
        Deque<position> deque = new LinkedList<position>();
        middleFinderHelper(mx, my, items, deque);
        return deque;
    }

    private void middleFinderHelper(int mx, int my, int items, Deque<position> deque) {
        if ((!deque.contains(new position(mx, my))) && (deque.size() < items) && positionInBound(mx, my)) {
            deque.add(new position(mx, my));
            middleFinderHelper(mx, my + 6, items, deque);
            middleFinderHelper(mx, my - 6, items, deque);
            middleFinderHelper(mx - 5, my + 3, items, deque);
            middleFinderHelper(mx - 5, my - 3, items, deque);
            middleFinderHelper(mx + 5, my - 3, items, deque);
            middleFinderHelper(mx + 5, my + 3, items, deque);
        }
    }

    public void arrangeAllHexagon() {
        Deque<position> list = middleFinder(30, 30, 500);
        for (position middle : list) {
            addHexagon(middle.x, middle.y, 3);

        }
    }

    public static void main(String[] args) {
        HexWorld world = new HexWorld(60, 60);
        world.arrangeAllHexagon();
        world.draw();
    }


}
