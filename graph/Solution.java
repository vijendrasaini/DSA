package graph;
import java.util.*;
class Solution {
    public ArrayList<ArrayList<Integer>> getComponents(int V, int[][] edges) {
        // code here
        List<List<Integer>> adj = getAdj(V, edges);
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        
        boolean[] vis = new boolean[V];
        Queue<Integer> q = new ArrayDeque<>();
        Deque<Integer> d = new ArrayDeque<>();
        Stack<Integer> s = (Stack<Integer>) d;
        Collections.sort((Stack<Integer>) d);
        for(int i = 0; i < V; i++) {
            if(!vis[i]) {
                ArrayList<Integer> group = new ArrayList<>();
                q.offer(i);
                vis[i] = true;
                while(!q.isEmpty()) {
                    int node = q.poll();
                    group.add(node);
                    for(int nei : adj.get(node)) {
                        if(!vis[nei]) {
                            vis[nei] = true;
                            q.offer(nei);
                        }
                    }
                    
                }
                
                ans.add(group);
            }
        }
        
        return ans;
    }
    
    
    void dfs(int node, List<List<Integer>> adj, ArrayList<Integer> ans, boolean[] vis) {
        vis[node] = true;
        ans.add(node);
        
        for(int nei : adj.get(node)) {
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