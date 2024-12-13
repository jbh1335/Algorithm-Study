class Solution {
    public String solution(int a, int b) {
        String answer = "";
        int[] month = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int day = 0;
        
        for(int i = 1; i < a; i++) {
            day += month[i];
        }
        
        day += b;
        
        if(day % 7 == 0) answer = "THU";
        else if(day % 7 == 1) answer = "FRI";
        else if(day % 7 == 2) answer = "SAT";
        else if(day % 7 == 3) answer = "SUN";
        else if(day % 7 == 4) answer = "MON";
        else if(day % 7 == 5) answer = "TUE";
        else answer = "WED";
        
        return answer;
    }
}