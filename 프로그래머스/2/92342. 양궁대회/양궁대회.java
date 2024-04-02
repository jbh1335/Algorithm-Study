import java.util.*;
class Solution {
    static int max;
    static ArrayList<int[]> list;
    public int[] solution(int n, int[] info) {
        int[] answer = new int[11];
        list = new ArrayList<>();
        
        dfs(0, n, new int[11], info);
        if(list.size() == 0) return new int[] {-1};
        if(list.size() > 1) {
            Collections.sort(list, (arr1, arr2) -> {
                int idx = 10;
                while(true) {
                    if(arr1[idx] == arr2[idx]) {
                        idx--;
                    } else {
                        break;
                    }
                }
                return arr2[idx] - arr1[idx];
            });       
        }
        
        answer = list.get(0);
        return answer;
    }
    
    public static void dfs(int cnt, int n, int[] arr, int[] info) {
        if(cnt == 11) {
            int apeach = 0, rian = 0, score = 11;
            for(int i = 0; i < 11; i++) {
                score--;
                // 둘다 0점이면 아무도 점수 못받음
                if(arr[i] == 0 && info[i] == 0) continue;
                // 라이언이 이김
                if(arr[i] > info[i]) rian += score;
                // 어피치가 이김
                else apeach += score;
            }
            
            // 라이언이 이겼으면
            int diff = rian - apeach;
            if(diff != 0 && diff >= max) {
                if(diff > max) { // 새로운 기록일 때는 갱신
                    max = diff;
                    list.clear();
                }
                // 기록 저장
                int[] newArr = arr.clone();
                // n > 0이면 남은 화살 마지막에 다 넣어야함
                if(n > 0) newArr[10] = n;
                list.add(newArr);
            } 
            return;
        }
        
        // 라이언이 이김
        if(n > info[cnt]) { 
            arr[cnt] = info[cnt] + 1;
            dfs(cnt+1, n-arr[cnt], arr, info);
            arr[cnt] = 0;
        }
        // 라이언 그냥 넘어감
        dfs(cnt+1, n, arr, info);
    }
}