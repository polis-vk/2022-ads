package company.vk.polis.ads.workshop;
//С утра шел дождь, и ничего не предвещало беды.
// Но к обеду выглянуло солнце, и в лагерь заглянула СЭС.
// Пройдя по всем домикам и корпусам,
// СЭС вынесла следующий вердикт: бельевые верёвки в жилых домиках не удовлетворяют нормам СЭС.
// Как выяснилось, в каждом домике должно быть ровно по одной бельевой верёвке,
// и все верёвки должны иметь одинаковую длину. В лагере имеется n бельевых верёвок и k домиков.
// Чтобы лагерь не закрыли, требуется так нарезать данные верёвки,
// чтобы среди полученных верёвочек было k одинаковой длины.
// Размер штрафа обратно пропорционален длине бельевых верёвок, которые будут развешены в домиках.
// Поэтому начальство лагеря стремится максимизировать длину этих верёвочек.


import java.io.*;
import java.util.StringTokenizer;

//https://www.eolymp.com/ru/submissions/12002262
public final class Ropes {
    private Ropes() {
        // Should not be instantiated
    }
    private static void solve(final FastScanner in, final PrintWriter out) {
        // Write me
        int n = in.nextInt();
        int k = in.nextInt();
        int[] ropes = new int[n];
        long sum = 0;
        for (int i = 0; i < ropes.length; i++) {
            int rope = in.nextInt();
            ropes[i] = rope;
            sum += rope;
        }

        if (sum < k) {
            out.println(0);
            return;
        }

        long l = 1;
        long r = sum / k + 1;
        while (l < r - 1) {
            long mid = (l + r) >>> 1;
            int houseCount = 0;
            for (int length : ropes) {
                long cnt = length / mid;
                houseCount += cnt;
            }
            if (houseCount < k) {
                r = mid;
            } else {
                l = mid;
            }
        }
        out.println(l);
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
