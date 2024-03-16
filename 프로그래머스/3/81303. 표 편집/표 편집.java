import java.util.*;
class Solution {
    public String solution(int n, int k, String[] cmd) {
        // 삭제된 행의 인덱스 저장
        Stack<Integer> stack = new Stack<>();
        int now = k, size = n;
        
        for(int i = 0; i < cmd.length; i++) {
            String[] cmdArr = cmd[i].split(" ");
            
            if(cmdArr[0].equals("U")) {
                // 위로 x칸
                now -= Integer.parseInt(cmdArr[1]);
            } else if(cmdArr[0].equals("D")) {
                // 아래로 x칸
                now += Integer.parseInt(cmdArr[1]);
            } else if(cmdArr[0].equals("C")) { 
                // 현재 선택된 행 삭제 stack에 저장
                stack.push(now);
                // 삭제된 행이 가장 마지막 행인 경우 바로 윗 행 선택
                if(now == size-1) now--;
                // 행 삭제
                size--;
            } else { 
                // 가장 최근에 삭제된 행 복구하고 현재 선택된 행은 바뀌지 않음
                int restore = stack.pop();
                // 현재 위치보다 앞으로 들어오면 now가 한칸 아래로 밀림
                if(restore <= now) now++;
                size++;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        while(size-- > 0) {
            sb.append("O");
        }
        
        while(!stack.isEmpty()) {
            sb.insert(stack.pop(), "X");
        }
        return sb.toString();
    }
}