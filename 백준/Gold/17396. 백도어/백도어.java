import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int arr[] = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        List<int[]>[] graph = new ArrayList[N];
        for(int i=0; i<N; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i =0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            graph[start].add(new int[]{end, time});
            graph[end].add(new int[]{start, time});
        }

        long[] dist = new long[N];
        for(int i=0; i<N; i++) {
            dist[i] = Long.MAX_VALUE;
        }

        dist[0] = 0;

        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparing(a -> a[1]));
        q.add(new int[]{0,0});

        while(!q.isEmpty()) {
            int[] curArr = q.poll();
            int cur = curArr[0];
            int curTime = curArr[1];

            if(curTime > dist[cur]) {
                continue;
            }

            for(int[] neighbor : graph[cur]) {
                int next = neighbor[0];
                int time = neighbor[1];

                if(next != N-1 && arr[next] == 1) continue;

                if(dist[next] > dist[cur] + time) {
                    dist[next] = dist[cur] + time;
                    q.add(new int[] {next, (int)dist[cur] + time});
                }
            }
        }
        System.out.println(dist[N-1] == Long.MAX_VALUE ? -1 : dist[N-1]);
    }
}
