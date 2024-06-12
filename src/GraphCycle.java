import java.util.ArrayList;
import java.util.List;

public class GraphCycle {
    public static void main(String[] args) {
        Vertex vertexA = new Vertex("A");
        Vertex vertexB = new Vertex("B");
        Vertex vertexC = new Vertex("C");
        Vertex vertexD = new Vertex("D");

        Graph graph = new Graph();
        graph.addVertex(vertexA);
        graph.addVertex(vertexB);
        graph.addVertex(vertexC);
        graph.addVertex(vertexD);

        graph.addEdge(vertexA, vertexB);
        graph.addEdge(vertexB, vertexC);
        graph.addEdge(vertexC, vertexA);
        graph.addEdge(vertexD, vertexC);

        System.out.println(graph.hasCycle());
        // res is true (it truly has no cycles)
    }

    public static class Vertex {

        private String label;
        private boolean beingVisited;
        private boolean visited;
        private List<Vertex> adjacencyList;

        public Vertex(String label) {
            this.label = label;
            this.adjacencyList = new ArrayList<>();
        }

        public void addNeighbor(Vertex adjacent) {
            this.adjacencyList.add(adjacent);
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public boolean isBeingVisited() {
            return beingVisited;
        }

        public void setBeingVisited(boolean beingVisited) {
            this.beingVisited = beingVisited;
        }

        public boolean isVisited() {
            return visited;
        }

        public void setVisited(boolean visited) {
            this.visited = visited;
        }

        public List<Vertex> getAdjacencyList() {
            return adjacencyList;
        }

        public void setAdjacencyList(List<Vertex> adjacencyList) {
            this.adjacencyList = adjacencyList;
        }
        //getters and setters
    }
    public static class Graph {

        private List<Vertex> vertices;

        public Graph() {
            this.vertices = new ArrayList<>();
        }

        public void addVertex(Vertex vertex) {
            this.vertices.add(vertex);
        }

        public void addEdge(Vertex from, Vertex to) {
            from.addNeighbor(to);
        }
        public  boolean hasCycle(Vertex sourceVertex) {
            sourceVertex.setBeingVisited(true);

            for (Vertex neighbor : sourceVertex.getAdjacencyList()) {
                if (neighbor.isBeingVisited()) {
                    // backward edge exists
                    return true;
                } else if (!neighbor.isVisited() && hasCycle(neighbor)) {
                    return true;
                }
            }

            sourceVertex.setBeingVisited(false);
            sourceVertex.setVisited(true);
            return false;
        }
        public boolean hasCycle() {
            for (Vertex vertex : vertices) {
                if (!vertex.isVisited() && hasCycle(vertex)) {
                    return true;
                }
            }
            return false;
        }
        // ...
    }
}

