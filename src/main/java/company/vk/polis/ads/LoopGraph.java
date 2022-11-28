import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class LoopGraph {

    private enum Color {
        WHITE,
        BLACK,
        GREY
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // Кол-во вершин
        int n = in.nextInt();
        // Количество рёбер
        int k = in.nextInt();
        // Список смежности
        List<Integer>[] graphData = new List[n + 1];
        Color[] visited = new Color[n + 1];
        boolean[] inLoop = new boolean[n + 1];
        for (int i = 1; i < n + 1; i++) {
            graphData[i] = new ArrayList<>();
            visited[i] = Color.WHITE;
        }
        // Формирование графа
        for (int i = 0; i < k; i++) {
            int firstTop = in.nextInt();
            int secondTop = in.nextInt();
            graphData[firstTop].add(secondTop);
            graphData[secondTop].add(firstTop);
        }
        Deque<Integer> history = new LinkedList<>();
        Set<Integer> alreadyInLoop = new HashSet<>();
        for (int top = 1; top < n + 1; top++) {
            dfs(top, 0, graphData, visited, alreadyInLoop, history);
        }
        if (alreadyInLoop.size() == 0) {
            System.out.println("No");
        } else {
            System.out.println("Yes");
            int minLoopTop = Integer.MAX_VALUE;
            for (int top : alreadyInLoop) {
                if (top < minLoopTop) {
                    minLoopTop = top;
                }
            }
            System.out.println(minLoopTop);
        }
    }

    private static void dfs(int top, int fromTop, List<Integer>[] graphData,
                            Color[] visited, Set<Integer> alreadyInLoop, Deque<Integer> history) {
        visited[top] = Color.GREY;
        history.addFirst(top);
        for (int neighborTop : graphData[top]) {
            if (neighborTop == fromTop) {
                continue;
            }
            if (visited[neighborTop] == Color.WHITE) {
                dfs(neighborTop, top, graphData, visited, alreadyInLoop, history);
            } else if (visited[neighborTop] == Color.GREY) {
                for (int historyTop : history) {
                    if (historyTop == neighborTop || alreadyInLoop.contains(historyTop)) {
                        break;
                    }
                    alreadyInLoop.add(historyTop);
                }
                alreadyInLoop.add(neighborTop);
            }
        }
        history.pollFirst();
        visited[top] = Color.BLACK;
    }
}