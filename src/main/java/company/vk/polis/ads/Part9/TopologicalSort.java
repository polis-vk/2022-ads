package company.vk.polis.ads;

import java.util.*;

//TOPOLOGICAL SORT -> Done
//https://www.eolymp.com/ru/submissions/12348328
public class Task2 {

    private static final int NO_SORT = -1;

    public static void sort(int pos, List<ArrayList<Integer>> graph, List<Integer> sortNodes, int[] arrayOfColors) {
        if (arrayOfColors[pos] == 1) {
            sortNodes.add(NO_SORT);
        }
        if (arrayOfColors[pos] == 0) {
            arrayOfColors[pos] = 1;
            for (int i : graph.get(pos)) {
                sort(i, graph, sortNodes, arrayOfColors);
            }
            sortNodes.add(pos);
            arrayOfColors[pos] = 2;
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
            sort(i, graph, sortNodes, arrayOfColors);
        }

        if (sortNodes.contains(NO_SORT)) {
            System.out.println(NO_SORT);
        } else {
            Collections.reverse(sortNodes);
            for (Integer element : sortNodes) {
                System.out.print(element + " ");
            }
        }
    }
}
