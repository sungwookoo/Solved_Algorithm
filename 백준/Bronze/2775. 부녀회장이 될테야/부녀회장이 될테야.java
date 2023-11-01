import java.io.*;

public class Main {

    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int tc = 0; tc < t; tc++) {
            ans = 0;
            int k = Integer.parseInt(br.readLine());
            int n = Integer.parseInt(br.readLine());

            sb.append(go(k, n)).append("\n");
        }

        System.out.println(sb);
    }

    static int go(int k, int n) {
        if (n == 0) {
            return 0;
        }
        if (k == 0) {
            return n;
        }

        return go(k - 1, n) + go(k, n - 1);
    }
}
