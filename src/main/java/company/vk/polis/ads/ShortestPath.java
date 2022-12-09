package company.vk.polis.ads;

import java.util.*;

public class ShortestPath {
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
        private int[] parent;

        public Graph(int cntVertex) {
            adjacencyList = new ArrayList<>(cntVertex);

            for (int i = 1; i <= cntVertex; i++) {
                adjacencyList.add(new ArrayList<>());
            }

            parent = new int[cntVertex];
            Arrays.fill(parent, -1);

            distance = new int[cntVertex];
            Arrays.fill(distance, Integer.MAX_VALUE);
        }

        public void addVertex(int instanceVertex, int destinationVertex, int weight) {
            adjacencyList.get(instanceVertex).add(new Node(destinationVertex, weight));
            adjacencyList.get(destinationVertex).add(new Node(instanceVertex, weight));
        }


        public void functionDijkstra(int sourceVertex) {
            distance[sourceVertex] = 0;
            PriorityQueue<Integer> queue = new PriorityQueue<>();
            queue.offer(sourceVertex);

            while (!queue.isEmpty()) {
                int currentVertex = queue.poll();
                for (Node child : adjacencyList.get(currentVertex)) {
                    int valueEdge = child.weight + distance[currentVertex];
                    if (valueEdge < distance[child.child]) {
                        distance[child.child] = valueEdge;
                        parent[child.child] = currentVertex;
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

        int instanceVertex = scanner.nextInt();
        int destinationVertex = scanner.nextInt();
        Graph graph = new Graph(cntVertex);

        for (int i = 0; i < cntEdges; i++) {
            graph.addVertex(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
        }

        graph.functionDijkstra(instanceVertex);

        if (graph.parent[destinationVertex] == -1) {
            System.out.println(-1);
            return;
        }

        System.out.println(graph.distance[destinationVertex]);


        Stack<Integer> path = new Stack<>();
        path.push(destinationVertex);
        int currentVertex = destinationVertex;
        while (currentVertex != instanceVertex) {
            currentVertex = graph.parent[currentVertex];
            path.push(currentVertex);
        }

        while (!path.isEmpty()) {
            System.out.print(path.pop() + " ");
        }
    }
}
