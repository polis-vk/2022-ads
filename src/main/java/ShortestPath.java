//https://www.eolymp.com/ru/submissions/12382734

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;

public class ShortestPath {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            int n = in.nextInt();
            int m = in.nextInt();
            int s = in.nextInt();
            int f = in.nextInt();
            Graph graph = new Graph(n);
            for (int i = 0; i < m; i++) {
                graph.addEdge(in.nextInt(), in.nextInt(), in.nextInt());
            }
            if (!graph.dijkstra(s, f)) {
                System.out.println("-1");
                return;
            }
            Stack<Integer> path = graph.restorePath(f);
            System.out.println(path.pop());
            while (!path.isEmpty()) {
                System.out.print(path.pop() + " ");
            }
        }
    }

    private static class Graph {
        private final List<Edge>[] adjacencyList;
        private final int[] settled;
        private final int[] dist;

        Graph(int vertex) {
            adjacencyList = new ArrayList[vertex + 1];
            for (int i = 1; i < vertex + 1; i++) {
                adjacencyList[i] = new ArrayList<>();
            }
            settled = new int[vertex + 1];
            dist = new int[vertex + 1];
        }

        void addEdge(int start, int finish, int cost) {
            adjacencyList[start].add(new Edge(finish, cost));
            adjacencyList[finish].add(new Edge(start, cost));
        }

        boolean dijkstra(int src, int dest) {
            Arrays.fill(dist, Integer.MAX_VALUE);
            final PriorityQueue<Integer> pq = new PriorityQueue<>();
            pq.add(src);
            dist[src] = 0;
            while (!pq.isEmpty()) {
                int v = pq.poll();
                for (Edge e : adjacencyList[v]) {
                    int w = dist[v] + e.weight;
                    if (dist[e.vertex] > w) {
                        dist[e.vertex] = w;
                        settled[e.vertex] = v;
                        pq.add(e.vertex);
                    }
                }
            }
            return settled[dest] != 0;
        }

        Stack<Integer> restorePath(int dest) {
            Stack<Integer> path = new Stack<>();
            path.add(dest);
            int currVertex = dest;
            while (settled[currVertex] != 0) {
                currVertex = settled[currVertex];
                path.add(currVertex);
            }
            path.add(dist[dest]);
            return path;
        }

        private static class Edge {
            public int vertex;
            public int weight;

            public Edge(int vertex, int weight) {
                this.vertex = vertex;
                this.weight = weight;
            }

        }

    }
}