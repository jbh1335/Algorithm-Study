import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		int[] num = new int[d+1];
		
		
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			if(i < k) {
				num[arr[i]]++;
			}
		}
		
		int count = 0;
		num[c]++;
		for(int i = 0; i <= d; i++) {
			if(num[i] > 0) count++;
		}
		
		int max = count;
		for(int i = 1; i < N; i++) {
			num[arr[i-1]]--;
			if(num[arr[i-1]] == 0) {
				count--;				
			}
			
			int idx = i + k - 1;
			if(idx >= N) idx -= N;
			
			if(num[arr[idx]] == 0) {
				count++;
			}
			num[arr[idx]]++;
			
			if(num[c] == 0) count++;
			
			max = Math.max(max, count);
		}
		
		System.out.println(max);
	}

}
