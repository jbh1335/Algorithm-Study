import java.util.*;
class Solution {
    static long minX = Long.MAX_VALUE, minY = Long.MAX_VALUE,
                maxX = Long.MIN_VALUE, maxY = Long.MIN_VALUE;
    static int[] select;
    static ArrayList<long[]> list;
    public String[] solution(int[][] line) {
        select = new int[2];
        list = new ArrayList<>();
        com(0, 0, line);
        
        char[][] map = new char[(int)(maxY-minY+1)][(int)(maxX-minX+1)];
        for(int i = 0; i < map.length; i++) {
            Arrays.fill(map[i], '.');
        }
        
        for(long[] arr : list) {
            int x = (int) (maxY - arr[1]);
            int y = (int) (arr[0] - minX);
            map[x][y] = '*';
        }
        
        int idx = 0;
        String[] answer = new String[map.length];
        for(int i = 0; i < map.length; i++) {
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < map[0].length; j++) {
                sb.append(map[i][j]);
            }
            answer[idx++] = sb.toString();
        }
        return answer;
    }
    
    // 교점이 정수인지 검사
    public static boolean intersection(int[] line1, int[] line2) { 
        long a = line1[0], b = line1[1], e = line1[2];
        long c = line2[0], d = line2[1], f = line2[2];
        
        // 분모가 0이면 불가능
        long denominator = a*d - b*c;
        if(denominator == 0) return false;
        
        double x = (double) (b*f - e*d) / denominator;
        double y = (double) (e*c - a*f) / denominator;
        
        // 교점이 정수이면 저장
        if(x == (long) x && y == (long) y) {
            list.add(new long[]{(long)x, (long)y});
            return true;
        }
        return false;
    }
    
    // 교점을 구할 2개의 선 조합으로 뽑기
    public static void com(int cnt, int start, int[][] line) {
        if(cnt == 2) {
            // 정수 교점이라면
            if(intersection(line[select[0]], line[select[1]])) {
                long[] arr = list.get(list.size()-1);
                minX = Math.min(minX, arr[0]);
                minY = Math.min(minY, arr[1]);
                maxX = Math.max(maxX, arr[0]);
                maxY = Math.max(maxY, arr[1]);
            }
            return;
        }
        
        for(int i = start; i < line.length; i++) {
            select[cnt] = i;
            com(cnt+1, i+1, line);
        }
    }
}