import java.util.*;
import java.io.*;

public class Main {

    static int N, K;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            q.add(i);
        }

        int count = 1;
        List<Integer> ans = new ArrayList<>();
        while (!q.isEmpty()) {
            int cur = q.poll();
            if (count == K) {
                ans.add(cur);
                count = 0;
            } else {
                q.add(cur);
            }
            count++;
        }

        String str = "<";
        for (int i = 0; i < N; i++) {
            if (i == N - 1) {
                str = str + ans.get(i);
                break;
            }
            str = str + ans.get(i) + ", ";
        }
        str += ">";
        System.out.println(str);

    }

}
