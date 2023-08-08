import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
//			System.out.println("["+b+"를 뺐을 경우]");
			list = new ArrayList<Integer>();
			for(int i=0; i<N; i++) {
				if(arr[i] == b) continue;
				list.add(arr[i]);
			}
			
			count = 1;
			int pre = list.get(0);
			
			for(int i=1; i<list.size(); i++) {
				if(pre != list.get(i)) {
					count = 1;
				}
				else {
					count++;
					max = Math.max(max, count);
				}
				
				pre = list.get(i);
			}
			
		}
		System.out.println(max);
	}

}
