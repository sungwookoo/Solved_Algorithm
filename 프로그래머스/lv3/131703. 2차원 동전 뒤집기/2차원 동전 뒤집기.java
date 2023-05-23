import java.util.*;
class Solution {
    static int[][] oMap;
    static int[][] tMap;
    static int[][] cMap;
    static String[][] visited;
    static int N, M, ans;
    
    public int solution(int[][] beginning, int[][] target) {
        // 동전을 뒤집으려면, 같은 줄에 있는 모든 동전들을 뒤집어야함.
        N = beginning.length;
        M = beginning[0].length;
        
        oMap = beginning;
        tMap = target;
        cMap = new int[N][M];
        visited = new String[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = "";
            }
        }       
    
        int answer = -1;
        
        for(int i=0; i<N; i++) {
            dfs(1,i,0,1);
        }
        for(int j=0; j<M; j++) {
            dfs(0,0,j,1);
        }
        ans = Integer.MAX_VALUE;
        
        if(ans != Integer.MAX_VALUE) answer = ans;
        
        return answer;
    }
    
    static void dfs(int mode, int x, int y, int count) {
        
        if(isOut(x, y)) {
            // System.out.println(x+","+y+" 아웃");
            return;
        }
        if(visitCheck(x, y)) {
            // System.out.println(x+","+y+" already visit ");
            return;
        }
        
               
        // 뒤집기
        if(mode == 1) {
            reverse(1, x, y);
        }
        else reverse(0, x, y);
                
        visited[x][y] = mapToString(cMap);
        
        if(check()) {
            ans = Math.min(ans, count);
            recovery();
            return;
        }
        // 가로
        dfs(1, x + 1, y, count + 1);
        // 세로
        dfs(0, x, y + 1, count + 1);
        
    }
    
    static boolean isOut(int x, int y) {
        return x < 0 || y < 0 || x > N-1 || y > M-1;
    }
    
    static boolean visitCheck(int x, int y) {
        return visited[x][y].equals(mapToString(cMap));
    }
    
    static String mapToString(int[][] map) {
        String str = "";
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                str += map[i][j];
            }
        }
        
        return str;
    }
    
                
    static boolean check() {
        
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(cMap[i][j] != tMap[i][j]) return false;        
            }
        }
        
        return true;
    }
    
    // mode - 1: 가로 , 0: 세로
    static void reverse(int mode, int x, int y) {
        if(mode == 1) {
            for(int i=0; i<M; i++) {
                cMap[x][i] = cMap[x][i] == 1?0:1;
            }
        }
        else {
            for(int i=0; i<N; i++) {
                cMap[i][y] = cMap[i][y] == 1?0:1;
            }
        }
    }
    
    static void recovery() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                cMap[i][j] = oMap[i][j];
            }
        }
    }
}