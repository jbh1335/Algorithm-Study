import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] parents;
	static int[][] graph;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		parents = new int[N+1];
		graph = new int[M][3];
		
		for(int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			graph[i][0] = Integer.parseInt(st.nextToken());
			graph[i][1] = Integer.parseInt(st.nextToken());
			graph[i][2] = Integer.parseInt(st.nextToken());			
		}
		
		Arrays.sort(graph, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[2], o2[2]);
			}
		});
		
		makeSet();
		int result = 0;
		int count = 0;
		
		for(int i = 0; i < M; i++) {
			int pi = findSet(graph[i][0]);
			int pj = findSet(graph[i][1]);
			
			if(union(pi, pj)) {
				count++;
				result += graph[i][2];
				if(count == N-1) break;
			}
		}
		
		System.out.println(result);
	}
	
	public static boolean union(int i, int j) {
		int pi = findSet(i);
		int pj = findSet(j);
		
		if(pi == pj) return false;
		parents[pi] = pj;
		return true;
	}
	
	public static int findSet(int i) {
		if(parents[i] == i) return i;
		
		int pi = findSet(parents[i]);
		parents[i] = pi;
		return pi;
	}
	
	public static void makeSet() {
		for(int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}


}
