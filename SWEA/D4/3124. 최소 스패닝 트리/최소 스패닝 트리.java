import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	static class Edge implements Comparable<Edge> {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}

	static List<Edge> edgeList;
	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");

			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());

			edgeList = new ArrayList<>();
			parents = new int[V + 1];

			for (int i = 1; i <= V; i++) {
				makeSet(i);
			}

			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				edgeList.add(new Edge(from, to, weight));
			}

			// 가중치에 따라 오름차순 정렬
			Collections.sort(edgeList);

			long ans = 0;
			int count = 0;

			for (Edge edge : edgeList) {
				// 싸이클이 발생하지 않았으면
				if (union(edge.from, edge.to)) {
					ans += edge.weight;
					if (++count == V - 1) { // 연결 간선수가 (정점 수 - 1) 이면 최소 신장 트리 완성
						break;
					}
				}
			}
			sb.append(ans);

			sb.append("\n");
		}
		System.out.println(sb);
	}

	static void makeSet(int a) {
		parents[a] = a;
	}

	static int findSet(int a) {
		if (a == parents[a])
			return a;
		else {
			return parents[a] = findSet(parents[a]);
		}
	}

	static boolean union(int a, int b) {
		if (findSet(a) == findSet(b))
			return false;
		parents[findSet(b)] = findSet(a);
		return true;
	}

	static boolean isUnion(int a, int b) {
		return findSet(a) == findSet(b);
	}

}