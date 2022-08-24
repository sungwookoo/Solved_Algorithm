import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	static int S;
	static Queue<Status> q;
	static int ans;
	static boolean visited[][] = new boolean[1001][1001];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = Integer.parseInt(br.readLine());
		q = new LinkedList<>();
		q.add(new Status(1, 0, 0));
		visited[1][0] = true;
		bfs();

		System.out.println(ans);
	}

	static void bfs() {
		while (!q.isEmpty()) {
			Status poll = q.poll();

			if (poll.s == S) {
				ans = poll.t;
				return;
			}

			// 연산 1. 화면에 있는 이모티콘을 모두 복사해서 클립보드에 저장한다.
			// 단, 클립보드에 이모티콘을 복사하면 이전에 클립보드에 있던 내용은 덮어쓰기 된다.
			q.add(new Status(poll.s, poll.s, poll.t + 1)); // 연산 1 (복사)

			// 연산 2. 클립보드에 있는 모든 이모티콘을 화면에 붙여넣기 한다.
			// 단, 클립보드가 비어있는 상태에서는 붙여 넣기를 할 수 없다.
			if (poll.c > 0 && poll.s + poll.c <= S && !visited[poll.s + poll.c][poll.c]) {
				q.add(new Status(poll.s + poll.c, poll.c, poll.t + 1)); // 연산 2 (붙여넣기)
				visited[poll.s + poll.c][poll.c] = true;
			}


			// 연산 3. 화면에 있는 이모티콘 중 하나를 삭제한다.
			// 화면이 비어있는 상태에서는 삭제를 할 수 없다.
			if (poll.s > 0 && !visited[poll.s - 1][poll.c]) {
				q.add(new Status(poll.s - 1, poll.c, poll.t + 1));
				visited[poll.s - 1][poll.c] = true;
			}

		}

	}

	static class Status {
		int s, c, t; // 화면의 이모티콘 개수, 클립보드의 이모티콘 개수, 시간

		public Status(int s, int c, int t) {
			super();
			this.s = s;
			this.c = c;
			this.t = t;
		}

	}

}
