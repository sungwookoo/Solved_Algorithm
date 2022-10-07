import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, map[][];
	static int[] dirX = { 0, 1, 0, -1 }; // 우 하 좌 상
	static int[] dirY = { 1, 0, -1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 보드의 크기
		N = Integer.parseInt(br.readLine());

		map = new int[N + 1][N + 1];

		// 사과의 개수
		int K = Integer.parseInt(br.readLine());

		// 사과들의 위치
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			map[x][y] = 1;
		}

		// 방향 변환 횟수
		int L = Integer.parseInt(br.readLine());

		// 뱀의 방향 변환 정보들
		// X : 시간 / C : 회전 방향 ('L' = 왼쪽, 'D' = 오른쪽)

		char[] orders = new char[10000];

		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			char C = st.nextToken().charAt(0);
			orders[X] = C;
		}

		System.out.println(play(orders));
	}

	static int play(char[] orders) {
		Queue<Snake> q = new ArrayDeque<>();
		Queue<Point> history = new ArrayDeque<>();
		q.offer(new Snake(1, 1, 1, 1, 0, 0));
		map[1][1] = 2;

		// d = 0:우, 1:하, 2:좌, 3:상
		while (!q.isEmpty()) {
			Snake cur = q.poll();
			int hx = cur.hx;
			int hy = cur.hy;
			int tx = cur.tx;
			int ty = cur.ty;
			int d = cur.d;
			int t = cur.t;

			// 현재 시간에 지정된 명령이 있다면 아래 실행

			// 왼쪽으로 90도 회전
			if (orders[t] == 'L') {
				d = d == 0 ? 3 : d - 1;
			}

			// 오른쪽으로 90도 회전
			if (orders[t] == 'D') {
				d = d == 3 ? 0 : d + 1;
			}

//			먼저 뱀은 몸길이를 늘려 머리를 다음칸에 위치시킨다.
			int dx = hx + dirX[d];
			int dy = hy + dirY[d];

//			벽과 부딪히면 게임이 끝난다.
			if (dx < 1 || dy < 1 || dx > N || dy > N) {
				return t+1;
			}

//			자기자신의 몸과 부딪히면 게임이 끝난다.
			if (map[dx][dy] == 2) {
				return t+1;
			}

			history.offer(new Point(dx, dy));

//			만약 이동한 칸에 사과가 있다면, 그 칸에 있던 사과가 없어지고 꼬리는 움직이지 않는다.
			if (map[dx][dy] == 1) {
				map[dx][dy] = 2;
			}

//			만약 이동한 칸에 사과가 없다면, 몸길이를 줄여서 꼬리가 위치한 칸을 비워준다. 즉, 몸길이는 변하지 않는다.
			if (map[dx][dy] == 0) {
				map[dx][dy] = 2;

				// 기존 꼬리가 위치한 칸을 비운다.
				map[tx][ty] = 0;

				// 이동한 history를 따라 꼬리 위치를 바꿔준다.
				Point p = history.poll();
				tx = p.x;
				ty = p.y;

			}

			q.offer(new Snake(dx, dy, tx, ty, d, t + 1));

		}

		return -1;
	}

	static class Snake {
		int hx, hy, tx, ty, d, t;

		public Snake(int hx, int hy, int tx, int ty, int d, int t) {
			super();
			this.hx = hx;
			this.hy = hy;
			this.tx = tx;
			this.ty = ty;
			this.d = d;
			this.t = t;
		}
	}

	static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

}
