package company.vk.polis.ads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TopologicalSort {
    private static class Graph {
        private final List<List<Integer>> vertexes;
        private final Color[] visited;
        private final List<Integer> sortedArray = new ArrayList<>();
        private boolean looped;

        public Graph(int capacity) {
            vertexes = new ArrayList<>(capacity + 1);
            for (int i = 0; i <= capacity; i++) {
                vertexes.add(new ArrayList<>());
            }
            visited = new Color[capacity + 1];
            Arrays.fill(visited, Color.WHITE);
        }

        public void addVertex(int left, int right) {
            vertexes.get(left).add(right);
        }

        public List<Integer> getTopologicalSortedArray() {
            for (int i = 1; i < vertexes.size(); i++) {
                if (visited[i] == Color.WHITE) {
                    dfs(i);
                }
                if (looped) {
                    return Collections.emptyList();
                }
            }
            Collections.reverse(sortedArray);
            return sortedArray;
        }


        private void dfs(int currentVertex) {
            visited[currentVertex] = Color.GRAY;
            for (int child : vertexes.get(currentVertex)) {
                if (visited[child] == Color.GRAY) {
                    looped = true;
                    return;
                }
                if (visited[child] == Color.WHITE) {
                    dfs(child);
                }
            }
            visited[currentVertex] = Color.BLACK;
            sortedArray.add(currentVertex);
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
        for (int i = 0; i < m; i++) {
            graph.addVertex(in.nextInt(), in.nextInt());
        }
        List<Integer> result = graph.getTopologicalSortedArray();
        if (result.isEmpty()) {
            System.out.println(-1);
            return;
        }
        for (Integer el : result) {
            System.out.println(el + " ");
        }
    }
}
