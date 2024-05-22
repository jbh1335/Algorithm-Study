import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<String> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        list = new ArrayList<>();
        list.add("*");

        int count = 1;
        for(int i = 2; i <= N; i++) {
            count += 4;
            makeStar(count);
        }

        for(String s : list) {
            System.out.println(s);
        }
    }

    public static void makeStar(int count) {
        for(int i = 0; i < list.size(); i++) {
            String str = "* " + list.get(i) + " *";
            list.set(i, str);
        }

        String str1 = "*", str2 = "*";
        while(count-- > 2) {
            str1 += "*";
            str2 += " ";
        }

        str1 += "*";
        str2 += "*";

        list.add(0, str2);
        list.add(str2);

        list.add(0, str1);
        list.add(str1);
    }
}
