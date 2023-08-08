import java.awt.geom.AffineTransform;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int arr[] = new int[N];
		Set<Integer> set = new HashSet<>();
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			set.add(arr[i]);
		}
		
		List<Integer> list; 
		int count = 0;
		int max = 1;
		for(int b : set) {
			list = new ArrayList<Integer>();
			for(int i=0; i<N; i++) {
				if(arr[i] == b) continue;
				list.add(arr[i]);
			}
			
			count = 1;
			
			for(int i=1; i<list.size(); i++) {
 				if(list.get(i-1).equals(list.get(i))) {
 					
 					count ++;
 					
 					max = Math.max(max, count);
 					
 				}
 				else {
 					count = 1;
 				}
			}
			
			
		}
		System.out.println(max);
		
		
		
		
		
		
		
	}

}
