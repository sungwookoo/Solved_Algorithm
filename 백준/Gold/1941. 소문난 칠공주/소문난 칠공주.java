import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

	static char map[][], N;
	static boolean visited[];
	static int[] dirX = { -1, 1, 0, 0 };
	static int[] dirY = { 0, 0, -1, 1 };
	static int[] combX, combY;
	static int ans;
	
	// 조합
	static int[] numbers;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ans = 0;
		N = 5;

		map = new char[N][N];

		for (int i = 0; i < N; i++) {
			char[] input = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				map[i][j] = input[j];
			}
		}
		
		numbers = new int[7];
		combX = new int[25];
		combY = new int[25];
		
		
		for(int i=0; i<25; i++) {
			combX[i] = i/5;
			combY[i] = i%5;
		}

		comb(0, 0, 0);

		System.out.println(ans);
	}

	
	static void comb(int count, int index, int depth) {
		if(count == 7) {
			bfs();
//			System.out.println(Arrays.toString(numbers));
			return;
		}
		
		if(depth == 25) return;
		
		numbers[index] = depth;
		comb(count+1, index+1, depth+1);
		comb(count, index, depth+1);
		
	}
	
	static void bfs() {
		Queue<Integer> queue = new ArrayDeque<>();
		visited = new boolean[7];
		
		visited[0] = true;
		queue.add(numbers[0]);
		int count = 1;
		int sCount = 0;
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			if(map[combX[cur]][combY[cur]] == 'S') sCount ++;
			for(int d=0; d<4; d++) {
				for(int i=1; i<7; i++) {
					if(!visited[i] && combX[cur]+dirX[d] == combX[numbers[i]] && combY[cur]+dirY[d] == combY[numbers[i]]) {
						visited[i] = true;
						queue.add(numbers[i]);
						count++;
					}
				}
			}
		}
		
		if(count == 7) {
			if(sCount > 3) {
				ans++;
			}
		}
	}
	

	static boolean isOut(int dx, int dy) {
		return dx < 0 || dy < 0 || dx > N - 1 || dy > N - 1;
	}

}
