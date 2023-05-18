package algorithms;

import graph.Graph;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

/**
 * Depth-first Graph Search
 * @author Daniel Svirida
 * @version 1
 */
public class DFS {
    private Graph<Integer> graph;
    private TreeMap<Integer, Integer> mapPath;
    private Set<Integer> knownVertices;

    /**
     * Search Constructor
     * @param graph the graph that is to be searched
     */
    public DFS(Graph<Integer> graph){
        this.graph = graph;
    }

    /**
     * Start DFS search from the source
     * @param source the starting vertex in the maze
     * @return Map of possible pathways in the maze
     */
    public TreeMap<Integer, Integer> search(int source){
        knownVertices = new HashSet<>();
        mapPath = new TreeMap<>();

        if(!graph.containsVertex(source)){
            return mapPath;
        }

        search(source,0);
        mapPath.remove(0, 0);
        return mapPath;
    }

    /**
     * Recursive DFS search from the current vertex so forth
     * @param current current vertex inspected
     * @param previous previous vertex inspected
     */
    private void search(int current, int previous){
        if(!knownVertices.contains(current)){
            knownVertices.add(current);
            mapPath.put(current, previous);

            for (int neighbor : graph.adjacencyList(current)){
                search(neighbor, current);
            }
        }
    }

    @Override
    public String toString() {
        return "DFS{" +
                "mapPath=" + mapPath +
                ", knownVertices=" + knownVertices +
                '}';
    }
}
