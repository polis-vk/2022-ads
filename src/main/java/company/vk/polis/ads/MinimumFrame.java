package company.vk.polis.ads;

import java.util.*;

public class MinimumFrame {
    private static class Edge implements Comparable<Edge> {
        private int from;
        private int to;
        private int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return weight - o.weight;
        }
    }

    public static class Kruskal {
        private final int[] rank;
        private final int[] s;

        public Kruskal(int cntVertex) {
            rank = new int[cntVertex];
            s = new int[cntVertex];

            for (int i = 0; i < cntVertex; i++) {
                s[i] = i;
            }

            Arrays.fill(rank, 1, cntVertex, 1);
        }

        public int functionKruskal(Edge[] edges) {
            int weight = 0;

            for (Edge edge: edges) {
                int left = find(edge.from);
                int right = find(edge.to);
                if (left != right) {
                    union(left, right);
                    weight += edge.weight;
                }
            }

            return weight;
        }

        public void union(int left, int right) {
            if (rank[left] < rank[right]) {
                s[left] = right;
            } else {
                s[right] = left;
            }

            if (rank[left] == rank[right]) {
                rank[left] += 1;
            }
        }

        public int find(int x) {
            if (s[x] == x) {
                return x;
            } else {
                return s[x] = find(s[x]);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int cntVertex = scanner.nextInt() + 1;
        int cntEdges = scanner.nextInt();

        Edge[] edges = new Edge[cntEdges];

        for (int i = 0; i < cntEdges; i++) {
            edges[i] = new Edge(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
        }

        Arrays.sort(edges);

        Kruskal kruskal = new Kruskal(cntVertex);
        System.out.println(kruskal.functionKruskal(edges));
    }
}
