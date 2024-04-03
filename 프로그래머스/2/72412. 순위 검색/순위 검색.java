import java.util.*;
class Solution {
    static HashMap<String, ArrayList<Integer>> map;
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        map = new HashMap<>();
        
        // 만들 수 있는 모든 조합 저장
        for(int i = 0; i < info.length; i++) {
            String[] splitArr = info[i].split(" ");
            dfs(0, "", splitArr);
        }
        
        // map에 있는 list를 오름차순 정렬
        for(String s : map.keySet()) {
            Collections.sort(map.get(s));
        }
        
        // 조건에 맞는 개수 구하기
        for(int i = 0; i < query.length; i++) {
            String[] infoArr = query[i].split(" and ");
            String str = infoArr[0] + infoArr[1] + infoArr[2];
            // 마지막은 점수와 분리
            String[] splitArr = infoArr[3].split(" ");
            str += splitArr[0];
            int score = Integer.parseInt(splitArr[1]);
            
            // 해당 정보가 있으면 이분탐색으로 몇개 있는지 검사, 없으면 0
            answer[i] = map.containsKey(str) ? binarySearch(score, map.get(str)) : 0;
        }
        return answer;
    }
    
    public static int binarySearch(int score, ArrayList<Integer> list) {
        int start = 0, end = list.size()-1, idx = 0;
        if(score <= list.get(start)) return list.size();
        if(score > list.get(end)) return 0;
        
        while(start <= end) {
            int mid = (start + end) / 2;
            
            if(list.get(mid) >= score) {
                idx = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return list.size() - idx;
    }
    
    public static void dfs(int cnt, String str, String[] infoArr) {
        if(cnt == 4) {
            ArrayList<Integer> list = new ArrayList<>();
            // 이미 존재하면 이어서 저장
            if(map.containsKey(str)) list = map.get(str);
            list.add(Integer.parseInt(infoArr[4]));
            map.put(str, list);
            return;
        }
        
        dfs(cnt+1, str+infoArr[cnt], infoArr);
        dfs(cnt+1, str+"-", infoArr);
    }
}