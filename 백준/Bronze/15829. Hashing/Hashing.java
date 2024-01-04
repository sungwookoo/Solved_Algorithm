import java.io.*;

public class Main {

    static int a;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long ans = 0;
        a = Integer.parseInt(br.readLine());
        char[] arr = br.readLine().toCharArray();
        int r = 31;
        for (int i = 0; i < arr.length; i++) {
            ans += ((int) arr[i] - 96) * Math.pow(r, i);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(ans);

        System.out.println(sb);
    }

}
