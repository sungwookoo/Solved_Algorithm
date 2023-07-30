import java.util.*;
class Solution {
    public int[] solution(String[] genres, int[] plays) {
        HashMap<String, Integer> hm = new HashMap<>();
        // 1. 장르 순위
        // 2. 장르 내 노래순위
        for(int i=0; i<genres.length; i++) {
            if(hm.get(genres[i]) == null) {
                hm.put(genres[i], plays[i]);
            }
            else {
                hm.put(genres[i], hm.get(genres[i])+plays[i]);
            }
        }
        
        ArrayList<String> genreRank = new ArrayList<>();
        
        while(hm.size() > 0) {
            int max = Integer.MIN_VALUE;
            String maxKey = "";
            for(String key : hm.keySet()) {
                int play = hm.get(key);
                if(play > max) {
                    max = play;
                    maxKey = key;
                }
            }
            genreRank.add(maxKey);
            hm.remove(maxKey);
        }
        
        ArrayList<Music> ans = new ArrayList<>();
        for(String genre : genreRank) {
            ArrayList<Music> list = new ArrayList<>();
            for(int i=0; i<genres.length; i++) {
                if(genres[i].equals(genre)) {
                    list.add(new Music(i, genre, plays[i]));
                }
            }
            Collections.sort(list);
            
            if(list.size() <= 1) {
                ans.add(list.get(0));
            }
            else {
                ans.add(list.get(0));
                ans.add(list.get(1));
            }
        
        }
        
        int[] answer = new int[ans.size()];
        for(int i=0; i<ans.size(); i++) {
            answer[i] = ans.get(i).index;
        }
        
        return answer;
    }
    
    
    static class Music implements Comparable<Music> {
        int index, play;
        String genre;
        public Music(int index, String genre, int play) {
            this.index = index;
            this.genre = genre;
            this.play = play;
        }
        
        @Override
        public int compareTo(Music o1) {
            return o1.play - this.play;
        }
        
    }
}