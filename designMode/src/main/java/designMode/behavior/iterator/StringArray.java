package designMode.behavior.iterator;

public class StringArray implements Aggregate{

    public static void main(String[] args) {
        StringArray stringArray = new StringArray(new String[]{"a", "b", "c"});
        Iterator iterator = stringArray.iterator();

        //遍历迭代器
        while (iterator.hasNext()) {
            String next = (String) iterator.next();
            System.out.println(next);
        }
    }

    private String values[];

    public StringArray(String[] values) {
        this.values = values;
    }

    public Iterator iterator(){
        return new StringIterator();
    }

    private class StringIterator implements Iterator{

        private int pos;

        @Override
        public boolean hasNext() {
            return pos < values.length;
        }

        @Override
        public Object next() {
            return values[pos++];
        }
    }

}
