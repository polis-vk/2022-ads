package company.vk.polis.ads;

import java.util.*;

public class ShortestPath {
    public static class Graph {
        private final ArrayList<ArrayList<Integer>> adjacencyList;
        private final Queue<Integer> queue = new ArrayDeque<>();
        private final Colors[] visited;
        private int[] parentArray;
        private int[] distance;

        private enum Colors {
            WHITE,
            RED
        }

        public Graph(int cntVertex) {
            adjacencyList = new ArrayList<>(cntVertex);

            for (int i = 1; i <= cntVertex; i++) {
                adjacencyList.add(new ArrayList<>());
            }

            visited = new Colors[cntVertex];
            Arrays.fill(visited, Colors.WHITE);

            parentArray = new int[cntVertex];
            Arrays.fill(parentArray, -1);

            distance = new int[cntVertex];
            Arrays.fill(distance, -1);
        }

        public void addVertex(int instanceVertex, int destinationVertex) {
            adjacencyList.get(instanceVertex).add(destinationVertex);
            adjacencyList.get(destinationVertex).add(instanceVertex);
        }

        public int getDistance(int index) {
            return distance[index];
        }

        public int getParent(int index) {
            return parentArray[index];
        }

        private void bfs(int sourceVertex) {
            queue.add(sourceVertex);
            visited[sourceVertex] = Colors.RED;

            while (!queue.isEmpty()) {
                int currentNode = queue.poll();
                for (int vertex : adjacencyList.get(currentNode)) {
                    if (visited[vertex] == Colors.WHITE) {
                        parentArray[vertex] = currentNode;
                        distance[vertex] = distance[currentNode] + 1;
                        visited[vertex] = Colors.RED;
                        queue.add(vertex);
                    }
                }
            }
        }
    }

    public static void main(final String[] arg) {
        Scanner scanner = new Scanner(System.in);

        int cntVertex = scanner.nextInt() + 1;
        int cntEdges = scanner.nextInt();
        Graph graph = new Graph(cntVertex);

        int instancePath = scanner.nextInt();
        int destinationPath = scanner.nextInt();

        for (int i = 0; i < cntEdges; i++) {
            int leftValue = scanner.nextInt();
            int rightValue = scanner.nextInt();
            graph.addVertex(leftValue, rightValue);
        }

        graph.distance[instancePath] = 0;
        graph.bfs(instancePath);

        int shortestPathSize = graph.getDistance(destinationPath);

        if (shortestPathSize == -1) {
            System.out.println(shortestPathSize);
            return;
        }

        StringBuilder resultPath = new StringBuilder();

        int currentNode = destinationPath;

        while (instancePath != currentNode) {
            resultPath.append(currentNode).append(" ");
            currentNode = graph.getParent(currentNode);
        }

        resultPath.append(currentNode);

        System.out.println(shortestPathSize);
        System.out.println(resultPath.reverse());
    }
}
