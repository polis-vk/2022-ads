package company.vk.polis.ads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Set;

public class CondGraph {
    private enum Color {
        WHITE,
        GRAY,
        BLACK
    }

    private static class Graph {
        private final List<List<Integer>> vertexes;
        private final List<List<Integer>> invertedVertexes;
        private final Color[] visited;
        private final List<Integer> topologicalSortedGraph = new ArrayList<>();
        private final int[] blocks;

        public Graph(int capacity) {
            int newCapacity = capacity + 1;
            vertexes = new ArrayList<>(newCapacity);
            invertedVertexes = new ArrayList<>(newCapacity);
            visited = new Color[newCapacity];
            Arrays.fill(visited, Color.WHITE);
            blocks = new int[newCapacity];
            for (int i = 0; i < newCapacity; i++) {
                vertexes.add(new ArrayList<>());
                invertedVertexes.add(new ArrayList<>());
            }
        }

        public void addVertex(int left, int right) {
            vertexes.get(left).add(right);
            invertedVertexes.get(right).add(left);
        }

        public void condense(int currentVertex, int currentBlock) {
            blocks[currentVertex] = currentBlock;
            visited[currentVertex] = Color.GRAY;
            for (int child : invertedVertexes.get(currentVertex)) {
                if (visited[child] == Color.WHITE) {
                    condense(child, currentBlock);
                }
            }
        }

        public List<Integer> getSortedGraph() {
            for (int i = 1; i < visited.length; i++) {
                if (visited[i] == Color.WHITE) {
                    dfs(i);
                }
            }
            Collections.reverse(topologicalSortedGraph);
            Arrays.fill(visited, Color.WHITE);
            return topologicalSortedGraph;
        }

        public Color getColorByIndex(int index) {
            if (index < 0 || index >= visited.length) {
                throw new IndexOutOfBoundsException();
            }
            return visited[index];
        }

        public void printAmountOfEdges() {
            int sumOfWays = getResultingGraph().stream()
                    .mapToInt(Set::size)
                    .sum();
            System.out.println(sumOfWays);
        }

        private void dfs(int currentVertex) {
            visited[currentVertex] = Color.GRAY;
            for (int child : vertexes.get(currentVertex)) {
                if (visited[child] == Color.WHITE) {
                    dfs(child);
                }
            }
            visited[currentVertex] = Color.BLACK;
            topologicalSortedGraph.add(currentVertex);
        }

        private List<Set<Integer>> getResultingGraph() {
            final List<Set<Integer>> resultingGraph = new ArrayList<>(visited.length);
            for (int i = 0; i < visited.length; i++) {
                resultingGraph.add(new HashSet<>());
            }
            for (int i = 1; i < visited.length; i++) {
                for (int adjacentVertex : invertedVertexes.get(i)) {
                    if (blocks[i] != blocks[adjacentVertex]) {
                        resultingGraph.get(blocks[adjacentVertex]).add(blocks[i]);
                    }
                }
            }
            return resultingGraph;
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
        int blocksCount = 1;
        for (int currentEl : graph.getSortedGraph()) {
            if (graph.getColorByIndex(currentEl) == Color.WHITE) {
                graph.condense(currentEl, blocksCount++);
            }
        }
        graph.printAmountOfEdges();
    }
}