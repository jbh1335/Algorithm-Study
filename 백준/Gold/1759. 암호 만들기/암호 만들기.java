import java.io.*;
import java.util.*;

public class Main {
    static int L, C;
    static String[] password;
    static String[] vowel = {"a", "e", "i", "o", "u"};
    static ArrayList<String> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();

        password = new String[C];
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < C; i++) {
            password[i] = st.nextToken();
        }

        Arrays.sort(password);
        com(0, 0, "");

        for(String str : list) {
            System.out.println(str);
        }
    }

    public static void com(int cnt, int start, String str) {
        if(cnt == L) {
            int count = 0;
            for(int i = 0; i < vowel.length; i++) {
                if(str.contains(vowel[i])) count++;
            }

            if(1 <= count && count <= L-2) list.add(str);
            return;
        }

        for(int i = start; i < C; i++) {
            com(cnt+1, i+1, str+ password[i]);
        }
    }
}
