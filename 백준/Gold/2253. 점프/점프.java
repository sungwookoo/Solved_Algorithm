import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] no = new int[M];
		for(int i=0; i<M; i++) {
			no[i] = Integer.parseInt(br.readLine());
		}
		
		int[] arr = new int[N+1];
		for(int i=1; i<=N; i++) {
			boolean flag = false;
			for(int j=0; j<M; j++) {
				if(i == no[j]) {
					flag = true;
					break;
				}
			}
			arr[i] = flag? -1:i;
		}
		
		Queue<Pair> q = new ArrayDeque<>();
		q.add(new Pair(1, 1, 0));
		boolean[] visited = new boolean[N+1];
		visited[1] = true;
		int ans = -1;
		
		if(arr[2] == -1) {
			System.out.println(ans);
			System.exit(0);
		}
		
		int[] controller = {-1,0,1};
		
		while(!q.isEmpty()) {
			Pair cur = q.poll();
//			System.out.println(cur.cnt+"번째 : "+cur.num+", 직전 점프력 : "+cur.power);
			
			if(cur.num == 1) {
				q.add(new Pair(cur.num+cur.power, cur.power, 1));
				continue;
			}
			
			if(cur.num == N) {
				ans = cur.cnt;
				break;
			}
			
			for(int d=0; d<3; d++) {
				if(cur.power + controller[d] < 1) continue;
				int power = cur.power + controller[d];
				if(cur.num + power > N) continue;
			
				
				if(visited[cur.num + power]) continue;
				
				boolean flag = true;
				for(int i=0; i<M; i++) {
					if(cur.num + power == no[i]) flag = false;
				}
				
				if(!flag) continue;
				
				
				
				
				if(cur.num + power != N && N - (cur.num + power) < power-1) continue;
//				System.out.println(cur.num+" : "+ (N - (cur.num + power)));
				
				visited[cur.num + power] = true;
				q.add(new Pair(cur.num + power, power, cur.cnt+1));
			}
		}
		
		System.out.println(ans);
	}
	
	static class Pair {
		int num, power, cnt;

		public Pair(int num, int power, int cnt) {
			this.num = num;
			this.power = power;
			this.cnt = cnt;
		}		
	}

}
