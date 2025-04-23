import java.util.*;
class Solution {
    public String[] solution(String[] record) {
        HashMap<String, String> userIdMap = new HashMap<>();
        ArrayList<String[]> list = new ArrayList<>();
        
        for(String str : record) {
            String[] splitArr = str.split(" ");
            
            if(splitArr[0].equals("Enter")) {
                userIdMap.put(splitArr[1], splitArr[2]);
                list.add(new String[] {splitArr[1], "님이 들어왔습니다."});
            } else if(splitArr[0].equals("Leave")) {
                list.add(new String[] {splitArr[1], "님이 나갔습니다."});
            } else {
                userIdMap.put(splitArr[1], splitArr[2]);
            }
        }
        
        String[] answer = new String[list.size()];
        for(int i = 0; i < answer.length; i++) {
            String[] arr = list.get(i);
            answer[i] = userIdMap.get(arr[0]) + arr[1];
        }
        
        return answer;
    }
}