package graph;

import java.util.*;

/**
 * Graph Structure
 *
 * @author Daniel Svirida
 * @version 1
 * @param <V>
 */
public class Graph<V> {
    private Map<V, Node> adjacencyLists = new HashMap<>();

    /**
     * Adds a vertex to the graph
     *
     * @param element element representing the vertex
     */
    public void addVertex(V element) {
        //the vertices are part of a set
        if(containsVertex(element)){
            return;
        }

        //add it (the element and a null head reference in the LL
        adjacencyLists.put(element, null);
    }

    /**
     * Adds an edge between two vertices
     *
     * @param first  first vertex
     * @param second second vertex
     */
    public void addEdge(V first, V second) {
        //edges are part of a set(no duplicates))
        if(containsEdge(first, second)){
            return;
        }

        //add the new edge at the start of both of the adjacency lists
        addDirectedEdge(first, second);
        addDirectedEdge(second, first);

    }

    private void addDirectedEdge(V first, V second) {
        Node oldHead = adjacencyLists.get(first);
        if(oldHead == null){
            adjacencyLists.put(first, new Node(second));
        }else{
            //put a new node at the start of the linked list
            adjacencyLists.put(first, new Node(second, oldHead));
        }
    }

    /**
     * Gives the adjacent vertices of a vertex
     * @param first the vertex whos adjacency list you want to see
     * @return adjacency list for the vertex
     */
    public List<V> adjacencyList(V first){
        List<V> list = new LinkedList<>();

        Node node = adjacencyLists.get(first);

        if(first != null) {
            list.add(node.otherVertex);
            while(node.next != null){
                node = node.next;
                list.add(node.otherVertex);
            }
        }

        return list;
    }

    /**
     * Tells whether the graph contains a vertex
     * @param search the vertex to be searched
     * @return true if it contains that vertex, false if not
     */
    public boolean containsVertex(V search) {
        return adjacencyLists.containsKey(search);
    }

    /**
     * Tells whether the graph contains a certain edge
     * @param first first vertex in the edge
     * @param second second vertex in the edge
     * @return true if the edge exists, false if not
     */
    public boolean containsEdge(V first, V second) {
        //make sure the vertices are in the graph
        if(containsVertex(first) && containsVertex(second)) {
            //get the adjacency list of first
            Node current = adjacencyLists.get(first);

            //loop over the list and look for second in the list
            while(current != null){
                if(current.otherVertex.equals(second)){
                    return true;
                }
                current = current.next;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Graph{" +
                "adjacencyLists=" + adjacencyLists +
                '}';
    }

    //inner classes

    /**
     * Node clas for adjacency list
     */
    private class Node{
        //data in the node
        private V otherVertex;

        //next node
        private Node next;

        public Node(V otherVertex){
            this.otherVertex = otherVertex;
        }

        public Node(V otherVertex, Node next){
            this.otherVertex = otherVertex;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "otherVertex=" + otherVertex +
                    ", next=" + next +
                    '}';
        }
    }
}
