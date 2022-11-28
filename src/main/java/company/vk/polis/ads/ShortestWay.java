package company.vk.polis.ads;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class ShortestWay {
    private static final int DEFAULT_VALUE = -1;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();
        List<List<Integer>> graph = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int left = in.nextInt();
            int right = in.nextInt();
            graph.get(left).add(right);
            graph.get(right).add(left);
        }
        int[] parent = new int[n + 1];
        int[] distance = new int[n + 1];
        Arrays.fill(parent, DEFAULT_VALUE);
        Arrays.fill(distance, DEFAULT_VALUE);
        distance[a] = 0;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(a);
        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();
            for (int currentChild : graph.get(currentVertex)) {
                if (distance[currentChild] == DEFAULT_VALUE) {
                    distance[currentChild] = distance[currentVertex] + 1;
                    queue.add(currentChild);
                    parent[currentChild] = currentVertex;
                }
            }
        }
        System.out.println(distance[b]);
        if (parent[b] == DEFAULT_VALUE) {
            return;
        }

        StringBuilder path = new StringBuilder();
        int currentVertex = b;
        while (currentVertex != a) {
            path.append(currentVertex).append(" ");
            currentVertex = parent[currentVertex];
        }
        path.append(a);
        System.out.println(path.reverse());
    }
}

