import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static int[] dirX = {-1,1,0,0};
    static int[] dirY = {0,0,-1,1};
    static boolean[][] visited;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];

        for(int i=0; i<N; i++) {
            char[] input = br.readLine().toCharArray();
            for(int j=0; j<N; j++) {
                map[i][j] = input[j]-'0';
            }
        }

        int ans = 0;
        ArrayList<Integer> ansList = new ArrayList<>();
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(!visited[i][j] && map[i][j]!=0) {
                    ans ++;
                    ansList.add(bfs(i,j));
                }
            }
        }
        if(ans > 0) {
            Collections.sort(ansList);
            sb.append(ans+"\n");
            for(int a:ansList) {
                sb.append(a+"\n");
            }
            System.out.println(sb);
        }
        else {
            System.out.println(0);
        }
    }

    static int bfs(int x, int y) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(x,y));
        visited[x][y] = true;
        int count = 1;
        while(!q.isEmpty()) {
            Pair cur = q.poll();

            for(int d=0; d<4; d++) {
                int dx = cur.x + dirX[d];
                int dy = cur.y + dirY[d];

                if(!check(dx,dy) || map[dx][dy] ==0 || visited[dx][dy]) {
                    continue;
                }

                count++;
                visited[dx][dy] = true;
                q.add(new Pair(dx,dy));
            }

        }

        return count;
    }

    static class Pair {
        int x,y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static boolean check(int x, int y) {
        return x>=0 && x<N && y>=0 && y<N;
    }
}
