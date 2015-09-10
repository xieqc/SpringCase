package com.xie.springcase.hibernate.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Xie on 2015/9/2.
 */
@Entity
@Table(name = "sys_funct", schema = "", catalog = "ums_db")
public class SysFunct {
    private Integer id;
    private String name;
    private String url;
    private String remark;
    private Byte status;
    private SysFunct parent;
    private List<SysRole> sysRoles;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "status")
    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysFunct sysFunct = (SysFunct) o;

        if (id != null ? !id.equals(sysFunct.id) : sysFunct.id != null) return false;
        if (name != null ? !name.equals(sysFunct.name) : sysFunct.name != null) return false;
        if (url != null ? !url.equals(sysFunct.url) : sysFunct.url != null) return false;
        if (remark != null ? !remark.equals(sysFunct.remark) : sysFunct.remark != null) return false;
        if (status != null ? !status.equals(sysFunct.status) : sysFunct.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @OneToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    public SysFunct getParent() {
        return parent;
    }

    public void setParent(SysFunct parent) {
        this.parent = parent;
    }

    @ManyToMany(mappedBy = "sysFuncts")
    public List<SysRole> getSysRoles() {
        return sysRoles;
    }

    public void setSysRoles(List<SysRole> sysRoles) {
        this.sysRoles = sysRoles;
    }
}
