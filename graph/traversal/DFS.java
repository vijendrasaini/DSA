package traversal;

import java.util.*;

public class DFS {
    public void isBridge(int V, int[][] edges) {
        List<List<Integer>> adj = getAdj(V, edges);
        
        boolean[] vis = new boolean[V];
        dfs(0, vis, adj);
    }
    
    public void dfs(int node, boolean[] vis, List<List<Integer>> adj) {
        vis[node] = true;
        
        for(int nei : adj.get(node)) 
            if(!vis[nei])
                dfs(nei, vis, adj);
    }

    public List<List<Integer>> getAdj(int V, int[][] edges) {
        List<List<Integer>> list = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            list.add(new ArrayList<>());
        }

        for (int[] e : edges) {
            int u = e[0], v = e[1];
            list.get(u).add(v);
            list.get(v).add(u);
        }

        return list;
    }
}
