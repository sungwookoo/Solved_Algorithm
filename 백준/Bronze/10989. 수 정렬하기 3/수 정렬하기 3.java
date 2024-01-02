import java.util.*;
import java.io.*;

public class Main {
	static int N;
    static int[] arr;
	public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        
        Arrays.sort(arr);
        
        for(int a: arr) {
            sb.append(a).append("\n");
        }
       
        System.out.println(sb);
    }
}