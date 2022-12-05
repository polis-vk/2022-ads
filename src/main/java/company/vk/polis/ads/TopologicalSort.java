package company.vk.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */

// https://www.eolymp.com/ru/submissions/12366927

public final class TopologicalSort {
    private static ArrayList<ArrayList<Integer>> listOfList;
    private static List<Integer> list = new LinkedList<>();
    private static byte[] isVisited;


    private TopologicalSort() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int vertexNumber = in.nextInt();
        int edgesNumber = in.nextInt();
        listOfList = new ArrayList<ArrayList<Integer>>();
        isVisited = new byte[vertexNumber + 1];

        for (int i = 0; i <= vertexNumber; i++) {
            listOfList.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < edgesNumber; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            listOfList.get(a).add(b);
        }
        topsort(vertexNumber);
        if (!flag && !list.isEmpty())
            list.forEach(e -> System.out.print(e + " "));
        else
            System.out.println(-1);
    }

    private static void topsort(int edgesNumber) {
        for (int i = 1; i <= edgesNumber; i++) {
            if (isVisited[i] == 0) {
                dfs(i);
            }
        }
    }

    private static boolean flag = false;

    private static void dfs(int node) {
        isVisited[node] = 1;
        for (int neighboor : listOfList.get(node)) {
            //Узел не посетили
            if (isVisited[neighboor] == 0) {
                dfs(neighboor);
            }
            //Узел серый
            if (isVisited[neighboor] == 1) {
                flag = true;
                return;
            }
        }
        isVisited[node] = 2;
        list.add(0, node);
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