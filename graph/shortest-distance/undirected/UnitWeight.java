
import java.util.*;
public class UnitWeight {
    public int[] shortestPath(int V, int[][] edges, int src) {
        // code here
        List<List<Integer>> adj = getAdj(V, edges);
        int[] dis = new int[V];
        Arrays.fill(dis, -1);
        boolean[] vis = new boolean[V];
        
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(src);
        dis[src] = 0;
        vis[src] = true;
        while(!q.isEmpty()) {
            int node = q.poll();
            for(int nei : adj.get(node)) {
                if(!vis[nei]) {
                    vis[nei] = true;
                    dis[nei] = dis[node] + 1;
                    q.offer(nei);
                }
            }
        }
        
        return dis;
        
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
