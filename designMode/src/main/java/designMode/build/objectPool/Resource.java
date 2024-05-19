package designMode.build.objectPool;

public class Resource {

    private ResourcePool resourcePool;

    public Resource(ResourcePool resourcePool) {
        this.resourcePool = resourcePool;
    }

    //资源释放方法
    public void release(){
        resourcePool.releaseResource(this);
    }

}
