import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		
		for(int tc=1;tc<=T; tc++) {
			sb.append("#").append(tc).append(" ");
			
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			parents = new int[N+1];
			
			for(int i=1; i<=N; i++) {
				makeSet(i);
			}
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				if(Integer.parseInt(st.nextToken()) == 0) {
					union(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
				}
				else {
					sb.append(isUnion(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()))?1:0);
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	static void makeSet(int a) {
		parents[a] = a;
	}
	
	static int findSet(int a) {
		if(a == parents[a]) return a;
		else {
			return parents[a] = findSet(parents[a]);
		}
	}
	
	static void union(int a, int b) {
		if(findSet(a) == findSet(b)) return;
		parents[findSet(b)] = findSet(a);
	}
	
	static boolean isUnion(int a, int b) {
		return findSet(a) == findSet(b);
	}

}