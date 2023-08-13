import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static int cnt; // 그룹의 수
	static int cycle; // 사이클 끊은 횟수
	static int[] parent;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		cnt = 0; 
		cycle = 0;
		parent = new int[N+1];
		
		for(int i=1; i<=N; i++) parent[i] = i; 
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(find(a) == find(b)) { // 사이클 발생
				cycle++; 
				continue;
			}
			
			union(a, b);
		}
		
		for(int i=1; i<=N; i++) {
			if(parent[i] == i) cnt++; // 그룹 수
		}
		
		cnt--; // 모든 그룹 연결하기 위한 간선의 개수는 그룹의 수 - 1
		
		System.out.println(cnt+cycle);
		
	}
	
	static int find(int a) {
		if(parent[a] == a) return a;
		return parent[a] = find(parent[a]);
	}
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if(a > b) parent[a] = b;
		else parent[b] = a;
	}

}
