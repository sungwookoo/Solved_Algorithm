import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int L,C;
    static char[] input;
    static char[] numbers;
    static boolean[] visited;
    static boolean[] visitedAlpha;
    static TreeSet<String> ansSet;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        input = new char[C];
        numbers = new char[L];
        visited = new boolean[C];
        ansSet = new TreeSet<>();
        visitedAlpha = new boolean[26];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<C; i++) {
            input[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(input);
        dfs(0,0);

        for(String a:ansSet) {
            sb.append(a+"\n");
        }
        System.out.println(sb);

    }

    // 최소 1개의 모음, 2개의 자음
    // 알파벳 순으로만 배열
    static void dfs(int start, int count) {
        if(count == L) {
            int c = 0; // 자음 수
            int v = 0; // 모음 수
            for(int i=0; i<L; i++) {
                if(numbers[i] == 'a' || numbers[i] == 'e' || numbers[i] == 'i' || numbers[i] == 'o' || numbers[i] == 'u') v++;
                else c++;
            }
            if(c>=2 && v>=1) {
                ansSet.add(new String(numbers));
            }
            return;
        }

        for(int i=start; i<C; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            numbers[count] = input[i];
            dfs(i+1, count+1);
            visited[i] = false;
        }


    }
}
