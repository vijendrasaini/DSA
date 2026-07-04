package mst;

import java.util.*;

public class DSU {
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
