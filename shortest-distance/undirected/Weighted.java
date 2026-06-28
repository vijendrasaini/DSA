import java.util.*;

public class Weighted {
    public int[] dijkstra(int V, int[][] edges, int src) {
        List<List<int[]>> adj = getAdj(V, edges);
        
        int[] dis = new int[V];
        Arrays.fill(dis, Integer.MAX_VALUE);
        boolean[] vis = new boolean[V];
        
        dis[src] = 0;
        
        for(int i = 0; i < V; i++) {
            int min = Integer.MAX_VALUE, node = -1;
            for(int j = 0; j < V; j++) {
                if(!vis[j] && min > dis[j]) {
                    node = j;
                    min = dis[j];
                }
            }
            
            vis[node] = true;
            
            for(int[] pair : adj.get(node)) {
                int nei = pair[0], d = pair[1], newDis = dis[node] + d;
                if(dis[nei] > newDis) {
                    dis[nei] = newDis;
                }
            }
        }
        
        return dis;
    }
    
    private List<List<int[]>> getAdj(int V, int[][] edges) {
        List<List<int[]>> adj = new ArrayList<>();
        
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
        
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int dis = edge[2];
            
            adj.get(u).add(new int[]{v, dis});
            adj.get(v).add(new int[]{u, dis});
        }
        
        return adj;
    }
}