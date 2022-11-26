import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public class GraphCondense {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        List<Integer>[] graph = new List[n + 1];
        List<Integer>[] graphTransposed = new List[n + 1];
        for (int i = 1; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
            graphTransposed[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int fromTop = in.nextInt();
            int toTop = in.nextInt();
            graph[fromTop].add(toTop);
            graphTransposed[toTop].add(fromTop);
        }

        Collection<Integer> sortedTops = topSort(graph);
        int[] colors = paint(graphTransposed, sortedTops);
        Set<Pair> colorsConsidered = new HashSet<>();
        int arcsCount = 0;
        for (int top = 1; top < n + 1; top++) {
            for (int neighborTop : graph[top]) {
                if (colors[top] != colors[neighborTop]) {
                    Pair colorsPair = new Pair(colors[top], colors[neighborTop]);
                    if (colorsConsidered.contains(colorsPair)) {
                        continue;
                    }
                    arcsCount++;
                    colorsConsidered.add(colorsPair);
                }
            }
        }
        System.out.println(arcsCount);
    }

    public static Collection<Integer> topSort(List<Integer>[] graphData) {
        Deque<Integer> sortedTops = new LinkedList<>();
        boolean[] visited = new boolean[graphData.length];
        for (int top = 1; top < graphData.length; top++) {
            dfs(top, graphData, visited, sortedTops);
        }
        return sortedTops;
    }

    private static int[] paint(List<Integer>[] graphData, Collection<Integer> orderedTops) {
        int[] colors = new int[graphData.length];
        int currentColor = 1;
        for (int top : orderedTops) {
            if (dfs(top, currentColor, colors, graphData)) {
                currentColor++;
            }
        }
        return colors;
    }

    private static boolean dfs(int top, int color, int[] colors, List<Integer>[] graphData) {
        if (colors[top] != 0) {
            return false;
        }
        colors[top] = color;
        for (int neighborTop : graphData[top]) {
            if (colors[neighborTop] == 0) {
                dfs(neighborTop, color, colors, graphData);
            }
        }
        return true;
    }

    private static void dfs(int top, List<Integer>[] graphData, boolean[] visited, Deque<Integer> tops) {
        if (visited[top]) {
            return;
        }
        visited[top] = true;
        for (int neighborTop : graphData[top]) {
            dfs(neighborTop, graphData, visited, tops);
        }
        tops.addFirst(top);
    }

    private static class Pair {
        int firstValue;
        int secondValue;

        public Pair(int firstValue, int secondValue) {
            this.firstValue = firstValue > secondValue ? firstValue : secondValue;
            this.secondValue = this.firstValue == firstValue ? secondValue : firstValue;
        }

        @Override
        public int hashCode() {
            return Objects.hash(firstValue, secondValue);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (obj != null && getClass() != obj.getClass()) {
                return false;
            }

            Pair pairObj = (Pair) obj;
            return firstValue == pairObj.firstValue &&
                   secondValue == pairObj.secondValue;
        }
    }
}
