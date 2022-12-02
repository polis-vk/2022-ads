package company.vk.polis.ads.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Scanner;


public class Cycles {
    static final int OFFSET = 1;

    enum Colors {
        WHITE, GREY, BLACK
    }

    static ArrayList<Integer>[] graphList;
    static Colors[] isVisitedCol;
    static Deque<Integer> depthPath;
    static boolean isCycle = false;
    static boolean[] isCycled;

    static int lowestVertexInCycle = Integer.MAX_VALUE;

    static void initGraph() {
        Scanner scanner = new Scanner(System.in);

        int vertexes = scanner.nextInt();
        int edges = scanner.nextInt();
        depthPath = new ArrayDeque<>();
        graphList = new ArrayList[vertexes];
        isVisitedCol = new Colors[vertexes];
        isCycled = new boolean[vertexes];
        for (int i = 0; i < vertexes; i++) {
            graphList[i] = new ArrayList<>();
            isVisitedCol[i] = Colors.WHITE;
        }

        for (int i = 0; i < edges; i++) {
            int from = scanner.nextInt() - OFFSET;
            int to = scanner.nextInt() - OFFSET;
            graphList[from].add(to);
            graphList[to].add(from);
        }
    }

    static void dfs(int vertex, int from) {
        isVisitedCol[vertex] = Colors.GREY;
        depthPath.push(vertex);

        for (int i = 0; i < graphList[vertex].size(); i++) {
            int to = graphList[vertex].get(i);
            if (to == from) {
                continue;
            }
            if (isVisitedCol[to] == Colors.WHITE) {
                dfs(to, vertex);
            } else if (isVisitedCol[to] == Colors.GREY) {
                isCycled[to] = true;

                for (int ver : depthPath) {
                    if (ver == to || isCycled[ver]) {
                        break;
                    }
                    isCycled[ver] = true;
                }
            }
        }

        depthPath.removeFirst();
        isVisitedCol[vertex] = Colors.BLACK;
    }

    public static void main(String[] args) {
        initGraph();

        for (int i = 0; i < graphList.length; i++) {
            dfs(i, 0);
        }

        for (int i = 0; i < isCycled.length; i++) {
            if (isCycled[i]) {
                lowestVertexInCycle = i;
                isCycle = true;
                break;
            }
        }

        if (isCycle) {
            System.out.println("Yes");
            System.out.print(lowestVertexInCycle + OFFSET);
            return;
        }

        System.out.print("No");
    }
}