//https://www.eolymp.com/ru/submissions/12353401
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class GraphCondensation {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            int n = in.nextInt();
            int k = in.nextInt();
            Graph graph = new Graph(n);
            for (int i = 0; i < k; i++) {
                graph.addEdge(in.nextInt(), in.nextInt());
            }
            System.out.println(graph.SCCs());
        }
    }

    private static class Graph {
        private final List<Integer>[] adjacencyList;
        private final List<Integer>[] transposedList;
        private final int[] visited;
        private final int[] components;

        Graph(int vertex) {
            adjacencyList = new LinkedList[vertex + 1];
            transposedList = new LinkedList[vertex + 1];
            for (int i = 0; i < adjacencyList.length; i++) {
                adjacencyList[i] = new LinkedList<>();
                transposedList[i] = new LinkedList<>();
            }
            visited = new int[adjacencyList.length];
            components = new int[adjacencyList.length];
        }

        void addEdge(int start, int finish) {
            adjacencyList[start].add(finish);
            transposedList[finish].add(start);
        }

        private void dfs(List<Integer>[] graph, int startVertex, int componentId) {
            visited[startVertex] = 1;
            components[startVertex] = componentId;
            for (int neighbourVertex : graph[startVertex]) {
                if (visited[neighbourVertex] == 0) {
                    dfs(graph, neighbourVertex, componentId);
                }
            }
        }

        void fillOrder(int startVertex, Stack<Integer> sortedVertex) {
            visited[startVertex] = 1;
            for (int i : adjacencyList[startVertex]) {
                if (visited[i] == 0) {
                    fillOrder(i, sortedVertex);
                }
            }
            sortedVertex.push(startVertex);
        }

        private Set<Integer>[] condensation(int n) {
            Set<Integer>[] condensationGraph = new HashSet[n];
            for (int i = 0; i < n; i++) {
                condensationGraph[i] = new HashSet<>();
            }
            for (int i = 1; i < adjacencyList.length; i++) {
                for (int j : adjacencyList[i]) {
                    if (components[i] != components[j]) {
                        condensationGraph[components[i]].add(components[j]);
                    }
                }
            }
            return condensationGraph;
        }

        Integer SCCs() {
            Stack<Integer> stack = new Stack<>();
            for (int i = 1; i < adjacencyList.length; i++) {
                if (visited[i] == 0) {
                    fillOrder(i, stack);
                }
            }
            Arrays.fill(visited, 0);
            int count = 0;
            int current;
            for (int i = 1; i < adjacencyList.length; i++) {
                current = stack.pop();
                if (visited[current] == 0) {
                    count++;
                    dfs(transposedList, current, count);
                }
            }
            Set<Integer>[] condensationGraph = condensation(count);
            int sum = 0;
            for (int i = 0; i < count; i++) {
                sum += condensationGraph[i].size();
            }
            return sum;
        }
    }
}