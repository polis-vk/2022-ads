//https://www.eolymp.com/ru/submissions/12320425
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class TopologicalSort {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            int n = in.nextInt();
            int m = in.nextInt();
            Graph graph = new Graph(n);
            for (int i = 0; i < m; i++) {
                graph.addEdge(in.nextInt(), in.nextInt());
            }
            Stack<Integer> sort = graph.topologicalSort();
            if (sort == null) {
                System.out.println("-1");
            } else {
                while (sort.size() > 0) {
                    System.out.print(sort.pop() + " ");
                }
            }
        }
    }

    private static class Graph {
        private final List<Integer>[] adjacencyList;
        private final int[] visited;

        Graph(int vertex) {
            adjacencyList = new LinkedList[vertex + 1];
            for (int i = 0; i < adjacencyList.length; i++) {
                adjacencyList[i] = new LinkedList<>();
            }
            visited = new int[adjacencyList.length];
        }

        void addEdge(int start, int finish) {
            adjacencyList[start].add(finish);
        }

        void dfs(int current, int parenta, Stack<Integer> sorted) {
            visited[current] = 1;
            for (int i : adjacencyList[current]) {
                if (visited[i] == 1 && i != parenta) {
                    return;
                }
                if (visited[i] == 0) {
                    dfs(i, current, sorted);
                }
            }
            visited[current] = 2;
            sorted.add(current);
        }

        Stack<Integer> topologicalSort() {
            final Stack<Integer> sorted = new Stack<>();
            for (int i = 1; i < adjacencyList.length; i++) {
                if (visited[i] == 0) {
                    dfs(i, 0, sorted);
                }
            }
            return sorted.size() == adjacencyList.length - 1 ? sorted : null;
        }
    }
}