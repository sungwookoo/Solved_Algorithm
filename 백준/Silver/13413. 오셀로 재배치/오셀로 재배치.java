import java.util.*;
import java.io.*;

public class Main {

    // static variable
    static int ans;
    static char[] omap, dmap;
    static int w, b;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int N;
        char[] omap, dmap;
        int w, b;

        for (int tc = 1; tc <= T; tc++) {
            ans = Integer.MAX_VALUE;
            N = Integer.parseInt(br.readLine());
            omap = br.readLine().toCharArray();
            dmap = br.readLine().toCharArray();
            w = 0;
            b = 0;

            for (int i = 0; i < N; i++) {
                if (omap[i] == 'W' && dmap[i] == 'B')
                    w++;
                else if (omap[i] == 'B' && dmap[i] == 'W')
                    b++;
            }

            System.out.println(w < b ? b : w);
        }
    }
}
