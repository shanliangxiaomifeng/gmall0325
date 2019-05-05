package com.atguigu.gmall.bean;

import javax.persistence.Column;
import javax.persistence.Id;

public class AdBanner {
    @Id
    private String id;
    @Column
    private String adDesc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdDesc() {
        return adDesc;
    }

    public void setAdDesc(String adDesc) {
        this.adDesc = adDesc;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Column
    private String fileName;
}
