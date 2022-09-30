import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, answer;
	static int[][] map;
	static int[][] copy;
	static int[] direction;
	static boolean[][] visited;
	static int[] dirX = { -1, 0, 1, 0 }; // 상 우 하 좌
	static int[] dirY = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;
		N = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];
		direction = new int[5];

		for (int i = 1; i <= N; i++) {
			stringTokenizer = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
			}
		}

		dfs(0);
		System.out.println(answer);

	}

	static void dfs(int count) {
		if (count == 5) {
			findMax();
			return;
		}

		for (int d = 0; d < 4; d++) {
			direction[count] = d;
			dfs(count + 1);
		}
	}

	static void findMax() {
		copy = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			copy[i] = map[i].clone();
		}

		for (int d = 0; d < direction.length; d++) {
			visited = new boolean[N + 1][N + 1];

			if (direction[d] == 0) { // 상
				for (int i = 1; i <= N; i++) {
					for (int j = 1; j <= N; j++) {
						move(i, j, direction[d]);
					}
				}
			} else if (direction[d] == 2) { // 하
				for (int i = N; i >= 1; i--) {
					for (int j = 1; j <= N; j++) {
						move(i, j, direction[d]);
					}
				}
			} else if (direction[d] == 3) { // 좌
				for (int i = 1; i <= N; i++) {
					for (int j = 1; j <= N; j++) {
						move(j, i, direction[d]);
					}
				}
			} else { // 우
				for (int i = N; i >= 1; i--) {
					for (int j = 1; j <= N; j++) {
						move(j, i, direction[d]);
					}
				}
			}
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				answer = Math.max(copy[i][j], answer);
			}
		}
	}

	static void move(int x, int y, int d) {
		if (copy[x][y] == 0)
			return;
		while (true) {
			int dx = x + dirX[d];
			int dy = y + dirY[d];

			if (dx < 1 || dy < 1 || dx > N || dy > N) {
				return;
			}

			if (visited[dx][dy])
				return;

			// 합치는 경우 (이동하려는 칸과 같을 때)
			if (copy[dx][dy] == copy[x][y]) {
				visited[dx][dy] = true;
				copy[dx][dy] *= 2;
				copy[x][y] = 0;
				return;
			}

			// 못합치는 경우 (이동하려는 칸과 같지 않고 다른 수가 있을 때)
			if (copy[dx][dy] != copy[x][y] && copy[dx][dy] != 0) {
				return;
			}

			// 이동하려는 칸이 빈 칸일때
			copy[dx][dy] = copy[x][y];
			copy[x][y] = 0;

			x = dx;
			y = dy;

		}
	}

}
