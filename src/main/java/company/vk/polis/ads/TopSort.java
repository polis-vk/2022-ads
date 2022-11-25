import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TopSort {

    private enum Color {
        WHITE,
        GREY,
        BLACK
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // Кол-во вершин
        int n = in.nextInt();
        // Количество рёбер
        int m = in.nextInt();
        // Список смежности
        List<Integer>[] graphData = new List[n + 1];
        Color[] visited = new Color[n + 1];
        for (int i = 1; i < n + 1; i++) {
            graphData[i] = new ArrayList<>();
            visited[i] = Color.WHITE;
        }
        // Формирование графа
        for (int i = 0; i < m; i++) {
            int fromTop = in.nextInt();
            int toTop = in.nextInt();
            graphData[fromTop].add(toTop);
        }
        Deque<Integer> history = new LinkedList<>();
        boolean ableToTopSort = true;
        for (int top = 1; top < n + 1; top++) {
            if (visited[top] == Color.WHITE && !(ableToTopSort &= dfs(top, graphData, visited, history))) {
                break;
            }
        }
        if (ableToTopSort) {
            while (!history.isEmpty()) {
                System.out.print(history.pollFirst() + " ");
            }
        } else {
            System.out.println(-1);
        }
    }

    private static boolean dfs(int top, List<Integer>[] graphData,
                               Color[] visited, Deque<Integer> tops) {
        visited[top] = Color.GREY;
        for (int neighborTop : graphData[top]) {
            if (visited[neighborTop] == Color.GREY) {
                return false;
            }
            if (visited[neighborTop] == Color.WHITE) {
                if (!dfs(neighborTop, graphData, visited, tops)) {
                    return false;
                }
            }
        }
        tops.addFirst(top);
        visited[top] = Color.BLACK;
        return true;
    }
}
