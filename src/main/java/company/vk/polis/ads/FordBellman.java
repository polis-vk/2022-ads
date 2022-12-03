import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class FordBellman {
    private static final int INF = 30_000;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        List<Edge>[] graphData = new List[n + 1];
        for (int i = 1; i < n + 1; i++) {
            graphData[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int fromTop = in.nextInt();
            int toTop = in.nextInt();
            int length = in.nextInt();
            graphData[fromTop].add(new Edge(toTop, length));
        }

        int[] dist = bfs(graphData, 1);
        for (int i = 1; i < dist.length; i++) {
            System.out.print(dist[i] + " ");
        }
    }

    private static int[] bfs(List<Edge>[] graphData, int fromTop) {
        Queue<Integer> tops = new LinkedList<>();
        int[] dist = new int[graphData.length];
        for (int i = 1; i < dist.length; i++) {
            dist[i] = INF;
        }
        dist[fromTop] = 0;
        for (int i = 0; i < graphData.length - 2; i++) {
            tops.offer(fromTop);
            while (!tops.isEmpty()) {
                int top = tops.poll();
                for (Edge neighborEdge : graphData[top]) {
                    if (dist[neighborEdge.toTop] > dist[top] + neighborEdge.length) {
                        dist[neighborEdge.toTop] = dist[top] + neighborEdge.length;
                        tops.offer(neighborEdge.toTop);
                    }
                }
            }
        }
        return dist;
    }

    private static class Edge {
        public int toTop;
        public int length;

        public Edge(int toTop, int length) {
            this.toTop = toTop;
            this.length = length;
        }
    }
}
