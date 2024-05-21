import java.util.*;
class Solution {
    static int[] select;
    static ArrayList<String> list;
    public int solution(String[][] relation) {
        list = new ArrayList<>();
        
        for(int i = 1; i <= relation[0].length; i++) {
            select = new int[i];
            com(0, 0, i, relation);
        }
        
        return list.size();
    }
    
    // 조합으로 num개 뽑기
    public static void com(int cnt, int start, int num, String[][] relation) {
        if(cnt == num) {
            if(checkKey(relation)) {
                String str = "";
                for(int idx : select) {
                    str += idx;
                }
                
                boolean isNew = true;
                for(String s : list) {
                    String newStr = str.replaceAll("["+s+"]", "");
                    if(newStr.length() + s.length() == str.length()) {
                        isNew = false;
                        break;
                    }
                }
                
                if(isNew) list.add(str);
            }
            
            return;
        }
        
        for(int i = start; i < relation[0].length; i++) {
            select[cnt] = i;
            com(cnt+1, i+1, num, relation);
        }
    }
    
    // 후보키가 가능한지 확인
    public static boolean checkKey(String[][] relation) {
        HashSet<String> set = new HashSet<>();
        
        for(int i = 0; i < relation.length; i++) {
            String str = "";
            for(int idx : select) {
                str += relation[i][idx] + ",";
            }
            
            if(!set.add(str)) return false;
        }
        return true;
    }
}