public class ConstructQuadTree {
    //7/12/2021自己想的。还不错。就是1个大图怎么分成四个小图那里卡了，想着和valid suduku那样for循环，其实不用，直接列四个值就知道了
    public Node construct(int[][] grid) {
        return helper(0,0,grid.length,grid);
    }
    Node helper(int r,int c,int len,int[][] grid){
        if (len==0){
            return null;
        }
        boolean same=true;
        int benchmark=grid[r][c];
        Node n=new Node();
        for (int i=r;i<r+len;i++){
            for (int j=c;j<c+len;j++){
                if (grid[i][j]!=benchmark){
                    same=false;
                    break;
                }
            }
        }
        n.isLeaf=same;
        n.val=benchmark==0?false:true;
        if (n.isLeaf){
            return n;
        }
        n.topLeft=helper(r,c,len/2,grid);
        n.topRight=helper(r,c+len/2,len/2,grid);
        n.bottomLeft=helper(r+len/2,c,len/2,grid);
        n.bottomRight=helper(r+len/2,c+len/2,len/2,grid);

        return n;
    }
    class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;


        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }
}
