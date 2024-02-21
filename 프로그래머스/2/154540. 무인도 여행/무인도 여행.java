import java.util.*;
class Solution {
    /*
        X: 바다, 숫자(1~9): 무인도
        하나의 무인도에 적혀 있는 숫자들의 합: 최대 며칠동안 머물 수 있는지 (식량)
        각 섬에서 최대 며칠씩 머물 수 있는지 배열에 오름차순으로 저장해서 리턴
        무인도가 없으면 -1
    */
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static ArrayList<Integer> list = new ArrayList<>();
    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public int[] solution(String[] maps) {
        map = new int[maps.length][maps[0].length()];
        visited = new boolean[maps.length][maps[0].length()];
        
        for(int i = 0; i < maps.length; i++) {
            String str = maps[i];
            for(int j = 0; j < str.length(); j++) {
                if(str.charAt(j) == 'X') map[i][j] = 0;
                else map[i][j] = str.charAt(j) - '0';
            }
        }
        
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                if(!visited[i][j] && map[i][j] > 0) bfs(i, j);
            }
        }
        
        if(list.size() == 0) list.add(-1);
        Collections.sort(list);
        
        int[] answer = new int[list.size()];
        for(int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
    
    public static void bfs(int x, int y) {
        Queue<Point> que = new LinkedList<>();
        que.offer(new Point(x, y));
        visited[x][y] = true;
        int sum = map[x][y];
        
        while(!que.isEmpty()) {
            Point p = que.poll();
            
            for(int d = 0; d < 4; d++) {
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];
                
                if(nx >= 0 && ny >= 0 && nx < map.length && ny < map[0].length) {
                    if(!visited[nx][ny] && map[nx][ny] > 0) {
                        que.offer(new Point(nx, ny));
                        visited[nx][ny] = true;
                        sum += map[nx][ny];
                    }
                }
            }
        }
        
        if(sum > 0) list.add(sum);
    }
}