import java.util.*;
class Solution {
    static int[] parents;
    public int solution(int n, int[][] costs) {
        int answer = 0;
        parents = new int[n];
        
        // 처음에 자기 자신을 부모로 만들어줘서 초기화하기
        for(int i = 0; i < n; i++) {
            parents[i] = i;
        }
        
        // 비용에 따라 오름차순 정렬
        Arrays.sort(costs, (arr1, arr2) -> arr1[2] - arr2[2]);
        for(int i = 0; i < costs.length; i++) {
            if(union(costs[i][0], costs[i][1])) {
                answer += costs[i][2];
            }
        }
        return answer;
    }
    
    // x의 부모 찾기
    public static int find(int x) {
        if(parents[x] == x) return x;
        
        int px = find(parents[x]);
        parents[x] = px;
        return px;
    }
    
    // x, y 합치기
    public static boolean union(int x, int y) {
        int px = find(x);
        int py = find(y);
        
        if(px == py) return false;
        
        if(px <= py) parents[py] = px;
        else parents[px] = py;
        return true;
    }
}