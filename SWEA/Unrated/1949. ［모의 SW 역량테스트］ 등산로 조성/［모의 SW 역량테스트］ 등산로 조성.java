import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	// 상 -> 하 -> 좌 -> 우
	static int[] dirX = { -1, 1, 0, 0 };
	static int[] dirY = { 0, 0, -1, 1 };

	/*
	 * N: 지도 한 변의 길이 
	 * K: 최대 공사 가능 깊이 
	 * map: 지도 
	 * max: 최대 길이 
	 * top: 가장 높은 봉우리 높이
	 */
	static int N, K, map[][], max, top;
	
	static boolean visited[][]; // DFS 방문 체크 배열

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			max = Integer.MIN_VALUE;
			top = Integer.MIN_VALUE;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					top = Math.max(map[i][j], top);
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// 가장 높은 봉우리에서만 DFS 진입
					if (map[i][j] == top) {
						// 방문 배열 초기화
						visited = new boolean[N][N];
						// 시작 지점 방문 체크
						visited[i][j] = true;
						dfs(i, j, 1, false);
					}
				}
			}

			sb.append("#").append(tc).append(" ").append(max).append("\n");

		}
		System.out.println(sb);
	}

	/*
	 * x, y: 열, 행
	 * depth: DFS 깊이 (연결한 지형 길이)
	 * flag: 공사 여부 (지형을 깎는 공사는 단 한 번만 가능하다)
	 */
	static void dfs(int x, int y, int depth, boolean flag) {
		// 현재 depth가 최대 길이라면 max 갱신
		max = Math.max(max, depth);

		// 상하좌우 이동
		for (int d = 0; d < 4; d++) {
			int dx = x + dirX[d];
			int dy = y + dirY[d];

			// 지도를 벗어나거나 이미 방문한 지형이라면 continue
			if (isOut(dx, dy) || visited[dx][dy])
				continue;

			// 지형을 깎기 전(공사 전) 일 경우
			if (!flag) {
				// 이동할 곳의 지형의 높이가 더 낮은 경우 (지형을 깎을 필요가 없음)
				if (map[dx][dy] < map[x][y]) {
					// 방문 체크
					visited[dx][dy] = true;
					// 현재 공사 여부(flag) 그대로 다음 지형으로 이동
					dfs(dx, dy, depth + 1, flag);
					// 방문 여부 원상복구
					visited[dx][dy] = false;
				}
				// 이동할 곳의 지형과 높이가 같다면 (지형을 깎아야함)
				else if (map[dx][dy] == map[x][y]) {
					// 깎기(공사)전 높이 저장
					int temp = map[dx][dy];
					
					// 방문 체크
					visited[dx][dy] = true;
					// 지형 깎기(공사)
					map[dx][dy] = map[x][y] - 1;
					
					// 공사 여부 true로 변경 후 다음 지형으로 이동
					dfs(dx, dy, depth + 1, true);
					
					// 방문 여부 원상복구
					visited[dx][dy] = false;
					// 깎기(공사)전 높이로 원상복구
					map[dx][dy] = temp;
				}
				
				// 이동할 곳의 지형의 높이보다 더 높은 곳이면
				else {
					// 최대 K만큼 깎았을 때 연결이 가능하다면 (현재 지형과 이동할 지형의 차보다 K가 더 크다면)
					if (Math.abs(map[dx][dy] - map[x][y]) < K) {
						// 깎기(공사)전 높이 저장
						int temp = map[dx][dy];
						
						// 방문 체크
						visited[dx][dy] = true;
						
						// 지형 깎기(공사)
						map[dx][dy] = map[x][y] - 1;
						
						// 공사 여부 true로 변경 후 다음 지형으로 이동
						dfs(dx, dy, depth + 1, true);
						
						// 방문 여부 원상복구
						visited[dx][dy] = false;
						// 깎기(공사)전 높이로 원상복구
						map[dx][dy] = temp;
					}
				}
			}

			// 지형을 깎은 후(공사 후)일 경우
			else {
				// 이동할 곳의 지형의 높이가 더 낮을 경우
				if (map[dx][dy] < map[x][y]) {
					// 방문 체크
					visited[dx][dy] = true;
					// 다음 지형으로 이동
					dfs(dx, dy, depth + 1, flag);
					// 방문 여부 원상 복구
					visited[dx][dy] = false;
				}
			}
		}
	}

	static boolean isOut(int x, int y) {
		return x < 0 || y < 0 || x > N - 1 || y > N - 1;
	}
}
