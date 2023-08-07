import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] arr = br.readLine().toCharArray();
        int i = Integer.parseInt(br.readLine());
        System.out.println(arr[i-1]);
    }
}