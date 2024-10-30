class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String answer = "";
        int total = changeTime(video_len);
        int now = changeTime(pos);
        int start = changeTime(op_start);
        int end = changeTime(op_end);
        
        for(String command : commands) {
            // 오프닝 구간에 있으면 건너뛰기
            if(start <= now && now <= end) now = end;
            
            if(command.equals("prev")) { // 10초 전으로 이동
                now -= 10;
                if(now < 0) now = 0;
            } else { // 10초 후로 이동
                now += 10;
                if(now > total) now = total;
            }
        }
        
        if(start <= now && now <= end) now = end;
        
        int min = now / 60;
        int sec = now % 60;
        
        answer += min < 10 ? "0" + min : min;
        answer += ":";
        answer += sec < 10 ? "0" + sec : sec;
        
        return answer;
    }
    
    public static int changeTime(String time) {
        String[] splitTime = time.split(":");
        int min = Integer.parseInt(splitTime[0]);
        int sec = Integer.parseInt(splitTime[1]);
        
        return (min * 60) + sec;
    }
}