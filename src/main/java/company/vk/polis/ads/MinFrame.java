package company.vk.polis.ads;

import java.util.*;

public final class MinFrame {
    public static void main(final String[] arg) {
        Scanner in = new Scanner(System.in);
        int vertexes = in.nextInt();
        int edges = in.nextInt();

        List<Edge>[] graph = new ArrayList[vertexes];
        for(int i = 0; i < vertexes; i++){
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < edges; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            int dist = in.nextInt();
            graph[from].add(new Edge(to, dist));
            graph[to].add(new Edge(from, dist));
        }

        int[] dist = new int[vertexes];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        boolean[] used = new boolean[vertexes];

        TreeSet<Edge> heap = new TreeSet<>();
        heap.add(new Edge(0, 0));

        while (!heap.isEmpty()) {
            int from = heap.pollFirst().to;
            for (Edge edge : graph[from]) {
                int to = edge.to;
                int distance = edge.dist;
                if (!used[to] && distance < dist[to]) {
                    heap.remove(new Edge(to, dist[to]));
                    dist[to] = distance;
                    heap.add(new Edge(to, dist[to]));
                }
            }
            used[from] = true;
        }


        int weight = 0;
        for(int i = 0; i < vertexes; i++){
            weight += dist[i];
        }

        System.out.println(weight);
    }
}