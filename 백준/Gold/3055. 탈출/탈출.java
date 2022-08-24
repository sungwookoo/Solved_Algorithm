import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	// 백준 3055 탈출

	static int R, C;
	static char[][] map;
	static int[][] visited;

	static List<Position> waterList;
	static int startX, startY;
	static int endX, endY;
	static int ans;

	static int[] dirX = { -1, 1, 0, 0 };
	static int[] dirY = { 0, 0, -1, 1 };

	static class Position { // type : 0 - 고슴도치, 1 - 물
		int x, y, type;

		public Position(int x, int y, int type) {
			super();
			this.x = x;
			this.y = y;
			this.type = type;
		}
	}

	// 고슴도치 S / 물 * / 돌 X / 비버굴 D
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		visited = new int[R][C];

		waterList = new ArrayList<>();

		startX = 0;
		startY = 0;
		endX = 0;
		endY = 0;
		ans = 0;

		for (int i = 0; i < R; i++) {
			char[] input = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				map[i][j] = input[j];

				if (input[j] == 'S') {
					startX = i;
					startY = j;
				} else if (input[j] == 'D') {
					endX = i;
					endY = j;
				} else if (input[j] == '*') {
					waterList.add(new Position(i, j, 1));
				}
			}
		}

		bfs();

		sb.append(visited[endX][endY] == 0 ? "KAKTUS" : visited[endX][endY] - 1);
		sb.append("\n");

		System.out.println(sb);
	}

	static void bfs() {
		Queue<Position> q = new LinkedList<>();

		for (Position pos : waterList) { // 큐에 물 위치 추가
			q.add(pos);
			visited[pos.x][pos.y] = -1;
		}

		q.add(new Position(startX, startY, 0)); // 큐에 고슴도치 위치 추가
		visited[startX][startY] = 1;

		while (!q.isEmpty()) {
			Position cur = q.poll();

			if (cur.type == 1) { // 큐에서 뺀게 물인 경우
				for (int d = 0; d < 4; d++) {
					int dx = cur.x + dirX[d];
					int dy = cur.y + dirY[d];

					if (check(dx, dy) && visited[dx][dy] == 0 && (map[dx][dy] == '.' || map[dx][dy] == 'S')) {
						visited[dx][dy] = -1;
						q.add(new Position(dx, dy, 1));
					}
				}
			}

			else if (cur.type == 0) { // 큐에서 뺀게 고슴도치인 경우
				for (int d = 0; d < 4; d++) {
					int dx = cur.x + dirX[d];
					int dy = cur.y + dirY[d];

					if (check(dx, dy) && visited[dx][dy] == 0 && (map[dx][dy] == '.' || map[dx][dy] == 'D')) {
						visited[dx][dy] = visited[cur.x][cur.y] + 1;
						q.add(new Position(dx, dy, 0));
					}
				}
			}

		}

	}

	static boolean check(int x, int y) {
		return x >= 0 && x < R && y >= 0 && y < C;
	}

}