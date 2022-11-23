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

  private static class Graph {

    List<List<Integer>> vertexes;
    private boolean[] used;
    private List<Integer> sorted;

    Graph(int size) {
      vertexes = new ArrayList<>(size + 1);
      for (int i = 0; i <= size; ++i) {
        vertexes.add(new ArrayList<>());
      }
      used = new boolean[size + 1];
      sorted = new ArrayList<>(size + 1);
    }

    void addEdge(int l, int r) {
      vertexes.get(l).add(r);
    }

    void dfs(int v) {
      used[v] = true;
      for (int u : vertexes.get(v)) {
        if (!used[u]) {
          dfs(u);
        }
      }
      sorted.add(v);
    }

    void topologicalSort() {
      for (int v = 1; v < vertexes.size(); ++v) {
        if (!used[v]) {
          dfs(v);
        }
      }
      Collections.reverse(sorted);
    }
  }

  private static class ReversedGraph {
    List<List<Integer>> vertexes;
    private int[] color;

    ReversedGraph(int size) {
      vertexes = new ArrayList<>(size + 1);
      for (int i = 0; i <= size; ++i) {
        vertexes.add(new ArrayList<>());
      }
      color = new int[size + 1];
    }

    void addEdge(int l, int r) {
      vertexes.get(l).add(r);
    }

    void dfs(int v, int c) {
      color[v] = c;
      for (int u : vertexes.get(v)) {
        if (color[u] == -1) {
          dfs(u, c);
        }
      }
    }

    void paintVertexes(Graph graph) {
      Arrays.fill(color, -1);
      int c = 0;
      for (int v : graph.sorted) {
        if (color[v] == -1) {
          dfs(v, c++);
        }
      }
    }
  }

  private static class Edge implements Comparable<Edge> {

    int a, b;
    Edge(int a, int b) {
      this.a = a;
      this.b = b;
    }
    @Override
    public int compareTo(Edge e) {
      if (a == e.a) return b - e.b;
      return a - e.a;
    }
  }

  private static void solve(final FastScanner in, final PrintWriter out) {
    int n = in.nextInt();
    int m = in.nextInt();


    Graph graph = new Graph(n);
    ReversedGraph reversedGraph = new ReversedGraph(n);
    for (int i = 0; i < m; ++i) {
      int l = in.nextInt();
      int r = in.nextInt();
      graph.addEdge(l, r);
      reversedGraph.addEdge(r, l);
    }

    graph.topologicalSort();
    reversedGraph.paintVertexes(graph);


    Set<Edge> set = new HashSet<>();
    for (int v = 1; v <= n; ++v) {
      for (int to : graph.vertexes.get(v)) {
        if (reversedGraph.color[v] != reversedGraph.color[to]) {
          set.add(new Edge(reversedGraph.color[v], reversedGraph.color[to]));
        }
      }
    }
    out.print(set.size());

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
