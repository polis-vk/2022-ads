package company.vk.polis.ads;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

class Edge implements Comparable<Edge>{
    int to;
    int dist;

    Edge(int to, int dist) {
        this.to = to;
        this.dist = dist;
    }

    @Override
    public int compareTo(Edge e) {
        return dist - e.dist;
    }
}

public final class ShortestWay {
    private static void printWay(int vertex, int[] prev) {
        if (vertex == -1) {
            return;
        }
        printWay(prev[vertex], prev);
        System.out.print(vertex + 1);
        System.out.print(" ");
    }


    public static void main(final String[] arg) {
        Scanner in = new Scanner(System.in);
        int vertexes = in.nextInt();
        int edges = in.nextInt();
        int start = in.nextInt() - 1;
        int finish = in.nextInt() - 1;
        Edge[][] graph = new Edge[vertexes][vertexes];


        for (int i = 0; i < edges; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            int dist = in.nextInt();
            graph[from][to] = new Edge(to, dist);
            graph[to][from] = new Edge(from, dist);
        }

        int[] dist = new int[vertexes];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        int[] prev = new int[vertexes];
        Arrays.fill(prev, -1);

        PriorityQueue<Edge> heap = new PriorityQueue<>();
        heap.add(new Edge(start, 0));

        while (!heap.isEmpty()) {
            int from = heap.poll().to;
            for (Edge edge : graph[from]) {
                if (edge == null) {
                    continue;
                }
                int to = edge.to;
                int distance = edge.dist;
                if (dist[from] + distance < dist[to]) {
                    dist[to] = dist[from] + distance;
                    heap.add(new Edge(to, dist[to]));
                    prev[to] = from;
                }
            }
        }
        if (dist[finish] == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(dist[finish]);
            printWay(finish, prev);
        }
    }
}
