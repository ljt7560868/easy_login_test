package com.credithc.easylogin.DTO;

import java.io.Serializable;
import java.util.Set;

public class LoginUser implements Serializable {

    private static final long serialVersionUID = -1373760761780840081L;

    private Long id;

    private String loginName;

    private Long appId;

    private String name;

    private String code;

    private Long roleId;

    private String roleName;

    private String roleCode;

    private String mobile;

    private String orgName;

    private Integer isTest = 0;

    private Integer dataSecurityVal = 0;

    private Set<Long> roleIdSet;

    private Set<String> roleCodeSet;

    private Set<Long> permissionIdSet;

    private Set<String> permissionCodeSet;

    private String permissionOrgs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Integer getIsTest() {
        return isTest;
    }

    public void setIsTest(Integer isTest) {
        this.isTest = isTest;
    }

    public Integer getDataSecurityVal() {
        return dataSecurityVal;
    }

    public void setDataSecurityVal(Integer dataSecurityVal) {
        this.dataSecurityVal = dataSecurityVal;
    }

    public Set<Long> getRoleIdSet() {
        return roleIdSet;
    }

    public void setRoleIdSet(Set<Long> roleIdSet) {
        this.roleIdSet = roleIdSet;
    }

    public Set<String> getRoleCodeSet() {
        return roleCodeSet;
    }

    public void setRoleCodeSet(Set<String> roleCodeSet) {
        this.roleCodeSet = roleCodeSet;
    }

    public Set<Long> getPermissionIdSet() {
        return permissionIdSet;
    }

    public void setPermissionIdSet(Set<Long> permissionIdSet) {
        this.permissionIdSet = permissionIdSet;
    }

    public Set<String> getPermissionCodeSet() {
        return permissionCodeSet;
    }

    public void setPermissionCodeSet(Set<String> permissionCodeSet) {
        this.permissionCodeSet = permissionCodeSet;
    }

    public String getPermissionOrgs() {
        return permissionOrgs;
    }

    public void setPermissionOrgs(String permissionOrgs) {
        this.permissionOrgs = permissionOrgs;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    /**
     * 本函数输出将作为默认的<shiro:principal/>输出.
     */
    @Override
    public String toString() {
        return loginName;
    }
}