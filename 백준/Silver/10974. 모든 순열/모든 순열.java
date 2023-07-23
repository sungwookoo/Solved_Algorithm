import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int[] input;
    static int[] numbers; 
    static boolean[] isSelected;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		input = new int[N];
		numbers = new int[N];
		isSelected = new boolean[N];
		
		for(int i=1; i<=N; i++) {
			input[i-1] = i;
		}
		perm(0);
	}
	
	static void perm(int count) {
		if(count == N) {
			for(int a : numbers) {
				System.out.print(a+" ");
			}
			System.out.println();
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(isSelected[i]) continue;
			
			numbers[count] = input[i];
			
			isSelected[i] = true;
			perm(count+1);
			isSelected[i] = false;
		}
	}

}
