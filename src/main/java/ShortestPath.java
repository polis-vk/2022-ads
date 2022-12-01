//https://www.eolymp.com/ru/submissions/12304600
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class ShortestPath {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            int n = in.nextInt();
            int m = in.nextInt();
            int a = in.nextInt();
            int b = in.nextInt();
            Graph graph = new Graph(n);
            for (int i = 0; i < m; i++) {
                graph.addEdge(in.nextInt(), in.nextInt());
            }
            Stack<Integer> path = graph.bfs(a, b);
            if (path.size() == 0) {
                System.out.println(-1);
            } else {
                System.out.println(path.size() - 1);
                while (!path.isEmpty()) {
                    System.out.print(path.pop() + " ");
                }
            }
        }
    }

    private static class Graph {
        private final List<Integer>[] adjacencyList;

        Graph(int vertex) {
            adjacencyList = new LinkedList[vertex + 1];
            for (int i = 0; i < vertex + 1; i++) {
                adjacencyList[i] = new LinkedList<>();
            }
        }

        void addEdge(int start, int finish) {
            adjacencyList[start].add(finish);
            adjacencyList[finish].add(start);
        }

        Stack<Integer> bfs(int start, int end) {
            ArrayList<Integer> visited = new ArrayList<>();
            for (int i = 0; i < adjacencyList.length; i++) {
                visited.add(0);
            }
            ArrayList<Integer> parent = new ArrayList<>();
            for (int i = 0; i < adjacencyList.length; i++) {
                parent.add(-1);
            }
            Queue<Integer> q = new ArrayDeque<>();
            q.add(start);
            visited.set(start, 1);
            while (!q.isEmpty()) {
                int current = q.poll();
                for (int neighbour : adjacencyList[current]) {
                    if (visited.get(neighbour) == 0) {
                        visited.set(neighbour, 1);
                        q.add(neighbour);
                        parent.set(neighbour, current);
                    }
                }
            }
            if (parent.get(end) == -1) {
                return new Stack<>();
            }
            Stack<Integer> path = new Stack<>();
            while (parent.get(end) != -1) {
                path.add(end);
                end = parent.get(end);
            }
            path.add(start);
            return path;
        }
    }
}