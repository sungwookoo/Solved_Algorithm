import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	// 백준 1753 최단경로

	static class Node implements Comparable<Node> {
		int to, weight;

		public Node(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}

	}

	static int V, E;
	static int[] D;
	static boolean[] visited;
	static List<Node>[] adjList;
	static int start;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		start = Integer.parseInt(br.readLine());
		adjList = new ArrayList[V + 1];
		D = new int[V + 1];
		Arrays.fill(D, Integer.MAX_VALUE);
		visited = new boolean[V + 1];

		for (int i = 1; i <= V; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adjList[from].add(new Node(to, weight));
		}

		dijkstraPQ(start);

		for (int i = 1; i <= V; i++) {
			if (D[i] == Integer.MAX_VALUE)
				System.out.println("INF");
			else {
				System.out.println(D[i]);
			}
		}

	}

	private static void dijkstraPQ(int s) {
		PriorityQueue<Node> q = new PriorityQueue<>();
		q.offer(new Node(s, 0));
		D[s] = 0; // 출발정점 처리

		// 테스트
//		for(int j=1; j<=V; j++) {
//			if(D[j] == Integer.MAX_VALUE) {
//				System.out.print("INF"+" ");
//			}
//			else System.out.print(D[j]+" ");
//		}
//		System.out.println();

		while (!q.isEmpty()) {
			Node cur = q.poll();
			if (visited[cur.to])
				continue;
			visited[cur.to] = true;

			
			
			// 현재 노드가 갈수있는 모든 노드들 순회
			for (int i = 0; i < adjList[cur.to].size(); i++) {
				Node dest = adjList[cur.to].get(i); // 갈 수 있는 노드
				if (D[dest.to] > dest.weight + D[cur.to]) {
					D[dest.to] = dest.weight + D[cur.to];
					q.offer(new Node(dest.to, D[dest.to]));
				}
				
				// 테스트
				
//				System.out.println("@@"+cur.to);
//				
//				for(int j=1; j<=V; j++) {
//					if(D[j] == Integer.MAX_VALUE) {
//						System.out.print("INF"+" ");
//					}
//					else System.out.print(D[j]+" ");
//				}
//				System.out.println();
				
			}
		}
	}
}