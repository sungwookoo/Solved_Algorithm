import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, B, H[], min;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			H = new int[N];
			min = Integer.MAX_VALUE;
			visited = new boolean[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				H[i] = Integer.parseInt(st.nextToken());
			}

			subset(0);

			sb.append("#").append(tc).append(" ").append(min).append("\n");

		}

		System.out.println(sb);
	}

	static void subset(int index) {
		if (index == N) {

			int sum = 0;
			for (int i = 0; i < N; i++) {
				if (visited[i]) {
					sum += H[i];
				}
			}
			if (sum >= B)
				min = Math.min(min, Math.abs(sum - B));
			return;
		}

		visited[index] = true;
		subset(index + 1);

		visited[index] = false;
		subset(index + 1);

	}

}
