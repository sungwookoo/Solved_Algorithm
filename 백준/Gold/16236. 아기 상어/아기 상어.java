import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	// 백준 16236 상어

	static int N;
	static int[][] map;
	static int[][] visited;

	static int[] dirX = { -1, 1, 0, 0 };
	static int[] dirY = { 0, 0, -1, 1 };

	static ArrayList<Position> feedList = new ArrayList<>(); // 먹이 리스트

	static Position shark; // 아기 상어 위치 기록
	static int size = 2; // 아기 상어 크기
	static int exp = 0; // 아기 상어 먹은 횟수 (크기 증가 시 초기화)
	static int ans; // 총 이동한 거리

	static class Position { // x, y, 아기상어 크기, 아기상어 먹은 물고기 수
		int x, y, time;

		public Position(int x, int y, int time) {
			super();
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new int[N][N];

		boolean isEmpty = true;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					// 아기상어 위치 저장
					shark = new Position(i, j, 0);
					map[i][j] = 0;
				} else {
					if (map[i][j] != 0)
						isEmpty = false;
				}
			}
		}

		if (isEmpty) {
			System.out.println(0);
			System.exit(0);
		}

		bfs();

		sb.append(ans);
		sb.append("\n");

		System.out.println(sb);
	}

	static void bfs() {
		Queue<Position> q = new LinkedList<>();
		q.add(shark); // 큐에 아기상어 추가
		visited[shark.x][shark.y] = 1;
		while (true) {
			while (!q.isEmpty()) {
				Position cur = q.poll();
				for (int d = 0; d < 4; d++) {
					int dx = cur.x + dirX[d];
					int dy = cur.y + dirY[d];

					if (check(dx, dy)) {

						// 크기가 더 큰 물고기가 없고 방문하지 않은 곳인 경우 진입
						if (map[dx][dy] <= size && visited[dx][dy] == 0) {

							// 방문 표시
							visited[dx][dy] = visited[cur.x][cur.y] + 1;

							// 아기상어보다 작은 물고기가 존재할 경우 (이동 & 먹기)
							if (map[dx][dy] > 0 && map[dx][dy] < size) {
								q.add(new Position(dx, dy, cur.time + 1));
								feedList.add(new Position(dx, dy, cur.time + 1));
							}

							// 아기상어와 크기가 같은 물고기가 존재할 경우 (이동)
							// or 비어있을 경우 (이동)
							if (map[dx][dy] == size || map[dx][dy] == 0) {
								q.add(new Position(dx, dy, cur.time + 1));
							}
						}
					}
				}

			} // end inner while

			if (!feedList.isEmpty()) {
				eat();

				q.clear();
				visited = new int[N][N];

				q.add(shark);
				visited[shark.x][shark.y] = 1;
			} else {
				return;
			}

		}

	}

	static void eat() {
		Collections.sort(feedList, new Comparator<Position>() {

			@Override
			public int compare(Position o1, Position o2) {
				if (o1.time == o2.time) {
					if (o1.x == o2.x) {
						if (o1.y > o2.y) {
							return 1;
						} else {
							return -1;
						}
					} else {
						if (o1.x > o2.x) {
							return 1;
						} else {
							return -1;
						}
					}
				} else if (o1.time > o2.time) {
					return 1;
				} else {
					return -1;
				}
			};
		});

		Position cur = feedList.get(0);

		shark.x = cur.x;
		shark.y = cur.y;

		// 먹은 횟수가 사이즈와 같아질 경우
		if (++exp == size) {
			// 사이즈 증가, 먹은 횟수 초기화
			size++;
			exp = 0;
		}

		ans += cur.time;

		map[cur.x][cur.y] = 0;

		feedList.clear();
	}

	static boolean check(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

}