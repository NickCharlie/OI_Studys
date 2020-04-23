package com.company;

import java.awt.*;

/**
 * @author NickHellooo
 */
public class algoVisualizer {

    public static final String MAIN_WINDOW_TITLE = "Nick";
    private static final int DELAY = 2;
    private static final int MAX_PATHS_COUNT = 4;
    private static final int DEFAULT_INVALID_INT_DATA = -1;

    private MazeData data;
    private static algoFrame frame;
    private static final int BLOCK_SIDE = 8;
    public static final int[] MAZE_SIZE = {111, 111};

    public static algoVisualizer visualizer;

    public algoVisualizer(int n, int m) {
        // 初始化数据
        data = new MazeData(n, m);
        int screenHeight = data.getN() * BLOCK_SIDE;
        int screenWidth = data.getM() * BLOCK_SIDE;
        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new algoFrame(MAIN_WINDOW_TITLE, screenWidth, screenHeight);
            new Thread(this::run).start();
        });
    }

    private void run() {
        produceRun();
        data.clear();
        workRun();
    }

    private void workRun() {
        setData(DEFAULT_INVALID_INT_DATA, DEFAULT_INVALID_INT_DATA, false);
        dfs(data.getEntranceX(), data.getEntranceY());
        setData(DEFAULT_INVALID_INT_DATA, DEFAULT_INVALID_INT_DATA, false);
    }

    private void produceRun() {
        setData(DEFAULT_INVALID_INT_DATA, DEFAULT_INVALID_INT_DATA);
        produceBfs(data.getEntranceX(), data.getEntranceY() + 1);
        setData(DEFAULT_INVALID_INT_DATA, DEFAULT_INVALID_INT_DATA);
    }

    private void produceBfs(int x, int y) {
        setData(DEFAULT_INVALID_INT_DATA, DEFAULT_INVALID_INT_DATA);

        MazeData.RandomQueue<MazeData.Position> randomQueue = new MazeData.RandomQueue<>();
        MazeData.Position origin = new MazeData.Position(data.getEntranceX(), data.getEntranceY() + 1);
        randomQueue.add(origin);
        data.visited[origin.getX()][origin.getY()] = true;

        while (randomQueue.size() != 0) {
            MazeData.Position curPos = randomQueue.remove();

            for (int i = 0; i < MAX_PATHS_COUNT; i++) {
                int newX = curPos.getX() + MazeData.getD()[i][0] * 2;
                int newY = curPos.getY() + MazeData.getD()[i][1] * 2;

                if (data.inArea(newX, newY) && !data.visited[newX][newY] && data.maze[newX][newY] == MazeData.MAZE_ROAD) {
                    randomQueue.add(new MazeData.Position(newX, newY));
                    data.visited[newX][newY] = true;
                    setData(curPos.getX() + MazeData.getD()[i][0], curPos.getY() + MazeData.getD()[i][1]);
                }
            }
        }
        setData(DEFAULT_INVALID_INT_DATA, DEFAULT_INVALID_INT_DATA);
    }

    private boolean dfs(int x, int y) {
        if (!data.inArea(x, y)) {
            return false;
        }
        data.visited[x][y] = true;
        setData(x, y, true);
        if (x == data.getExitX() && y == data.getExitY()) {
            return true;
        }
        for (int i = 0; i < MAX_PATHS_COUNT; i++) {
            int newX = x + MazeData.getD()[i][0];
            int newY = y + MazeData.getD()[i][1];
            if (data.inArea(newX, newY) && data.getMaze(newX, newY) == MazeData.MAZE_ROAD
                    && !data.visited[newX][newY]) {
                if (dfs(newX, newY)) {
                    return true;
                }
            }
        }
        setData(x, y, false);
        return false;
    }

    private void setData(int x, int y, boolean isPath) {
        if (!data.inArea(x, y)) {
            return;
        }
        data.path[x][y] = isPath;
        frame.render(data);
        algoVisHelper.pause(DELAY);
    }

    private void setData(int x, int y) {
        if (data.inArea(x, y)) {
            data.maze[x][y] = MazeData.MAZE_ROAD;
        }
        frame.render(data);
        algoVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {
        visualizer = new algoVisualizer(MAZE_SIZE[0], MAZE_SIZE[1]);
    }
}
