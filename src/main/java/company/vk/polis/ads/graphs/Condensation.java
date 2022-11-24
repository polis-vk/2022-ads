package company.vk.polis.ads.graphs;


import java.util.*;
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
public final class Condensation {
  private Condensation() {
    // Should not be instantiated
  }

  private static List<List<Integer>> graph;
  private static List<List<Integer>> invertedGraph;

  private static boolean [] used;

  private static List<Integer> order;
  private static int [] component;

  private static void dfs1(int v) {
    used[v] = true;
    for (int u : graph.get(v)) {
      if (!used[u]) {
        dfs1(u);
      }
    }
    order.add(v);
  }

  private static void dfs2(int v, int c) {
    used[v] = true;
    component[v] = c;
    for (int u : invertedGraph.get(v)) {
      if (!used[u]) {
        dfs2(u, c);
      }
    }

  }


  static class Edge implements Comparable<Edge>
  {
    int a, b;

    Edge(int a, int b)
    {
      this.a = a;
      this.b = b;
    }

    @Override
    public int compareTo(Edge e)
    {
      if (a == e.a) return b - e.b;
      return a - e.a;
    }

  }
  private static void solve(final FastScanner in, final PrintWriter out) {
    int n = in.nextInt();
    int m = in.nextInt();

    graph = new ArrayList<>(n + 1);
    invertedGraph = new ArrayList<>(n + 1);

    for (int i = 0; i <= n; ++i) {
      graph.add(new ArrayList<>());
      invertedGraph.add(new ArrayList<>());
    }

    for (int i = 0; i < m; ++i) {
      int l = in.nextInt();
      int r = in.nextInt();
      graph.get(l).add(r);
      invertedGraph.get(r).add(l);
    }

    used = new boolean[n + 1];
    order = new ArrayList<>(n + 1);
    for (int v = 1; v <= n; ++v) {
      if (!used[v]) {
        dfs1(v);
      }
    }

    Arrays.fill(used, false);
    component = new int [n + 1];
    int c = 1;
    for (int i = 0; i < n; ++i) {
      int v = order.get(n - 1 - i);
      if (!used[v]) {
        dfs2(v, c++);
      }
    }

    Set<Edge> s = new TreeSet<>();
    for(int v = 1; v <= n; ++v) {
      for (int u : graph.get(v)) {
        if (component[v] != component[u]) {
          s.add(new Edge(component[v], component[u]));
        }
      }
    }

    out.print(s.size());
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
