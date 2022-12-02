package company.vk.polis.ads;

import java.util.*;

//Циклы в графе - Done
//https://www.eolymp.com/ru/submissions/12346339
public class Task3 {

    private static final int MAX_NUMBER = 100001;

    private static void initializationNodesOfGraph(ArrayList<ArrayList<Integer>> nodes, int vertexes) {
        for (int i = 1; i <= vertexes; i++) {
            nodes.add(new ArrayList<>());
        }
    }

    private static void dfs(ArrayList<ArrayList<Integer>> nodes, int v, int[] arrayOfcolors, int[] p, boolean[] isLoopsInGraph) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.add(v);
        while (!stack.isEmpty()) {
            int first = stack.peekFirst();
            arrayOfcolors[first] = 1;
            boolean child = false;
            int checkSwitch = 0;

            for (Integer finish : nodes.get(first)) {
                if (p[first] != finish) {
                    if (arrayOfcolors[finish] == 0) {
                        p[finish] = first;
                        stack.addFirst(finish);
                        checkSwitch = 1;
                        break;
                    } else if (arrayOfcolors[finish] == 1) {
                        isLoopsInGraph[finish] = true;
                        int u = first;
                        if (!isLoopsInGraph[first]) {
                            while (u != finish) {
                                isLoopsInGraph[u] = true;
                                u = p[u];
                                if (isLoopsInGraph[u]) {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            if (checkSwitch == 0) {
                arrayOfcolors[first] = 2;
                stack.removeFirst();
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int vertexes = in.nextInt(), edges = in.nextInt();
        int min = MAX_NUMBER;
        int[] arrayOfcolors = new int[vertexes];
        int[] p = new int[vertexes];
        boolean[] isLoopsInGraph = new boolean[vertexes];
        ArrayList<ArrayList<Integer>> nodes = new ArrayList<>();

        initializationNodesOfGraph(nodes, vertexes);

        for (int i = 0; i < edges; i++) {
            int startNode = in.nextInt();
            int finishNode = in.nextInt();
            nodes.get(startNode - 1).add(finishNode - 1);
            nodes.get(finishNode - 1).add(startNode - 1);
        }

        for (int i = 0; i < vertexes; i++) {
            if (arrayOfcolors[i] == 0) {
                dfs(nodes, i, arrayOfcolors, p, isLoopsInGraph);
            }
        }

        for (int i = 0; i < vertexes; i++) {
            if (isLoopsInGraph[i] && min > i) {
                min = i;
            }
        }

        if (min != MAX_NUMBER) {
            System.out.println("Yes");
            System.out.println(min + 1);
        } else {
            System.out.println("No");
        }
    }
}