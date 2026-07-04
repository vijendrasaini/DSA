package mst;

import java.util.*;

public class Prims {
    public int spanningTree(int V, int[][] edges) {
        List<List<int[]>> adj = getAdj(V, edges);

        int ans = 0;
        boolean[] isMST = new boolean[V];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[] { 0, 0 });

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int u = curr[0], currWt = curr[1];

            if (isMST[u])
                continue;

            isMST[u] = true;
            ans += currWt;
            for (int[] nei : adj.get(u)) {
                int v = nei[0], wt = nei[1];
                if (isMST[v])
                    continue;

                pq.offer(new int[] { v, wt });
            }
        }

        return ans;

    }

    public List<List<int[]>> getAdj(int V, int[][] edges) {
        List<List<int[]>> list = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            list.add(new ArrayList<>());
        }

        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            list.get(u).add(new int[] { v, w });
            list.get(v).add(new int[] { u, w });
        }

        return list;
    }
}