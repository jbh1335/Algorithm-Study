import java.util.*;
class Solution {
    public int[] solution(String[] genres, int[] plays) {
        // 장르별로 가장 많이 재생된 노래 2개씩 모아 베스트 앨범 출시
        
        // 장르, 재생 횟수
        HashMap<String, Integer> playedMap = new HashMap<>();
        // String: 장르, int[]: 재생 횟수, 고유번호
        HashMap<String, ArrayList<int[]>> musicMap = new HashMap<>();
        for(int i = 0; i < genres.length; i++) {
            // 장르마다 재생 횟수 누적
            playedMap.put(genres[i], playedMap.getOrDefault(genres[i], 0) + plays[i]);
            
            // 기존 list 있으면 가져오기
            ArrayList<int[]> list = new ArrayList<>();
            if(musicMap.containsKey(genres[i])) list = musicMap.get(genres[i]);
            
            // 장르에 각 노래의 재생 횟수, 고유번호 저장
            list.add(new int[] {plays[i], i});
            musicMap.put(genres[i], list);
        }
        
        // 1. 속한 노래가 많이 재생된 장르를 먼저 수록
        ArrayList<String> playedList = new ArrayList<>(playedMap.keySet());
        Collections.sort(playedList, (o1, o2) -> playedMap.get(o2) - playedMap.get(o1));
        
        ArrayList<Integer> resultList = new ArrayList<>();
        for(String genre : playedList) {
            ArrayList<int[]> musicList = musicMap.get(genre);  
            Collections.sort(musicList, (arr1, arr2) -> {
                // 2. 장르 내에서 많이 재생된 노래를 먼저 수록
                // 3. 장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록
                if(arr1[0] == arr2[0]) return arr1[1] - arr2[1];
                return arr2[0] - arr1[0];
            });
            
            resultList.add(musicList.get(0)[1]);
            if(musicList.size() >= 2) resultList.add(musicList.get(1)[1]);
        }
        
        int[] answer = new int[resultList.size()];
        for(int i = 0; i < answer.length; i++) {
            answer[i] = resultList.get(i);
        }
        return answer;
    }
}