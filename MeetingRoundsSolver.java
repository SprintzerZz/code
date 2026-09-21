import java.io.*;
import java.util.*;

public class MeetingRoundsSolver {

    static final int[][] DIRS = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 读取 n, m
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 读取地图
        String[] grid = new String[n];
        for (int i = 0; i < n; i++) {
            grid[i] = br.readLine();
        }

        // 读取 A、B 起点
        st = new StringTokenizer(br.readLine());
        int x1 = Integer.parseInt(st.nextToken()) - 1;
        int y1 = Integer.parseInt(st.nextToken()) - 1;
        int x2 = Integer.parseInt(st.nextToken()) - 1;
        int y2 = Integer.parseInt(st.nextToken()) - 1;

        // 读取终点
        st = new StringTokenizer(br.readLine());
        int xt = Integer.parseInt(st.nextToken()) - 1;
        int yt = Integer.parseInt(st.nextToken()) - 1;

        // 三次 BFS
        int[][] distA = bfs(grid, x1, y1);
        int[][] distB = bfs(grid, x2, y2);
        int[][] distT = bfs(grid, xt, yt);

        int ans = Integer.MAX_VALUE;

        // 枚举所有可能的相遇点
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                // 三方都必须可达
                if (distA[i][j] == -1
                        || distB[i][j] == -1
                        || distT[i][j] == -1) {
                    continue;
                }

                // 相遇前，先到的人可以原地等待
                int meetTime = Math.max(
                        distA[i][j],
                        distB[i][j]
                );

                // 相遇后每回合最多走 2 步
                int toTarget = (distT[i][j] + 1) / 2;

                ans = Math.min(
                        ans,
                        meetTime + toTarget
                );
            }
        }

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    static int[][] bfs(String[] grid, int sx, int sy) {

        int n = grid.length;
        int m = grid[0].length();

        int[][] dist = new int[n][m];

        for (int[] row : dist) {
            Arrays.fill(row, -1);
        }

        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[]{sx, sy});
        dist[sx][sy] = 0;

        while (!queue.isEmpty()) {

            int[] cur = queue.poll();

            int x = cur[0];
            int y = cur[1];

            for (int[] dir : DIRS) {

                int nx = x + dir[0];
                int ny = y + dir[1];

                // 越界
                if (nx < 0 || nx >= n
                        || ny < 0 || ny >= m) {
                    continue;
                }

                // 障碍
                if (grid[nx].charAt(ny) == '#') {
                    continue;
                }

                // 已访问
                if (dist[nx][ny] != -1) {
                    continue;
                }

                dist[nx][ny] = dist[x][y] + 1;

                queue.offer(new int[]{nx, ny});
            }
        }

        return dist;
    }
}