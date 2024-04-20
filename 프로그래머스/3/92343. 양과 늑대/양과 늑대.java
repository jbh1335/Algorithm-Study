import java.util.*;
class Solution {
    static int answer;
    static ArrayList<Integer>[] childList;
    public int solution(int[] info, int[][] edges) {
        childList = new ArrayList[info.length];
        for(int i = 0; i < info.length; i++) {
            childList[i] = new ArrayList<>();
        }
        
        // 연결리스트로 자식 저장
        for(int i = 0; i < edges.length; i++) {
            childList[edges[i][0]].add(edges[i][1]);
        }
        
        dfs(0, 0, 0, new ArrayList<>(), info);
        return answer;
    }
    
    public static void dfs(int idx, int sheepCnt, int wolfCnt, ArrayList<Integer> nodeList, int[] info) {
        // 양과 늑대 개수 증가
        if(info[idx] == 0) sheepCnt++;
        else wolfCnt++;
        
        // 늑대수가 양보다 크거나 같으면 불가능
        if(wolfCnt >= sheepCnt) return;
        // 양의 최댓값 저장
        answer = Math.max(answer, sheepCnt);
        
        // 현재 노드에서 이동할 수 있는 다음 노드들을 저장하는 list
        ArrayList<Integer> list = new ArrayList<>(nodeList);
        // 자기 자신은 이미 방문했으니 삭제
        list.remove(Integer.valueOf(idx));
        // 자식이 있다면 추가
        if(childList[idx].size() > 0) {
            for(int child : childList[idx]) {
                list.add(child);
            }
        }
        
        // 이동해야하는 노드 모두 방문
        for(int next : list) {
            dfs(next, sheepCnt, wolfCnt, list, info);
        }
    }
}