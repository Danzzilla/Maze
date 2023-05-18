package algorithms;

import graph.Graph;

import java.util.*;

/**
 * Breadth-first search
 * @author Daniel Svirida
 * @version 1
 */
public class BFS {
    private Graph<Integer> graph;
    private TreeMap<Integer, Integer> mapPath;

    /**
     * Search Constructor
     * @param graph the graph to be searched
     */
    public BFS(Graph<Integer> graph){
        this.graph = graph;
    }

    /**
     * BFS search the graph
     * @param source starting point of the maze
     * @return Map of possible pathways in the maze
     */
    public TreeMap<Integer, Integer> search(int source){
        Set<Integer> knownVertices = new HashSet<>();
        mapPath = new TreeMap<>();
        Queue<Integer> queue = new LinkedList<>();

        queue.add(source);

        while(!queue.isEmpty()){
            int current = queue.remove();
            knownVertices.add(current);

            for(int neighbor : graph.adjacencyList(current)){
                if(!knownVertices.contains(neighbor)){
                    queue.add(neighbor);
                    mapPath.put(neighbor, current);
                }
            }
        }

        return mapPath;
    }

    @Override
    public String toString() {
        return "BFS{" +
                "graph=" + graph +
                ", mapPath=" + mapPath +
                '}';
    }
}
