package com.company;

import java.util.LinkedList;
import java.util.Random;

/**
 * @author NickHellooo
 */
public class MazeData {

    private int n, m;
    public char[][] maze;
    public static final char MAZE_ROAD = '1';
    public static final char MAZE_WALL = '0';

    private int entranceX, entranceY;
    private int exitX, exitY;
    private static final int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public boolean[][] visited;
    public boolean[][] path;

    public MazeData(int n, int m) {
        if (n % 2 == 0 || m % 2 == 0) {
            return;
        }

        this.n = n;
        this.m = m;
        maze = new char[n][m];
        visited = new boolean[n][m];
        path = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i % 2 == 1 && j % 2 == 1) {
                    maze[i][j] = MAZE_ROAD;
                } else {
                    maze[i][j] = MAZE_WALL;
                }
                visited[i][j] = false;
                path[i][j] = false;
            }
        }
        setEntranceX(1);
        setEntranceY(0);
        randomExit();

        maze[entranceX][entranceY] = MAZE_ROAD;
        maze[exitX][exitY] = MAZE_ROAD;
    }

    public boolean inArea(int x, int y) {
        return (x >= 0 && x < n) && (y >= 0 && y < m);
    }

    public char getMaze(int i, int j) {
        if (!inArea(i, j)) {
            return ' ';
        }
        return maze[i][j];
    }

    public static class Position {

        private int x, y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    public static class RandomQueue<T> {

        private LinkedList<T> queue;

        public RandomQueue() {
            queue = new LinkedList<T>();
        }

        public void add(T t) {
            if (Math.random() > 0.5) {
                queue.addFirst(t);
            } else {
                queue.addLast(t);
            }
        }

        public T remove() {
            if (queue.size() == 0) {
                return null;
            }
            if (Math.random() > 0.5) {
                return queue.removeFirst();
            } else {
                return queue.removeLast();
            }
        }

        public int size() {
            return queue.size();
        }

        public boolean empty() {
            return size() == 0;
        }
    }

    private void randomExit() {
        int randomInt = new Random().nextInt(n);
        if (randomInt < n / 2) {
            randomInt += n / 2;
            if (randomInt % 2 != 1) {
                randomInt++;
            }
            setExitY(randomInt);
            setExitX(m - 1);
        } else {
            randomInt += n / 5;
            if (randomInt % 2 != 1) {
                randomInt++;
            }
            setExitY(n - 1);
            setExitX(randomInt - 2);
        }
    }

    public void clear() {
        for (int i = 0; i < this.visited.length; i++) {
            for (int j = 0; j < this.visited[0].length; j++) {
                this.visited[i][j] = false;
            }
        }
    }

    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }

    public int getEntranceX() {
        return entranceX;
    }

    public void setEntranceX(int entranceX) {
        this.entranceX = entranceX;
    }

    public int getEntranceY() {
        return entranceY;
    }

    public void setEntranceY(int entranceY) {
        this.entranceY = entranceY;
    }

    public int getExitX() {
        return exitX;
    }

    public void setExitX(int exitX) {
        this.exitX = exitX;
    }

    public int getExitY() {
        return exitY;
    }

    public void setExitY(int exitY) {
        this.exitY = exitY;
    }

    public static int[][] getD() {
        return d;
    }
}
