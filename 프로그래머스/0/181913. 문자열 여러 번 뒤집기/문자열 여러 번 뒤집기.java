class Solution {
    public String solution(String my_string, int[][] queries) {
        String answer = my_string;
        
        for(int[] arr : queries) {
            StringBuilder sb = new StringBuilder();
            sb.append(answer.substring(arr[0], arr[1]+1));
            sb.reverse();
            sb.insert(0, answer.substring(0, arr[0]));
            sb.insert(arr[1]+1, answer.substring(arr[1]+1, answer.length()));
            answer = sb.toString();
        }
        
        return answer;
    }
}