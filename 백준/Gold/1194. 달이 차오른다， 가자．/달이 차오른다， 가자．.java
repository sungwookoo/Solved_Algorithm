import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int[] dirX = { -1, 1, 0, 0 };
	static int[] dirY = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		char[][] map = new char[N][M];
		int[][][] visited = new int[N][M][64];

		Pair pair = new Pair(0,0,0);
		
		for (int i = 0; i < N; i++) {
			char[] input = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				map[i][j] = input[j];
				if(map[i][j] == '0') {
					pair = new Pair(i,j,0);
				}
			}
		}
		int ans = bfs(pair, map, visited, N, M);
		System.out.println(ans == -1?-1:ans-1);

	}

	static int bfs(Pair pair, char[][] map, int[][][] visited, int N, int M) {
		Queue<Pair> q = new ArrayDeque<>();
		q.offer(pair);
		visited[pair.x][pair.y][pair.key] = 1;
		
		while (!q.isEmpty()) {
			Pair cur = q.poll();
			int x = cur.x;
			int y = cur.y;
			int key = cur.key;
			if (map[x][y] == '1') {
				return visited[x][y][key];
			}
			
			for (int d = 0; d < 4; d++) {
				int dx = x + dirX[d];
				int dy = y + dirY[d];
				int newKey = key;
				// 범위벗어남
				if (dx < 0 || dy < 0 || dx >= N || dy >= M)
					continue;

				// 방문함
				if (visited[dx][dy][key] != 0) {
					continue;
				}
				
				// 벽만남
				if (map[dx][dy] == '#')
					continue;
				
				// 키만남
				if ('a' <= map[dx][dy] && 'f' >= map[dx][dy]) {
					newKey = key | (1 << map[dx][dy] - 'a');
				}
				
				// 문만남
				if ('A' <= map[dx][dy] && 'F' >= map[dx][dy]) {
					if ((key & (1 << map[dx][dy] - 'A')) == 0) {
						continue;
					}
				}
				
				// 방문체크 & 큐에 넣음
				visited[dx][dy][newKey] = visited[x][y][key] + 1;
				q.offer(new Pair(dx, dy, newKey));

			}
		}
		return -1;
	}

	static class Pair {
		int x, y, key;

		public Pair(int x, int y, int key) {
			super();
			this.x = x;
			this.y = y;
			this.key = key;
		}

		@Override
		public String toString() {
			return "Pair [x=" + x + ", y=" + y + ", key=" + key + "]";
		}
		
	}

}