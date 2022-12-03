import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class MinSkeleton {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();

        Queue<Edge> edges = new PriorityQueue<>(m);

        for (int i = 0; i < m; i++) {
            edges.add(new Edge(in.nextInt() - 1, in.nextInt() - 1, in.nextInt()));
        }

        int[] s = new int[n];
        for (int i = 0; i < n; i++) {
            s[i] = i;
        }
        int weight = 0;
        while (!edges.isEmpty()) {
            Edge edge = edges.poll();
            if (s[edge.fromTop] != s[edge.toTop]) {
                int mainS = s[edge.fromTop];
                int sToConcat = s[edge.toTop];
                for (int i = 0; i < n; i++) {
                    if (s[i] == sToConcat) {
                        s[i] = mainS;
                    }
                }
                weight += edge.weight;
            }
        }
        System.out.println(weight);
    }

    private static class Edge implements Comparable<Edge> {
        int fromTop;
        int toTop;
        int weight;

        public Edge(int fromTop, int toTop, int weight) {
            this.fromTop = fromTop;
            this.toTop = toTop;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return weight - o.weight;
        }
    }
}
