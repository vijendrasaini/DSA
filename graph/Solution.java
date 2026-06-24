package graph;
import java.util.*;
public class Solution {
    public ArrayList<ArrayList<Integer>> getComponents(int V, int[][] edges) {
        // code here
        List<List<Integer>> adj = getAdj(V, edges);
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        
        boolean[] vis = new boolean[V];
        Queue<Integer> q = new ArrayDeque<>();
        for(int i = 0; i < V; i++) {
            if(!vis[i]) {
                ArrayList<Integer> group = new ArrayList<>();
                q.offer(i);
                while(!q.isEmpty()) {
                    int node = q.poll();
                    
                    if(!vis[node]) group.add(node);
                    vis[node] = true;
                    for(int nei : adj.get(node)) {
                        if(!vis[nei]) q.offer(nei);
                    }
                    
                }
                
                ans.add(group);
            }
        }
        
        return ans;
    }
    
    
    void dfs(int i, List<List<Integer>> adj, ArrayList<Integer> ans, boolean[] vis) {
        vis[i] = true;
        ans.add(i);
        
        for(int nei : adj.get(i)) {
            if(!vis[nei]) dfs(nei, adj, ans, vis);
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