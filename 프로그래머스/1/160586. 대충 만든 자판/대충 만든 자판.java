class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        int[] answer = new int[targets.length];
        
        for(int i = 0; i < targets.length; i++) {
            int count = 0;
            boolean exist = true;
            for(int j = 0; j < targets[i].length(); j++) {
                char ch = targets[i].charAt(j);
                int minIdx = 100;
                
                for(int a = 0; a < keymap.length; a++) {
                    int idx = keymap[a].indexOf(ch);
                    if(idx != -1) minIdx = Math.min(minIdx, idx);
                }
                
                if(minIdx == 100) {
                    exist = false;
                    break;
                } else {
                    count += (minIdx+1);
                }
            }
            answer[i] = exist ? count : -1;
        }
        return answer;
    }
}