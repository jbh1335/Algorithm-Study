class Solution {
    public int solution(String myString, String pat) {
        int answer = 0;
        
        myString = myString.replace("A", "C");
        myString = myString.replace("B", "A");
        myString = myString.replace("C", "B");
        
        answer = myString.contains(pat) ? 1 : 0;
        return answer;
    }
}