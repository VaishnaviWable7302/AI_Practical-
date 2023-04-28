import java.util.*;

public class PrimsAlgorithm {
    private static final int INF = Integer.MAX_VALUE;
    private int n; // number of vertices
    private List<List<Edge>> adj; // adjacency list representation of graph

    public PrimsAlgorithm(int n) {
        this.n = n;
        adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v, int w) {
        adj.get(u).add(new Edge(v, w));
        adj.get(v).add(new Edge(u, w));
    }

    public int prims(int source) {
        int mstCost = 0;
        boolean[] visited = new boolean[n];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(source, 0));
        while (!pq.isEmpty()) {
            Edge e = pq.poll();
            int u = e.to;
            if (visited[u]) {
                continue;
            }
            visited[u] = true;
            mstCost += e.weight;
            for (Edge v : adj.get(u)) {
                if (!visited[v.to]) {
                    pq.add(v);
                }
            }
        }
        return mstCost;
    }

    private static class Edge implements Comparable<Edge> {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(weight, other.weight);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        PrimsAlgorithm graph = new PrimsAlgorithm(n);
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            graph.addEdge(u, v, w);
        }
        int source = sc.nextInt();
        int mstCost = graph.prims(source);
        System.out.println(mstCost);
    }
}
