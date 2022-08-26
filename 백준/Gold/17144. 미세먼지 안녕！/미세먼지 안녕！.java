import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// 백준 17144 미세먼지 안녕!

	static int N, M, T;
	static int map[][];
	static int[] dirX = { -1, 1, 0, 0 };
	static int[] dirY = { 0, 0, -1, 1 };
	static int airX, airY; // 공기청정기 하단 좌표
	static int ans;

	// ┌←────────┐
	// ↓ ↑
	// -1ㅡㅡㅡㅡㅡ→┘
	// -1───────→┐
	// ↑ ↓
	// └←ㅡㅡㅡㅡㅡㅡ┘

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		map = new int[N + 1][M + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == -1) {
					airX = i;
					airY = j;
				}
			}
		}

		simulate();
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (map[i][j] == -1) {
					continue;
				}
				ans += map[i][j];
			}
		}

		sb.append(ans);
		System.out.println(sb);
		
	}

	static void simulate() {
		for (int t = 0; t < T; t++) {
			int[][] copy = new int[N + 1][M + 1];
			
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= M; j++) {
					copy = spread(i, j, copy);
				}
			}
//			printMap(copy);

			
			// 공기청정기 상부 
			
			// 좌변
			for(int i=airX-1; i>1; i--) {
				if(copy[i][1] == -1) {
					continue;
				}
				copy[i][1] = copy[i-1][1];
			}
			
			// 상변
			for(int j=1; j<M; j++) {
				copy[1][j] = copy[1][j+1];
			}
			
			// 우변
			for(int i=1; i<airX-1; i++) {
				copy[i][M] = copy[i+1][M];
			}
			
			// 하변
			for(int j=M; j>1; j--) {
				if(copy[airX-1][j-1] == -1) {
					copy[airX-1][j] = 0;
					continue;
				}
				copy[airX-1][j] = copy[airX-1][j-1];
			}
			
			
			// 공기청정기 하부
			
			// 좌변
			for(int i= airX; i<N; i++) {
				if(copy[i][1] == -1) {
					continue;
				}
				copy[i][1] = copy[i+1][1];
			}
			
			// 하변
			for(int j=1; j<M; j++) {
				copy[N][j] = copy[N][j+1];
			}
			
			// 우변
			for(int i=N; i>airX; i--) {
				copy[i][M] = copy[i-1][M];
			}
			
			// 상변
			for(int j=M; j>1; j--) {
				if(copy[airX][j-1] == -1) {
					copy[airX][j] = 0;
					continue;
				}
				copy[airX][j] = copy[airX][j-1];
			}
			
			
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= M; j++) {
					map[i][j] = copy[i][j];
				}
			}
			
//			printMap(copy);
//			System.out.println("-------------------");
		}
	}
	// ┌←────────┐
	// ↓ ↑
	// -1ㅡㅡㅡㅡㅡ→┘
	// -1───────→┐
	// ↑ ↓
	// └←ㅡㅡㅡㅡㅡㅡ┘

	// [1초 동안 아래 두 가지 이벤트가 순서대로 발생]

	// 1. 미세먼지가 인접한 네 방향으로 확산 (공기청정기 or 범위밖 확산X)
	// 확산되는 양은 map[r][c] / 5
	// 확산되고 남는 양은 map[r][c]-(map[r][c]/5)*(확산된방향의개수)

	// 2. 공기청정기 작동
	// 위쪽 공기청정기 바람은 반시계방향, 아래쪽은 시계방향 (바깥쪽만 회전)
	// 1초에 한칸씩 회전
	// 공기청정기로 들어간 미세먼지는 정화(사라짐)

	static int[][] spread(int i, int j, int[][] copy) {
		if (map[i][j] > 4) {
			int count = 0;
			for (int d = 0; d < 4; d++) {
				int dx = i + dirX[d];
				int dy = j + dirY[d];

				if (!check(dx, dy) || map[dx][dy] == -1)
					continue;
				count++;
				copy[dx][dy] += map[i][j] / 5;
			}

			copy[i][j] += map[i][j] - (map[i][j] / 5) * count;

		} else {
			copy[i][j] += map[i][j];
		}

		return copy;
	}

	// 2. 공기청정기 작동
	// 위쪽 공기청정기 바람은 반시계방향, 아래쪽은 시계방향 (바깥쪽만 회전)
	// 1초에 한칸씩 회전
	// 공기청정기로 들어간 미세먼지는 정화(사라짐)

	static void work() {

	}

	static boolean check(int x, int y) {
		return x > 0 && x <= N && y > 0 && y <= M;
	}

	static void printMap(int[][] map) {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				System.out.printf("%2d ", map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

	static void copyToMap(int[][] copy) {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				map[i][j] = copy[i][j];
			}
		}
	}

}