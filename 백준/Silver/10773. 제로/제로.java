import java.io.*;
import java.math.BigInteger;
import java.util.FormatFlagsConversionMismatchException;
import java.util.Stack;

public class Main {


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        
        
        int N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++) {
        	int num = Integer.parseInt(br.readLine());
        	if(num == 0 && !stack.isEmpty()) stack.pop();
        	else stack.push(num);
        }
        
        long sum = 0;
        
        while(!stack.isEmpty()) {
        	sum += stack.pop();
        }

        System.out.println(sum);
    }
}
