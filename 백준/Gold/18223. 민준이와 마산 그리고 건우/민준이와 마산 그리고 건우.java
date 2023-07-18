import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int V, E, P;
	static boolean[] visited;
	static int[] D;
	static List<Node>[] adjList;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken()); // 정점
		E = Integer.parseInt(st.nextToken()); // 간선
		P = Integer.parseInt(st.nextToken()); // 건우 위치
		
		D = new int[V + 1];
		
		adjList = new ArrayList[V + 1];
		for (int i = 1; i <= V; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adjList[from].add(new Node(to, weight));
			adjList[to].add(new Node(from, weight));
		}

		dijkstra(1);
		int d1 = D[V];
		
		int d2 = D[P];
		
		dijkstra(P);
		int d3 = D[V];
		
		
		System.out.println((d1 == (d2 + d3)) ? "SAVE HIM" : "GOOD BYE");
	}

	static void dijkstra(int start) {

		Arrays.fill(D, Integer.MAX_VALUE);

		PriorityQueue<Node> queue = new PriorityQueue<>();
		queue.add(new Node(start, 0));
		D[start] = 0;

		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			
			for (int i = 0; i < adjList[cur.to].size(); i++) {
				Node next = adjList[cur.to].get(i); // 가려는 노드
				if (D[next.to] > next.weight + D[cur.to]) {
					D[next.to] = next.weight + D[cur.to];
					queue.add(new Node(next.to, D[next.to]));
				}
			}
		}

	}

	static class Node implements Comparable<Node> {

		int to, weight;

		public Node(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}

	}

}
