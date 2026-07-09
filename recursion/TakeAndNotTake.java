import java.util.*;

public class TakeAndNotTake {
    public List<String> powerSet(String s) {
        List<String> ans = new ArrayList<>();
        get(0, s, s.length(), new StringBuilder(), ans);
        Collections.sort(ans);
        return ans;
    }

    void get(int index, String s, int n, StringBuilder current, List<String> ans) {
        if(index == n) {
            ans.add(current.toString());
            return;
        }
        
        // not Take
        get(index + 1, s, n, current, ans);

        // take
        current.append(s.charAt(index));
        get(index + 1, s, n, current, ans);
        current.deleteCharAt(current.length() - 1);
    }
}