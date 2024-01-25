import java.io.*;
import java.util.*;

public class Main {
	static int N, M, count, max;
	static int[][] graph, newGraph;
	static boolean[][] visited;
	static int[][] num;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static ArrayList<int[]> list = new ArrayList<>();
	static Queue<int[]> que = new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new int[N][M];
		newGraph = new int[N][M];
		num = new int[3][2];
		visited = new boolean[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				newGraph[i][j] = graph[i][j];
				if(graph[i][j] == 0) {
					list.add(new int[] {i, j});
				}
			}
		}
		
		com(0, 0);
		System.out.println(max);
	}
	
	public static void bfs() {
		while(!que.isEmpty()) {
			int[] num = que.poll();
			
			for(int i = 0; i < 4; i++) {
				int nx = num[0] + dx[i];
				int ny = num[1] + dy[i];
				
				if(nx >= 0 && ny >= 0 && nx < N && ny < M) {
					if(newGraph[nx][ny] == 0) {
						que.add(new int[] {nx, ny});
						newGraph[nx][ny] = 2;
					}
				}
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(newGraph[i][j] == 0) {
					count++;
				}
			}
		}
	}
	
	public static void com(int cnt, int start) {
		if(cnt == 3) {
			for(int i = 0; i < 3; i++) {
				newGraph[num[i][0]][num[i][1]] = 1;
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(newGraph[i][j] == 2) {
						que.add(new int[] {i, j});
					}
				}
			}
			
			bfs();
			
			max = Math.max(max, count);
			makeNewGraph();
			count = 0;
			return;
		}
		
		for(int i = start; i < list.size(); i++) {
			num[cnt][0] = list.get(i)[0];
			num[cnt][1] = list.get(i)[1];
			com(cnt+1, i+1);
		}
	}

	public static void makeNewGraph() {
		for(int i = 0; i < N; i++) {
			newGraph[i] = graph[i].clone();
		}
	}
}
