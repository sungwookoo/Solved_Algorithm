import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		List<Member> list = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			list.add(new Member(Integer.parseInt(st.nextToken()), st.nextToken()));
		}
		
		Collections.sort(list);
		
		for(Member member : list) {
			sb.append(member.age).append(" ").append(member.name).append("\n");
		}
		
		System.out.println(sb);
		
	}
	
	static class Member implements Comparable<Member> {
		
		int age;
		String name;
		
		
		public Member(int age, String name) {
			super();
			this.age = age;
			this.name = name;
		}

		@Override
		public int compareTo(Member o) {
			return this.age - o.age;
		}
		
	}

}
