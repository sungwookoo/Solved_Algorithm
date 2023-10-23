import java.io.*;

public class Main {

    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(br.readLine());
        int count = 1;
        int size = 2;

        if (N == 1) {
            System.out.println(1);
            System.exit(0);
        }

        while (size <= N) {
            size = size + (6 * count);
            count++;
        }

        System.out.println(count);
    }

}
