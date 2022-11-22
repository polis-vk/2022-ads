package company.vk.polis.ads.graphs;


import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class ShortestWay {
  private ShortestWay() {
    // Should not be instantiated
  }

  private static class Graph {

    List<List<Integer>> vertexes;

    Graph(int size) {
      vertexes = new ArrayList<>(size + 1);
      for (int i = 0; i <= size; ++i) {
        vertexes.add(new ArrayList<>());
      }
    }

    void addEdge(int l, int r) {
      vertexes.get(l).add(r);
      vertexes.get(r).add(l);
    }

    void shortestWay(int start, int end) {
      List<Integer> parent = Stream.generate(() -> -1).limit(vertexes.size() + 1).collect(Collectors.toCollection(ArrayList::new));
      List<Integer> dist = Stream.generate(() -> -1).limit(vertexes.size() + 1).collect(Collectors.toCollection(ArrayList::new));
      dist.set(start, 0);
      parent.set(start, -1);

      Queue<Integer> queue = new ArrayDeque<>();
      queue.add(start);
      while (!queue.isEmpty()) {
        int v = queue.poll();
        for (int to : vertexes.get(v)) {
          if (dist.get(to) == -1) {
            dist.set(to, dist.get(v) + 1);
            queue.add(to);
            parent.set(to, v);
          }
        }
      }

      if (parent.get(end) == -1) {
        System.out.println("-1");
      } else {
        System.out.println(dist.get(end));

        List<Integer> path = new ArrayList<>();
        path.add(end);
        while (parent.get(end) != -1) {
          end = parent.get(end);
          path.add(end);
        }

        Collections.reverse(path);
        path.forEach(v -> System.out.print(v + " "));
      }
    }

  }

    private static void solve(final FastScanner in, final PrintWriter out) {
      int n = in.nextInt();
      int m = in.nextInt();
      int start = in.nextInt();
      int end = in.nextInt();

      Graph graph = new Graph(n);
      for (int i = 0; i < m; ++i) {
        int l = in.nextInt();
        int r = in.nextInt();
        graph.addEdge(l, r);
      }

      graph.shortestWay(start, end);
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
