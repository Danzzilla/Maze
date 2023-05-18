package generation;

import graph.Graph;

/**
 * Generation of Maze
 *
 * @author Daniel Svirida
 * @version 1
 */
public class MazeGeneration {

    private Graph<Integer> graph;
    private int cols;
    private int setLength;

    /**
     * Constructor for Maze Generation
     * @param cols number of columns the maze is to have
     * @param rows number of rows the maze is to have
     */
    public MazeGeneration(int cols, int rows){
        this.cols = cols;
        setLength = cols * rows;
        this.graph = new Graph<>();
    }

    /**
     * Generate the maze
     * @return the graph representing the maze
     */
    public Graph<Integer> generateMaze(){
        DisjointSets disjointSets = new DisjointSets(setLength);

        for (int i = 0; i < setLength; i++) {
            graph.addVertex(i);
        }
        for (int i = 0; i < setLength - 1; i++) {
            //boolean for finding a pair of vertices not part
            //of the same set in the disjointSets Set
            boolean found = false;
            int vertex = -1;
            int neighbor = -1;

            while(!found){
                vertex = randomVertex();
                neighbor = randomNeighbor(vertex);

                if(!disjointSets.sameSet(vertex, neighbor)){
                    //found a pair
                    found = true;
                }
            }

            disjointSets.union(vertex, neighbor);
            graph.addEdge(vertex, neighbor);
        }

        return graph;
    }

    /**
     * Chooses a random vertex in the graph
     * @return random vertex in the bounds of the specific graph
     */
    private int randomVertex(){
        return (int)(Math.random() * setLength);
    }

    /**
     * Chooses a random neighbor around a vertex
     * @param vertex a vertex whos neighbor needs to be random
     * @return random neighbor vertex
     */
    private int randomNeighbor(int vertex){
        switch((int)(Math.random() * 4)){
            case 0:
                //West
                if(vertex - 1 > 0 && vertex % cols != 0){
                    return vertex - 1;
                }
            case 1:
                //East
                if(vertex + 1 < setLength && (vertex + 1) % cols != 0){
                    return vertex + 1;
                }
            case 2:
                //North
                if(vertex - cols > 0){
                    return vertex - cols;
                }
            case 3:
                //South
                if(vertex + cols < setLength){
                    return vertex + cols;
                }
            default:
                //if the method didnt find a valid random neighbor for the vertex
                //it chooses a different random number and tries again
                return randomNeighbor(vertex);
        }

    }

    @Override
    public String toString() {
        return "MazeGeneration{" +
                "graph=" + graph +
                '}';
    }
}
