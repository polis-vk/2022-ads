//https://www.eolymp.com/ru/submissions/12381028
import java.util.Arrays;
import java.util.Scanner;

public class MinimumSpanningTree {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            int n = in.nextInt();
            int m = in.nextInt();
            Graph graph = new Graph(n, m);
            for (int i = 0; i < m; i++) {
                graph.edge[i] = new Graph.Edge(in.nextInt(), in.nextInt(), in.nextInt());
            }
            System.out.println(graph.kruskal());
        }
    }

    private static class Graph {
        record Edge(int src, int dest, int weight) implements Comparable<Edge> {

            @Override
            public int compareTo(Edge that) {
                return this.weight - that.weight;
            }
        }

        private static class Subset {
            int parent;
            int rank;
        }

        private final int vertices;
        private final Edge[] edge;

        Graph(int v, int e) {
            vertices = v;
            edge = new Edge[e];
        }

        int kruskal() {
            Arrays.sort(edge);
            Subset[] subsets = new Subset[vertices + 1];
            for (int i = 0; i < vertices + 1; i++) {
                subsets[i] = new Subset();
            }
            for (int i = 0; i < vertices; i++) {
                subsets[i].parent = i;
                subsets[i].rank = 0;
            }
            int index = 0;
            int sum = 0;
            int e = 0;
            while (e < vertices - 1) {
                Edge nextEdge = edge[index++];
                int x = find(subsets, nextEdge.src);
                int y = find(subsets, nextEdge.dest);
                if (x != y) {
                    e++;
                    sum += nextEdge.weight;
                    union(subsets, x, y);
                }
            }
            return sum;
        }

        private int find(Subset[] subsets, int i) {
            if (subsets[i].parent != i) {
                subsets[i].parent = find(subsets, subsets[i].parent);
            }
            return subsets[i].parent;
        }

        private void union(Subset[] subsets, int x, int y) {
            int xroot = find(subsets, x);
            int yroot = find(subsets, y);
            if (subsets[xroot].rank < subsets[yroot].rank) {
                subsets[xroot].parent = yroot;
            } else if (subsets[xroot].rank > subsets[yroot].rank) {
                subsets[yroot].parent = xroot;
            } else {
                subsets[yroot].parent = xroot;
                subsets[xroot].rank++;
            }
        }
    }
}