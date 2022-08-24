import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	static char[][] mapN; // 일반맵
	static char[][] mapB; // 색약맵
	static int[][] visited;
	static int[] dirX = { -1, 1, 0, 0 };
	static int[] dirY = { 0, 0, -1, 1 };
	static int N;

	static class Pair {
		int x, y;

		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		mapN = new char[N][N];
		mapB = new char[N][N];
		visited = new int[N][N];
		for (int i = 0; i < N; i++) {
			char[] input = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				mapN[i][j] = input[j];
				mapB[i][j] = input[j];
				if (input[j] == 'G') {
					mapB[i][j] = 'R';
				}
			}
		}

//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(mapB[i][j] + " ");
//			}
//			System.out.println();
//		}

		int ansN = 0; // 색약 아닌 사람
		int ansB = 0; // 색약인 사람

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visited[i][j] == 0) {
					bfsN(new Pair(i, j));
					ansN++;
				}
			}
		}

		visited = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visited[i][j] == 0) {
					bfsB(new Pair(i, j));
					ansB++;
				}
			}
		}

		System.out.println(ansN + " " + ansB);
	}

	static void bfsN(Pair pair) {
		Queue<Pair> q = new LinkedList<>();

		q.add(pair);
		visited[pair.x][pair.y] = 1;

		while (!q.isEmpty()) {
			Pair cur = q.poll();

			for (int d = 0; d < 4; d++) {
				int dx = cur.x + dirX[d];
				int dy = cur.y + dirY[d];
				if (check(dx, dy)) {
					if (visited[dx][dy] == 0 && mapN[dx][dy] == mapN[cur.x][cur.y]) {
						visited[dx][dy] = 1;
						q.add(new Pair(dx, dy));
					}
				}
			}
		}
	}

	static void bfsB(Pair pair) {
		Queue<Pair> q = new LinkedList<>();

		q.add(pair);
		visited[pair.x][pair.y] = 1;

		while (!q.isEmpty()) {
			Pair cur = q.poll();

			for (int d = 0; d < 4; d++) {
				int dx = cur.x + dirX[d];
				int dy = cur.y + dirY[d];
				if (check(dx, dy)) {
					if (visited[dx][dy] == 0 && mapB[dx][dy] == mapB[cur.x][cur.y]) {
						visited[dx][dy] = 1;
						q.add(new Pair(dx, dy));
					}
				}
			}
		}
	}

	static boolean check(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

}