package graph.Dependacy;

import java.util.*;

public class FindExecutableSortingOrTopoSort {
    public ArrayList<Integer> topoSort(int V, int[][] edges) {
        // code here
        List<List<Integer>> adj = getAdj(V, edges);
        ArrayList<Integer> ans = new ArrayList<>();

        int[] ind = getIndegrees(V, edges);
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < V; i++) {
            if (ind[i] == 0) {
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            int node = q.poll();
            ans.add(node);

            for (int nei : adj.get(node)) {
                ind[nei]--;

                if (ind[nei] == 0) {
                    q.offer(nei);
                }
            }
        }

        return ans;
    }

    public int[] getIndegrees(int V, int[][] edges) {
        int[] ind = new int[V];
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            ind[v]++;
        }

        return ind;
    }

    public List<List<Integer>> getAdj(int V, int[][] edges) {
        List<List<Integer>> list = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            list.add(new ArrayList<>());
        }

        for (int[] e : edges) {
            int u = e[0], v = e[1];
            list.get(u).add(v);
        }

        return list;
    }
}
