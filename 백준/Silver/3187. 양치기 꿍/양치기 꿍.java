import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, sheep, wolf;
    static int v, k;
    static int[] dirX = {-1,1,0,0};
    static int[] dirY = {0,0,-1,1};
    static char[][] map;
    static boolean[][] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M];

        for(int i=0; i<N; i++) {
            char[] input = br.readLine().toCharArray();
            for(int j=0; j<M; j++) {
                map[i][j] = input[j];
            }
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(!visited[i][j] && map[i][j] != '#') {
                    v = 0;
                    k = 0;
                    if (map[i][j] == 'v') v++;
                    if (map[i][j] == 'k') k++;
                    bfs(i, j);
                    if (k > v) v = 0;
                    else k = 0;
                    wolf += v;
                    sheep += k;
                }

            }
        }

        System.out.println(sheep+" "+wolf);
    }

    static void bfs(int x, int y) {
        Queue<Pair> queue = new ArrayDeque<>();
        visited[x][y] = true;
        queue.add(new Pair(x, y));

        while(!queue.isEmpty()) {
            Pair cur = queue.poll();
            for(int d=0; d<4; d++) {
                int dx = cur.x + dirX[d];
                int dy = cur.y + dirY[d];

                if (isOut(dx,dy)) continue;
                if (map[dx][dy]=='#' || visited[dx][dy]) continue;

                visited[dx][dy] = true;
                if(map[dx][dy] == 'v') v++;
                if(map[dx][dy] == 'k') k++;
                queue.add(new Pair(dx,dy));

            }



        }

    }

    static boolean isOut(int dx, int dy) {
        return dx < 0 || dy < 0 || dx > N-1 || dy > M-1;
    }

    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}