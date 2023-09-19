
class Solution {
    static String[] friends;
    static int ans;
    public int solution(int n, String[] data) {
        boolean[] visited = new boolean[8];
        String[] friendss = {"A", "C", "F", "J", "M", "N", "R", "T"};
        friends = friendss;
        
        ans = 0;
        dfs("", visited, data);

        return ans;
    }

    static void dfs(String strs, boolean[] visited, String[] data) {
        if (strs.length() == 7) {
            if (check(strs, data)) {
                ans++;
            }
            return;
        }

        for (int i = 0; i < 8; i++) {
            if (!visited[i]) {
                visited[i] = true;
                String str = strs + friends[i];
                dfs(str, visited, data);
                visited[i] = false;
            }
        }
    }

    static boolean check(String strs, String[] data) {
        for (String s : data) {
            int pos1 = strs.indexOf(s.charAt(0));
            int pos2 = strs.indexOf(s.charAt(2));
            char op = s.charAt(3);
            int index = s.charAt(4) - '0';
            if (op == '=') {
                if (!(Math.abs(pos1 - pos2) == index + 1))
                    return false;
            } else if (op == '>') {
                if (!(Math.abs(pos1 - pos2) > index + 1))
                    return false;
            } else if (op == '<') {
                if (!(Math.abs(pos1 - pos2) < index + 1))
                    return false;
            }
        }
        return true;
    }
}