import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int map[][];
	static int[] dirX = { 0, -1, 1, 0, 0 };
	static int[] dirY = { 0, 0, 0, -1, 1 };
	static int N, M, K;
	static List<V> vList;
	static int time;

	static class V implements Comparable<V>{
		int x, y, c, d;

		public V(int x, int y, int c, int d) {
			super();
			this.x = x;
			this.y = y;
			this.c = c;
			this.d = d;
		}

		@Override
		public int compareTo(V o) {
			return o.c - this.c;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			vList = new ArrayList<>();
			time = 0;
			map = new int[N][N];

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				vList.add(new V(x, y, c, d));
			}
			
			sb.append("#").append(tc).append(" ").append(simulate()).append("\n");
		}
		
		System.out.println(sb);
		
		
	}

	static int simulate() {
		while (time < M) {

			// 이동
			for (int i = 0; i < vList.size(); i++) {
				V cur = vList.get(i);

				switch (cur.d) {
				case 1:
					if (cur.x == 1) {
						if (cur.c == 1) {
							vList.remove(i);
							continue;
						}
						cur.x--;
						cur.d = 2;
						cur.c = cur.c / 2;
					} else {
						cur.x--;
					}
					break;
				case 2:
					if (cur.x == N - 2) {
						if (cur.c == 1) {
							vList.remove(i);
							continue;
						}
						cur.x++;
						cur.d = 1;
						cur.c = cur.c / 2;
					} else {
						cur.x++;
					}
					break;
				case 3:
					if (cur.y == 1) {
						if (cur.c == 1) {
							vList.remove(i);
							continue;
						}
						cur.y--;
						cur.d = 4;
						cur.c = cur.c / 2;
					} else {
						cur.y--;
					}
					break;
				case 4:
					if (cur.y == N - 2) {
						if (cur.c == 1) {
							vList.remove(i);
							continue;
						}
						cur.y++;
						cur.d = 3;
						cur.c = cur.c / 2;
					} else {
						cur.y++;
					}
					break;
				}

				vList.set(i, cur);
			}
			
			Collections.sort(vList);
			
			// 합치기
			if (vList.size() > 1) {
				for (int i = 0; i < vList.size(); i++) {
					V v1 = vList.get(i);
					for (int j = i+1; j < vList.size(); j++) {
						V v2 = vList.get(j);
						if(v1.x == v2.x && v1.y == v2.y) {
							v1.c += v2.c;
							vList.remove(j--);
						}
					}
				}
			}

			// 시간 증가
			time++;
		}
		
		int count = 0;
		for(int i=0; i<vList.size(); i++) {
			count += vList.get(i).c;
		}
		
		return count;
		
	}
}
