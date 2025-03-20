import java.util.*;
class Solution {
    public int solution(String dirs) {
        HashSet<String> set = new HashSet<>();
        int x = 0, y = 0;
        
        for(int i = 0; i < dirs.length(); i++) {
            char dir = dirs.charAt(i);
            int nx = x, ny = y;
            
            if(dir == 'U') ny++;
            else if(dir == 'D') ny--;
            else if(dir == 'L') nx--;
            else nx++;
            
            if(nx >= -5 && ny >= -5 && nx <= 5 && ny <= 5) {
                set.add("" + x + y + nx + ny);
                set.add("" + nx + ny + x + y);
                x = nx;
                y = ny;
            }
        }
        
        return set.size() / 2;
    }
}