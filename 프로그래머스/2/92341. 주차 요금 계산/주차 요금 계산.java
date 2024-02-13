import java.util.*;
class Solution {
    public int[] solution(int[] fees, String[] records) { 
        // 차량이 소요한 시간을 구하기 위한 recordMap
        HashMap<String, Integer> recordMap = new HashMap<>();
        // 차량마다 총 소요한 시간을 저장하는 timeMap
        HashMap<String, Integer> timeMap = new HashMap<>();
        for(int i = 0; i < records.length; i++) {
            // 시각, 차량번호, 내역을 구분
            String[] recordArr = records[i].split(" ");
            String carNum = recordArr[1];
            
            // 시각을 시간과 분으로 구분
            String[] timeArr = recordArr[0].split(":");
            int hour = Integer.parseInt(timeArr[0]);
            int min = Integer.parseInt(timeArr[1]);
            
            // 시간을 분으로 변경해서 계산
            int totalMin = (hour * 60) + min;
            
            if(recordArr[2].equals("IN")) { // 입차           
                recordMap.put(recordArr[1], totalMin);
            } else { // 출차
                // 몇분동안 있었는지 구하기
                int time = totalMin - recordMap.get(carNum);
                timeMap.put(carNum, timeMap.getOrDefault(carNum, 0) + time);
                recordMap.remove(carNum);
            }
        }
        
        // 입차기록은 있지만 출차기록이 없는 경우 -> 23:59에 출차한 것으로 계산
        for(String s : recordMap.keySet()) {
            int outTime = (60 * 23) + 59;
            int time = outTime - recordMap.get(s);
            
            timeMap.put(s, timeMap.getOrDefault(s, 0) + time);
        }
        
        // 요금 계산
        for(String s : timeMap.keySet()) {
            // 총 소요시간
            int time = timeMap.get(s);
            // 기본요금에 대한 시간 빼고
            time -= fees[0]; 
            // 기본요금 부여
            int fee = fees[1];
            
            // 시간이 남았으면
            if(time > 0) {
                // 남은 시간은 분당 요금으로 계산
                fee += (time / fees[2]) * fees[3];
                if(time % fees[2] != 0) fee += fees[3];
            }
            
            // 요금으로 다시 저장
            timeMap.put(s, fee);
        }
        
        // 차량 번호에 따라 오름차순 정렬
        ArrayList<String> list = new ArrayList<>(timeMap.keySet());
        Collections.sort(list);
        
        int[] answer = new int[timeMap.size()];
        for(int i = 0; i < list.size(); i++) {
            answer[i] = timeMap.get(list.get(i));
        }
        return answer;
    }
}