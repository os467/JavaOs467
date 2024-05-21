package designMode.behavior.strategy;

public interface SortStrategy {
    void sort(int[] array);
}


class BubbleSortStrategy implements SortStrategy {
    @Override
    public void sort(int[] array) {
        // 实现冒泡排序算法
        System.out.println("使用冒泡排序");
        // ...排序逻辑
        int temp;
        for (int i = 0; i < array.length - 1; i++) {
            //不断将较小的替换到前面，最后一个元素将是本趟最大元素
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j+1]){
                    temp =array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }
}


class QuickSortStrategy implements SortStrategy {
    @Override
    public void sort(int[] array) {
        // 实现快速排序算法
        System.out.println("使用快速排序");
        // ...排序逻辑
        quickSort(array,0,array.length-1);
    }

    private void quickSort(int[] array, int low, int high) {
        if (low < high){
            //p为基准索引
            //i,j作为左右遍历双指针使用
            int p = low,i = low,j = high;
            int temp;//用于交换
            //开始一趟快速排序
            while (i <= j){
                //先从右边开始找比基准小的进行交换
                while (i <= j){
                    if (array[j] < array[p]){
                        temp = array[p];
                        array[p] = array[j];
                        array[j] = temp;
                        p = j;//基准值位置更新
                        j--;
                        break;
                    }
                    j--;
                }
                //再从左边找比基准大的进行交换
                while (i <= j){
                    if (array[i] > array[p]){
                        temp = array[p];
                        array[p] = array[i];
                        array[i] = temp;
                        p = i;//基准值位置更新
                        i++;
                        break;
                    }
                    i++;
                }
            }
            //对基准左边部分做快速排序
            quickSort(array,low,p-1);
            //对基准右边部分做快速排序
            quickSort(array,p+1,high);
        }


    }
}


class InsertionSortStrategy implements SortStrategy {
    @Override
    public void sort(int[] array) {
        // 实现插入排序算法
        System.out.println("使用插入排序");
        // ...排序逻辑
        int temp,j;

        //从第一个元素之后一个元素开始插入
        for (int i = 1; i < array.length; i++) {
            //是否需要排序
            if (array[i] < array[i-1]){
                //需要插入排序
                temp = array[i];
                //逐一比较，直到到达末尾或找到已排序元素小于插入值
                for (j = i - 1; j >= 0 && array[j] > temp; j--) {
                    //该数字向后移动一位
                    array[j + 1] = array[j];
                }
                array[j+1] = temp;
            }
        }
    }
}