import java.util.*;
class Solution {
    static String[] select = new String[2];
    static HashMap<String, Integer> giveMap = new HashMap<>();
    static HashMap<String, Integer> getMap = new HashMap<>();
    static HashMap<String, Integer> answerMap = new HashMap<>();
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        
        for(int i = 0; i < gifts.length; i++) {
            String[] splitArr = gifts[i].split(" ");
            // 보낸 선물 수 저장
            giveMap.put(splitArr[0], giveMap.getOrDefault(splitArr[0], 0)+1);
            // 받은 선물 수 저장
            getMap.put(splitArr[1], getMap.getOrDefault(splitArr[1], 0)+1);
        }
        
        com(0, 0, friends, gifts);
        for(String s : answerMap.keySet()) {
            answer = Math.max(answer, answerMap.get(s));
        }
        
        return answer;
    }
    
    // 조합으로 2명 뽑기
    public static void com(int cnt, int start, String[] friends, String[] gifts) {
        if(cnt == 2) {
            findWinner(gifts);
            return;
        }
        
        for(int i = start; i < friends.length; i++) {
            select[cnt] = friends[i];
            com(cnt+1, i+1, friends, gifts);
        }
    }
    
    // 2명의 관계 확인
    public static void findWinner(String[] gifts) {
        // 최종적으로 선물을 받을 사람
        String win = "";
        // 선물을 준 수 저장
        int person1giveNum = 0, person2giveNum = 0;
        // select[0]이 select[1]한테 줌
        String person1give2 = select[0] + " " + select[1];
        // select[1]이 select[0]한테 줌
        String person2give1 = select[1] + " " + select[0];
        // 두 사람이 선물을 주고받은 기록이 있는지 확인
        boolean isGived = false;
        for(int i = 0; i < gifts.length; i++) {
            if(gifts[i].equals(person1give2)) {
                person1giveNum++;
                isGived = true;
            } else if(gifts[i].equals(person2give1)) {
                person2giveNum++;
                isGived = true;
            }
        }
        
        // 기록이 없음
        if(!isGived) {
            win = noRelationOrSameNum();
        } else {
            // 두 사람이 선물을 주고받은 기록이 있다면 더 많은 선물을 준 사람이 받음
            if(person1giveNum > person2giveNum) win = select[0];
            else if(person1giveNum < person2giveNum) win = select[1];
            else win = noRelationOrSameNum();
        }
        
        // 승자가 있으면 선물 받음
        if(!win.equals("")) answerMap.put(win, answerMap.getOrDefault(win, 0)+1);
    }
    
    // 서로 선물을 주고받은 기록이 없거나 주고받은 수가 같을 경우
    public static String noRelationOrSameNum() {
        String win = "";
        // 선물지수가 더 큰 사람이 받음 -> 준 선물 - 받은 선물
        int giveNum1 = giveMap.containsKey(select[0]) ? giveMap.get(select[0]) : 0;
        int getNum1 = getMap.containsKey(select[0]) ? getMap.get(select[0]) : 0;
        int resultNum1 = giveNum1 - getNum1;

        int giveNum2 = giveMap.containsKey(select[1]) ? giveMap.get(select[1]) : 0;
        int getNum2 = getMap.containsKey(select[1]) ? getMap.get(select[1]) : 0;
        int resultNum2 = giveNum2 - getNum2;

        // 만약 선물지수도 같으면 아무도 못받음 (win 비어있음)
        if(resultNum1 > resultNum2) {
            win = select[0];
        } else if (resultNum1 < resultNum2) {
            win = select[1];
        }
        
        return win;
    }
}