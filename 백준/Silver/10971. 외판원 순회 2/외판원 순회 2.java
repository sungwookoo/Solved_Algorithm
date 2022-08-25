import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.StringTokenizer;

import org.omg.CORBA.PUBLIC_MEMBER;

public class Main {

	static int N;
	static int[][] arr;
	static boolean[] visited;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		
		ans = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			visited = new boolean[N];
			visited[i] = true;
			dfs(i, i, 0, 0);
		}

		System.out.println(ans);

	}

	static void dfs(int start, int now, int sum, int count) {
		
		if (count == N - 1) {
			if (arr[now][start] != 0) {
				sum += arr[now][start];
				if (sum < ans) {
					ans = sum;
				}
			}
			return;
		}

		for (int i = 0; i < N; i++) {
			if (visited[i] || arr[now][i] == 0)
				continue;

			visited[i] = true;
			dfs(start, i, sum + arr[now][i], count + 1);
			visited[i] = false;
		}
	}
}