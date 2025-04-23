import java.util.*;
class Solution {
    public int[] solution(int[] fees, String[] records) {
        HashMap<Integer, Integer> timeMap = new HashMap<>(); // 차량의 입차 시간 저장
        HashMap<Integer, Integer> totalTimeMap = new HashMap<>(); // 차량의 누적 주차 시간 저장
        
        for(String record : records) {
            String[] splitRecord = record.split(" ");
            
            // 입/출차 시간
            String[] splitTime = splitRecord[0].split(":");
            int time = Integer.parseInt(splitTime[0]) * 60 + Integer.parseInt(splitTime[1]);
            
            // 차량번호
            int carNum = Integer.parseInt(splitRecord[1]);
            
            if(splitRecord[2].equals("IN")) { // 입차
                timeMap.put(carNum, time); // 들어간 시간 저장
            } else { // 출차
                int stayedTime = time - timeMap.get(carNum); // 머문 시간
                totalTimeMap.put(carNum, totalTimeMap.getOrDefault(carNum, 0) + stayedTime); // 누적 시간 저장
                timeMap.remove(carNum);
            }
        }
        
        // 입차는 있고 출차 내역이 없으면 23:59에 출차되었다고 간주
        for(int carNum : timeMap.keySet()) {
            int lastTime = 23 * 60 + 59;
            int stayedTime = lastTime - timeMap.get(carNum);
            totalTimeMap.put(carNum, totalTimeMap.getOrDefault(carNum, 0) + stayedTime);
        }
        
        // 각 차량마다 누적 시간으로 요금 계산
        for(int carNum : totalTimeMap.keySet()) {
            int time = totalTimeMap.get(carNum);
            time -= fees[0]; // 기본 시간
            int fee = fees[1]; // 기본 요금
            
            // 기본 요금으로 처리 못하면 추가 계산
            if(time > 0) {
                fee += (time / fees[2]) * fees[3];
                if(time % fees[2] != 0) fee += fees[3];
            }
            
            totalTimeMap.put(carNum, fee);
        }
        
        // 차량 번호 오름차순 정렬
        ArrayList<Integer> list = new ArrayList<>(totalTimeMap.keySet());
        Collections.sort(list);
        
        int[] answer = new int[list.size()];
        for(int i = 0; i < answer.length; i++) {
            answer[i] = totalTimeMap.get(list.get(i));
        }
        
        return answer;
    }
}