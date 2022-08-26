import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static int N;
    static boolean[][] visited;
    static boolean[][] dead;
    static int[] dirX = {-1,1,0,0};
    static int[] dirY = {0,0,-1,1};
    static int maxRain;
    static int max;


    static class Pair {
        int x,y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];


        maxRain = 0;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                maxRain = Math.max(map[i][j], maxRain);
            }
        }


        for(int r=1; r<=maxRain; r++) {
            int sum = 0;
            visited = new boolean[N][N];
            dead = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(!visited[i][j] && map[i][j] > r && !dead[i][j]){
//                        System.out.println(i+" "+j);
                        bfs(i, j, r);
                        sum++;
                    }
                }
            }
            max = Math.max(sum,max);
//            System.out.println("rain:"+r+" -> "+max);
        }
        sb.append(max==0?1:max);

        System.out.println(sb);
    }

    static void bfs(int x, int y, int rain) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(x,y));
        if(map[x][y] >= rain) {
            visited[x][y] = true;
        }

        while(!q.isEmpty()) {
            Pair cur = q.poll();

            for(int d=0; d<4; d++) {
                int dx = cur.x+dirX[d];
                int dy = cur.y+dirY[d];
                if(check(dx,dy)) {
                    if (map[dx][dy] <= rain) dead[dx][dy] = true;
                    if (visited[dx][dy] || map[dx][dy] <= rain) continue;

                    visited[dx][dy] = true;
                    q.add(new Pair(dx, dy));
                }
            }
        }
//        System.out.println("rain:"+rain);
//        for(int i=0; i<N; i++) {
//            for(int j=0; j<N; j++) {
//                System.out.print(visited[i][j]);
//            }
//            System.out.println();
//        }
//        System.out.println();
    }

    static boolean check(int x, int y) {
        return x>=0 && x<N && y>=0 && y<N;
    }
}
