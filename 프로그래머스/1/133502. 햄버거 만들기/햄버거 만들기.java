class Solution {
    public int solution(int[] ingredient) {
        int answer = 0, idx = 0;
        int[] arr = new int[ingredient.length];
        
        for(int i : ingredient) {
            arr[idx] = i;
            
            if(idx >= 3) {
                if(arr[idx-3] == 1 && arr[idx-2] == 2 &&
                  arr[idx-1] == 3 && arr[idx] == 1) {
                    idx -= 4;
                    answer++;
                }
            }
            
            idx++;
        }
    
        return answer;
    }
}