import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {
	// 백준 9205 맥주 마시면서 걸어가기
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= t; tc++) {
			int n = Integer.parseInt(br.readLine());
			int[][] dist = new int[n+2][n+2];
			boolean[][] d = new boolean[n+2][n+2];
			List<int[]> list = new ArrayList<>();
			
			for(int i=0; i<n+2; i++) {
				st = new StringTokenizer(br.readLine());
				list.add(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
			}
			
			
			for(int i=0; i<n+2; i++) {
				for(int j=0; j<n+2; j++) {
					int[] point1 = list.get(i);
					int[] point2 = list.get(j);
					dist[i][j] = Math.abs(point1[0] - point2[0]) + Math.abs(point1[1] - point2[1]);
					
					if(dist[i][j] <= 50 * 20) {
						d[i][j] = true;
					}
				}
			}
			
			for(int k=0; k<n+2; k++) {
				for(int i=0; i<n+2; i++) {
					for(int j=0; j<n+2; j++) {
						if(d[i][k] & d[k][j]) {
							d[i][j] = true;
						}
					}
				}
			}
			
			System.out.println(d[0][n+1]?"happy":"sad");
			
			
		}

	}
}