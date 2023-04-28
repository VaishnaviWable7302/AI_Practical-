import java.util.PriorityQueue;

public class GraphColoring1 {
    private int[][] graph;
    private int[] colors;
    private int numColors;
    private int numVertices;

    public GraphColoring1(int[][] graph, int numColors) {
        this.graph = graph;
        this.numColors = numColors;
        this.numVertices = graph.length;
        this.colors = new int[numVertices];
    }

    public boolean isSafe(int v, int c) {
        for (int i = 0; i < numVertices; i++) {
            if (graph[v][i] == 1 && colors[i] == c) {
                return false;
            }
        }
        return true;
    }

    public void printSolution() {
        for (int i = 0; i < numVertices; i++) {
            System.out.println("Vertex " + i + " color: " + colors[i]);
        }
    }

    public void solve() {
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        pq.add(new Node(-1, 0, null));
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (node.vertex == numVertices - 1) {
                Node sol = node;
                while (sol != null) {
                    colors[sol.vertex] = sol.color;
                    sol = sol.parent;
                }
                return;
            }
            for (int c = 1; c <= numColors; c++) {
                if (isSafe(node.vertex + 1, c)) {
                    Node child = new Node(node.vertex + 1, c, node);
                    pq.add(child);
                }
            }
        }
    }

    private class Node implements Comparable<Node> {
        int vertex;
        int color;
        Node parent;

        public Node(int vertex, int color, Node parent) {
            this.vertex = vertex;
            this.color = color;
            this.parent = parent;
        }

        public int compareTo(Node other) {
            return Integer.compare(other.vertex, this.vertex);
        }
    }
}

public class Graph1Coloring1 {
    public static void main(String[] args) {
        int n = 4;
        NQueens nQueens = new NQueens(4);
        nQueens.solve(n);
        nQueens.printSolution();

        int[][] graph = {
                { 0, 1, 1, 1 },
                { 1, 0, 1, 0 },
                { 1, 1, 0, 1 },
                { 1, 0, 1, 0 }
        };

        Graph1Coloring1 graphColoring = new Graph1Coloring1();
        graphColoring.printSolution();
        graphColoring.printSolution();
    }
}
