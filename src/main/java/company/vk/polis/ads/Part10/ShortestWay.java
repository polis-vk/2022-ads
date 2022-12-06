package company.vk.polis.ads;

import java.util.*;

//Done-> https://www.eolymp.com/ru/submissions/12383091

public class ShortestWay {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), m = in.nextInt();
        int s = in.nextInt(), f = in.nextInt();
        List<ArrayList<Node<Integer, Integer>>> graph = new ArrayList<>(n + 1);
        List<Node<Integer, Integer>> temp = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        int[] len = new int[n + 1];
        int[] parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            graph.add(i, new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            int size = in.nextInt();
            graph.get(x).add(new Node<>(y, size));
            graph.get(y).add(new Node<>(x, size));
        }

        for (int i = 0; i <= n; i++) {
            len[i] = Integer.MAX_VALUE;
        }
        len[s] = 0;
        temp.add(new Node<>(len[s], s));
        while (!temp.isEmpty()) {
            int v = temp.remove(0).v;
            for (int i = 0; i < graph.get(v).size(); i++) {
                int y = graph.get(v).get(i).u;
                int size = graph.get(v).get(i).v;
                if (len[v] + size < len[y]) {
                    temp.remove(new Node<>(len[y], y));
                    len[y] = len[v] + size;
                    parent[y] = v;
                    temp.add(new Node<>(len[y], y));
                }
            }
        }

        if (len[f] == Integer.MAX_VALUE) {
            System.out.println(-1);
        }

        System.out.println(len[f]);
        int addingEl = f;
        while (addingEl != s) {
            ans.add(addingEl);
            addingEl = parent[addingEl];
        }
        ans.add(s);
        Collections.reverse(ans);
        ans.forEach(el -> System.out.print(el + " "));
    }
}

class Node<U, V> {
    protected U u;
    protected V v;

    public Node(U u, V v) {
        this.u = u;
        this.v = v;
    }
}
