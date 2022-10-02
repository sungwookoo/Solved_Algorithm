import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class Marble {
		int rx, ry, bx, by, t;

		public Marble(int rx, int ry, int bx, int by, int t) {
			super();
			this.rx = rx;
			this.ry = ry;
			this.bx = bx;
			this.by = by;
			this.t = t;
		}

	}

	static int N, M;
	static char[][] map;
	static boolean[][][][] visited;
	static Marble marble;
	static int[] dirX = { -1, 0, 1, 0 };
	static int[] dirY = { 0, 1, 0, -1 };
	static int goalX, goalY;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];

		int rx = 0, ry = 0, bx = 0, by = 0;

		for (int i = 0; i < N; i++) {
			char[] input = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				map[i][j] = input[j];
				if (map[i][j] == 'R') {
					rx = i;
					ry = j;
				}
				else if (map[i][j] == 'B') {
					bx = i;
					by = j;
				}
				else if(map[i][j] == 'O') {
					goalX = i;
					goalY = j;
				}
			}
		}

		marble = new Marble(rx, ry, bx, by, 1);

		visited = new boolean[N][M][N][M];
		
		System.out.println(bfs());
	}

	static int bfs() {
		Queue<Marble> q = new ArrayDeque<>();
		q.offer(marble);
		visited[marble.rx][marble.ry][marble.bx][marble.by] = true;

		while (!q.isEmpty()) {
			Marble poll = q.poll();

			if (poll.t > 10) {
				return -1;
			}

			for (int d = 0; d < 4; d++) {
				int dxR = poll.rx;
				int dyR = poll.ry;
				int dxB = poll.bx;
				int dyB = poll.by;
				
				boolean goalR = false;
				boolean goalB = false;

				while(map[dxR + dirX[d]][dyR+dirY[d]] != '#') {
					dxR += dirX[d];
					dyR += dirY[d];
					
					if(dxR == goalX && dyR == goalY) {
						goalR = true;
						break;
					}
				}
				
				while(map[dxB + dirX[d]][dyB+dirY[d]] != '#') {
					dxB += dirX[d];
					dyB += dirY[d];
					
					if(dxB == goalX && dyB == goalY) {
						goalB = true;
						break;
					}
				}
				
				if(goalB) continue;
				if(goalR && !goalB) {
					return poll.t;
				}
				
				if(dxR == dxB && dyR == dyB) {
					if(d == 0) { // 상
						if(poll.rx>poll.bx) dxR -= dirX[d];
						else dxB -= dirX[d];
					} 
					else if (d == 1) { // 우
						if(poll.ry<poll.by) dyR -= dirY[d];
						else dyB -= dirY[d];
					} 
					else if (d == 2) { // 하
						if(poll.rx<poll.bx) dxR -= dirX[d];
						else dxB -= dirX[d];
					} 
					else { // 좌
						if(poll.ry>poll.by) dyR -= dirY[d];
						else dyB -= dirY[d];
					}
					
				}
				
				if (!visited[dxR][dyR][dxB][dyB]) {
					visited[dxR][dyR][dxB][dyB] = true;
					q.offer(new Marble(dxR, dyR, dxB, dyB, poll.t + 1));
				}
			}

		}

		return -1;
	}
}
