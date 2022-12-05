//https://www.eolymp.com/ru/submissions/12383157
import java.util.Arrays;
import java.util.Scanner;

public class FordBellman {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            int n = in.nextInt();
            int m = in.nextInt();
            Graph graph = new Graph(n, m);
            for (int i = 0; i < m; i++) {
                graph.addEdge(i, in.nextInt(), in.nextInt(), in.nextInt());
            }
            graph.bellmanFord(1);
        }
    }

    private static class Graph {
        private final Edge[] edge;
        private final int vertexes;

        Graph(int vertexes, int edges) {
            this.vertexes = vertexes;
            edge = new Edge[edges + 1];
            for (int i = 0; i < edges + 1; i++) {
                edge[i] = new Edge(0, 0, 0);
            }
        }

        void addEdge(int i, int start, int finish, int weight) {
            edge[i] = new Edge(start, finish, weight);
        }

        void bellmanFord(int src) {
            int[] dist = new int[vertexes + 1];
            final int NOT_EXIST = 30_000;
            Arrays.fill(dist, NOT_EXIST);
            dist[src] = 0;
            for (int i = 0; i < vertexes - 1; i++) {
                for (Edge e : edge) {
                    if (dist[e.src] != NOT_EXIST && dist[e.src] + e.weight < dist[e.dest]) {
                        dist[e.dest] = dist[e.src] + e.weight;
                    }
                }
            }
            for (int i = 1; i < dist.length; i++) {
                System.out.print(dist[i] + " ");
            }
        }

        private static class Edge {
            public int src;
            public int dest;
            public int weight;

            public Edge(int src, int dest, int weight) {
                this.src = src;
                this.dest = dest;
                this.weight = weight;
            }
        }
    }
}