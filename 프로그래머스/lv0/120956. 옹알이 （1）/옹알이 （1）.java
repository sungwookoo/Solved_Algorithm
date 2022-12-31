class Solution {
    public int solution(String[] babbling) {
       int answer = 0;
		
		for(int i=0; i<babbling.length; i++) {
			if(check(babbling[i])) answer++;
		}
		
		return answer;
        
    }
    
    	static boolean check(String str) {
		if(str.length() == 1) return false;
		String[] words = {"aya", "ye", "woo", "ma"};
		int pointer = 0;
		
		while(true) {	
			boolean flag = false;
			for(int i=0; i<words.length; i++) {
				int remain = str.length() - pointer;
				if(remain < words[i].length()) continue;
				String subStr = str.substring(pointer, pointer + words[i].length());
				if(subStr.equals(words[i])) {
					pointer += words[i].length();
					flag = true;
					break;
				}
			}
			
			if(!flag) {
				if(pointer == str.length()) return true;	
				else return false;
			}
		}
	}
}