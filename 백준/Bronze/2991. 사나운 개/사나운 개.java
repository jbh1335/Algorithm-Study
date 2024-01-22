import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken()); // A분 동안 공격
        int B = Integer.parseInt(st.nextToken()); // B분 동안 휴식
        int C = Integer.parseInt(st.nextToken()); // C분 동안 공격
        int D = Integer.parseInt(st.nextToken()); // D분 동안 휴식

        st = new StringTokenizer(br.readLine());
        int P = Integer.parseInt(st.nextToken()); // 우체부의 도착 시간 ex) 3 -> 3번째 분이 진행되는 중에 도착
        int M = Integer.parseInt(st.nextToken()); // 우유배달원의 도착 시간
        int N = Integer.parseInt(st.nextToken()); // 신문배달원의 도착 시간

        int[] answer = new int[3]; // 3명이 개에게 공격받은 수를 저장하는 배열
        answer[0] = countAttack(P, A, B); // 개1
        answer[0] += countAttack(P, C, D); // 개2
        answer[1] = countAttack(M, A, B);
        answer[1] += countAttack(M, C, D);
        answer[2] = countAttack(N, A, B);
        answer[2] += countAttack(N, C, D);

        for(int i = 0; i < 3; i++) {
            System.out.println(answer[i]);
        }
    }

    // 몇 마리에게 공격받는지 계산
    public static int countAttack(int arriveTime, int x, int y) {
        int count = 0, end = x+y;
        while(true) {
            if(arriveTime <= end) {
                // 공격 시간
                if(end-x-y < arriveTime && arriveTime <= end-y) count++;
                break;
            } else {
                end += x+y;
            }
        }
        return count;
    }
}