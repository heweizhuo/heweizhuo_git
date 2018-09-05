package backTracking;

public class exist {
    public static void main(String[] args){
        char[][] board =new char[][]{{'A','B','C','E'}, {'S','F','C','S'}, {'A','D','E','E'}};
        String word1 = "ABCCED";
        String word2 = "SEE";
        String word3 = "ABCB";
        exist exist=new exist();
        System.out.println(exist.exist1(board,word1));
        System.out.println(exist.exist1(board,word2));
        System.out.println(exist.exist1(board,word3));
    }

    public boolean exist1(char[][] board, String word) {
        int row=board.length,col=board[0].length;
        for (int i=0;i<row;i++){
            for (int j=0;j<col;j++){
                if(board[i][j]==word.charAt(0) && existBackTracking(new int[row][col],board,word,i,j,0)) {
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean existBackTracking(int[][] visited,char[][] board,String word,int x,int y,int i){
        if(i==word.length()) return true;
        if(x>=visited.length || y>=visited[0].length || x<0 || y<0) return false;
        boolean is=false;
        if(visited[x][y]==0 && board[x][y]==word.charAt(i)){
            visited[x][y] = 1;
            if(existBackTracking(visited,board,word,x+1,y,i+1)) is=true;
            if(!is && existBackTracking(visited,board,word,x,y+1,i+1)) is=true;
            if(!is && existBackTracking(visited,board,word,x-1,y,i+1)) is=true;
            if(!is && existBackTracking(visited,board,word,x,y-1,i+1)) is=true;
            if(!is) visited[x][y]=0;
        }
        return is;
    }
}
