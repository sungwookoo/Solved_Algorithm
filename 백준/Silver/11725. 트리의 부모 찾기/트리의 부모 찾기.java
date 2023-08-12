import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Main {
	static boolean[] visited;
	static int[] p;
	static int N;
	static List<Integer>[] graph;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		visited = new boolean[N+1];
		p = new int[N+1];
		graph = new ArrayList[N+1];
		
		for(int i=1; i<=N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i=1; i< N; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			graph[from].add(to);
			graph[to].add(from);
		}
		
		dfs(1);
		
		StringBuilder sb = new StringBuilder();
		for(int i=2; i<=N; i++) {
			sb.append(p[i]).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void dfs(int index) {
		visited[index] = true;
		for(int a : graph[index]) {
			if(!visited[a]) {
				p[a] = index;
				dfs(a);
			}
		}
	}

}
