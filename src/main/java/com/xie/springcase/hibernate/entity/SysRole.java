package com.xie.springcase.hibernate.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Xie on 2015/9/2.
 */
@Entity
@Table(name = "sys_role", schema = "", catalog = "ums_db")
public class SysRole {
    private Integer id;
    private String name;
    private String remark;
    private Byte status;
    private SysRole parent;
    private List<SysFunct> sysFuncts;
    private List<SysUser> sysUsers;

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

        SysRole sysRole = (SysRole) o;

        if (id != null ? !id.equals(sysRole.id) : sysRole.id != null) return false;
        if (name != null ? !name.equals(sysRole.name) : sysRole.name != null) return false;
        if (remark != null ? !remark.equals(sysRole.remark) : sysRole.remark != null) return false;
        if (status != null ? !status.equals(sysRole.status) : sysRole.status != null) return false;

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
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    public SysRole getParent() {
        return parent;
    }

    public void setParent(SysRole parent) {
        this.parent = parent;
    }

    @ManyToMany
    @JoinTable(name = "cfg_role_funct", catalog = "ums_db", schema = "", joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "funct_id", referencedColumnName = "id", nullable = false))
    public List<SysFunct> getSysFuncts() {
        return sysFuncts;
    }

    public void setSysFuncts(List<SysFunct> sysFuncts) {
        this.sysFuncts = sysFuncts;
    }

    @ManyToMany(mappedBy = "sysRoles")
    public List<SysUser> getSysUsers() {
        return sysUsers;
    }

    public void setSysUsers(List<SysUser> sysUsers) {
        this.sysUsers = sysUsers;
    }
}
