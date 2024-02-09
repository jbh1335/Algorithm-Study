import java.io.*;
import java.util.*;

/*
    <투 포인터>
    3개의 수를 뽑아서 합이 0이 되는 경우의 수 구하기
    arr을 오름차순 정렬함
    0번째 인덱스부터 시작해서 하나씩 뽑아놓고 그다음부터 2개를 투포인터 사용해서 뽑음
        -> i번째를 뽑으면 i+1번 ~ 마지막 중에 2개 뽑음
        -> sum이 0보다 작거나 같으면 start++, 0보다 크면 end--
    처음 뽑은 숫자가 양수이면 어차피 0을 못 만들기 때문에 멈춤
    0을 뽑은 경우 고려해야 할 부분이 2가지 있음
    1. arr[start]와 arr[end]가 같은 경우
        ex) -10 5 5 5 5 5 5 5
        ex) 0 0 0 0 0 1
        같은 수들 중에 조합으로 2개 뽑기
    2. start 오른쪽 또는 end 왼쪽이 같은 경우
        ex) -8 -2 -2 -2 10 10 10 10
        같은 수만큼 구해서 곱함 (3 * 4)
    위의 경우를 따로 구하지 않고 하나씩 비교해서 찾으면 시간초과 발생
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        long answer = 0;
        // 하나를 미리 뽑고 조건에 맞는 나머지 2개 찾기
        for(int i = 0; i < arr.length; i++) {
            // 미리 뽑은 숫자가 양수이면 어차피 0을 못 만듦
            if(arr[i] > 0) break;
            int start = i+1, end = N-1, pick = arr[i];
            while(start < end) {
                int sum = pick + arr[start] + arr[end];

                if(sum == 0) {
                    // 두 수가 같으면 그 사이에 있는 수들도 같음 (오름차순 정렬)
                    // 같은 수들 중에 2개를 조합으로 뽑음
                    if(arr[start] == arr[end]) {
                        answer += com(end-start+1);
                        break;
                    }

                    // start 오른쪽 숫자가 같으면
                    int sameStartCnt = 1;
                    while(arr[start] == arr[start+1]) {
                        if(start+1 == end) break;
                        sameStartCnt++;
                        start++;
                    }

                    // end 왼쪽 숫자가 같으면
                    int sameEndCnt = 1;
                    while(arr[end-1] == arr[end]) {
                        if(end-1 == start) break;
                        sameEndCnt++;
                        end--;
                    }

                    answer += sameStartCnt * sameEndCnt;
                    start++;
                } else if(sum > 0) {
                    end--;
                } else {
                    start++;
                }
            }
        }
        System.out.println(answer);
    }

    // nC2
    public static int com(int n) {
        return n * (n-1) / 2;
    }
}
