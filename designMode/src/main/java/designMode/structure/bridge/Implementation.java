package designMode.structure.bridge;

//实现类接口
public interface Implementation {

    void operation();
}


//具体实现类A
class SpecificImplA implements Implementation {
    @Override
    public void operation() {
        System.out.println("具体实现操作A");
    }
}

//具体实现类B
class SpecificImplB implements Implementation {
    @Override
    public void operation() {
        System.out.println("具体实现操作B");
    }
}