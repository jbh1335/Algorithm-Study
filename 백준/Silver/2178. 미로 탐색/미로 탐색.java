import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] graph;
	static boolean[][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int j = 0; j < M; j++) {
				graph[i][j] = Integer.valueOf(str.charAt(j)) - '0';
			}
		}
		
		bfs(0, 0);
		
		System.out.println(graph[N-1][M-1]);
	}
	
	public static void bfs(int x, int y) {
		Queue<int[]> que = new LinkedList<>();
		visited[x][y] = true;
		que.add(new int[] {x, y});
		
		while(!que.isEmpty()) {
			int[] num = que.poll();
			
			for(int i = 0; i < 4; i++) {
				int nx = num[0] + dx[i];
				int ny = num[1] + dy[i];
				
				if(nx >= 0 && ny >= 0 && nx < N && ny < M) {
					if(!visited[nx][ny] && graph[nx][ny] == 1) {
						visited[nx][ny] = true;
						que.add(new int[] {nx, ny});
						graph[nx][ny] = graph[num[0]][num[1]] + 1;
					}
				}
			}
		}
		
	}

}
