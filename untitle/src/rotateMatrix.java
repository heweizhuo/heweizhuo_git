public class rotateMatrix {
    public static void main(String[] args){
        int [][]matrix=new int[][]{{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};
        rotateMatrix rotateMatrix=new rotateMatrix();
        int n=matrix.length;
        rotateMatrix.rotate(matrix);
        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }
    public void rotate(int[][] matrix) {
        if(matrix.length==0) return;
        int n=matrix.length,tmp=0;
        for (int i=0;i<n/2;i++){
            for (int j=i;j<n-i-1;j++){
                System.out.println("("+i+","+j+")->("+j+","+(n-i-1)+")->("+(n-i-1)+","+(n-j-1)+")->("+(n-j-1)+","+i+")");
                System.out.println(matrix[i][j]+"->"+matrix[j][n-i-1]+"->"+matrix[n-i-1][n-j-1]+"->"+matrix[n-j-1][i]);
                tmp=matrix[j][n-i-1];
                matrix[j][n-i-1]=matrix[i][j];
                matrix[i][j]=matrix[n-j-1][i];
                matrix[n-j-1][i]=matrix[n-i-1][n-j-1];
                matrix[n-i-1][n-j-1]=tmp;
            }
        }
        return;
    }
}
