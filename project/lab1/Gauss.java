package project.lab1;


public class Gauss {
    public static int N = 110, m = 6 ;
    public static double INF = 1E-6;
    public static double[][] a = new double[N][N];
    private Float[] ms;

    public Gauss(Float[] ms) {
        this.ms = ms;
    }

    public void setM(){
        //定义x数组
        int[] x = new int[8];
        for(int i = 0;i < 8;i++){
            x[i] = i;
        }
        //定义y数组
        double [] y = new double[8];
        for(int i = 0;i < 8;i++){
            y[i] = Math.sin(x[i]) + 2;
        }
        //定义h数组
        double[] h = new double[8];
        for(int i = 0;i < 7;i++){
            h[i] = x[i + 1] - x[i];
        }
        //定义aa数组
        double[] aa = new double[8];
        for(int i = 1;i < 7;i++){
            aa[i] = h[i - 1]/(h[i - 1] + h[i]);
        }
        //定义bb数组
        double[] bb = new double[8];
        for(int i = 1; i < 7;i++){
            bb[i] = 3*((1 - aa[i]) * (y[i] - y[i - 1])/h[i - 1] + aa[i] * (y[i + 1] - y[i])/h[i]);
        }


        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m+1; j++) {
                a[i][j] = 0;
            }
        }
        a[0][0] = 2;
        a[0][1] = aa[1];
        a[0][6] = bb[1] - (1 - aa[1]) * Math.cos(x[0]);

        a[1][0] = 1 - aa[2];
        a[1][1] = 2;
        a[1][2] = aa[2];
        a[1][6] = bb[2];

        a[2][1] = 1 - aa[3];
        a[2][2] = 2;
        a[2][3] = aa[3];
        a[2][6] = bb[3];

        a[3][2] = 1 - aa[4];
        a[3][3] = 2;
        a[3][4] = aa[4];
        a[3][6] = bb[4];

        a[4][3] = 1 - aa[5];
        a[4][4] = 2;
        a[4][5] = aa[5];
        a[4][6] = bb[5];

        a[5][4] = 1 - aa[6];
        a[5][5] = 2;
        a[5][6] = bb[6] - aa[6] * Math.cos(x[7]);




        //判断，0有唯一解，1有无数组解，2无解
        int t = gauss();
        if (t == 0) {
            for (int i = 0; i<m; i++){
                ms[i+1] = (float)(Math.abs(a[i][m]) < INF ? 0 : a[i][m]);
            }
        }
        else if (t == 1) System.out.println("Infinite group solutions");
        else if (t == 2) System.out.println("No solutions");

    }

    //高斯消元
    public static int gauss() {
        //c是column列，r是row行, 每次遍历c列中的所有值，拿到c列中最大绝对值的位置t行
        int c, r;
        for (c = 0, r = 0; c < m; c++) {

            int t = r;
            for (int i = r; i < m; i++){
                if (Math.abs(a[i][c]) > Math.abs(a[t][c])) t = i;
            }

            if (Math.abs(a[t][c]) < INF) continue;              //如果为0，那么就说明当前列全部为0，直接看下一个就行

            for (int i = c; i < m+1; i++) swap(t, r, i);      //将a[t]和a[r]进行交换，也就是将包含最大绝对值的t行和当前最顶端r行进行交换
            for (int i = m; i >= c; i--) a[r][i] /= a[r][c];   //将a[r][i]变为1,也就是这行所有值除以a[r][c],切记要反着更新值，不然数据会错
            for (int i = r + 1; i < m; i++) {                  //从第r行开始，将第c列的所有数都消为0
                if (Math.abs(a[i][c]) > INF) {
                    for (int j = m; j >= c; j--) {
                        a[i][j] -= a[i][c] * a[r][j];      //将每行都减去行首*对应列的数，就能保证：行首 -= 1*行首 = 0
                    }
                }
            }

            r++; //换到下一行
        }

        // r小于m说明剩下的方程个数小于m，不存在唯一解，判断是无解还是有无数组解
        if (r < m) {
            for (int i = r; i < m; i++) {
                if (Math.abs(a[i][m]) > INF) return 2;     //也就是如果0等于非0的情况，无解
            }
            return 1;   //有无数组解
        }

        //如果有唯一解，那么倒着消一遍就可以了，也就是从倒数第二行开始，将等式左边从1开始的非0数，根据下一行对应列的值进行消元，直到等式左侧除了那个1以外所有值为0
        for (int i = m-1; i >= 0; i--) {
            for (int j = i+1; j < m; j++) {
                a[i][m] -= a[i][j] * a[j][m];
            }
        }
        return 0;
    }

    //我***的java, 有个swap会死吗，c++和python哪个不比你好使？
    public static void swap(int t, int c, int i) {
        double temp = a[t][i];
        a[t][i] = a[c][i];
        a[c][i] = temp;
    }
}
