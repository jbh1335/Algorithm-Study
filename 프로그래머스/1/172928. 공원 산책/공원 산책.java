class Solution {
    static int startX, startY;
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public int[] solution(String[] park, String[] routes) {
        map = new char[park.length][park[0].length()];
        
        for(int i = 0; i < park.length; i++) {
            String str = park[i];
            for(int j = 0; j < str.length(); j++) {
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'S') {
                    startX = i;
                    startY = j;
                    map[i][j] = 'O';
                }
            }
        }
        
        for(int i = 0; i < routes.length; i++) {
            String[] splitArr = routes[i].split(" ");
            move(splitArr[0].charAt(0), Integer.parseInt(splitArr[1]));
        }
        
        int[] answer = {startX, startY};
        return answer;
    }
    
    public static void move(char op, int n) {
        int dir = 0;
        if(op == 'N') dir = 0;
        else if(op == 'S') dir = 1;
        else if(op == 'W') dir = 2;
        else dir = 3;
        
        int x = startX, y = startY;
        boolean flag = true;
        while(n-- > 0) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            
            if(nx >= 0 && ny >= 0 && nx < map.length && ny < map[0].length && map[nx][ny] == 'O') {
                x = nx;
                y = ny;
            } else {
                flag = false;
                break;
            }
        }
        
        if(flag) {
            startX = x;
            startY = y;
        }
    }
}