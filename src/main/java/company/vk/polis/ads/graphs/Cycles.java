package company.vk.polis.ads.graphs;


import java.util.ArrayList;
import java.util.Arrays;
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
public final class Cycles {
  private Cycles() {
    // Should not be instantiated
  }

  private static class Graph {

    private List<List<Integer>> vertexes;
    private int[] color;
    private int[] parent;
    private int minVertex;

    Graph(int size) {
      vertexes = new ArrayList<>(size + 1);
      for (int i = 0; i <= size; ++i) {
        vertexes.add(new ArrayList<>());
      }
      color = new int[size + 1];
      parent = new int[size + 1];
      minVertex = Integer.MAX_VALUE;
    }

    void addEdge(int l, int r) {
      vertexes.get(l).add(r);
      vertexes.get(r).add(l);
    }

    void dfs(int v, int p) {
      parent[v] = p;
      color[v] = 1;
      for (int u : vertexes.get(v)) {
        if (color[u] == 0) {
          dfs(u, v);
        } else if (color[u] == 1 && u != p) {
          int min = u;
          int tmp = v;
          while (tmp != u) {
            min = Math.min(min, tmp);
            tmp = parent[tmp];
          }
          minVertex = Math.min(minVertex, min);

        }
      }
      color[v] = 2;
    }

    void isCycle(final PrintWriter out) {
      for (int v = 1; v < vertexes.size(); ++v) {
        if (color[v] == 0) {
          dfs(v, -1);
        }
      }
      if (minVertex == Integer.MAX_VALUE) {
        out.println("No");
      } else {
        out.print("Yes\n" + minVertex);
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

      graph.isCycle(out);

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
}
