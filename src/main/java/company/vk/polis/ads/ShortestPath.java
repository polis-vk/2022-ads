import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class ShortestPath {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();

        int a = in.nextInt();
        int b = in.nextInt();
        List<Integer>[] graphData = new List[n + 1];
        boolean[] visited = new boolean[n + 1];
        for (int i = 1; i < n + 1; i++) {
            graphData[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int firstTop = in.nextInt();
            int secondTop = in.nextInt();
            graphData[firstTop].add(secondTop);
            graphData[secondTop].add(firstTop);
        }

        int[] parents = bsf(a, b, n, graphData);
        if (parents[b] == 0) {
            System.out.println(-1);
        } else {
            Deque<Integer> orderedParents = new LinkedList<>();
            int top = b;
            while (top != 0) {
                orderedParents.addFirst(top);
                top = parents[top];
            }
            System.out.println(orderedParents.size() - 1);
            while (!orderedParents.isEmpty()) {
                System.out.print(orderedParents.poll() + " ");
            }
        }
    }

    private static int[] bsf(int fromTop, int toTop, int n, List<Integer>[] graphData) {
        Queue<Integer> tops = new LinkedList<>();
        int[] parents = new int[n + 1];
        int[] dist = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            dist[i] = n + 1;
        }
        dist[fromTop] = 0;
        tops.offer(fromTop);
        while (!tops.isEmpty()) {
            int top = tops.poll();
            for (int neighborTop : graphData[top]) {
                if (dist[neighborTop] > dist[top] + 1) {
                    parents[neighborTop] = top;
                    dist[neighborTop] = dist[top] + 1;
                    tops.offer(neighborTop);
                }
            }
        }
        return parents;
    }
}
