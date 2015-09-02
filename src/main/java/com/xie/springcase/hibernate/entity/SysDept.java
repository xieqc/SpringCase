package com.xie.springcase.hibernate.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Xie on 2015/9/2.
 */
@Entity
@Table(name = "sys_dept", schema = "", catalog = "ums_db")
public class SysDept {
    private Integer id;
    private String name;
    private String remark;
    private Byte status;
    private SysDept superior;
    private List<SysUser> sysUsers;

    @Id
    @Column(name = "id")
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

        SysDept sysDept = (SysDept) o;

        if (id != null ? !id.equals(sysDept.id) : sysDept.id != null) return false;
        if (name != null ? !name.equals(sysDept.name) : sysDept.name != null) return false;
        if (remark != null ? !remark.equals(sysDept.remark) : sysDept.remark != null) return false;
        if (status != null ? !status.equals(sysDept.status) : sysDept.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @OneToOne
    @JoinColumn(name = "superior_id", referencedColumnName = "id")
    public SysDept getSuperior() {
        return superior;
    }

    public void setSuperior(SysDept superior) {
        this.superior = superior;
    }

    @OneToMany(mappedBy = "sysDept")
    public List<SysUser> getSysUsers() {
        return sysUsers;
    }

    public void setSysUsers(List<SysUser> sysUsers) {
        this.sysUsers = sysUsers;
    }
}
