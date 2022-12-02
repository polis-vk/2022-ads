package company.vk.polis.ads.graph;

import java.util.ArrayList;
import java.util.Scanner;

public final class TopologicalSort {
    static final int OFFSET = 1;

    enum Colors {
        WHITE, GREY, BLACK
    }

    static ArrayList<Integer>[] graphList;
    static ArrayList<Integer> top = new ArrayList<>();
    static Colors[] isVisitedCol;
    static boolean isCycle = false;

    static void initGraph() {
        Scanner scanner = new Scanner(System.in);

        int vertexes = scanner.nextInt();
        int edges = scanner.nextInt();

        graphList = new ArrayList[vertexes];
        isVisitedCol = new Colors[vertexes];
        for (int i = 0; i < vertexes; i++) {
            graphList[i] = new ArrayList<>();
            isVisitedCol[i] = Colors.WHITE;
        }

        for (int i = 0; i < edges; i++) {
            int from = scanner.nextInt() - OFFSET;
            int to = scanner.nextInt() - OFFSET;
            graphList[from].add(to);
        }
    }

    static void dfs(int vertex) {
        isVisitedCol[vertex] = Colors.GREY;

        for (int i = 0; i < graphList[vertex].size(); i++) {
            int to = graphList[vertex].get(i);
            if (isVisitedCol[to] == Colors.WHITE) {
                dfs(to);
            } else if (isVisitedCol[to] == Colors.GREY) {
                isCycle = true;
            }
        }
        isVisitedCol[vertex] = Colors.BLACK;
        top.add(vertex);
    }

    public static void main(String[] args) {
        initGraph();

        for (int i = 0; i < graphList.length; i++) {
            if (isVisitedCol[i] == Colors.WHITE) {
                dfs(i);
            }
        }

        if (isCycle) {
            System.out.println(-1);
            return;
        }

        for (int i = top.size() - 1; i >= 0; i--) {
            System.out.print(top.get(i) + OFFSET);
            System.out.print(" ");
        }
    }
}