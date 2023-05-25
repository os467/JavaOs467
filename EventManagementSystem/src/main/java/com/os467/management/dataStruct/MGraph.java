package com.os467.management.dataStruct;

import com.os467.management.Constant;
import com.os467.management.entity.View;

import java.util.*;

public class MGraph {

    //地图点对点集合
    private static Map<String,String> wayMap = new HashMap<>();

    //顶点数组
    private static View[] vertex = Constant.views;

    //图的路径数组
    private static int[][] pre = Constant.pre;

    //图的顶点数和边数
    private static int vertexNum = Constant.MAX_SIZE, edgeNum = Constant.EDGE_NUM;


    public String getTargetInfo(String tP) {
        for (View view : vertex) {
            if (view.getName().equals(tP)){
                return view.getIntroduction();
            }
        }
        return "";
    }

    /**
     * 获取两点之间的最短路径
     * @param sP
     * @param tP
     * @return
     */
    public String getWay(String sP, String tP) {
        Integer s = getVertexIndex(sP);
        Integer t = getVertexIndex(tP);
        String wayInfo = wayMap.get(s + "," + t);
        if (wayInfo != null){
            return wayInfo;
        }else {
            dijkstra(s);
            wayInfo = wayMap.get(s + "," + t);
        }
        return wayInfo;
    }

    /**
     * 迪杰斯特拉算法生成单源点到其它源点路径及路径长度，保存到哈希表中便于下次直接取出使用
     * @param source
     */
    private void dijkstra(Integer source) {
        HashMap<Integer,Path> s = new HashMap<>();
        //记录每次更新的最近距离
        HashMap<Integer,Path> vs = new HashMap<>();

        //s开始只包含源，s有集合和记录作用
        s.put(source,new Path(0,(source+1)+"->"));
        for (int i = 0; i < vertexNum; i++) {
            if(i == source){
                continue;
            }
            //添加其它点到vs集合，初始化一开始最近距离
            vs.put(i,new Path(Constant.MAX_INT,""));
        }

        while (true){
            //通过s集合中最短路径点更新vs集合
            updateVs(s,vs);
            //获取到vs集合中目前最短路径点加入v集合
            Integer index = selectDisInVs(vs);
            Path dist = vs.get(index);
            //从vs集合中移出
            vs.remove(index);
            //加入s集合
            s.put(index,dist);
            if (vs.isEmpty()){
                break;
            }
        }

        //保存此源点到其它点集合
        Set<Integer> keySet = s.keySet();
        for (Integer k : keySet) {
            if (k.equals(source)){
                continue;
            }else {
                Path path = s.get(k);
                String pathPrint = path.getPrint();
                wayMap.put(source+","+k,pathPrint);
            }
        }
    }

    private void updateVs(HashMap<Integer, Path> s, HashMap<Integer, Path> vs) {
        Set<Integer> sk = s.keySet();
        //通过s集合点集更新vs
        for (Integer key : sk) {
            //更新新结点
            Path disPath = s.get(key);
            Integer sDis = disPath.getDist();
            for (int i = 0; i < vertexNum; i++) {
                if (i == key){
                    continue;
                }
                //更新vs点集中与当前s集点连通点
                if (pre[key][i] != Constant.MAX_INT && vs.containsKey(i)){
                    //更新vs集合中的i索引结点的最新源路径
                    Path old = vs.get(i);
                    Integer dist = old.getDist();
                    //如果s集合中的点能够更新vs集合中点的源路径长度则更新
                    int preUpdate = sDis + pre[key][i];
                    if (dist > preUpdate){
                        //说明vs中dist的长度可以被更新，通过s集合中的点有更好的路径选择
                        old.setDist(preUpdate);
                        //更新为s点集中记录的路径
                        old.setDistPath(disPath.getDistPath() + (i + 1) + "->");
                    }else {
                        //否则不做更新
                        continue;
                    }
                }
            }
        }
    }

    private Integer selectDisInVs(HashMap<Integer, Path> vs) {
        Integer index = null;
        Integer dist = null;
        Set<Integer> keySet = vs.keySet();
        for (Integer key : keySet) {
            Integer dis = vs.get(key).getDist();
            if (index == null){
                index = key;
                dist = dis;
            }else if (dis < dist){
                index = key;
                dist = dis;
            }
        }
        return index;
    }

    private Integer getVertexIndex(String name) {
        for (int i = 0; i < vertexNum; i++) {
            if (vertex[i].getName().equals(name)){
                return i;
            }
        }
        return null;
    }
}