package graph;

import java.util.ArrayList;
import java.util.List;

public class CommonMethods {

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