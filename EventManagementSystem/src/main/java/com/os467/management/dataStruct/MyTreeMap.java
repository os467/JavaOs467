package com.os467.management.dataStruct;

import java.util.List;
import java.util.Stack;

public class MyTreeMap<K, V> {

    private TreeNode root;

    private Integer nodeNum = 0;

    private Integer leafNum = 0;

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
     * ��������������
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
        incLeafNum();
    }

    private void incLeafNum() {
        leafNum = nodeNum + 1;
    }

    /**
     * �������
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
     * ��ȡ�ɹ�ASL
     * @return
     */
    public double getAslSuccess() {
        if (aslSuccess == null){
            calculate();
        }
        return aslSuccess;
    }

    /**
     * ����ASL
     */
    private void calculate() {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        Integer successTime = 0;
        Integer levelSum = 0;
        Integer failedLevelSum = 0;
        TreeNode treeNode = stack.peek();
        while (treeNode != null || !stack.empty()){
            while (treeNode != null){
                //��������ջ
                stack.push(treeNode);
                treeNode = treeNode.lChild;
            }
            //���ʵ�Ҷ��ʧ�ܴ���+1
            failedLevelSum += stack.peek().level;

            treeNode = stack.peek();
            //���ʸ����ж��Ƿ�Ϊ�ն�
            if (treeNode != null){
                levelSum += treeNode.level;
                successTime++;
            }
            //ջ��Ԫ�ص���
            stack.pop();
            TreeNode last = treeNode;
            //������
            treeNode = treeNode.rChild;
            if (treeNode == null){
                //���ʵ�Ҷ��ʧ�ܴ���+1
                failedLevelSum += last.level;
            }
        }

        aslFailed = (double)failedLevelSum/(double)leafNum;
        aslSuccess = (double)levelSum / (double)successTime;
    }

    /**
     * ��ȡʧ��ASL
     * @return
     */
    public double getAslFailed() {
        if (aslFailed == null){
            calculate();
        }
        return aslFailed;
    }
}
