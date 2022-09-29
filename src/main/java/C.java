import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class C {
  private C() {
    // Should not be instantiated
  }

  private static void mergeSort(Participant[] arr, int l, int r) {
    if (l + 1 == r) {
      return;
    }
    int mid = l + ((r - l) >> 1);
    mergeSort(arr, l, mid);
    mergeSort(arr, mid, r);
    merge(arr, l, mid, r);
  }

  private static void merge(Participant[] arr, int l, int mid, int r) {
    int inx = 0;
    int it1 = l;
    int it2 = mid;
    Participant[] storage = new Participant[r - l];
    while (it1 != mid && it2 != r) {
      if (arr[it1].compareTo(arr[it2]) < 0) {
        storage[inx++] = arr[it1++];
      } else {
        storage[inx++] = arr[it2++];
      }
    }
    while (it2 != r) {
      storage[inx++] = arr[it2++];
    }
    while (it1 != mid) {
      storage[inx++] = arr[it1++];
    }
    System.arraycopy(storage, 0, arr, l, storage.length);
  }

  static class Participant implements Comparable<Participant> {

    private final int id;

    private final int scores;

    public Participant(int id, int scores) {
      this.id = id;
      this.scores = scores;
    }

    @Override
    public String toString() {
      return id + " " + scores;
    }

    @Override
    public int compareTo(Participant o) {
      if (scores == o.scores) {
        return id - o.id;
      }
      return o.scores - scores;
    }
  }

  private static void solve(final FastScanner in, final PrintWriter out) {
    int n = in.nextInt();
    int id = 0;
    int scores = 0;
    Participant[] arr = new Participant[n];
    for (int i = 0; i < n; ++i) {
      id = in.nextInt();
      scores = in.nextInt();
      arr[i] = new Participant(id, scores);
    }
    mergeSort(arr,0 , n);
    for (Participant i : arr) {
      System.out.println(i);
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
