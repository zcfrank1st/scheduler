package com.fanli.dataplatform.scheduler.client.domain;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sunny on 15/3/4.
 */
public class TreeNodeDO implements Serializable {

    private String project;

    private String path;

    private String label;

    private File file;

    private boolean isDirectory;

    private List<TreeNodeDO> children = new ArrayList<TreeNodeDO>();


    public TreeNodeDO() {
    }

    public TreeNodeDO(TreeNodeDO pre, File file) {
        this.file = file;
        this.project = pre.getProject();
        this.label = file.getName();
        this.path = pre.getPath() + "/" + file.getName();
        this.isDirectory = file.isDirectory();
    }

    public TreeNodeDO(File file) {
        this.file = file;
        this.project = file.getName();
        this.label = file.getName();
        this.path = file.getName();
        this.isDirectory = file.isDirectory();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<TreeNodeDO> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNodeDO> children) {
        this.children = children;
    }

    public void addChild(TreeNodeDO child) {
        this.children.add(child);
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public void setDirectory(boolean isDirectory) {
        this.isDirectory = isDirectory;
    }
}
