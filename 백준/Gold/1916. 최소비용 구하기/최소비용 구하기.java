import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        long[][] arr = new long[N+1][N+1];
        long[] dist = new long[N+1];

        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                if(i == j) {
                    arr[i][j] = 0;
                    continue;
                }
                arr[i][j] = Integer.MAX_VALUE;
            }
        }

        for(int i=1; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            arr[start][end] = Math.min(arr[start][end], weight);
        }

        st = new StringTokenizer(br.readLine());
        int startA = Integer.parseInt(st.nextToken());
        int endA = Integer.parseInt(st.nextToken());

        for(int i=1; i<=N; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        Queue<Integer> q = new PriorityQueue<>();
        dist[startA] = 0;
        q.add(startA);


        while(!q.isEmpty()) {
            int cur = q.poll();

            for(int next=1; next<=N; next++) {
                if(arr[cur][next] != Integer.MAX_VALUE && dist[next] > dist[cur] + arr[cur][next]) {
                    dist[next] = dist[cur] + arr[cur][next];
                    q.add(next);
                }
            }
        }

        System.out.println(dist[endA] == Integer.MAX_VALUE ? 0 : dist[endA]);

    }
}
