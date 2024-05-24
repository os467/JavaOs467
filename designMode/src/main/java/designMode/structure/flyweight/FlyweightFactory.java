package designMode.structure.flyweight;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FlyweightFactory {

    private Map<String,Flyweight> flyweightMap = new ConcurrentHashMap<>();

    public Flyweight getFlyweight(String key){
        return flyweightMap.computeIfAbsent(key,k -> new ConcreteSharableFlyweight(k));
    }

}
