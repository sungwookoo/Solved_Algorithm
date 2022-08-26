import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	// 백준 4485 녹색 옷 입은 애가 젤다지 ?
	static int N = -1;
	static int[][] map;
	static int[][] D;
	static int ans;
	static int[] dirX = { -1, 1, 0, 0 };
	static int[] dirY = { 0, 0, -1, 1 };

	static class Pair implements Comparable<Pair> {
		int x, y, money;

		public Pair(int x, int y, int money) {
			super();
			this.x = x;
			this.y = y;
			this.money = money;
		}

		@Override
		public int compareTo(Pair o) {
			return this.money - o.money;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int tc = 1;

		while (true) {
			N = Integer.parseInt(br.readLine());
			if (N == 0)
				break;
			sb.append("Problem " + (tc++) + ": ");
			map = new int[N][N];
			D = new int[N][N];
			ans = Integer.MAX_VALUE;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					D[i][j] = Integer.MAX_VALUE;
				}
			}

			dijkstra();

			sb.append(ans + "\n");
		}

		System.out.println(sb);
	}

	static void dijkstra() {
		PriorityQueue<Pair> q = new PriorityQueue<>();
		q.offer(new Pair(0, 0, map[0][0]));
		D[0][0] = map[0][0];

		while (!q.isEmpty()) {
			Pair cur = q.poll();

			for (int d = 0; d < 4; d++) {
				int dx = cur.x + dirX[d];
				int dy = cur.y + dirY[d];

				if (!check(dx, dy))
					continue;

				if (D[dx][dy] > map[dx][dy] + D[cur.x][cur.y]) {
					D[dx][dy] = map[dx][dy] + D[cur.x][cur.y];
					q.offer(new Pair(dx, dy, D[dx][dy]));
				}
			}

		}

		ans = D[N - 1][N - 1];

	}

	static boolean check(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
}