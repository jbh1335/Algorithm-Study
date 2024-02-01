import java.util.*;
class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        // 본인이 신고 당한 횟수를 저장하는 reportMap
        HashMap<String, Integer> reportMap = new HashMap<>();
        for(int i = 0; i < id_list.length; i++) {
            reportMap.put(id_list[i], 0);
        }
        
        // 한 유저가 동일한 유저에게 여러번 신고해도 1회로 처리하므로 report배열을 중복 제거
        HashSet<String> reportSet = new HashSet<>();
        for(int i = 0; i < report.length; i++) {
            reportSet.add(report[i]);
        }
        
        // k번 이상 신고당한 사람들 저장하는 kSet
        HashSet<String> kSet = new HashSet<>();
        for(String s : reportSet) {
            String[] splitArr = s.split(" ");
            reportMap.replace(splitArr[1], reportMap.get(splitArr[1])+1);
            // 신고당한 수가 k번 넘으면 kSet 저장
            if(reportMap.get(splitArr[1]) >= k) kSet.add(splitArr[1]);
        }
        
        // 정답 저장하는 resultMap
        HashMap<String, Integer> resultMap = new HashMap<>();
        for(String s : reportSet) {
            String[] splitArr = s.split(" ");
            if(kSet.contains(splitArr[1])) resultMap.put(splitArr[0], resultMap.getOrDefault(splitArr[0], 0) + 1);
        }
        
        int[] answer = new int[id_list.length];
        for(int i = 0; i < id_list.length; i++) {
            answer[i] = resultMap.containsKey(id_list[i]) ? resultMap.get(id_list[i]) : 0;
        }
        
        return answer;
    }
}