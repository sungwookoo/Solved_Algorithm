import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static boolean flag = false;
	static List<Integer>[] list;
	static boolean[] visited;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		list = new ArrayList[N];
		visited = new boolean[N];
		
		for(int i=0; i<N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			list[u].add(v);
			list[v].add(u);
		}
		
		for (int i = 0; i < N; i++) {
			dfs(i, 1);
			if(flag) break;
		}
		
		sb.append(flag?1:0);
		System.out.println(sb);

	}

	static void dfs(int start, int count) {
		if (count == 5) {
			flag = true;
			return;
		}
		visited[start] = true;
		for (int a : list[start]) {
			if (!visited[a]) {
				dfs(a, count + 1);
			}

			if (flag) return;
				
		}
		visited[start] = false;
	}
}