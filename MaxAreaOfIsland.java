public class MaxAreaOfIsland {
     public int maxAreaOfIsland(int[][] grid) {
        int max=0;
        if(grid==null || grid.length==0) return max;
        int gridn=grid.length;
        int gridm=grid[0].length;
        int num=0;
        for(int n=0;n<gridn;n++){
            for(int m=0;m<gridm;m++){
                //如果为1说明有陆地，去DFS“污染”整个陆地
                if(grid[n][m]==1){
                    int area= dfs(grid, n, m);
                    max=Math.max(max,area);
                }
            }
        }
        return max;
    }
    public int dfs(int [][]grid,int n,int m){
        //是否超出边界
        if(inArea(grid,n,m)==false) return 0;

        //图和二叉树不一样，图的遍历不像二叉树一样是单向的，图遍历后可能就会回到一开始遍历的点
        //所以就需要“污染”值来区分点是否被遍历过
        //是否是岛屿，不是岛屿（是1不是0和2）就直接return
        if(grid[n][m]!=1) return 0;
        grid[n][m]=2;//污染岛屿

        //遍历上下左右
        return 1+ dfs(grid,n-1,m)
        +dfs(grid,n+1,m)
        +dfs(grid,n,m-1)
        +dfs(grid,n,m+1);

    }
    public boolean inArea(int [][]grid,int n, int m){
        int gridn=grid.length;
        int gridm=grid[0].length;
        if(n>=0 && n<gridn && m>=0 && m<gridm) return true;
        return false;
    }

    public static void main(String[] args) {
        MaxAreaOfIsland solution = new MaxAreaOfIsland();

        testCase(solution, new int[][] {
                {0,0,1,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,1,1,1,0,0,0,0},
                {0,1,1,0,1,0,0,0,0,0,0,0,0},
                {0,1,0,0,1,1,0,0,1,0,0,0,0},
                {0,1,0,0,1,1,0,0,1,1,1,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,1,1,1,0,0,0,0},
                {0,0,0,0,0,0,1,1,1,0,0,0,0}
        }, 8);

        testCase(solution, new int[][] {
                {0,0,0,0,0,0,0,0},
                {0,1,1,1,0,0,0,0},
                {0,1,1,1,0,0,0,0},
                {0,0,0,0,0,0,0,0}
        }, 9);

        testCase(solution, new int[][] {
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0}
        }, 0);

        System.out.println("All MaxAreaOfIsland tests passed.");
    }

    private static void testCase(MaxAreaOfIsland solution, int[][] grid, int expected) {
        int actual = solution.maxAreaOfIsland(grid);
        System.out.println("actual=" + actual + ", expected=" + expected);

        if (actual != expected) {
            throw new AssertionError("Test failed: expected " + expected + " but got " + actual);
        }
    }

}
