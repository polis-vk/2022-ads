package company.vk.polis.ads;


import java.util.*;

//Done -> https://www.eolymp.com/ru/submissions/12383195

public class FordBellman {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        NodeF[] graph = new NodeF[m];
        List<Integer> bestWay = new ArrayList<>(Collections.nCopies(n + 1, 0));
        bestWay.set(1, 0);
        for (int i = 0; i < graph.length; i++) {
            int b = in.nextInt();
            int e = in.nextInt();
            int vesN = in.nextInt();
            graph[i] = new NodeF(b, e, vesN);
        }

        for (int i = 2; i < bestWay.size(); i++) {
            bestWay.set(i, 30000);
        }

        for (int i = 0; i < n - 1; i++) {
            for (NodeF nodeF : graph){
                if(bestWay.get(nodeF.u) != 30000) {
                    bestWay.set(nodeF.v, Math.min(bestWay.get(nodeF.v), bestWay.get(nodeF.u) + nodeF.ves));
                }
            }
        }
        bestWay.remove(0);
        bestWay.forEach(el -> System.out.print(el + " "));
    }
}

class NodeF {
    protected int u;
    protected int v;
    protected int ves;

    public NodeF(int u, int v, int ves) {
        this.u = u;
        this.v = v;
        this.ves = ves;
    }
}
