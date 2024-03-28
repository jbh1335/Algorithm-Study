class Solution {
    public int solution(String name) {
        int answer = 0;
        int length = name.length();
        int move = length - 1;
        for(int i = 0; i < name.length(); i++) {
            char ch = name.charAt(i);
            answer += Math.min(ch-'A', 'Z'-ch+1);
            
            // 연속된 A가 끝나는 지점 찾기
            int idx = i + 1;
            while(idx < length && name.charAt(idx) == 'A') {
                idx++;
            }
            
            // 0번째에서 i번째까지 갔다가 다시 0번째로 돌아옴(i*2) + 왼쪽으로 이동해서 idx까지 검사(length-idx)
            move = Math.min(move, (i*2)+length-idx);
            // 왼쪽으로 이동해서 idx까지 갔다가 다시 0번째로 돌아옴(length-idx)*2 + 오른쪽으로 이동해서 i번째까지 검사(i)
            move = Math.min(move, (length-idx)*2+i);
        }
        
        answer += move;
        return answer;
    }
}