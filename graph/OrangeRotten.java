package graph;

import java.util.*;

public class OrangeRotten {
    public int orangesRotting(int[][] g) {
        Queue<int[]> q = new ArrayDeque<>();

        for(int i = 0; i < g.length; i++) {
            for(int j = 0; j < g[i].length; j++) {
                if(g[i][j] == 2) {
                    q.offer(new int[]{i, j});
                }
            }
        }

        int time = 0;
        int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        while(!q.isEmpty()) {
            int size = q.size();

            boolean hasRotten = false;
            for(int i = 0; i < size; i++) {
                int[] pair = q.poll();
                for(int[] d : dir) {
                    int x = pair[0] + d[0], y = pair[1] + d[1];
                    if(x > -1 && x < g.length && y > -1 && y < g[0].length) {
                        if(g[x][y] == 1) {
                            g[x][y] = 2;
                            q.offer(new int[]{x, y});
                            hasRotten = true;
                        }
                    }
                }
            }

            if(hasRotten) {
                time++;
            }
        }

        for(int i = 0; i < g.length; i++) {
            for(int j = 0; j < g[i].length; j++) {
                if(g[i][j] == 1) {
                    return -1;
                }
            }
        }

        return time;
    }
}
