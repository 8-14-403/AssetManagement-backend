package com.htjn.assetManagement.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "assert")
public class Asset {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "assert_id", length = 36)
    private String id;
    @Column(name = "assets_type", length = 32)
    private String assetsType;  //资产类别
    @Column(name = "assert_code", nullable = false, length = 32)
    private String assertCode; //资产编号
    @Column(name = "company_number", length = 32)
    private String companyNumber;  //单位编号
    @Column(name = "device_name", nullable = false, length = 40)
    private String deviceName;  //设备名称
    @Column(name = "model", length =32)
    private String model; //型号
    @Column(name = "country", length = 10)
    private String country; //国家
    @Column(name = "manufacture", length = 40)
    private String manufacturer;  //生产厂家
    @Column(name = "factory_number", length = 32)
    private String factoryNumber;  //出厂编号
    @Column(name = "department", length = 40)
    private String department; //使用部门
    @Column(name = "user", length = 40)
    private String user; //使用人
    @Column(name = "original_value")
    private String originalValue; //资产原值
    @Column(name = "project", length = 40)
    private String project; //计划项目
    @Column(name = "number")
    private  int number; //数量
    @Column(name = "comment", columnDefinition="TEXT")
    private String comment; //备注
    @Column(name = "create_time",insertable = false,updatable = false,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createTime; //生成时间
    @Column(name = "update_time",insertable = false,updatable = false,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updateTime; //更新时间


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAssetsType() {
        return assetsType;
    }

    public void setAssetsType(String assetsType) {
        this.assetsType = assetsType;
    }

    public String getAssertCode() {
        return assertCode;
    }

    public void setAssertCode(String assertCode) {
        this.assertCode = assertCode;
    }

    public String getCompanyNumber() {
        return companyNumber;
    }

    public void setCompanyNumber(String companyNumber) {
        this.companyNumber = companyNumber;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getFactoryNumber() {
        return factoryNumber;
    }

    public void setFactoryNumber(String factoryNumber) {
        this.factoryNumber = factoryNumber;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getOriginalValue() {
        return originalValue;
    }

    public void setOriginalValue(String originalValue) {
        this.originalValue = originalValue;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Asset{" +
                "id='" + id + '\'' +
                ", assetsType='" + assetsType + '\'' +
                ", assertCode='" + assertCode + '\'' +
                ", companyNumber='" + companyNumber + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", model='" + model + '\'' +
                ", country='" + country + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", factoryNumber='" + factoryNumber + '\'' +
                ", department='" + department + '\'' +
                ", user='" + user + '\'' +
                ", originalValue='" + originalValue + '\'' +
                ", project='" + project + '\'' +
                ", number=" + number +
                ", comment='" + comment + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
