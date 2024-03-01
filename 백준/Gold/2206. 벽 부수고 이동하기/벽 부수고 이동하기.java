import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] graph;
	static boolean visited[][][];
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static class Point {
		int x;
		int y;
		int dist;
		int use;
		
		public Point(int x, int y, int dist, int use) {
			super();
			this.x = x;
			this.y = y;
			this.dist = dist;
			this.use = use;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new int[N][M];
		visited = new boolean[N][M][2];
		
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int j = 0; j < M; j++) {
				graph[i][j] = Integer.valueOf(str.charAt(j)) - '0';
			}
		}
		
		if(N == 1 && M == 1) System.out.println(1);
		else System.out.println(move(0, 0));
	}

	public static int move(int x, int y) {
		Queue<Point> que = new LinkedList<>();
		que.add(new Point(x, y, 0, 0));
		visited[x][y][0] = true;
		
		while(!que.isEmpty()) {
			Point p = que.poll();
			
			for(int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				
				if(nx >= 0 && ny >= 0 && nx < N && ny < M) {
					if(nx == N-1 && ny == M-1) {
						return (p.dist+2);
					}
					
					if(p.use == 0) {
						if(!visited[nx][ny][0]) {
							if(graph[nx][ny] == 0) {
								que.add(new Point(nx, ny, p.dist+1, p.use));
								visited[nx][ny][0] = true;
							} else {
								que.add(new Point(nx, ny, p.dist+1, p.use+1));
								visited[nx][ny][1] = true;
							}
						}
					} else if(p.use == 1) {
						if(!visited[nx][ny][1]) {
							if(graph[nx][ny] == 0) {
								que.add(new Point(nx, ny, p.dist+1, p.use));
								visited[nx][ny][1] = true;
							}
						}
					}
				}
			}
		}
		
		return -1;
	}
}
