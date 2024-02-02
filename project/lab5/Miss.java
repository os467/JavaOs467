package project.lab5;


/**
 * 消主元法
 */
public class Miss {


  /*  private float[][] matrix = {
            {10F,-1F,-2F,7.2F},
            {-1F,10F,-2F,8.3F},
            {-1F,-1F,5F,4.2F}
    };*/

 /*   private float[][] matrix =
            {
                    {-3F,2F,6F,4F},
                    {10F,-7F,0F,7F},
                    {5F,-1F,5F,6F}
            };*/

    /*private float[][] matrix =
            {
                    {2F,3F,5F,5F},
                    {3F,4F,7F,6F},
                    {1F,3F,3F,5F}
            };*/

    /*private float[][] matrix =
            {
                    {6F,18F,-15F,-17F,14F,-17F,24F},
                    {-4F,-19F,11F,12F,-4F,15F,12F},
                    {-7F,-2F,-22F,6F,-13F,21F,13F},
                    {-24F,-10F,-21F,10F,8F,-11F,24F},
                    {12F,-22F,9F,8F,-3F,-4F,24F},
                    {-5F,-18F,13F,4F,22F,15F,3F}
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

    //行数量
    private int r;
    //列数量
    private int c;
    //已扫描行
    private int pass = 0;
    //原始矩阵副本
    private float[][] matrixOrigin;
    //解集
    private float[] x;

    public static void main(String[] args) {
        Miss guss = new Miss();
        guss.d();
    }

    private void d() {

        //检查矩阵合法性并初始化参数
        if (checkMatrix()) return;

        //打印矩阵
        printMatrix();

        //先记录以下原矩阵
        matrixOrigin = new float[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                matrixOrigin[i][j] = matrix[i][j];
            }
        }

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         //先消去x1,然后x2，最后解出x3,x2,x1
        for (int i = 0; i < c - 2; i++) {
            //将系数最大的行选出
            Float max = null;
            Integer maxRow = null;
            //比大小找最大主元行
            for (int j = 0; j < r; j++) {
                if (max == null || Math.abs(matrix[j][i]) > Math.abs(max)){
                    max = matrix[j][i];
                    maxRow = j;
                }
            }
            //交换已扫描行最大行
            exchangeRow(pass,maxRow);
            maxRow = pass;
            pass++;
            //maxRow行的系数更新
            for (int j = 0; j < c; j++) {
                matrix[maxRow][j] /= max;
            }

            //更新其它行的系数值
            for (int j = pass; j < r; j++) {
                float a = matrix[j][i];
                //消去a
                matrix[j][i] = 0;
                //更改每行b值
                matrix[j][c-1] += -a * matrix[maxRow][c-1];
                //更改每行其它系数值
                for (int k = 0; k < c - 1; k++) {
                    if (k != i){
                        matrix[j][k] += a * -matrix[maxRow][k];
                    }
                }
            }

        }

        //开始回解
        for (int i = r-1; i >= 0 ; i--) {
            float sumDiv = 0;
            for (int j = 0; j < c - 1; j++) {
                sumDiv+=matrix[i][j];
            }
            x[i] = matrix[i][c-1]/sumDiv;
            //消去其它行的未知数
            for (int j = i - 1; j >= 0; j--) {
                //更新b值
                matrix[j][c-1] -= matrix[j][i] * x[i];
                //系数置0
                matrix[j][i] = 0;
            }
        }

        //打印结果
        printAns();
    }

    private void exchangeRow(int pass, Integer maxRow) {
        if (pass == maxRow) return;
        for (int i = 0; i < c; i++) {
            matrix[maxRow][i] += matrix[pass][i];
            matrix[pass][i] = matrix[maxRow][i] - matrix[pass][i];
            matrix[maxRow][i] -= matrix[pass][i];
        }
    }

    private void printAns() {
        float[] lrMiss = new float[r];

        //计算左边 - 右边
        for (int i = 0; i < r; i++) {
            float sum = 0;
            for (int j = 0; j < c - 1; j++) {
                sum += matrixOrigin[i][j] * x[j];
            }
            lrMiss[i] = sum - matrixOrigin[i][c-1];
        }

        int padding = 20;
        for (int i = 0; i < r; i++) {
            String ansX = "x"+(i+1)+" = "+ (Math.abs(x[i]) < 0.00001F ? 0 : x[i]);
            System.out.print(ansX);
            for (int j = 0; j < padding - ansX.length(); j++) {
                System.out.print(" ");
            }
            System.out.print("|");
            System.out.println("\t\t 左边 - 右边 = "+lrMiss[i]);
        }
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
        x = new float[c-1];
        return false;
    }


}
