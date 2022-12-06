package company.vk.polis.ads;

import java.util.*;

//Done -> https://www.eolymp.com/ru/submissions/12385761

public class MinKarkas {

    private static int findCur(int x, int[] array) {
        if (x == array[x]) {
            return x;
        }
        return array[x] = findCur(array[x], array);
    }

    private static void connect(int x1, int x2, int[] array) {
        x1 = findCur(x1, array);
        x2 = findCur(x2, array);
        if (x1 != x2) {
            array[x2] = x1;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int tmp = n;
        long min = 0;
        Queue<NodeM> nodeMQueue = new PriorityQueue<>(n, Comparator.comparing(nodeM -> nodeM.ves));
        int[] array = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            array[i] = i;
        }
        for (int i = 0; i < m; i++) {
            int b = in.nextInt();
            int e = in.nextInt();
            int w = in.nextInt();
            nodeMQueue.add(new NodeM(b, e, w));
        }
        while (tmp != 1) {
            NodeM node = nodeMQueue.poll();
            int tmpFrom = node != null ? node.u : 0;
            int tmpTo = node != null ? node.v : 0;
            if (findCur(tmpFrom, array) != findCur(tmpTo, array)) {
                connect(tmpFrom, tmpTo, array);
                int weight = node != null ? node.ves : 0;
                min += weight;
                tmp--;
            }
        }
        System.out.println(min);
    }
}

class NodeM {
    protected int u;
    protected int v;
    protected int ves;

    NodeM(int u, int v, int ves) {
        this.u = u;
        this.v = v;
        this.ves = ves;
    }
}
