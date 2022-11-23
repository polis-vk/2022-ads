package company.vk.polis.ads.graphs;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;


/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class TopologicalSort{
  private TopologicalSort() {
    // Should not be instantiated
  }

  private static class Graph {

    List<List<Integer>> vertexes;
    private int [] color;
    private List<Integer> sorted;
    boolean flag;

    Graph(int size) {
      vertexes = new ArrayList<>(size + 1);
      for (int i = 0; i <= size; ++i) {
        vertexes.add(new ArrayList<>());
      }
      color = new int[size + 1];
      sorted = new ArrayList<>(size + 1);
      flag = true;
    }

    void addEdge(int l, int r) {
      vertexes.get(l).add(r);
    }

    void dfs(int v, int p) {
      color[v] = 1;
      for (int u : vertexes.get(v)) {
        if (color[u] == 0) {
          dfs(u, v);
        } else if (color[u] == 1 && u != p) {
          flag = false;
          return;
        }
      }
      color[v] = 2;
      sorted.add(v);
    }

    void topologicalSort() {
      for (int v = 1; v < vertexes.size(); ++v) {
        if (color[v] == 0) {
          dfs(v, -1);
        }
        if (!flag) {
          System.out.println(-1);
          return;
        }
      }
      Collections.reverse(sorted);
      sorted.forEach(v -> System.out.print(v + " "));
    }


  }

  private static void solve(final FastScanner in, final PrintWriter out) {
    int n = in.nextInt();
    int m = in.nextInt();


    Graph graph = new Graph(n);
    for (int i = 0; i < m; ++i) {
      int l = in.nextInt();
      int r = in.nextInt();
      graph.addEdge(l, r);
    }

    graph.topologicalSort();

  }

  private static final class FastScanner {
    private final BufferedReader reader;
    private StringTokenizer tokenizer;

    FastScanner(final InputStream in) {
      reader = new BufferedReader(new InputStreamReader(in));
    }

    String next() {
      while (tokenizer == null || !tokenizer.hasMoreTokens()) {
        try {
          tokenizer = new StringTokenizer(reader.readLine());
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      return tokenizer.nextToken();
    }

    int nextInt() {
      return Integer.parseInt(next());
    }
  }

  public static void main(final String[] arg) {
    final FastScanner in = new FastScanner(System.in);
    try (PrintWriter out = new PrintWriter(System.out)) {
      solve(in, out);
    }
  }
}
