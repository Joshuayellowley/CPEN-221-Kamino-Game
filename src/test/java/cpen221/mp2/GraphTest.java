package cpen221.mp2;

import cpen221.mp2.graph.Edge;
import cpen221.mp2.graph.Graph;
import cpen221.mp2.graph.Vertex;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class GraphTest {

    @Test
    public void testCreateGraph() {
        Vertex v1 = new Vertex(1, "A");
        Vertex v2 = new Vertex(2, "B");
        Vertex v3 = new Vertex(3, "C");
        Vertex v4 = new Vertex(4, "D");

        Edge<Vertex> e1 = new Edge<>(v1, v2, 5);
        Edge<Vertex> e2 = new Edge<>(v2, v3, 7);
        Edge<Vertex> e3 = new Edge<>(v1, v4, 9);

        Graph<Vertex, Edge<Vertex>> g = new Graph<>();
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);

        assertEquals(e2, g.getEdge(v2, v3));
        assertEquals(21, g.pathLength(g.shortestPath(v3, v4)));
    }

    @Test
    public void testAddVAndE(){
        Vertex v1 = new Vertex(1, "A");
        Vertex v2 = new Vertex(2, "B");
        Vertex v3 = new Vertex(3, "C");
        Vertex v4 = new Vertex(4, "D");
        Graph<Vertex, Edge<Vertex>> g = new Graph<>();

        Edge<Vertex> e1 = new Edge<>(v1, v2, 5);
        Edge<Vertex> e2 = new Edge<>(v2, v3, 7);
        Edge<Vertex> e3 = new Edge<>(v1, v4, 9);
        Edge<Vertex> e4 = new Edge<>(v2, v4, 10);
        Edge<Vertex> e5 = new Edge<>(v3, v4, 11);

        Set<Edge<Vertex>> eset = new HashSet<>();
        Map<Vertex,Edge<Vertex>> neighborSet = new HashMap<>();

        eset.add(e1);
        eset.add(e2);
        eset.add(e3);
        eset.add(e4);

        neighborSet.put(v1,e3);
        neighborSet.put(v2,e4);

        assertTrue(g.addVertex(v1));
        assertFalse(g.addVertex(v1));
        assertTrue(g.addVertex(v2));
        assertFalse(g.addVertex(v2));
        assertTrue(g.addVertex(v3));
        assertFalse(g.addVertex(v3));
        assertTrue(g.addVertex(v4));
        assertFalse(g.addVertex(v4));

        assertTrue(g.addEdge(e1));
        assertFalse(g.addEdge(e1));
        assertTrue(g.addEdge(e2));
        assertFalse(g.addEdge(e2));
        assertTrue(g.addEdge(e3));
        assertFalse(g.addEdge(e3));
        assertTrue(g.addEdge(e4));
        assertFalse(g.addEdge(e4));

        assertEquals(e1.length(),5);
        assertEquals(e2.length(),7);
        assertEquals(e3.length(),9);

        assertTrue(g.edge(e1));
        assertTrue(g.edge(e4));
        assertFalse(g.edge(e5));
        assertTrue(g.edge(v2,v3));
        assertTrue(g.edge(v3,v2));
        assertFalse(g.edge(v3,v1));

        assertEquals(9, g.edgeLength(v4,v1));
        assertEquals(9, g.edgeLength(v1,v4));

        assertEquals(31, g.edgeLengthSum());

        assertEquals(eset, g.allEdges());
        assertEquals(neighborSet, g.getNeighbours(v4));
        assertEquals(17, g.pathLength(g.shortestPath(v3, v4)));
    }

    @Test
    public void testTree() {
        Vertex v1 = new Vertex(1, "A");
        Vertex v2 = new Vertex(2, "B");
        Vertex v3 = new Vertex(3, "C");
        Vertex v4 = new Vertex(4, "D");
        Vertex v5 = new Vertex(5, "E");
        Vertex v6 = new Vertex(6, "F");



        Edge<Vertex> e1 = new Edge<>(v1, v4, 2);
        Edge<Vertex> e2 = new Edge<>(v1, v6, 3);
        Edge<Vertex> e3 = new Edge<>(v4, v6, 1);
        Edge<Vertex> e4 = new Edge<>(v2, v6, 7);
        Edge<Vertex> e5 = new Edge<>(v2, v5, 9);
        Edge<Vertex> e6 = new Edge<>(v6, v5, 4);
        Edge<Vertex> e7 = new Edge<>(v2, v3, 10);
        Edge<Vertex> e8= new Edge<>(v3, v5, 11);



        Graph<Vertex, Edge<Vertex>> g = new Graph<>();
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addVertex(v5);
        g.addVertex(v6);

        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);
        g.addEdge(e4);
        g.addEdge(e5);
        g.addEdge(e6);
        g.addEdge(e7);
        g.addEdge(e8);

        g.minimumSpanningTree();
    }

    @Test
    public void testTree2() {
        Vertex v1 = new Vertex(1, "A");
        Vertex v2 = new Vertex(2, "B");
        Vertex v3 = new Vertex(3, "C");
        Vertex v4 = new Vertex(4, "D");
        Vertex v5 = new Vertex(5, "E");
        Vertex v6 = new Vertex(6, "F");



        Edge<Vertex> e1 = new Edge<>(v1, v3, 2);
        Edge<Vertex> e2 = new Edge<>(v1, v2, 3);
        Edge<Vertex> e3 = new Edge<>(v4, v5, 1);
        Edge<Vertex> e4 = new Edge<>(v2, v4, 7);
        Edge<Vertex> e5 = new Edge<>(v2, v3, 9);
        Edge<Vertex> e6 = new Edge<>(v6, v5, 4);
        Edge<Vertex> e7 = new Edge<>(v5, v1, 10);
        Edge<Vertex> e8= new Edge<>(v3, v4, 11);

        Graph<Vertex, Edge<Vertex>> g = new Graph<>();
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addVertex(v5);
        g.addVertex(v6);

        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);
        g.addEdge(e4);
        g.addEdge(e5);
        g.addEdge(e6);
        g.addEdge(e7);
        g.addEdge(e8);

        g.minimumSpanningTree();
    }

    @Test
    public void testSorting() {
        Vertex v1 = new Vertex(1, "A");
        Vertex v2 = new Vertex(2, "B");
        Vertex v3 = new Vertex(3, "C");
        Vertex v4 = new Vertex(4, "D");
        Vertex v5 = new Vertex(5, "E");
        Vertex v6 = new Vertex(6, "F");



        Edge<Vertex> e1 = new Edge<>(v1, v4, 2);
        Edge<Vertex> e2 = new Edge<>(v1, v6, 3);
        Edge<Vertex> e3 = new Edge<>(v4, v6, 1);
        Edge<Vertex> e4 = new Edge<>(v2, v6, 7);
        Edge<Vertex> e5 = new Edge<>(v2, v5, 9);
        Edge<Vertex> e6 = new Edge<>(v6, v5, 4);
        Edge<Vertex> e7 = new Edge<>(v2, v3, 10);
        Edge<Vertex> e8= new Edge<>(v3, v5, 11);



        Graph<Vertex, Edge<Vertex>> g = new Graph<>();
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addVertex(v5);
        g.addVertex(v6);

        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);
        g.addEdge(e4);
        g.addEdge(e5);
        g.addEdge(e6);
        g.addEdge(e7);
        g.addEdge(e8);

        g.minimumSpanningTree();
    }


    @Test
    public void testShortestPath() {
        Vertex v1 = new Vertex(1, "A");
        Vertex v2 = new Vertex(2, "B");
        Vertex v3 = new Vertex(3, "C");
        Vertex v4 = new Vertex(4, "D");
        Vertex v5 = new Vertex(5, "E");
        Vertex v6 = new Vertex(6, "F");


        Edge<Vertex> e1 = new Edge<>(v1, v3, 2);
        Edge<Vertex> e2 = new Edge<>(v1, v2, 3);
        Edge<Vertex> e3 = new Edge<>(v4, v5, 1);
        Edge<Vertex> e4 = new Edge<>(v2, v4, 7);
        Edge<Vertex> e5 = new Edge<>(v2, v3, 9);
        Edge<Vertex> e6 = new Edge<>(v6, v5, 4);
        Edge<Vertex> e7 = new Edge<>(v5, v1, 10);
        Edge<Vertex> e8 = new Edge<>(v3, v4, 11);

        Graph<Vertex, Edge<Vertex>> g = new Graph<>();
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addVertex(v5);
        g.addVertex(v6);

        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);
        g.addEdge(e4);
        g.addEdge(e5);
        g.addEdge(e6);
        g.addEdge(e7);
        g.addEdge(e8);

        List<Vertex> path1 = new ArrayList<>();
        List<Vertex> path2 = new ArrayList<>();
        List<Vertex> shortestPath = new ArrayList<>();

        shortestPath.add(v1);
        //shortestPath.add(v6);
        shortestPath.add(v2);
        shortestPath.add(v4);

        path1.add(v3);
        path1.add(v1);
        path1.add(v5);

        path2.add(v4);
        path2.add(v3);
        path2.add(v2);
        path2.add(v1);



        assertEquals(16,g.diameter(),0.0001);
        assertEquals(12, g.pathLength(path1), 0.00001);
        assertEquals(23, g.pathLength(path2), 0.00001);
        assertEquals(shortestPath, g.shortestPath(v1, v4));

    }

    @Test
    public void testShortestPath2() {
        Vertex v1 = new Vertex(1, "A");
        Vertex v2 = new Vertex(2, "B");
        Vertex v3 = new Vertex(3, "C");
        Vertex v4 = new Vertex(4, "D");
        Vertex v5 = new Vertex(5, "E");
        Vertex v6 = new Vertex(6, "F");


        Edge<Vertex> e1 = new Edge<>(v1, v5, 2);
        Edge<Vertex> e2 = new Edge<>(v3, v2, 3);
        Edge<Vertex> e3 = new Edge<>(v2, v5, 1);
        Edge<Vertex> e4 = new Edge<>(v5, v4, 7);
        Edge<Vertex> e5 = new Edge<>(v2, v1, 9);
        Edge<Vertex> e6 = new Edge<>(v3, v5, 4);
        Edge<Vertex> e7 = new Edge<>(v6, v1, 10);
        Edge<Vertex> e8 = new Edge<>(v3, v6, 11);

        Graph<Vertex, Edge<Vertex>> g = new Graph<>();
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addVertex(v5);
        g.addVertex(v6);

        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);
        g.addEdge(e4);
        g.addEdge(e5);
        g.addEdge(e6);
        g.addEdge(e7);
        g.addEdge(e8);

        List<Vertex> path1 = new ArrayList<>();
        List<Vertex> path2 = new ArrayList<>();
        List<Vertex> shortestPath = new ArrayList<>();

        shortestPath.add(v6);
        //shortestPath.add(v6);
        shortestPath.add(v1);
        shortestPath.add(v5);

        path1.add(v3);
        path1.add(v1);
        path1.add(v5);

        path2.add(v4);
        path2.add(v3);
        path2.add(v2);
        path2.add(v1);



        assertEquals(19,g.diameter(),0.0001);
//        assertEquals(12, g.pathLength(path1), 0.00001);
//        assertEquals(23, g.pathLength(path2), 0.00001);
        assertEquals(shortestPath, g.shortestPath(v6, v5));

    }

    @Test
    public void testInvalidPath(){
        Vertex v1 = new Vertex(1, "A");
        Vertex v2 = new Vertex(2, "B");
        Vertex v3 = new Vertex(3, "C");
        Vertex v4 = new Vertex(4, "D");
        Vertex v5 = new Vertex(5, "E");
        Vertex v6 = new Vertex(6, "F");
        Vertex v7 = new Vertex(7, "G");

        List<Vertex> invalidPath = new ArrayList<>();
        Graph<Vertex,Edge<Vertex>> g = new Graph<>();
        invalidPath.add(v1);
        invalidPath.add(v2);
        invalidPath.add(v7);
        invalidPath.add(v4);
        Random rng = new Random();
        assertEquals(0, g.pathLength(invalidPath));
        g.pruneRandomEdges(rng);
    }

    @Test
    public void testRemoveStuff() {
        Vertex v1 = new Vertex(1, "A");
        Vertex v2 = new Vertex(2, "B");
        Vertex v3 = new Vertex(3, "C");
        Vertex v4 = new Vertex(4, "D");
        Vertex v5 = new Vertex(5, "E");
        Vertex v6 = new Vertex(6, "F");

        Edge<Vertex> e1 = new Edge<>(v1, v2, 5);
        Edge<Vertex> e2 = new Edge<>(v2, v3, 7);
        Edge<Vertex> e3 = new Edge<>(v1, v4, 9);
        Edge<Vertex> e4 = new Edge<>(v5, v6);
        Graph<Vertex, Edge<Vertex>> g = new Graph<>();
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        Set<Vertex> allVertexes = new HashSet<>();
        allVertexes.add(v1);
        allVertexes.add(v2);
        allVertexes.add(v3);
        allVertexes.add(v4);
        assertEquals(allVertexes, g.allVertices());

        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);

        Set<Edge> allEdges = new HashSet<>();
        allEdges.add(e1);
        allEdges.add(e2);
        allEdges.add(e3);

        assertEquals(allEdges, g.allEdges());

        allEdges.add(e4);
        assertNotEquals(allEdges, g.allEdges());


        assertFalse(g.remove(e4));
        assertFalse(g.remove(v5));
        assertFalse(g.remove(v6));
        assertEquals(e2, g.getEdge(v2, v3));
        assertEquals(21, g.pathLength(g.shortestPath(v3, v4)));
        assertTrue(g.remove(v1));
    }

    @Test
    public void testEmptyStuff(){
        Graph<Vertex, Edge<Vertex>> g = new Graph<>();
        Vertex v5 = new Vertex(5, "E");
        Vertex v6 = new Vertex(6, "F");
        Edge e1 = new Edge(v5,v6);
        assertEquals(new ArrayList<>(), g.shortestPath(v5, v6));
        assertEquals(new HashMap<Vertex,Edge>(), g.getNeighbours(v5));
        assertEquals(new HashSet<Vertex>(), g.allVertices());
        assertEquals(new HashSet<Edge>(), g.allEdges());
        assertEquals(0,g.edgeLengthSum());
        assertEquals(0,g.edgeLength(v5, v6));
        assertEquals(null,g.getEdge(v5, v6));
        assertFalse(g.addEdge(null));
        assertFalse(g.addVertex(null));
        g.addVertex(v5);
        g.addVertex(v6);
        assertEquals(null, g.getEdge(v5,v6));
    }

    @Test
    public void testSearch(){
        Vertex v1 = new Vertex(1, "A");
        Vertex v2 = new Vertex(2, "B");
        Vertex v3 = new Vertex(3, "C");
        Vertex v4 = new Vertex(4, "D");
        Vertex v5 = new Vertex(5, "E");

        Edge<Vertex> e1 = new Edge<>(v1, v2, 5);
        Edge<Vertex> e2 = new Edge<>(v2, v3, 7);
        Edge<Vertex> e3 = new Edge<>(v1, v4, 9);

        Graph<Vertex, Edge<Vertex>> g = new Graph<>();
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);

        Set<Vertex> vertices1 = new HashSet<>();
        vertices1.add(v2);
        Set<Vertex> vertices2 = new HashSet<>();
        vertices2.add(v2);
        vertices2.add(v4);
        Set<Vertex> vertices3 = new HashSet<>();
        vertices3.add(v2);
        vertices3.add(v3);
        vertices3.add(v4);
        assertEquals(vertices1,g.search(v1,5));
        assertEquals(vertices2,g.search(v1,9));
        assertEquals(vertices3,g.search(v1,12));
        assertEquals(new HashSet<Vertex>(),g.search(v5,12));
        assertEquals(new HashSet<Vertex>(),g.search(v5,3));
    }
}
