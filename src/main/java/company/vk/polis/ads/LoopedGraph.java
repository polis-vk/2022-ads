package company.vk.polis.ads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LoopedGraph {
    private static final int NOT_EXISTS_VALUE = -1;

    private static class Graph {
        private final List<List<Integer>> vertexes;
        private final Color[] visited;
        private final boolean[] isLooped;
        private final int[] parent;
        private int minLoopedVertex = Integer.MAX_VALUE;

        public Graph(int capacity) {
            vertexes = new ArrayList<>(capacity + 1);
            for (int i = 0; i <= capacity; i++) {
                vertexes.add(new ArrayList<>());
            }
            visited = new Color[capacity + 1];
            Arrays.fill(visited, Color.WHITE);
            isLooped = new boolean[capacity + 1];
            parent = new int[capacity + 1];
        }

        public void addVertex(int left, int right) {
            vertexes.get(left).add(right);
            vertexes.get(right).add(left);
        }

        public int getLoopMinIndex() {
            for (int i = 1; i < vertexes.size(); i++) {
                if (visited[i] == Color.WHITE) {
                    parent[i] = NOT_EXISTS_VALUE;
                    dfs(i);
                }
            }
            Arrays.fill(visited, Color.WHITE);
            return minLoopedVertex == Integer.MAX_VALUE ? -1 : minLoopedVertex;
        }

        private void dfs(int currentVertex) {
            visited[currentVertex] = Color.GRAY;
            for (int currentChild : vertexes.get(currentVertex)) {
                if (visited[currentChild] == Color.WHITE) {
                    parent[currentChild] = currentVertex;
                    dfs(currentChild);
                } else if (visited[currentChild] == Color.GRAY && currentChild != parent[currentVertex]) {
                    for (int currentLoopElement = currentVertex; currentLoopElement != currentChild && !isLooped[currentLoopElement]; currentLoopElement = parent[currentLoopElement]) {
                        isLooped[currentLoopElement] = true;
                        minLoopedVertex = Math.min(minLoopedVertex, currentLoopElement);
                    }
                    isLooped[currentChild] = true;
                    minLoopedVertex = Math.min(minLoopedVertex, currentChild);
                }
            }
            visited[currentVertex] = Color.BLACK;
        }

        private enum Color {
            WHITE,
            GRAY,
            BLACK
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        Graph graph = new Graph(n);
        for (int i = 0; i < m; ++i) {
            graph.addVertex(in.nextInt(), in.nextInt());
        }
        int minIndex = graph.getLoopMinIndex();
        if (minIndex == NOT_EXISTS_VALUE) {
            System.out.println("No");
            return;
        }
        System.out.println("Yes");
        System.out.println(minIndex);
    }
}