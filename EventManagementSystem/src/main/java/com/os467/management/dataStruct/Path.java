package com.os467.management.dataStruct;


import com.os467.management.Constant;
import com.os467.management.entity.View;

public class Path {

    private Integer dist;

    private String distPath;

    public Path(int dist, String distPath) {
        this.dist = dist;
        this.distPath = distPath;
    }

    public Integer getDist() {
        return dist;
    }

    public void setDist(Integer dist) {
        this.dist = dist;
    }

    public String getDistPath() {
        return distPath;
    }

    public void setDistPath(String distPath) {
        this.distPath = distPath;
    }

    public String getPrint() {
        String[] strings = distPath.split("->");
        for (int i = 0; i < strings.length; i++) {
            View viewById = Constant.getViewById(strings[i]);
            strings[i] = viewById.getName();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("最短路径为:");
        for (int i = 0; i < strings.length - 1; i++) {
            sb.append(strings[i] + Constant.WAYS_SPLIT_CHAR);
        }
        if (strings.length > 0){
            sb.append(strings[strings.length - 1]);
        }
        sb.append("\n\n");
        sb.append("最短路径距离:");
        sb.append(dist);
        sb.append("米");
        return sb.toString();
    }
}
