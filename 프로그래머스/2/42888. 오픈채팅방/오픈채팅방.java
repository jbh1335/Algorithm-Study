import java.util.*;
class Solution {
    public String[] solution(String[] record) {
        // 유저아이디, 닉네임을 저장하는 userMap
        HashMap<String, String> userMap = new HashMap<>();
        // 유저아이디, 채팅 기록 저장하는 resultList
        ArrayList<String[]> resultList = new ArrayList<>();
        
        for(int i = 0; i < record.length; i++) {
            String[] recordArr = record[i].split(" ");
            
            if(recordArr[0].equals("Enter")) { // 입장
                // 유저아이디와 들어온 정보 저장
                resultList.add(new String[] {recordArr[1], "님이 들어왔습니다."});
                // 유저아이디의 닉네임 저장
                userMap.put(recordArr[1], recordArr[2]);
            } else if(recordArr[0].equals("Leave")) { // 퇴장
                resultList.add(new String[] {recordArr[1], "님이 나갔습니다."});
            } else { // 닉네임 변경
                userMap.put(recordArr[1], recordArr[2]);
            }
        }
        
        String[] answer = new String[resultList.size()];
        for(int i = 0; i < answer.length; i++) {
            String[] sArr = resultList.get(i);
            answer[i] = userMap.get(sArr[0]) + sArr[1];
        }
        return answer;
    }
}