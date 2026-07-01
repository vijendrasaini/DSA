package mst;

import java.util.*;

public class Island {
    public static void main(String[] args) {
        int m, n;
        
        m = n = 3;
        // int[][] positions = {{0,0}, {0,1}, {1,2}, {2,1}};
        
        m = n = 1;
        int[][] positions = {{0, 0}};
        int[] islandCounts = getIslandCount(m, n, positions);
        System.out.println(Arrays.toString(islandCounts));
    }

    private static int[] getIslandCount(int m, int n, int[][] positions) {
        DSU dsu = new DSU(m * n);

        int[][] grid = new int[m][n];
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int ans[] = new int[positions.length];
        int count = 0;
        for(int i = 0; i < positions.length; i++) {
            int[] u = positions[i];
            int ui = u[0], uj = u[1];
            grid[ui][uj] = 1;
            count ++;
            for(int[] dir : dirs) {
                int vi = dir[0] + ui, vj = dir[1] + uj;
                if(vi > -1 && vi < m && vj > -1 && vj < n) {
                    if(grid[vi][vj] == 1)
                    if(dsu.union(ui * m + uj, vi * m + vj)) {
                        count--;
                    }
                }
            }

            ans[i] = count;
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

