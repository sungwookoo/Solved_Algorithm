import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int V, E;

    static ArrayList<ArrayList<Integer>> graph;
    static int[] villager;
    static boolean[] visited;
    static int ans;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        V = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        E = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        visited = new boolean[V + 1];
        villager = new int[V + 1];

        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        dfs(a,b,0);

        StringBuilder sb = new StringBuilder();
        sb.append(ans==0?-1:ans);
        System.out.println(sb);
    }



    static void dfs(int start, int end, int count) {
        if(start == end) {
            ans = count;
            return;
        }

        visited[start] = true;
        int cur;
        for (int i = 0; i < graph.get(start).size(); i++) {
            cur = graph.get(start).get(i);
            if (!visited[cur]) {
                visited[cur] = true;
                dfs(cur,end,count+1);
            }
        }
    }
}
