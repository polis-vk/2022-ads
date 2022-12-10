package company.vk.polis.ads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class FordBelman {
    public static class Graph {
        private class Node {
            private int child;
            private int weight;

            public Node(int child, int weight) {
                this.child = child;
                this.weight = weight;
            }
        }

        private final ArrayList<ArrayList<Node>> adjacencyList;
        private int[] distance;

        public Graph(int cntVertex) {
            adjacencyList = new ArrayList<>(cntVertex);

            for (int i = 1; i <= cntVertex; i++) {
                adjacencyList.add(new ArrayList<>());
            }

            distance = new int[cntVertex];
            Arrays.fill(distance, 30000);
        }

        public void addVertex(int instanceVertex, int destinationVertex, int weight) {
            adjacencyList.get(instanceVertex).add(new Node(destinationVertex, weight));
        }


        public void functionFordBellman() {
            distance[1] = 0;
            PriorityQueue<Integer> queue = new PriorityQueue<>();
            queue.offer(1);

            while (!queue.isEmpty()) {
                int currentVertex = queue.poll();
                for (Node child : adjacencyList.get(currentVertex)) {
                    int valueEdge = child.weight + distance[currentVertex];
                    if (valueEdge < distance[child.child]) {
                        distance[child.child] = valueEdge;
                        queue.offer(child.child);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int cntVertex = scanner.nextInt() + 1;
        int cntEdges = scanner.nextInt();

        Graph graph = new Graph(cntVertex);

        for (int i = 0; i < cntEdges; i++) {
            graph.addVertex(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
        }

        graph.functionFordBellman();

        for (int i = 1; i < graph.distance.length; i++) {
            System.out.print(graph.distance[i] + " ");
        }
    }
}
