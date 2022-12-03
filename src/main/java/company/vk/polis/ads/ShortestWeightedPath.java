import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class ShortestWeightedPath {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int s = in.nextInt();
        int f = in.nextInt();
        List<Edge>[] graphData = new List[n + 1];
        for (int i = 1; i < n + 1; i++) {
            graphData[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int firstTop = in.nextInt();
            int secondTop = in.nextInt();
            int length = in.nextInt();
            graphData[firstTop].add(new Edge(secondTop, length));
            graphData[secondTop].add(new Edge(firstTop, length));
        }

        int[] parent = new int[n + 1];
        int[] dist = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        int length = bfs(graphData, s, f, parent, dist);
        System.out.println(length);
        int top = f;
        Deque<Integer> deque = new LinkedList<>();
        while (top != 0) {
            deque.addFirst(top);
            top = parent[top];
        }
        while (!deque.isEmpty()) {
            System.out.print(deque.poll() + " ");
        }
    }

    public static int bfs(List<Edge>[] graphData, int fromTop, int toTop, int[] parent, int[] dist) {
        Queue<Integer> tops = new LinkedList<>();
        dist[fromTop] = 0;
        tops.offer(fromTop);
        while (!tops.isEmpty()) {
            int top = tops.poll();
            for (Edge edge : graphData[top]) {
                if (dist[edge.toTop] > dist[top] + edge.weight) {
                    parent[edge.toTop] = top;
                    dist[edge.toTop] = dist[top] + edge.weight;
                    tops.offer(edge.toTop);
                }
            }
        }
        return dist[toTop];
    }

    private static class Edge {
        int toTop;
        int weight;

        public Edge(int toTop, int weight) {
            this.toTop = toTop;
            this.weight = weight;
        }
    }
}
