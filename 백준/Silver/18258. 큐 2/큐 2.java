import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        Queue<Integer> que = new LinkedList<>();

        int lastNum = 0;
        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            if(str.indexOf("push") != -1) {
                String[] splitArr = str.split(" ");
                int num = Integer.parseInt(splitArr[1]);
                que.offer(num);
                lastNum = num;
            } else if(str.equals("pop")) {
                if(que.isEmpty()) sb.append(-1 + " \n");
                else sb.append(que.poll() + " \n");
            } else if(str.equals("size")) {
                sb.append(que.size() + " \n");
            } else if(str.equals("empty")) {
                if(que.isEmpty()) sb.append(1 + " \n");
                else sb.append(0 + " \n");
            } else if(str.equals("front")) {
                if(que.isEmpty()) sb.append(-1 + " \n");
                else sb.append(que.peek() + " \n");
            } else if(str.equals("back")) {
                if(que.isEmpty()) sb.append(-1 + " \n");
                else sb.append(lastNum + " \n");
            }
        }
        System.out.println(sb);
    }
}
