public class LandPerimeter {
     public int islandPerimeter(int[][] grid) {
        if(grid==null || grid.length==0) return 0;
        int gridn=grid.length;
        int gridm=grid[0].length;
        int num=0;
        int perimeter= 0;
        for(int n=0;n<gridn;n++){
            for(int m=0;m<gridm;m++){
                //如果为1说明有陆地，去DFS“污染”整个陆地
                //就一个岛
                if(grid[n][m]==1){
                    perimeter= dfs(grid, n, m);
                }
            }
        }
        return perimeter;
    }
    public int dfs(int [][]grid,int n,int m){
        //是否超出边界
        //超出边界过了
        if(inArea(grid,n,m)==false) return 1;

        //图和二叉树不一样，图的遍历不像二叉树一样是单向的，图遍历后可能就会回到一开始遍历的点
        //所以就需要“污染”值来区分点是否被遍历过
        //是否是岛屿，不是岛屿（是1不是0和2）就直接return
        if(grid[n][m]==0) return 1;
        if(grid[n][m]!=1) return 0;
        grid[n][m]=2;//污染岛屿

        //遍历上下左右
        return dfs(grid,n-1,m)
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
    
}
