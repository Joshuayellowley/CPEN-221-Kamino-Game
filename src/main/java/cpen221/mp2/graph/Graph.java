package cpen221.mp2.graph;

import java.util.*;

/**
 * Represents a graph with vertices of type V.
 *
 * @param <V> represents a vertex type
 */
public class Graph<V extends Vertex, E extends Edge<V>> implements ImGraph<V, E>, IGraph<V, E> {

    private ArrayList<V> vertices = new ArrayList<>();
    private ArrayList<E> edges = new ArrayList<>();

    public Graph(){}

    /**
     * Add a vertex to the graph
     *
     * @param v vertex to add
     * @return true iff v is a vertex and is not already in the graph, false otherwise
     */
    public boolean addVertex(V v){
        if(v == null){
            return false;
        }
        if(!vertices.contains(v)){
            vertices.add(v);
            return true;
        } else {
            System.out.println("Vertex already contained in the vertex list");
            return false;
        }
    }

    /**
     * Check if a vertex is part of the graph
     *
     * @param v vertex to check in the graph
     * @return true if v is a vertex and is part of the graph and false otherwise
     */
    public boolean vertex(V v){
        return vertices.contains(v);
    }

    /**
     * Add an edge of the graph
     *
     * @param e the edge to add to the graph
     * @return true iff e is an edge and is not already in the graph, false otherwise
     */
    public boolean addEdge(E e){
        if(e == null){
            return false;
        }
        if(!edges.contains(e)){
            addVertex(e.v1());
            addVertex(e.v2());
            edges.add(e);
            return true;
        } else {
            System.out.println("Edge already contained in the edges list");
            return false;
        }
    }

    /**
     * Check if an edge is part of the graph
     *
     * @param e the edge to check in the graph
     * @return true if e is an edge in the graoh and false otherwise
     */
    public boolean edge(E e) {
        return edges.contains(e);
    }

    /**
     * Check if v1-v2 is an edge in the graph
     *
     * @param v1 the first vertex of the edge
     * @param v2 the second vertex of the edge
     * @return true of the v1-v2 edge is part of the graph and false otherwise
     */

    public boolean edge(V v1, V v2){
        for(E e: edges){
            if(e.incident(v1) && e.incident(v2)){
                return true;
            }
        }
        return false;
    }

    /**
     * Determine the length on an edge in the graph
     *
     * @param v1 the first vertex of the edge
     * @param v2 the second vertex of the edge
     * @return the length of the v1-v2 edge if this edge is part of the graph
     */
    public int edgeLength(V v1, V v2){
        for (E edge : edges) {
            if (edge.incident(v1) && edge.incident(v2)) {
                return edge.length();
            }
        }
        return 0;

    }

    /**
     * Obtain the sum of the lengths of all edges in the graph
     *
     * @return the sum of the lengths of all edges in the graph
     */
    public int edgeLengthSum(){
        int sum = 0;
        for(E e: this.edges){
            sum += e.length();
        }
        return sum;
    }

    /**
     * Remove an edge from the graph
     *
     * @param e the edge to remove
     * @return true if e was successfully removed and false otherwise
     */
    public boolean remove(E e){
        if(this.edges.contains(e)){
            this.edges.remove(e);
            return true;
        }
        System.out.println("The edge is not contained in this graph");
        return false;
    }

    /**
     * Remove a vertex from the graph
     *
     * @param v the vertex to remove
     * @return true if v was successfully removed and false otherwise
     */
    public boolean remove(V v){
        if(vertices.contains(v)){
            vertices.remove(v);
            return true;
        } else {
            System.out.println("Vertex is not contained in the vertex list");
            return false;
        }
    }


    /**
     * Obtain a set of all vertices in the graph.
     * Access to this set **should not** permit graph mutations.
     *
     * @return a set of all vertices in the graph
     */
    public Set<V> allVertices(){
        Set<V> vertexes = new HashSet<>();
        for(Vertex v: this.vertices){
            vertexes.add((V)v.copyVertex());
        }
        return vertexes;
    }

    /**
     * Obtain a set of all vertices incident on v.
     * Access to this set **should not** permit graph mutations.
     *
     * @param v the vertex of interest
     * @return all edges incident on v
     */
    public Set<E> allEdges(V v){
        Set<E> newEdges = new HashSet<>();
        for(E e: this.edges){
            if(e.incident(v)){
                newEdges.add( (E) new Edge(e.v1(),e.v2(),e.length()));
            }
        }
        return newEdges;
    }

    /**
     * Obtain a set of all edges in the graph.
     * Access to this set **should not** permit graph mutations.
     *
     * @return all edges in the graph
     */
    public Set<E> allEdges(){
        Set<E> newEdges = new HashSet<>();
        for(E e: this.edges){
            newEdges.add( (E) new Edge(e.v1(),e.v2(),e.length()));
        }
        return newEdges;
    }

    /**
     * Obtain all the neighbours of vertex v.
     * Access to this map **should not** permit graph mutations.
     *
     * @param v is the vertex whose neighbourhood we want.
     * @return a map containing each vertex w that neighbors v and the edge between v and w.
     */
    public Map<V, E> getNeighbours(V v){
        Set<E> edgeSet = this.allEdges(v);
        Map<V, E> newMap = new HashMap<>();
        for(E e: edgeSet){
            newMap.put(e.distinctVertex(v),e);
        }
        return newMap;
    }

    /**
     * Compute the shortest path from source to sink
     *
     * @param source the start vertex
     * @param sink   the end vertex
     * @return the vertices, in order, on the shortest path from source to sink (both end points are part of the list)
     */
    @Override
    public List<V> shortestPath(V source, V sink) {
        return null;
    }

    /**
     * Compute the minimum spanning tree of the graph.
     * See https://en.wikipedia.org/wiki/Minimum_spanning_tree
     *
     *
     * @return a list of edges that forms a minimum spanning tree of the graph
     */
    @Override
    public List<E> minimumSpanningTree() {

        ArrayList<V> allVertices = new ArrayList<>(this.vertices);
        ArrayList<V> visited = new ArrayList<>();
        ArrayList<E> path = new ArrayList<>();

        visited.add(allVertices.get(0));
        while(!visited.containsAll(allVertices)) {
            int minLen = Integer.MAX_VALUE;
                E chosenEdge = this.edges.get(0);
                V chosenVert = visited.get(0);
                for(V v: visited) {
                    for (Map.Entry<V, E> entry : this.getNeighbours(v).entrySet()) {
                        if (entry.getValue().length() < minLen
                                && !path.contains(entry.getValue())
                                && !visited.contains(entry.getKey())) {
                            minLen = entry.getValue().length();
                            chosenEdge = entry.getValue();
                            chosenVert = entry.getKey();
                        }
                    }
                }

                path.add(chosenEdge);
                visited.add(chosenVert);
        }
        for(E poop : path) {
            System.out.println("------");
            System.out.println(poop.v1().id());
            System.out.println(poop.v2().id());
        }
        return path;
    }


    /**
     * Compute the length of a given path
     *
     * @param path indicates the vertices on the given path
     * @return the length of path
     */
    @Override
    public int pathLength(List<V> path) {
        return 0;
    }

    /**
     * Obtain all vertices w that are no more than a <em>path distance</em> of range from v.
     *
     * @param v     the vertex to start the search from.
     * @param range the radius of the search.
     * @return a set of vertices that are within range of v (this set does not contain v).
     */
    @Override
    public Set<V> search(V v, int range) {
        return null;
    }

    /**
     * Compute the diameter of the graph.
     * <ul>
     * <li>The diameter of a graph is the length of the longest shortest path in the graph.</li>
     * <li>If a graph has multiple components then we will define the diameter
     * as the diameter of the largest component.</li>
     * </ul>
     *
     * @return the diameter of the graph.
     */
    @Override
    public int diameter() {
        return 0;
    }

    /**
     * Find the edge that connects two vertices if such an edge exists.
     * This method should not permit graph mutations.
     *
     * @param v1 one end of the edge
     * @param v2 the other end of the edge
     * @return the edge connecting v1 and v2
     */
    @Override
    public E getEdge(V v1, V v2) {
        return null;
    }


    //// add all new code above this line ////

    /**
     * This method removes some edges at random while preserving connectivity
     * <p>
     * DO NOT CHANGE THIS METHOD
     * </p>
     * <p>
     * You will need to implement allVertices() and allEdges(V v) for this
     * method to run correctly
     *</p>
     * <p><strong>requires:</strong> this graph is connected</p>
     *
     * @param rng random number generator to select edges at random
     */
    public void pruneRandomEdges(Random rng) {
        class VEPair {
            V v;
            E e;

            public VEPair(V v, E e) {
                this.v = v;
                this.e = e;
            }
        }
        /* Visited Nodes */
        Set<V> visited = new HashSet<>();
        /* Nodes to visit and the cpen221.mp2.graph.Edge used to reach them */
        Deque<VEPair> stack = new LinkedList<VEPair>();
        /* Edges that could be removed */
        ArrayList<E> candidates = new ArrayList<>();
        /* Edges that must be kept to maintain connectivity */
        Set<E> keep = new HashSet<>();

        V start = null;
        for (V v : this.allVertices()) {
            start = v;
            break;
        }
        if (start == null) {
            // nothing to do
            return;
        }
        stack.push(new VEPair(start, null));
        while (!stack.isEmpty()) {
            VEPair pair = stack.pop();
            if (visited.add(pair.v)) {
                keep.add(pair.e);
                for (E e : this.allEdges(pair.v)) {
                    stack.push(new VEPair(e.distinctVertex(pair.v), e));
                }
            } else if (!keep.contains(pair.e)) {
                candidates.add(pair.e);
            }
        }
        // randomly trim some candidate edges
        int iterations = rng.nextInt(candidates.size());
        for (int count = 0; count < iterations; ++count) {
            int end = candidates.size() - 1;
            int index = rng.nextInt(candidates.size());
            E trim = candidates.get(index);
            candidates.set(index, candidates.get(end));
            candidates.remove(end);
            remove(trim);
        }
    }

}
