package cpen221.mp2.graph;

import java.util.*;

/**
 * Represents a graph with vertices of type V.
 *
 * @param <V> represents a vertex type
 */
public class Graph<V extends Vertex, E extends Edge<V>> implements ImGraph<V, E>, IGraph<V, E> {

    private HashSet<V> vertices = new HashSet<>();
    private HashSet<E> edges = new HashSet<>();

    // Representation Invariant:
    // each V in vertices is unique, and cannot be null.
    // No vertex can exist without being connected to another, except the first
    // Vertex added to the graph
    // each E in Edges must connect two vertices, and cannot be null

    // Abstraction Function:
    // Represents a graph of a set V's which may/may not be connected by E's

    public Graph() {
    }

    /**
     * Add a vertex to the graph, if it does not already exist
     *
     * @param v vertex to add
     * @return true iff v is a vertex and is not already in the graph, false otherwise
     */
    public boolean addVertex(V v) {
        if (v == null) {
            return false;
        }
        if (!this.vertex(v)) {
            vertices.add(v);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check if a vertex is part of the graph
     *
     * @param v vertex to check in the graph
     * @return true if v is a vertex and is part of the graph and false otherwise
     */
    public boolean vertex(V v) {
        return vertices.contains(v);
    }

    /**
     * Adds an edge of the graph if e is incident with any V in vertices
     *
     * @param e the edge to add to the graph
     * @return true iff e is an edge and is not already in the graph
     * and it connects to another vertex, and false otherwise
     */
    public boolean addEdge(E e) {
        if (e == null) {
            return false;
        }
        if (!this.edge(e)) {
            for(V v : this.vertices){
                if(e.incident(v)){
                    addVertex(e.v1());
                    addVertex(e.v2());
                    edges.add(e);
                    return true;
                }
            }
        }
        System.out.println("Edge already contained in the edges list or is not connected to an existing vertex");
        return false;
    }

    /**
     * Check if an edge is part of the graph
     *
     * @param e the edge to check in the graph
     * @return true if e is an edge in the graph and not null, and false otherwise
     */
    public boolean edge(E e) {
        return edges.contains(e);
    }

    /**
     * Check if v1-v2 is an edge in the graph
     *
     * @param v1 the first vertex of the edge
     * @param v2 the second vertex of the edge
     * @return true of the v1-v2 edge is part of the graph and not null and false otherwise
     */

    public boolean edge(V v1, V v2) {
        for (E e : edges) {
            if (e.incident(v1) && e.incident(v2)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determine the length of an edge in the graph
     *
     * @param v1 the first vertex of the edge
     * @param v2 the second vertex of the edge
     * @return the length of the v1-v2 edge if this edge is part of the graph, otherwise returns 0
     */
    public int edgeLength(V v1, V v2) {
        if(edge(v1,v2)){
            return getEdge(v1,v2).length();
        }
        return 0;
    }

    /**
     * Obtain the sum of the lengths of all edges in the graph
     *
     * @return the sum of the lengths of all edges in the graph
     */
    public int edgeLengthSum() {
        int sum = 0;
        for (E e : this.edges) {
            sum += e.length();
        }
        return sum;
    }

    /**
     * Remove an edge from the graph if it exists
     *
     * @param e the edge to remove
     * @return true if e existed in the graph and was removed and false otherwise
     */
    public boolean remove(E e) {
        return edges.remove(e);
    }

    /**
     * Remove a vertex from the graph if it exists. If a vertex is removed,
     * remove the edges it was connected to as well.
     *
     * @param v the vertex to remove
     * @return true if v existed in the graph, and it and its edges were removed and false otherwise
     */
    public boolean remove(V v) {
        Set<E> connectedEdges = this.allEdges(v);
        if (vertex(v)) {
            for(E e: connectedEdges){
                if(e.incident(v)){
                    this.remove(e);
                }
            }
            this.vertices.remove(v);
            return true;
        } else {
            System.out.println("Vertex is not contained in the vertex list");
            return false;
        }
    }


    /**
     * Obtain a set of all vertices in the graph.
     *
     * @return a set of all vertices in the graph
     */
    public Set<V> allVertices() {
        return new HashSet<>(this.vertices);
    }

    /**
     * Obtain a set of all vertices incident on v.
     *
     * @param v the vertex of interest
     * @return all edges incident on v
     */
    public Set<E> allEdges(V v) {
        Set<E> newEdges = new HashSet<>();
        for (E e : this.edges) {
            if (e.incident(v)) {
                newEdges.add((E) new Edge(e.v1(), e.v2(), e.length()));
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
    public Set<E> allEdges() {
        return new HashSet<>(this.edges);
    }

    /**
     * Obtain all the neighbours of vertex v.
     *
     * @param v is the vertex whose neighbourhood we want.
     * @return a map containing each vertex w that neighbors v and the edge between v and w.
     * If v is not in graph, returns an empty set.
     */
    public Map<V, E> getNeighbours(V v) {
        if(!this.vertex(v)){
            return new HashMap<>();
        }
        Set<E> edgeSet = this.allEdges(v);
        Map<V, E> newMap = new HashMap<>();
        for (E e : edgeSet) {
            newMap.put(e.distinctVertex(v), e);
        }
        return newMap;
    }

    /**
     * Compute the shortest path in length from source to sink
     *
     * @param source the start vertex
     * @param sink   the end vertex
     * @return the vertices, in order, on the shortest path from source to sink
     * (both end points are part of the list). If they are not, returns an empty List.
     */
    @Override
    public List<V> shortestPath(V source, V sink) {
            if(!vertex(source) || !vertex(sink)){
                return new ArrayList<>();
            }
            Map<V,V> sourcePaths = doDijkstra(source);
            List<V> reversePath = new ArrayList<>();
            List<V> path = new ArrayList<>();
            reversePath.add(sink);
            V curV = sink;
            while(curV != source){
              reversePath.add(sourcePaths.get(curV));
              curV = sourcePaths.get(curV);
            }
            for(int i = reversePath.size()-1; i >= 0; i--){
                path.add(reversePath.get(i));
            }
        return path;
    }

    /**
     * Helper function for shortestPath
     * @param source the start vertex
     * @return a map that maps the current Vertex to the optimal previous one.
     * Optimal meaning it has the least total distance from source, on the way to sink.
     */

    private Map<V, V> doDijkstra(V source){
        Map<V,Integer> dist = new HashMap<>();
        Map<V,V> prev = new HashMap<>();
        Set<V> allVs = new HashSet<>();
        boolean flag = false;

        for(V v : vertices){
            dist.put(v, Integer.MAX_VALUE);
            allVs.add(v);
        }

        dist.put(source, 0);

        while(!allVs.isEmpty()){
            V curV = source;
            if(flag){
                int min = Integer.MAX_VALUE;
                for(Map.Entry<V,Integer> entry : dist.entrySet()){
                    if(entry.getValue() < min && allVs.contains(entry.getKey())) {
                        curV = entry.getKey();
                        min = entry.getValue();
                    }
                }
            }

            flag = true;
            allVs.remove(curV);
            int alt;
            for(Map.Entry<V,E> entry : getNeighbours(curV).entrySet()){
                if(allVs.contains(entry.getKey())){
                    alt = dist.get(curV) + getEdge(entry.getKey(),curV).length();
                    if(alt < dist.get(entry.getKey())){
                        dist.put(entry.getKey(), alt);
                        prev.put(entry.getKey(), curV);
                    }
                }
            }
        }
        return prev;
    }

    /**
     * Compute the shortest path lengths from source to all other vertices
     *
     * @param source the start vertex
     * @return a map of all the vertices in the graph, mapped with the shortest distance from the source.
     */
    private Map<V,Integer> getAllShortestPaths(V source){
        Map<V,Integer> dist = new HashMap<>();
        Map<V,V> prev = new HashMap<>();
        Set<V> allVs = new HashSet<>();
        boolean flag = false;

        for(V v : vertices){

            dist.put(v, Integer.MAX_VALUE);
            allVs.add(v);

        }

        dist.put(source, 0);


        while(!allVs.isEmpty()){
            V curV = source;

            if(flag){
                int min = Integer.MAX_VALUE;
                for(Map.Entry<V,Integer> entry : dist.entrySet()){
                    if(entry.getValue() < min && allVs.contains(entry.getKey())) {
                        curV = entry.getKey();
                        min = entry.getValue();
                    }
                }
            }

            flag = true;

            allVs.remove(curV);
            int alt;
            for(Map.Entry<V,E> entry : getNeighbours(curV).entrySet()){
                if(allVs.contains(entry.getKey())){
                    alt = dist.get(curV) + getEdge(entry.getKey(),curV).length();
                    if(alt < dist.get(entry.getKey())){
                        dist.put(entry.getKey(), alt);
                        prev.put(entry.getKey(), curV);
                    }
                }
            }


        }


        return dist;
    }

    /**
     * Compute the minimum spanning tree of the graph.
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
                E chosenEdge = (E) this.edges.toArray()[0];
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
        return path;
    }


    /**
     * Compute the length of a given path
     *
     * @param path indicates the vertices on the given path
     * @return the length of path, if all vertices in path exist in graph, otherwise returns 0
     */
    @Override
    public int pathLength(List<V> path) {
        int totalLength = 0;
        for(int i = 0; i < path.size() - 1; i++){

            E temp = getEdge(path.get(i),path.get(i+1));

            if(temp == null){
                System.out.println("This is an invalid path");
                return 0;
            }else{
                totalLength += temp.length();
            }
        }

        return totalLength;
    }

    /**
     * Obtain all vertices w that are no more than a path distance of range from v.
     *
     * @param v     the vertex to start the search from.
     * @param range the radius of the search.
     * @return a set of vertices that are within range of v (this set does not contain v).
     * If v does not exist in graph, or no vertices are in range, returns an empty set.
     */
    @Override
    public Set<V> search(V v, int range) {
        if(!vertex(v)) {
            return new HashSet<>();
        }
        Map<V,Integer> vertexDistance = this.getAllShortestPaths(v);
        Set<V> inRange = new HashSet<>();

        for(V vertex : vertices){
            if(vertexDistance.get(vertex) <= range && !v.equals(vertex)){
                inRange.add(vertex);
            }
        }

        return inRange;
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

        int max = 0;

        for(V v: vertices){
            for(V v1: vertices){
                if(this.getAllShortestPaths(v).get(v1) > max){
                    max = this.getAllShortestPaths(v).get(v1);
                }
            }
        }


        return max;
    }

    /**
     * Find the edge that connects two vertices if such an edge exists.
     * This method should not permit graph mutations.
     *
     * @param v1 one end of the edge
     * @param v2 the other end of the edge
     * @return the edge connecting v1 and v2, if it exists, otherwise returns null.
     */
    @Override
    public E getEdge(V v1, V v2) {
        if(!vertex(v1) || !vertex(v2)){
            return null;
        }
        for(E edge : edges){
            if(edge.incident(v1) && edge.incident(v2)){
                return edge;
            }
        }

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
