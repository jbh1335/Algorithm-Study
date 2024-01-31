import java.util.*;
class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        String[] splitToday = today.split("\\.");
        int todayYear = Integer.parseInt(splitToday[0]);
        int todayMonth = Integer.parseInt(splitToday[1]);
        int todayday = Integer.parseInt(splitToday[2]);
        
        HashMap<String, Integer> map = new HashMap<>();
        for(int i = 0; i < terms.length; i++) {
            String[] splitArr = terms[i].split(" ");
            map.put(splitArr[0], Integer.parseInt(splitArr[1]));
        }
        
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < privacies.length; i++) {
            String[] splitPriv = privacies[i].split(" ");
            String[] splitDate = splitPriv[0].split("\\.");
            int year = Integer.parseInt(splitDate[0]);
            int month = Integer.parseInt(splitDate[1]);
            int day = Integer.parseInt(splitDate[2]);
            
            month += map.get(splitPriv[1]);
            while(month > 12) {
                year++;
                month -= 12;
            }
            
            day--;
            if(day == 0) {
                day = 28;
                month--;
            }
            
            if(year < todayYear) list.add(i+1);
            else if(year == todayYear) {
                if(month < todayMonth) list.add(i+1);
                else if(month == todayMonth) {
                    if(day < todayday) list.add(i+1);
                }
            }
        }
        
        int[] answer = new int[list.size()];
        for(int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
}