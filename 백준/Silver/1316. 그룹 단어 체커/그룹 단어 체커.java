import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int count = 0;

        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            HashSet<Character> set = new HashSet<>();
            boolean isAble = true;
            set.add(str.charAt(0));

            for(int j = 1; j < str.length(); j++) {
                if(str.charAt(j) == str.charAt(j-1)) continue;
                if(!set.add(str.charAt(j))) {
                    isAble = false;
                    break;
                }
            }
            if(isAble) count++;
        }
        System.out.println(count);
    }
}
