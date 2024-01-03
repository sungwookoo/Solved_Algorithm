import java.util.*;
import java.io.*;

public class Main {

    static int a, b, v;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());

        if (a == v) {
            System.out.println(1);
            System.exit(0);
        }

        StringBuilder sb = new StringBuilder();
        sb.append((v - b) % (a - b) != 0 ? (v - b) / (a - b) + 1 : (v - b) / (a - b));

        System.out.println(sb);
    }

}
