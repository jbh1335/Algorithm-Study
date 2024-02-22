import java.util.*;
class Solution {
    static long answer;
    static char[] operator, select;
    static boolean[] visited;
    static ArrayList<String> expList = new ArrayList<>();
    public long solution(String expression) {
        operator = new char[] {'+', '-', '*'};
        select = new char[3];
        visited = new boolean[3];
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if(ch == '-' || ch == '+' || ch == '*') {
                expList.add(sb.toString());
                expList.add(String.valueOf(ch));
                sb = new StringBuilder();
            } else {
                sb.append(ch);
            }
        }
        expList.add(sb.toString());
        
        per(0, expression);
        return answer;
    }
    
    public static void per(int cnt, String expression) {
        if(cnt == 3) {
            long num = calculate(expression);
            answer = Math.max(answer, Math.abs(num));
            return;
        }
        
        for(int i = 0; i < 3; i++) {
            if(visited[i]) continue;
            
            select[cnt] = operator[i];
            visited[i] = true;
            per(cnt+1, expression);
            visited[i] = false;
        }
    }
    
    public static long calculate(String expression) {
        ArrayList<String> list = new ArrayList<>(expList);
        for(char op : select) {
            for(int i = 0; i < list.size(); i++) {
                if(list.get(i).equals(String.valueOf(op))) {
                    long num = operate(list.get(i-1), list.get(i+1), op);
                    list.set(i-1, String.valueOf(num));
                    list.remove(i);
                    list.remove(i);
                    i--;
                }
            }
        }
        
        return Long.valueOf(list.get(0));
    }
    
    public static long operate(String num1, String num2, char op) {
        long x = Long.valueOf(num1);
        long y = Long.valueOf(num2);
        
        if(op == '-') return x - y;
        else if(op == '+') return x + y;
        else return x * y;
    }
}