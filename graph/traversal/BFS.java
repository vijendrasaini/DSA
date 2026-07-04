package traversal;

import java.util.ArrayList;
import java.util.List;

public class BFS {
    void dfs(int node, List<List<Integer>> adj, boolean[] vis) {
        vis[node] = true;
        for(int nei : adj.get(node)) {
            if(!vis[nei]) dfs(nei, adj, vis);
        }
    }

    public List<List<Integer>> getAdj(int V, int[][] edges) {
        List<List<Integer>> list = new ArrayList<>();
        
        for(int i = 0; i < V; i++) {
            list.add(new ArrayList<>());
        }
        
        for(int[] e : edges) {
            int u = e[0], v = e[1];
            list.get(u).add(v);
            list.get(v).add(u);
        }
        
        return list;
    }
}
