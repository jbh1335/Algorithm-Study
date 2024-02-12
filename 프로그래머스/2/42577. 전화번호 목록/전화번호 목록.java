import java.util.*;
class Solution {
    public boolean solution(String[] phone_book) {
        for(int i = 0; i < phone_book.length; i++) {
            phone_book[i] = phone_book[i].replace(" ", "");
        }
        
        Arrays.sort(phone_book);
        
        for(int i = 0; i < phone_book.length-1; i++) {
            String start = phone_book[i];
            if(phone_book[i+1].startsWith(start)) return false;
        }
        return true;
    }
}