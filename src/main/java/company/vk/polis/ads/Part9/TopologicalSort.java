package company.vk.polis.ads;

import java.util.*;

//TOPOLOGICAL SORT -> Done
//https://www.eolymp.com/ru/submissions/12346305
public class Task2 {


    public static void sort(List<ArrayList<Integer>> graph, List<Integer> sortNodes, int a, int[] arrayOfColors) {
        if (arrayOfColors[a] == 1) {
            sortNodes.add(-1);
            return;
        }
        if (arrayOfColors[a] == 0) {
            arrayOfColors[a] = 1;
            for (int i : graph.get(a)) {
                sort(graph, sortNodes, i, arrayOfColors);
            }
            sortNodes.add(a);
            arrayOfColors[a] = 2;
        }
    }

    private static void initializationNodesOfGraph(int vertexes, List<ArrayList<Integer>> graph) {
        for (int i = 0; i <= vertexes; i++) {
            graph.add(new ArrayList<>());
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int vertexes = in.nextInt(), edges = in.nextInt();
        List<ArrayList<Integer>> graph = new ArrayList<>(vertexes + 1);
        int[] arrayOfColors = new int[vertexes + 1];
        List<Integer> sortNodes = new ArrayList<>();
        initializationNodesOfGraph(vertexes, graph);

        for (int i = 0; i < edges; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            graph.get(a).add(b);
        }

        for (int i = 1; i <= vertexes; i++) {
            sort(graph, sortNodes, i, arrayOfColors);
        }

        if (sortNodes.contains(-1)) {
            System.out.println(-1);
        } else {
            Collections.reverse(sortNodes);
            for (Integer element : sortNodes) {
                System.out.print(element + " ");
            }
        }
    }
}
