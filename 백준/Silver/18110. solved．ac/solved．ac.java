import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        if(N == 1) System.out.println(br.readLine());
        else if(N == 0) System.out.println("0");
        else {
        	int cut = (int)Math.round(N*0.15);
        	int arr[] = new int[N];
        	for(int i=0; i<N; i++) {
        		arr[i] = Integer.parseInt(br.readLine());
        	}
        	
        	Arrays.sort(arr);
        	
        	double sum = 0;
        	
        	for(int i=cut; i<arr.length-cut; i++) {
        		sum += arr[i];
        	}
        	
        	System.out.println(Math.round(sum / (arr.length-cut - cut)));
        }
        
    }
}
