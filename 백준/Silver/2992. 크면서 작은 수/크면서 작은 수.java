import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static int X;
	static int[] input;
	static int[] numbers;
	static boolean[] isSelected;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		X = Integer.parseInt(str);
		char[] in = str.toCharArray();

		input = new int[in.length];

		for (int i = 0; i < in.length; i++) {
			input[i] = in[i] - '0';
		}

		numbers = new int[in.length];
		isSelected = new boolean[in.length];

		perm(0);
		
		System.out.println(min == Integer.MAX_VALUE? 0 : min);
	}

	static void perm(int count) {
		if (count == input.length) {
			String str = "";
			for(int i=0; i<numbers.length; i++) {
				str += numbers[i];
			}
			int value = Integer.parseInt(str);
			
			if(value > X) {
				min = Math.min(value, min);
			}
			
			return;
		}

		for (int i = 0; i < input.length; i++) {
			if (isSelected[i])
				continue;

			isSelected[i] = true;
			numbers[count] = input[i];
			perm(count + 1);
			isSelected[i] = false;

		}

	}
}
