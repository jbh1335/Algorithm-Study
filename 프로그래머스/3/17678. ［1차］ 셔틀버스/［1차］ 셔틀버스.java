import java.util.*;
class Solution {
    /*
        09:00부터 총 n회 t간격으로 역에 도착하고 최대 m명이 탈 수 있다.
        셔틀 도착했을 때 도착한 순간 온 사람도 탈 수 있음 -> 그 이후는 X
    */
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        
        // 시간을 분으로 변경하여 저장
        int[] timeArr = new int[timetable.length];
        for(int i = 0; i < timetable.length; i++) {
            String[] splitArr = timetable[i].split(":");
            timeArr[i] = Integer.parseInt(splitArr[0]) * 60 + Integer.parseInt(splitArr[1]);
        }
        
        // 시간 오른차순 정렬
        Arrays.sort(timeArr);
        
        int arrive = 9 * 60; // 첫 도착 시간 -> 09:00
        int before = -1; // 마지막으로 탄 사람 인덱스
        int time = 0; // 도착시간 -> answer를 int형으로 저장
        while(n-- > 0) {
            int count = 0;
            for(int i = before+1; i < timeArr.length; i++) {
                if(timeArr[i] <= arrive) { // 셔틀 탈 수 있음
                    before = i;
                    // 최대 인원 탔으면 끝
                    if(++count == m) break;
                } else { // 늦게 도착해서 이번꺼 못탐 
                    break;
                }
            }
            
            // 마지막 셔틀
            if(n == 0) {
                if(count == m) { // 최대인원이 탔으면 마지막으로 탄사람보다 1분 빨리 타면됨
                    time = timeArr[before] - 1;
                } else if(count < m) { // 최대인원보다 적게 탔으면 도착시간에 와도됨
                    time = arrive;
                }
            }
            
            // 다음 도착시간
            arrive += t;
        }
        
        // int형을 string으로 변경
        int hour = time / 60;
        int min = time % 60;
        
        if(hour <= 9) answer += "0";
        answer += hour + ":";
        
        if(min <= 9) answer += "0";
        answer += min;
        
        return answer;
    }
}