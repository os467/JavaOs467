package designMode.build.objectPool;

import java.util.ArrayList;
import java.util.List;

public class ResourcePool {

    //可用资源列表
    private List<Resource> available = new ArrayList<>();

    //当前资源池创建被投入使用的资源列表
    private List<Resource> inuse = new ArrayList<>();

    //客户端获取资源
    public Resource acquireResource(){
        if (available.size() <= 0){
            Resource resource = new Resource(this);
            inuse.add(resource);
            return resource;
        }else {
            //返回第一个可用资源
            return available.remove(0);
        }
    }

    //客户端释放资源
    public void releaseResource(Resource resource) {
        available.add(resource);
    }
}

