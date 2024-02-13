import java.util.*;
class Solution {
    public int solution(String dirs) {
        int answer = 0;
        ArrayList<String> list = new ArrayList<>();
        
        int x = 0, y = 0;
        for(int i = 0; i < dirs.length(); i++) {
            char ch = dirs.charAt(i);
            int nx = x;
            int ny = y;
            
            if(ch == 'U') {
                nx++;
            } else if(ch == 'D') {
                nx--;
            } else if(ch == 'R') {
                ny++;
            } else if(ch == 'L') {
                ny--;
            }
            
            if(nx >= -5 && ny >= -5 && nx <= 5 && ny <= 5) {
                String str1 = "" + x + y + nx + ny;
                String str2 = "" + nx + ny + x + y;
                if(!list.contains(str1) && !list.contains(str2)) {
                    answer++;
                    list.add(str1);
                }
                x = nx;
                y = ny;
            }
        }
        return answer;
    }
}