import java.util.*;
class Solution {
    public String solution(String p) {
        // 처음부터 올바른 문자열이면 그대로 반환
        if(checkOk(p)) return p;
              
        String answer = dfs(p);
        return answer;
    }
    
    public static String dfs(String str) {
        // 1. 입력이 빈 문자열이면 반환
        if(str.equals("")) return "";

        // 2. w를 균형잡힌 문자열 u, v로 분리
        String[] uvArr = separate(str);
        String u = uvArr[0];
        String v = uvArr[1];

        // 3. 문자열 u가 올바른 문자열이면 u + dfs(v) 반환
        if(checkOk(u)) return u + dfs(v);

        // 4. 올바른 문자열이 아니면 아래 과정 수행
        // 1) 빈 문자열에 ( 붙임
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        // 2) v를 1단계부터 재귀적으로 수행한 결과 이어 붙임
        sb.append(dfs(v));
        // 3) 다시 ) 붙임
        sb.append(")");
        // 4) u의 첫번째와 마지막을 제거하고 나머지 문자열의 괄호 방향을 뒤집어서 붙임
        for(int i = 1; i < u.length()-1; i++) {
            if(u.charAt(i) == '(') sb.append(")");
            else sb.append("(");
        }
        // 5) 생성된 문자열 반환
        return sb.toString();
    }
    
    // 올바른 문자열인지 확인
    public static boolean checkOk(String str) {
        Stack<Character> stack = new Stack();
        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if(ch == '(') {
                stack.push(ch);
            } else {
                if(stack.isEmpty()) return false;
                stack.pop();
            }
        }
        return true;
    }
    
    // 문자열을 u, v로 분리
    public static String[] separate(String str) {
        int left = 0, right = 0;
        String[] uvArr = new String[2];
        
        for(int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == '(') left++;
            else right++;
            
            if(left == right) {
                uvArr[0] = str.substring(0, i+1);
                uvArr[1] = str.substring(i+1, str.length());
                break;
            }
        }
        return uvArr;
    }
}