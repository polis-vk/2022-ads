package company.vk.polis.ads;

import java.util.*;

//Кратчайший путь - Done
//https://www.eolymp.com/ru/submissions/12321929
public class Main {

    private static void bfs(List<ArrayList<Integer>> graph, int start, int[] dist, int[] par) {
        dist[start] = 0;
        Deque<Integer> q = new ArrayDeque<>();
        q.add(start);
        while (!q.isEmpty()) {
            int v = q.poll();
            for (int to : graph.get(v)) {
                if (dist[to] == -1) {
                    q.add(to);
                    dist[to] = dist[v] + 1;
                    par[to] = v;
                }
            }
        }
    }

    private static void initializationgNodesOfGraph(List<ArrayList<Integer>> graph, int vertexes) {
        for (int i = 0; i <= vertexes; i++) {
            graph.add(new ArrayList<>());
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int vertexes = in.nextInt(), edges = in.nextInt();
        int a = in.nextInt(), b = in.nextInt();
        List<ArrayList<Integer>> graph = new ArrayList<>(vertexes + 2);
        List<Integer> path = new ArrayList<>();
        int[] par = new int[vertexes + 2];
        int[] dist = new int[vertexes + 2];
        Arrays.fill(dist, -1);
        initializationgNodesOfGraph(graph, vertexes);

        for (int i = 0; i < edges; i++) {
            int start = in.nextInt();
            int end = in.nextInt();
            graph.get(start).add(end);
            graph.get(end).add(start);
        }

        bfs(graph,a, dist, par);

        if (par[b] != 0) {
            System.out.println(dist[b]);
            path.add(b);
            while (par[b] != 0) {
                b = par[b];
                path.add(b);
            }
            Collections.reverse(path);
            for (Integer nodes : path) {
                System.out.print(nodes + " ");
            }
        } else {
            System.out.println("-1");
        }
    }
}