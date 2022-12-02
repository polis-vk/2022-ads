package company.vk.polis.ads;

import java.util.*;

public class TopSort {
    public static class Graph {
        private final ArrayList<ArrayList<Integer>> adjacencyList;
        private final Deque<Integer> deque = new ArrayDeque<>();
        private final Colors[] visited;
        private boolean isCyclicPath;

        private enum Colors {
            WHITE,
            GRAY,
            BLACK
        }

        public Graph(int cntVertex) {
            adjacencyList = new ArrayList<>(cntVertex);

            for (int i = 1; i <= cntVertex; i++) {
                adjacencyList.add(new ArrayList<>());
            }

            visited = new Colors[cntVertex];
            Arrays.fill(visited, Colors.WHITE);

            isCyclicPath = false;
        }

        public void addVertex(int instanceVertex, int destinationVertex) {
            adjacencyList.get(instanceVertex).add(destinationVertex);
        }

        public void topSort(int cntVertex) {
            for (int i = 1; i < cntVertex; i++) {
                if (visited[i] == Colors.WHITE) {
                    DFS(i);
                }
            }
        }

        private void DFS(int sourceVertex) {
            if (isCyclicPath) {
                return;
            }

            visited[sourceVertex] = Colors.GRAY;

            for (int pairVertex : adjacencyList.get(sourceVertex)) {
                if (visited[pairVertex] == Colors.WHITE) {
                    DFS(pairVertex);
                } else if (visited[pairVertex] == Colors.GRAY) {
                    isCyclicPath = true;
                    return;
                }
            }

            visited[sourceVertex] = Colors.BLACK;
            deque.push(sourceVertex);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int cntVertex = scanner.nextInt() + 1;
        int cntEdges = scanner.nextInt();

        Graph graph = new Graph(cntVertex);

        for (int i = 0; i < cntEdges; i++) {
            int instanceVertex = scanner.nextInt();
            int destinationVertex = scanner.nextInt();
            graph.addVertex(instanceVertex, destinationVertex);
        }

        graph.topSort(cntVertex);

        if (graph.isCyclicPath) {
            System.out.println(-1);
        }

        while (!graph.deque.isEmpty()) {
            System.out.print(graph.deque.poll() + " ");
        }
    }
}
