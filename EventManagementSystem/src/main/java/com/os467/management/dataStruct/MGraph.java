package com.os467.management.dataStruct;

import com.os467.management.Constant;
import com.os467.management.entity.View;

import java.util.*;

public class MGraph {

    //��ͼ��Ե㼯��
    private static Map<String,String> wayMap = new HashMap<>();

    //��������
    private static View[] vertex = Constant.views;

    //ͼ��·������
    private static int[][] pre = Constant.pre;

    //ͼ�Ķ������ͱ���
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
     * ��ȡ����֮������·��
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
     * �Ͻ�˹�����㷨���ɵ�Դ�㵽����Դ��·����·�����ȣ����浽��ϣ���б����´�ֱ��ȡ��ʹ��
     * @param source
     */
    private void dijkstra(Integer source) {
        HashMap<Integer,Path> s = new HashMap<>();
        //��¼ÿ�θ��µ��������
        HashMap<Integer,Path> vs = new HashMap<>();

        //s��ʼֻ����Դ��s�м��Ϻͼ�¼����
        s.put(source,new Path(0,(source+1)+"->"));
        for (int i = 0; i < vertexNum; i++) {
            if(i == source){
                continue;
            }
            //��������㵽vs���ϣ���ʼ��һ��ʼ�������
            vs.put(i,new Path(Constant.MAX_INT,""));
        }

        while (true){
            //ͨ��s���������·�������vs����
            updateVs(s,vs);
            //��ȡ��vs������Ŀǰ���·�������v����
            Integer index = selectDisInVs(vs);
            Path dist = vs.get(index);
            //��vs�������Ƴ�
            vs.remove(index);
            //����s����
            s.put(index,dist);
            if (vs.isEmpty()){
                break;
            }
        }

        //�����Դ�㵽�����㼯��
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
        //ͨ��s���ϵ㼯����vs
        for (Integer key : sk) {
            //�����½��
            Path disPath = s.get(key);
            Integer sDis = disPath.getDist();
            for (int i = 0; i < vertexNum; i++) {
                if (i == key){
                    continue;
                }
                //����vs�㼯���뵱ǰs������ͨ��
                if (pre[key][i] != Constant.MAX_INT && vs.containsKey(i)){
                    //����vs�����е�i������������Դ·��
                    Path old = vs.get(i);
                    Integer dist = old.getDist();
                    //���s�����еĵ��ܹ�����vs�����е��Դ·�����������
                    int preUpdate = sDis + pre[key][i];
                    if (dist > preUpdate){
                        //˵��vs��dist�ĳ��ȿ��Ա����£�ͨ��s�����еĵ��и��õ�·��ѡ��
                        old.setDist(preUpdate);
                        //����Ϊs�㼯�м�¼��·��
                        old.setDistPath(disPath.getDistPath() + (i + 1) + "->");
                    }else {
                        //����������
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