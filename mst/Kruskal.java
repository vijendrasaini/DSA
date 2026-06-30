package mst;

import java.util.*;;

public class Kruskal {
    static int kruskalsMST(int V, int[][] edges) {
        DSU dsu = new DSU(V);

        int ans = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        for (int[] e : edges)
            pq.offer(e);

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int u = curr[0], v = curr[1], w = curr[2];
            if (dsu.union(u, v))
                ans += w;
        }

        return ans;
    }
}

class DSU {
    private int[] parent, size;

    public DSU(int n) {
        this.parent = new int[n];
        for (int i = 0; i < n; i++)
            this.parent[i] = i;
        this.size = new int[n];
        Arrays.fill(this.size, 1);
    }

    public int find(int node) {
        if (node == parent[node])
            return node;
        return parent[node] = find(parent[node]);
    }

    public boolean union(int u, int v) {
        int pu = find(u), pv = find(v);
        if (pu != pv) {
            if (size[pu] >= size[pv]) {
                size[pu] += size[pv];
                parent[pv] = pu;
            } else {
                size[pv] += size[pu];
                parent[pu] = pv;
            }

            return true;
        }

        return false;
    }
}