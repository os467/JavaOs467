package designMode.build.objectPool;

public class Client {

    public static void main(String[] args) {

        ResourcePool resourcePool = new ResourcePool();
        Resource resource = resourcePool.acquireResource();

        System.out.println("当前使用资源:"+resource);

        System.out.println("释放资源");
        resource.release();

        Resource resource1 = resourcePool.acquireResource();

        System.out.println("当前使用资源:"+resource1);

        Resource resource2 = resourcePool.acquireResource();

        System.out.println("当前使用资源:"+resource2);

    }

}
