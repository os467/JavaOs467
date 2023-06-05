package com.os467.management.dataStruct;

import java.util.List;

public class MyTreeMap<K, V> {

    private TreeNode root;

    private Integer nodeNum = 0;

    private Double aslSuccess;

    private Double aslFailed;

    public void create(List<K> keys, List<V> values) {
        if (values.get(0) != null){
            root = new TreeNode(keys.get(0),values.get(0),1);
            incNodeNum();
        }
        for (int i = 1; i < values.size(); i++) {
            put(keys.get(i),values.get(i));
        }
    }

    /**
     * 构建二叉排序树
     * @param k
     * @param v
     */
    private void put(K k, V v) {
        Integer key = (Integer) k;
        String value = (String) v;
        TreeNode p = root;
        Integer level = 1;
        while (true){
            ++level;
            if (key > (Integer) (p.key)){
                if (p.rChild == null){
                    p.rChild = new TreeNode(key,value,level);
                    incNodeNum();
                    break;
                }else {
                    p = p.rChild;
                }
            }else{
                if (p.lChild == null){
                    p.lChild = new TreeNode(key,value,level);
                    incNodeNum();
                    break;
                }else {
                    p = p.lChild;
                }
            }
        }
    }

    private void incNodeNum(){
        nodeNum++;
    }


    /**
     * 二叉查找
     * @param key
     * @return
     */
    public V get(K key) {
        TreeNode p = root;
        Integer key0 = (Integer) key;
        while (true){
            if (p != null){
                if (!key0.equals(p.key)){
                    if (key0 > (Integer) (p.key)){
                        p = p.rChild;
                    }else{
                        p = p.lChild;
                    }
                }else {
                    return (V) p.value;
                }
            }else {
                return null;
            }
        }
    }

    class TreeNode<K,V>{
        K key;
        V value;
        Integer level;
        TreeNode lChild;
        TreeNode rChild;

        public TreeNode(K v, V v1,Integer level) {
            key = v;
            value = v1;
            this.level = level;
        }
    }

    /**
     * 获取成功ASL
     * @return
     */
    public double getAslSuccess() {
        if (aslSuccess == null){
            calculate();
        }
        return aslSuccess;
    }

    private boolean inOrder(TreeNode r){
        if (r == null){
            return false;
        }
        //访问左子树
        boolean b1 = inOrder(r.lChild);
        if (!b1){
            failedLevelSum += r.level;
        }
        //访问根结点
        levelSum += r.level;
        //访问右子树
        boolean b = inOrder(r.rChild);
        if (!b){
            failedLevelSum += r.level;
        }
        return true;
    }

    private Integer failedLevelSum = 0;
    private Integer levelSum = 0;

    /**
     * 计算ASL
     */
    private void calculate() {
        failedLevelSum = 0;
        levelSum = 0;
        inOrder(root);
        aslFailed = (double)failedLevelSum/(double)(nodeNum+1);
        aslSuccess = (double)levelSum / (double)nodeNum;
    }


    /**
     * 获取失败ASL
     * @return
     */
    public double getAslFailed() {
        if (aslFailed == null){
            calculate();
        }
        return aslFailed;
    }
}
