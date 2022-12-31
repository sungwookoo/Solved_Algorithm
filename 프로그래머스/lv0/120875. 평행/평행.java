import java.util.List;
import java.util.ArrayList;

class Solution {
	static int solution(int[][] dots) {
		List<Double> list = new ArrayList<>();
		for(int i=0; i<3; i++) {
			for(int j=i+1; j<4; j++) {
				double gradient = (double)(dots[i][1]-dots[j][1])/(dots[i][0]-dots[j][0]);
				if(list.contains(gradient)) {
					return 1;
				}
				list.add(gradient);
			}
		}
		return 0;
	}
}