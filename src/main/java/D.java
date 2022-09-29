import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class D {
  private D() {
    // Should not be instantiated
  }

  private static void partitionSort(int[] arr, int l, int r) {
    if (l + 1 >= r) {
      return;
    }
    int pivot = randomPartition(arr, l, r);
    partitionSort(arr, l, pivot);
    partitionSort(arr, pivot + 1, r);
  }

  private static int randomPartition(int[] arr, int l, int r) {
    int i = ThreadLocalRandom.current().nextInt(r - l) + l;
    swap(arr, l, i);
    return partition(arr, l, r);
  }

  private static int partition(int[] arr, int l, int r) {
    int pivot = arr[l];
    int i = l;
    for (int j = l + 1; j < r; ++j) {
      if (arr[j] <= pivot) {
        swap(arr, ++i, j);
      }
    }
    swap(arr, i, l);
    return i;
  }

  private static void swap(int[] arr, int i, int j) {
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }

  private static int getIndexOfNonEqualElement(int [] arr, int startInx) {
    int it = startInx;
    while (it < arr.length - 1 && arr[it + 1] == arr[it]) {
      it++;
    }
    return (it == arr.length - 1) ? arr.length : it + 1;
  }
  private static void solve(final FastScanner in, final PrintWriter out) {
    int n = in.nextInt();
    int[] a = new int[n];
    for (int i = 0; i < n; ++i) {
      a[i] = in.nextInt();
    }
    int m = in.nextInt();
    int[] b = new int[m];
    for (int i = 0; i < m; ++i) {
      b[i] = in.nextInt();
    }
    partitionSort(a, 0, n);
    partitionSort(b, 0, m);
    boolean flag = true;
    int i = 0;
    int j = 0;
    while (i < n && j < m) {
      if (a[i] == b[j]) {
        i = getIndexOfNonEqualElement(a, i);
        j = getIndexOfNonEqualElement(b, j);
      } else {
        flag = false;
        break;
      }
    }
    if (flag) {
      if (i == n && j == m) {
        System.out.println("YES");
      } else {
        System.out.println("NO");
      }
    } else {
      System.out.println("NO");
    }
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

