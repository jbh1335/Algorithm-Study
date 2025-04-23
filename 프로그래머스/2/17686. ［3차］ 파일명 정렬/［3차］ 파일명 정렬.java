import java.util.*;
class Solution {
    public String[] solution(String[] files) {
        Arrays.sort(files, (str1, str2) -> {
            String[] splitArr1 = str1.split("[0-9]+");
            String[] splitArr2 = str2.split("[0-9]+");
            
            String head1 = splitArr1[0].toLowerCase();
            String head2 = splitArr2[0].toLowerCase();
            
            if(head1.equals(head2)) {
                splitArr1 = str1.split("[^0-9]+");
                splitArr2 = str2.split("[^0-9]+");
                
                String str1Num = splitArr1[1];
                if(str1Num.length() > 5) str1Num = str1Num.substring(0, 5);
                
                String str2Num = splitArr2[1];
                if(str2Num.length() > 5) str2Num = str2Num.substring(0, 5);
                
                return Integer.parseInt(str1Num) - Integer.parseInt(str2Num);
            }
            
            return head1.compareTo(head2);
        });
        
        return files;
    }
}