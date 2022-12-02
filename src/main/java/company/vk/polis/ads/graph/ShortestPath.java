package company.vk.polis.ads.graph;

import java.util.*;

public final class ShortestPath {
    static final int NOT_CROSSED = -1;
    static final int ORPHAN = -1;

    static final int OFFSET = 1;

    static ArrayList<Integer>[] graphList;
    static int[] distance;
    static int[] parent;

    static int start;
    static int finish;

    static void initGraph() {
        Scanner scanner = new Scanner(System.in);

        int vertexes = scanner.nextInt();
        int edges = scanner.nextInt();

        graphList = new ArrayList[vertexes];
        for (int i = 0; i < vertexes; i++) {
            graphList[i] = new ArrayList<>();
        }

        start = scanner.nextInt() - OFFSET;
        finish = scanner.nextInt() - OFFSET;

        distance = new int[vertexes];
        parent = new int[vertexes];
        Arrays.fill(distance, NOT_CROSSED);
        Arrays.fill(parent, ORPHAN);
        distance[start] = 0;

        for (int i = 0; i < edges; i++) {
            int from = scanner.nextInt() - OFFSET;
            int to = scanner.nextInt() - OFFSET;
            graphList[from].add(to);
            graphList[to].add(from); // not-oriented graph
        }
    }

    static void findShortestWay(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            for (int i = 0; i < graphList[vertex].size(); i++) {
                int to = graphList[vertex].get(i);
                if (distance[to] == NOT_CROSSED) {
                    queue.add(to);
                    distance[to] = distance[vertex] + 1;
                    parent[to] = vertex;
                }
            }
        }
    }

    static ArrayList<Integer> getWay(int finish) {
        ArrayList<Integer> path = new ArrayList<>();
        if (parent[finish] == ORPHAN) {
            path.add(ORPHAN);
        } else {
            path.add(finish);
            while (parent[finish] != ORPHAN) {
                finish = parent[finish];
                path.add(finish);
            }
        }
        return path;
    }

    public static void main(String[] args) {
        initGraph();
        findShortestWay(start);
        ArrayList<Integer> path = getWay(finish);

        if (path.get(0) == ORPHAN) {
            System.out.println(-1);
            return;
        }

        System.out.println(path.size() - 1);
        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.print(path.get(i) + 1);
            System.out.print(" ");
        }
    }
}  