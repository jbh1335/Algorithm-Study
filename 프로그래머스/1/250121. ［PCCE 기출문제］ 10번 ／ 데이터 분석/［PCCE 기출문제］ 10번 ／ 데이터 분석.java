import java.util.*;
class Solution {
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        ArrayList<int[]> list = new ArrayList<>();
        
        // data: 코드번호(code), 제조일(date), 최대수량(maximum), 현재수량(remain)
        int extIdx = findIdx(ext);
        for(int i = 0; i < data.length; i++) {
            int[] arr = data[i];
            if(arr[extIdx] < val_ext) list.add(arr);
        }
        
        int sortIdx = findIdx(sort_by);
        Collections.sort(list, (o1, o2) -> o1[sortIdx] - o2[sortIdx]);
        int[][] answer = new int[list.size()][4];
        
        for(int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
    
    public static int findIdx(String str) {
        int idx = 0;
        if(str.equals("code")) {
            idx = 0;
        } else if(str.equals("date")) {
            idx = 1;
        } else if(str.equals("maximum")) {
            idx = 2;
        } else {
            idx = 3;
        }
        return idx;
    }
}