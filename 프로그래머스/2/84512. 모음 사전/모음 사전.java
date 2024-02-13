class Solution {
    static int answer;
    static boolean isFound;
    static char[] alpha = {'A', 'E', 'I', 'O', 'U'};
    public int solution(String word) {
        find("", 0, word);
        return answer;
    }
    
    public static void find(String str, int cnt, String word) {
        if(isFound || cnt == 5) return;
        
        for(int i = 0; i < 5; i++) {
            if(isFound) return;
            String newStr = str + alpha[i];
            answer++;
            
            if(newStr.equals(word)) {
                isFound = true;
                return;
            }
            find(newStr, cnt+1, word);
        }
    }
}