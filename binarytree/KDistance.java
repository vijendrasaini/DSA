package binarytree;

import java.util.*;

public class KDistance {
    public List<Integer> distanceK(Node root, Node target, int k) {
        Map<Node, Node> parents = new HashMap<>();
        dfs(root, null, parents);

        List<Integer> ans = new ArrayList<>();
        Set<Node> vis = new HashSet<>();
        // getNodes(target, 0, k, ans, parents, vis);
        Deque<Node> queue = new ArrayDeque<>();
        queue.add(target);
        vis.add(target);
        int level = 0;
        while(!queue.isEmpty() && level <= k) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                Node curr = queue.poll();
                if(level == k) {
                    ans.add(curr.val);
                }
                List<Node> neis = new ArrayList<>();
                Node parent = parents.get(curr);
                if(parent != null) neis.add(parent);
                if(curr.left != null) neis.add(curr.left);
                if(curr.right != null) neis.add(curr.right);


                for(Node nei : neis) {
                    if(!vis.contains(nei)) {
                        vis.add(nei);
                        queue.add(nei);
                    }
                }
            }

            level++;
        }

        return ans;
    }

    void dfs(Node root, Node parent, Map<Node, Node> parents) {
        if(root == null) return;

        parents.put(root, parent);
        dfs(root.left, root, parents);
        dfs(root.right, root, parents);
    }
}