package project.lab5;

/**
 * 高斯赛德尔迭代法
 */
public class Guss {

    private int r ;

    private int c ;

   /* private float[][] matrix =
            {
                    {10F,-1F,-2F,7.2F},
                    {-1F,10F,-2F,8.3F},
                    {-1F,-1F,5F,4.2F}
            };*/

    private float[][] matrix =
            {
                    {226F,18F,-15F,-17F,14F,-17F,24F},
                    {-4F,201F,11F,12F,-4F,15F,12F},
                    {-7F,-2F,-198F,6F,-13F,21F,13F},
                    {-24F,-10F,-21F,230F,8F,-11F,24F},
                    {12F,-22F,9F,8F,217F,-4F,24F},
                    {-5F,-18F,13F,4F,22F,235F,3F}
            };



    //ξ
    private float limit = 0.0001F;

    private float xk_[];

    private float xk[];

    public static void main(String[] args) {
        Guss guss = new Guss();
        guss.d();
    }

    //迭代
    public void d(){

        //检查矩阵合法性并初始化参数
        if (checkMatrix()) return;

        //打印矩阵
        printMatrix();

        boolean first = true;

        //开始递推
        while (true){
            for (int i = 0; i < c - 1; i++) {
                float bi = matrix[i][c-1];
                float aii = matrix[i][i];
                xk[i] = 1/aii * (bi - sumAX1(i-1) - sumAX2(i + 1));
            }
            if (first){
                first = false;
                for (int i = 0; i < c - 1; i++) {
                    xk_[i] = xk[i];
                }
                continue;
            }else {
                if (max()){
                    break;
                }
                for (int i = 0; i < c - 1; i++) {
                    xk_[i] = xk[i];
                }
            }
        }

        System.out.println("\n");
        for (int i = 0; i < c - 1; i++) {
            //输出值
            System.out.print("x"+(i+1)+" = "+xk[i]+"\t\t");
        }
        System.out.println("\n\n");


    }

    private float sumAX2(int s) {
        float sum = 0;
        for (int j = s; j < c-1; j++) {
            sum += matrix[s-1][j] * xk_[j];
        }
        return sum;
    }

    private float sumAX1(int n) {
        float sum = 0;
        for (int j = 0; j <= n; j++) {
            sum += matrix[n+1][j] * xk[j];
        }
        return sum;
    }

    /**
     * MAX xk - xk_ < ξ
     * @return
     */
    private boolean max() {
        float max = 10000;
        boolean first = true;
        for (int i = 0; i < c - 1; i++) {
            if (xk[i] - xk_[i] > max || first){
                first = false;
                max = xk[i] - xk_[i];
            }
        }
        //x k次迭代 和 k-1 次迭代之差 MAX < ξ
        return max < limit;
    }

    private void printMatrix() {
        System.out.println("解目标方程组:");
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c - 1; j++) {
                if (j != 0){
                    System.out.print(Math.abs(matrix[i][j])+"x"+(j+1)+"\t");
                }else {
                    System.out.print(matrix[i][j]+"x"+(j+1)+"\t");
                }
                if (j != c-2){
                    if (matrix[i][j+1] > 0){
                        System.out.print("+\t");
                    }else {
                        System.out.print("-\t");
                    }
                }

            }
            System.out.println(" = \t\t"+matrix[i][c-1]);
        }
        System.out.println("\n\n");
    }

    private boolean checkMatrix() {
        r = matrix.length;
        if (r == 0) return true;
        c = matrix[0].length;
        xk   = new float[c-1];
        xk_   = new float[c-1];
        return false;
    }

}
