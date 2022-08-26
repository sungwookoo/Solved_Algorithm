import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	static int K;
	static int[][] M;
	static int[][] Rotate;
	static int score;
	static boolean[] isQueue;
	static boolean[] isRotated;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			K = Integer.parseInt(br.readLine());
			M = new int[5][9];
			Rotate = new int[K][2];
			isQueue = new boolean[5];
			isRotated = new boolean[5];
			score = 0;
			// 자석의 날 자성정보 input
			// 1번부터
			// N극 : 0 , S극 : 1
			for (int i = 1; i <= 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= 8; j++) {
					M[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 회전정보 input
			// 1이면 시계방향, -1이면 반시계방향
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 2; j++) {
					Rotate[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < K; i++) {
				// 1. 인접날 자성 검사
				isQueue = new boolean[5];
				check(Rotate[i][0]);
//				System.out.println(Arrays.toString(isQueue));
			
//				for(int j=1; j<=4; j++) {
//					for(int k=1; k<=8; k++) {
//						System.out.print(M[j][k]+" ");
//					}
//					System.out.println();
//				}
//				System.out.println();
				
				rotate(Rotate[i][0], Rotate[i][1], 0);
				
//				for(int j=1; j<=4; j++) {
//					for(int k=1; k<=8; k++) {
//						System.out.print(M[j][k]+" ");
//					}
//					System.out.println();
//				}
//				System.out.println("---------------");
			}
			
			for (int i = 1; i <= 4; i++) {
				if(M[i][1] == 1) {
					score += Math.pow(2, i - 1);
				}
			}
			sb.append("#"+tc+" ");
			sb.append(score+"\n");
			
		}
		
		System.out.println(sb);

	}

	/*
	 * - 1 번 자석에서 빨간색 화살표 위치에 있는 날의 자성이 N 극이면 0 점, S 극이면 1 점을 획득한다.
	 * 
	 * - 2 번 자석에서 빨간색 화살표 위치에 있는 날의 자성이 N 극이면 0 점, S 극이면 2 점을 획득한다.
	 * 
	 * - 3 번 자석에서 빨간색 화살표 위치에 있는 날의 자성이 N 극이면 0 점, S 극이면 4 점을 획득한다.
	 * 
	 * - 4 번 자석에서 빨간색 화살표 위치에 있는 날의 자성이 N 극이면 0 점, S 극이면 8 점을 획득한다.
	 */

	private static void rotate(int magnet, int direction, int count) {
		int nextMagnet = magnet + 1;
		nextMagnet = nextMagnet % 4 == 0 ? nextMagnet : nextMagnet % 4;
		
		if(count == 4) {
			return;
		}
		
		
		// 2. 회전
		if (isQueue[magnet]) {
			int temp = 0;

			// 반시계방향 회전
			if (direction == -1) {
				temp = M[magnet][1];
				for (int i = 1; i < 9; i++) {
					M[magnet][i - 1] = M[magnet][i];
				}
				M[magnet][8] = temp;
//				System.out.println(magnet+"반시계");
//				System.out.println(Arrays.toString(M[magnet]));
			}

			// 시계방향 회전
			else {
				temp = M[magnet][8];
				for (int i = 7; i >= 0; i--) {
					M[magnet][i + 1] = M[magnet][i];
				}
				M[magnet][1] = temp;
//				System.out.println(magnet+"시계");
//				System.out.println(Arrays.toString(M[magnet]));
			}
			
			isQueue[magnet] = false;
		}
		
		rotate(nextMagnet, direction == 1? -1:1, count+1);
		

	}

	static void check(int num) {
		if (num == 1) {
			isQueue[1] = true;
			if (M[1][3] != M[2][7]) {
				isQueue[2] = true;
			} else
				return;

			if (M[2][3] != M[3][7]) {
				isQueue[2] = true;
				isQueue[3] = true;
			} else
				return;

			if (M[3][3] != M[4][7]) {
				isQueue[3] = true;
				isQueue[4] = true;
			} else
				return;
		}

		else if (num == 2) {
			isQueue[2] = true;
			if (M[1][3] != M[2][7]) {
				isQueue[1] = true;
			}
			if (M[2][3] != M[3][7]) {
				isQueue[2] = true;
				isQueue[3] = true;
			} else
				return;
			if (M[3][3] != M[4][7]) {
				isQueue[3] = true;
				isQueue[4] = true;
			} else
				return;

		} else if (num == 3) {
			isQueue[3] = true;
			if (M[3][3] != M[4][7]) {
				isQueue[4] = true;
			}
			if (M[2][3] != M[3][7]) {
				isQueue[2] = true;
				isQueue[3] = true;
			} else
				return;
			if (M[1][3] != M[2][7]) {
				isQueue[1] = true;
				isQueue[2] = true;
			} else
				return;

		} else {
			isQueue[4] = true;
			if (M[3][3] != M[4][7]) {
				isQueue[3] = true;
				
			} else
				return;
			if (M[2][3] != M[3][7]) {
				isQueue[2] = true;
				isQueue[3] = true;
			} else
				return;
			if (M[1][3] != M[2][7]) {
				isQueue[1] = true;
				isQueue[2] = true;
			} else
				return;
		}
	}

}