package company.vk.polis.ads;

import java.util.*;

public final class FordBellman {
    static private final int MAX = 30000;

    public static void main(final String[] arg) {
        Scanner in = new Scanner(System.in);
        int vertexes = in.nextInt();
        int edges = in.nextInt();

        int[] from = new int[edges];
        int[] to = new int[edges];
        int[] weight = new int[edges];
        int[] dist = new int[vertexes];

        for (int i = 0; i < edges; i++) {
            from[i] = in.nextInt() - 1;
            to[i] = in.nextInt() - 1;
            weight[i] = in.nextInt();
        }

        Arrays.fill(dist, MAX);
        dist[0] = 0;

        for(int i = 0; i < vertexes; i++){
            for(int j = 0; j < edges; j++){
                if(dist[from[j]] < MAX){
                    int distance = dist[from[j]] + weight[j];
                    if(distance < dist[to[j]]){
                        dist[to[j]] = distance;
                    }
                }
            }
        }

        for(int i = 0; i < vertexes; i++){
            System.out.print(dist[i]);
            System.out.print(" ");
        }
    }
}