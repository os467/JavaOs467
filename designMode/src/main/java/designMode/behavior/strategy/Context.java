package designMode.behavior.strategy;

public class Context {

    //当前策略
    private SortStrategy strategy;

    public static void main(String[] args) {

        Context context = new Context();

        int[] array = {3,2,5,6,1,0,8,9};

        context.setStrategy(new BubbleSortStrategy());
        context.sortArray(array);

        context.setStrategy(new InsertionSortStrategy());
        context.sortArray(array);

        context.setStrategy(new QuickSortStrategy());
        context.sortArray(array);

    }

    private void setStrategy(SortStrategy strategy) {
        this.strategy = strategy;
    }

    public void sortArray(int[] array){
        strategy.sort(array);
    }

}
