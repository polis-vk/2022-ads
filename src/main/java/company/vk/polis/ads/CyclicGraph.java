package company.vk.polis.ads;

import java.util.*;

public class CyclicGraph {
    public static class Graph {
        private final ArrayList<ArrayList<Integer>> adjacencyList;
        private final Colors[] visited;
        private final int[] parent;
        private final int[] cyclePassed;
        private int minVertex;

        private enum Colors {
            WHITE,
            GRAY,
            BLACK
        }

        public Graph(int cntVertex) {
            adjacencyList = new ArrayList<>(cntVertex);
            parent = new int[cntVertex];
            cyclePassed = new int[cntVertex];

            for (int i = 1; i <= cntVertex; i++) {
                adjacencyList.add(new ArrayList<>());
            }

            visited = new Colors[cntVertex];
            Arrays.fill(visited, Colors.WHITE);

            minVertex = Integer.MAX_VALUE;
        }

        public void addVertex(int instanceVertex, int destinationVertex) {
            adjacencyList.get(instanceVertex).add(destinationVertex);
            adjacencyList.get(destinationVertex).add(instanceVertex);
        }

        public int getMinVertexInCycle(int cntVertex) {
            for (int i = 1; i < cntVertex; i++) {
                if (visited[i] == Colors.WHITE) {
                    parent[i] = -1;
                    DFS(i);
                }
            }

            return minVertex == Integer.MAX_VALUE ? -1 : minVertex;
        }

        private void DFS(int sourceVertex) {
            visited[sourceVertex] = Colors.GRAY;

            for (int pairVertex : adjacencyList.get(sourceVertex)) {
                if (visited[pairVertex] == Colors.WHITE) {
                    parent[pairVertex] = sourceVertex;
                    DFS(pairVertex);
                } else if (visited[pairVertex] == Colors.GRAY && pairVertex != parent[sourceVertex]) {
                    int currentNode = sourceVertex;
                    while (currentNode != pairVertex && cyclePassed[currentNode] == 0) {
                        cyclePassed[currentNode] = 1;
                        minVertex = Math.min(minVertex, currentNode);
                        currentNode = parent[currentNode];
                    }
                    cyclePassed[pairVertex] = 1;
                    minVertex = Math.min(minVertex, pairVertex);
                }
            }

            visited[sourceVertex] = Colors.BLACK;
        }

        public int getMinVertex() {
            return minVertex;
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

        int result = graph.getMinVertexInCycle(cntVertex);

        if (result == -1) {
            System.out.println("No");
            return;
        } else {
            System.out.println("Yes");
            System.out.println(result);
        }
    }
}
