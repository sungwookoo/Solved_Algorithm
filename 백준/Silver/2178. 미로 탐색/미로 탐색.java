import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map;
    static int[][] visited;
    static int[] dirX = {-1,1,0,0};
    static int[] dirY = {0,0,-1,1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N+1][M+1];
        visited = new int[N+1][M+1];


        for(int i=1; i<=N; i++) {
            char[] input = br.readLine().toCharArray();
            for(int j=1; j<=M; j++) {
                map[i][j] = input[j-1]-'0';
            }
        }

        bfs();

    }

    static void bfs() {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(1,1, 1));
        visited[1][1] = 1;
        while(!q.isEmpty()) {
            Pair cur = q.poll();
            if(cur.x == N && cur.y == M) {
                System.out.println(cur.count);

//                for(int i=1; i<=N; i++) {
//                    for(int j=1; j<=M; j++) {
//                        System.out.print(map[i][j]+" ");
//                    }
//                    System.out.println();
//                }
                return;
            }

            for(int d=0; d<4; d++) {
                int dx = cur.x + dirX[d];
                int dy = cur.y + dirY[d];

                if(!check(dx,dy) || map[dx][dy] == 0 || visited[dx][dy] != 0) {
                    continue;
                }

                visited[dx][dy] = visited[cur.x][cur.y] + 1;
                q.add(new Pair(dx,dy,cur.count+1));
            }
        }

    }

    static boolean check(int x, int y) {
        return x>0 && x<=N && y>0 && y<=M;
    }

    static class Pair {
        int x,y, count;

        public Pair(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }

}
