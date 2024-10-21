import java.util.*;
class Solution {
    public int[] solution(String[] genres, int[] plays) {
        // 장르별 총 재생 횟수 저장하는 playMap
        HashMap<String, Integer> playMap = new HashMap<>();
        // 장르별 재생 횟수, 인덱스 저장하는 genreMap
        HashMap<String, ArrayList<Integer>> genreMap = new HashMap<>();
        
        for(int i = 0; i < genres.length; i++) {
            playMap.put(genres[i], playMap.getOrDefault(genres[i], 0) + plays[i]);
            ArrayList<Integer> list = genreMap.containsKey(genres[i]) ? genreMap.get(genres[i]) : new ArrayList<>();
            list.add(i);
            genreMap.put(genres[i], list);
        }
        
        ArrayList<String> playList = new ArrayList<>(playMap.keySet());
        Collections.sort(playList, (o1, o2) -> playMap.get(o2) - playMap.get(o1));
        
        ArrayList<Integer> answerList = new ArrayList<>();
        for(String genre : playList) {
            ArrayList<Integer> list = genreMap.get(genre);
            Collections.sort(list, (o1, o2) -> plays[o2] - plays[o1]);
            
            for(int i = 0; i < list.size(); i++) {
                answerList.add(list.get(i));
                if(i == 1) break;
            }
        }
        
        int[] answer = new int[answerList.size()];
        for(int i = 0; i < answer.length; i++) {
            answer[i] = answerList.get(i);
        }
        
        return answer;
    }
}