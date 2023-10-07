import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] map;
    static int[][] visited;
    static int[] dirX = {-1, 1, 0, 0};
    static int[] dirY = {0, 0, -1, 1};
    static int count;
    static boolean check;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
//        int N = Integer.parseInt(br.readLine());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        count = 0;
        check = true;

        while (check) {
            count++;
            visited = new int[N][M];
            Queue<Pair> q = new ArrayDeque<>();
            q.add(new Pair(0, 0));
            while (!q.isEmpty()) {
                Pair cur = q.poll();
                for (int d = 0; d < 4; d++) {
                    int dx = cur.x + dirX[d];
                    int dy = cur.y + dirY[d];
                    if (isOut(dx, dy)) continue;
                    if (map[dx][dy] == 0 && visited[dx][dy] == 0) {
                        q.add(new Pair(dx, dy));
                        visited[dx][dy] = 1;
                    } else if (map[dx][dy] > 0) {
                        visited[dx][dy] += 1;
                    }
                }
            }
            check = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 1) {
                        if (visited[i][j] > 1) map[i][j] = 0;
                        else check = true;
                    }
                }
            }
        }
        
        System.out.println(count);
    }

    static boolean isOut(int dx, int dy) {
        return dx < 0 || dy < 0 || dx > N - 1 || dy > M - 1;
    }

    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}