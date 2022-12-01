//https://www.eolymp.com/ru/submissions/12320342

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class CycleInGraph {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            int n = in.nextInt();
            int k = in.nextInt();
            Graph graph = new Graph(n);
            for (int i = 0; i < k; i++) {
                graph.addEdge(in.nextInt(), in.nextInt());
            }
            Integer min = graph.cycleMin();
            if (min == null) {
                System.out.println("No");
            } else {
                System.out.println("Yes");
                System.out.print(min);
            }
        }
    }

    private static class Graph {
        private final List<Integer>[] adjacencyList;
        private final int[] visited;
        private final int[] parent;
        private final byte[] cycle;

        Graph(int vertex) {
            adjacencyList = new LinkedList[vertex + 1];
            for (int i = 0; i < adjacencyList.length; i++) {
                adjacencyList[i] = new LinkedList<>();
            }
            visited = new int[adjacencyList.length];
            parent = new int[adjacencyList.length];
            cycle = new byte[adjacencyList.length];
        }

        void addEdge(int start, int finish) {
            adjacencyList[start].add(finish);
            adjacencyList[finish].add(start);
        }

        private int cycleCount = 0;

        void dfs(int current, int parenta) {
            parent[current] = parenta;
            visited[current] = 1;
            for (int i : adjacencyList[current]) {
                if (visited[i] == 1 && i != parenta) {
                    cycleCount++;
                    cycle[i] = 1;
                    int cur = current;
                    while (cycle[cur] == 0) {
                        cycle[cur] = 1;
                        cur = parent[cur];
                    }
                }
                if (visited[i] == 0) {
                    dfs(i, current);
                }
            }
            visited[current] = 2;
        }

        Integer cycleMin() {
            for (int i = 0; i < adjacencyList.length; i++) {
                if (visited[i] == 0) {
                    dfs(i, 0);
                }
            }
            if (cycleCount == 0) {
                return null;
            }
            for (int i = 1; i < cycle.length; i++) {
                if (cycle[i] == 1) {
                    return i;
                }
            }
            return null;
        }
    }
}
/*
5 4
1 2
2 4
1 3
3 5

No
 */