public class GraphColoring {
    private int[][] graph;
    private int[] colors;
    private int numColors;
    private int numVertices;

    public GraphColoring(int[][] graph, int numColors) {
        this.graph = graph;
        this.numColors = numColors;
        this.numVertices = graph.length;
        this.colors = new int[numVertices];
    }

    public static void main(String[] args) {
        // empty method
    }

    public boolean isSafe(int v, int c) {
        for (int i = 0; i < numVertices; i++) {
            if (graph[v][i] == 1 && colors[i] == c) {
                return false;
            }
        }
        return true;
    }

    public boolean solve(int v) {
        if (v == numVertices) {
            return true;
        }
        for (int c = 1; c <= numColors; c++) {
            if (isSafe(v, c)) {
                colors[v] = c;
                if (solve(v + 1)) {
                    return true;
                }
                colors[v] = 0;
            }
        }
        return false;
    }

    public void printSolution() {
        for (int i = 0; i < numVertices; i++) {
            System.out.println("Vertex " + i + " color: " + colors[i]);
        }
    }
}
