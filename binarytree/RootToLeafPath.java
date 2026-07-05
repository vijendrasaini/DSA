package binarytree;

import java.util.*;

public class RootToLeafPath {
    public ArrayList<ArrayList<Integer>> Paths(Node root) {
        // code here
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        getPaths(root, new ArrayList<Integer>(), ans);
        return ans;
    }
    
    void getPaths(Node node, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> ans) {
        if(node == null) return;
        
        path.add(node.data);
        if(node.left == null && node.right == null) {
            // left node
            ans.add(new ArrayList<>(path));
        }
        
        getPaths(node.left, path, ans);
        getPaths(node.right, path, ans);
        
        path.remove(path.size() - 1);
    }
}
