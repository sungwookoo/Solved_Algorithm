import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int arr[];
	static List<Integer>[] list;

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N + 1];
		list = new ArrayList[N + 1];

		for (int i = 0; i <= N; i++) {
			list[i] = new ArrayList<>();
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			int num = Integer.parseInt(st.nextToken());
			if(num == -1) continue;
			list[num].add(i);
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());
			arr[num] += val;
		}
		
		dfs(1);
		
		for(int i=1; i<=N; i++) {
			sb.append(arr[i]).append(" ");
		}
		
		System.out.println(sb);

	}
	
	static void dfs(int start) {
		for(int next: list[start]) {
			arr[next] += arr[start];
			dfs(next);
		}
	}
}
