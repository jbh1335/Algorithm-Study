import java.util.*;
class Solution {
    static char[] select;
    static HashMap<String, Integer> map;
    public String[] solution(String[] orders, int[] course) {
        // 조건에 만족하는 단어를 저장
        ArrayList<String> answerList = new ArrayList<>();
        
        // 조합으로 몇개 뽑을지 
        for(int i = 0; i < course.length; i++) {
            map = new HashMap<>();
            // 각 단어마다 탐색
            for(int j = 0; j < orders.length; j++) {
                if(orders[j].length() < course[i]) continue;
                char[] arr = orders[j].toCharArray();
                Arrays.sort(arr);

                select = new char[course[i]];
                com(0, 0, course[i], arr);
            }
            
            // map에 있는 단어를 정렬하기 위한 list
            // 종류가 많은 순으로 정렬, 같으면 알파벳 순으로 정렬
            ArrayList<String> sortList = new ArrayList<>(map.keySet());
            Collections.sort(sortList, (o1, o2) -> {
                if(map.get(o1) == map.get(o2)) return o1.compareTo(o2);
                return map.get(o2) - map.get(o1);
            });
            
            // 종류의 최댓값은 2개 이상이어야함
            if(sortList.size() == 0) continue;
            int max = map.get(sortList.get(0));
            if(max >= 2) {
                for(String s : sortList) {
                    // 최댓값이 여러개이면 모두 저장
                    if(map.get(s) == max) answerList.add(s);
                    else break;
                }
            }
        }
        
        Collections.sort(answerList, (o1, o2) -> o1.compareTo(o2));
        String[] answer = new String[answerList.size()];
        for(int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }
        return answer;
    }
    
    // 조합으로 num개 뽑기
    public static void com(int cnt, int start, int num, char[] arr) {
        if(cnt == num) {
            String str = "";
            for(char ch : select) {
                str += ch;
            }
            
            map.put(str, map.getOrDefault(str, 0) + 1);
            return;
        }
        
        for(int i = start; i < arr.length; i++) {
            select[cnt] = arr[i];
            com(cnt+1, i+1, num, arr);
        }
    }
}