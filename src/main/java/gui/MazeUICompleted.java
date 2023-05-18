package gui;

import algorithms.BFS;
import algorithms.DFS;
import generation.AlgorithmType;
import generation.MazeGeneration;
import graph.Graph;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.TreeMap;

/**
 * Class that manages what algorithm fires depending on what button is pressed
 * @author Daniel Svirida
 * @version 1
 */
public class MazeUICompleted extends MazeUI {

    private Graph<Integer> graph;

    private int cols;
    private int rows;
    private int setLength;

    @Override
    public void runAlgorithm(AlgorithmType type)
    {
        switch(type){
            case GENERATE_MAZE:
                this.cols = getCols();
                this.rows = getRows();
                setLength = cols * rows;

                MazeGeneration mazeGraph = new MazeGeneration(cols, rows);
                graph = mazeGraph.generateMaze();
                drawMaze();
                break;
            case BFS:
                BFS bfs = new BFS(graph);
                showPath(bfs.search(0));
                break;
            case DFS:
                DFS dfs = new DFS(graph);
                showPath(dfs.search(0));
                break;
        }
    }

    /**
     * Draws a new Maze on the screen
     */
    public void drawMaze(){
        clearScreen();
        drawBackgroundGrid();
        setStrokeColor(Color.BLACK);
        setStrokeWidth(2);
        setFillColor(Color.YELLOW);

        for (int i = 0; i < setLength; i++) {
            drawCell(i, formatArray(graph.adjacencyList(i), i));
        }
    }

    /**
     * Formats an adjacency list into a boolean array that we can pass into drawCell
     * in order to have the correct borders drawn on the cell
     * @param list adjacency list for the vertex
     * @param vertex the vertex
     * @return the boolean array that shows what sides of the cell has borders
     */
    private boolean[] formatArray(List<Integer> list, int vertex){
        boolean[] array = {true, true, true, true};

        for (int i = 0; i < list.size(); i++) {
            int otherVertex = list.get(i);

            if(otherVertex + 1 == vertex){
                //West
                array[3] = false;
            }
            else if(otherVertex - 1 == vertex){
                //East
                array[1] = false;
            }
            else if(otherVertex + cols == vertex){
                //North
                array[0] = false;
            }
            else if(otherVertex - cols == vertex){
                //South
                array[2] = false;
            }
        }

        return array;
    }

    /**
     * Highlights the path from the top left to the bottom right
     * @param path TreeMap path with keys pointing to previous vertex in the path,
     *             traversing from the end to the beginning
     */
    public void showPath(TreeMap<Integer, Integer> path){
        int current = setLength - 1;
        while(current != 0){
            fillCell(current);
            current = path.get(current);
        }
        fillCell(current);
    }

    @Override
    public String toString() {
        return "MazeUICompleted{" +
                "cols=" + cols +
                ", rows=" + rows +
                '}';
    }
}
