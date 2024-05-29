import java.util.*;
class Solution {
    static int[] parents;
    static String[] value;
    public String[] solution(String[] commands) {
        ArrayList<String> list = new ArrayList<>();
        value = new String[50*50];
        parents = new int[50*50];
        for(int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
        
        for(String str : commands) {
            String[] splitArr = str.split(" ");
            String command = splitArr[0];
            
            if(command.equals("UPDATE")) { // 값 수정
                if(splitArr.length == 4) { // (r, c)의 값을 value로 변경
                    int r = Integer.parseInt(splitArr[1]) - 1;
                    int c = Integer.parseInt(splitArr[2]) - 1;
                    int p = findParent(50*r+c);
                    value[p] = splitArr[3];
                } else { // value1값을 value2값으로 변경
                    for(int i = 0; i < value.length; i++) {
                        if(value[i] == null) continue;
                        if(value[i].equals(splitArr[1])) value[i] = splitArr[2];
                    }
                }
            } else if(command.equals("MERGE")) { // 병합
                int r1 = Integer.parseInt(splitArr[1]) - 1;
                int c1 = Integer.parseInt(splitArr[2]) - 1;
                int r2 = Integer.parseInt(splitArr[3]) - 1;
                int c2 = Integer.parseInt(splitArr[4]) - 1;
                
                // 두 위치가 같으면 무시
                if(r1 == r2 && c1 == c2) continue;
                int p1 = findParent(50*r1+c1);
                int p2 = findParent(50*r2+c2);
                
                // 두 셀 중 한 셀이 값을 가지고 있을 경우 병합된 셀은 그 값을 가지게 됨
                // 두 셀 모두 값을 가지고 있을 경우 (r1, c1)의 값을 가지게 됨
                String newStr = value[p1] == null ? value[p2] : value[p1];
                
                // 병합
                union(p1, p2);
                if(p1 < p2) value[p1] = newStr;
                else value[p2] = newStr;
            } else if(command.equals("UNMERGE")) { // 병합 해제
                int r = Integer.parseInt(splitArr[1]) - 1;
                int c = Integer.parseInt(splitArr[2]) - 1;
                
                int p = findParent(50*r+c);
                String tmp = value[p];
                for(int i = 0; i < parents.length; i++) {
                    if(parents[i] == p) {
                        parents[i] = i;
                        value[i] = null;
                    }
                }
                
                value[50*r+c] = tmp;
            } else { // 출력
                int r = Integer.parseInt(splitArr[1]) - 1;
                int c = Integer.parseInt(splitArr[2]) - 1;
                
                int p = findParent(50*r+c);
                if(value[p] == null) list.add("EMPTY");
                else list.add(value[p]);
            }
            
            for(int i = 0; i < parents.length; i++) {
                findParent(i);
            }
        }
        
        String[] answer = new String[list.size()];
        for(int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
    
    public static void union(int i, int j) {
        int pi = findParent(i);
        int pj = findParent(j);
        
        if(pi == pj) return;
        
        if(pi < pj) parents[pj] = pi;
        else parents[pi] = pj;
        return;
    }
    
    public static int findParent(int x) {
        if(parents[x] == x) return x;
        
        parents[x] = findParent(parents[x]);
        return parents[x];
    }
}