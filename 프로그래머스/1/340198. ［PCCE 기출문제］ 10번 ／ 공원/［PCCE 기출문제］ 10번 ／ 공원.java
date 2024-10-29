class Solution {
    public int solution(int[] mats, String[][] park) {
        int answer = -1;
        
        for(int i : mats) {
            if(checkOk(i, park)) answer = Math.max(answer, i);
        }
        
        return answer;
    }
    
    public static boolean checkOk(int N, String[][] park) {
        for(int i = 0; i <= park.length-N; i++) {
            for(int j = 0; j <= park[0].length-N; j++) {
                boolean isAble = true;
                loop:
                for(int a = i; a < i+N; a++) {
                    for(int b = j; b < j+N; b++) {
                        if(!park[a][b].equals("-1")) {
                            isAble = false;
                            break loop;
                        }
                    }
                }
                
                if(isAble) return true;
            }
        }
        
        return false;
    }
}