package company.vk.polis.ads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FordBellman {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();

        List<Edge> edges = new ArrayList<>(m);
        for (int i = 0; i < m; ++i) {
            int left = in.nextInt();
            int right = in.nextInt();
            int weight = in.nextInt();
            edges.add(new Edge(left, right, weight));
        }

        int [] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[1] = 0;

        for (int i = 0; i < n; i++) {
            for(Edge edge : edges) {
                if (distance[edge.left] != Integer.MAX_VALUE) {
                    distance[edge.right] = Math.min(distance[edge.left] + edge.weight, distance[edge.right]);
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            int result = distance[i] == Integer.MAX_VALUE ? 30000 : distance[i];
            System.out.print(result + " ");
        }
    }

    private static class Edge  {
        public int left;
        public int right;
        public int weight;

        public Edge(int left, int right, int weight) {
            this.left = left;
            this.right = right;
            this.weight = weight;
        }
    }
}
