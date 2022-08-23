import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	static class Edge {
		int from, to;

		public Edge(int from, int to) {
			super();
			this.from = from;
			this.to = to;
		}
	}

	static Edge[] edgeList;
	static int[] parents;

	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#"+tc+" ");
			
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			parents = new int[N + 1];
			edgeList = new Edge[M];

			for (int i = 1; i <= N; i++) {
				makeSet(i);
			}

			// 정점 연결
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				edgeList[i] = new Edge(from, to);
			}

			
			// MST 만들기
			int count = 0;
			for (Edge edge : edgeList) {
				// 싸이클이 발생하지 않았으면
				if (union(edge.from, edge.to)) {
					if (++count == N - 1) { // 연결 간선수가 (정점 수 - 1) 이면 최소 신장 트리 완성
						break;
					}
				}
			}
			
			
			int ans = 1;
			
			int[] findList = new int[N+1];
			for(int i=1; i<=N; i++) {
				findList[i] = find(i);
			}
			
			Arrays.sort(findList);
			
			int temp = findList[1];
			for(int i=2; i<=N; i++) {
				int cur = findList[i];
				if(temp != cur) {			
					ans ++;
					temp = cur;
				}
			}
			
			sb.append(ans+"\n");
			
		}
		
		System.out.println(sb);
		
		
	}

	static void makeSet(int v) {
		parents[v] = v;
	}

	static int find(int v) {
		if (v == parents[v])
			return v;
		else {
			return parents[v] = find(parents[v]);
		}
	}

	static boolean union(int a, int b) {
		// 둘이 부모 같으면 return
		if (find(a) == find(b)) {
			return false;
		}
		// b의 부모의 부모를 a로 바꾼다.
		parents[find(b)] = find(a);
		return true;
	}

}