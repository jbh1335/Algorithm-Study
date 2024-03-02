import java.util.*;
class Solution {
    public int solution(String s) {
        int answer = s.length();
        
        for(int l = s.length()/2; l >= 1; l--) {
            ArrayList<String> list = new ArrayList<>();
            for(int i = 0; i < s.length(); i+=l) {
                String str = i+l <= s.length() ? s.substring(i, i+l) : s.substring(i, s.length());
                
                if(!list.isEmpty()) {
                    // 이전 문자와 같으면 개수 증가
                    if(list.get(list.size()-1).equals(str)) {
                        int num = Integer.parseInt(list.get(list.size()-2));
                        list.set(list.size()-2, String.valueOf(num+1));
                        continue;
                    }
                }
                
                // list가 비어있거나 이전 문자와 다르면 그냥 추가
                list.add("1");
                list.add(str);
            }
            
            // 문자열 길이 검사
            int sum = 0;
            for(String str : list) {
                // 1은 무시
                if(str.equals("1")) continue;
                sum += str.length();
            }
            answer = Math.min(answer, sum);
        }
        return answer;
    }
}