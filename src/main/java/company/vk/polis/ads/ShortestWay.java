package company.vk.polis.ads;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class ShortestWay {
    private static class Edge implements Comparable<Edge>  {
        public int vertex;
        public int weight;

        public Edge(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            if (weight != other.weight) {
                return weight - other.weight;
            }
            return vertex - other.vertex;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int s = in.nextInt();
        int f = in.nextInt();
        int[][] graph = new int[n + 1][n + 1];
        for (int[] weight : graph) {
            Arrays.fill(weight, Integer.MAX_VALUE);
        }
        for (int i = 0; i < m; i++) {
            int left = in.nextInt();
            int right = in.nextInt();
            int weight = in.nextInt();
            graph[left][right] = weight;
            graph[right][left] = weight;
        }
        int[] parent = new int[n + 1];
        int[] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[s] = 0;
        Arrays.fill(parent, -1);
        PriorityQueue<Edge> container = new PriorityQueue<>();
        container.add(new Edge(s, 0));
        while (!container.isEmpty()) {
            Edge currentEdge = container.poll();
            if (currentEdge.weight > distance[currentEdge.vertex]) {
                continue;
            }
            for (int i = 1; i < n + 1; i++) {
                if (graph[currentEdge.vertex][i] != Integer.MAX_VALUE) {
                    int newDist = distance[currentEdge.vertex] + graph[currentEdge.vertex][i];
                    if (distance[i] > newDist) {
                        distance[i] = newDist;
                        parent[i] = currentEdge.vertex;
                        container.add(new Edge(i, distance[i]));
                    }
                }
            }
        }
        if (distance[f] == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }
        LinkedList<Integer> result = new LinkedList<>();
        for (int currentVertex = f; currentVertex != -1; currentVertex = parent[currentVertex]) {
            result.addFirst(currentVertex);
        }
        System.out.println(distance[f]);
        for (int vertex: result) {
            System.out.print(vertex + " ");
        }
    }
}
