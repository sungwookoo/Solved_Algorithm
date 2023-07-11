import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int S, P;
		
		S = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		
		int[] counts = new int[4]; // A C G T
		int[] curCounts = new int[4];
		char[] arr = br.readLine().toCharArray();
		
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<4; i++) {
			counts[i] = Integer.parseInt(st.nextToken());
		}
		
		int ans = 0;
		
		int i = 0;
		
		while(i < P) {
			if(arr[i] == 'A') curCounts[0]++;
			else if(arr[i] == 'C') curCounts[1]++;
			else if(arr[i] == 'G') curCounts[2]++;
			else curCounts[3]++;
			i++;
		}
		
		boolean flag = true;
		for(int j=0; j<4; j++) {
			if(curCounts[j] < counts[j]) flag = false;
		}
		
		if(flag) ans ++;
		
		while(i < S) {
			char cur = arr[i];
			char prev = arr[i-P];
			
			if(cur == 'A') curCounts[0]++;
			else if(cur == 'C') curCounts[1]++;
			else if(cur == 'G') curCounts[2]++;
			else curCounts[3]++;
			
			if(prev == 'A') curCounts[0] = curCounts[0] == 0? 0: curCounts[0] - 1;
			else if(prev == 'C') curCounts[1] = curCounts[1] == 0? 0: curCounts[1] - 1;
			else if(prev == 'G') curCounts[2] = curCounts[2] == 0? 0: curCounts[2] - 1;
			else curCounts[3] = curCounts[3] == 0? 0: curCounts[3] - 1;
			
			flag = true;
			for(int j=0; j<4; j++) {
				if(curCounts[j] < counts[j]) flag = false;
			}
			
			if(flag) ans ++;
			
			i++;
			
		}
		
		System.out.println(ans);

	}

}
