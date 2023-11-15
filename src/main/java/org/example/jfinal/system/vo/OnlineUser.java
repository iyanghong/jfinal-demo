package org.example.jfinal.system.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.jfinal.system.enums.UserGender;
import org.example.jfinal.system.model.Role;
import org.example.jfinal.system.model.User;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class OnlineUser implements Serializable {

    public OnlineUser() {
    }

    /**
     * 性别（值）
     */
    private Integer genderValue;

    /**
     * 用户id
     */
    private int id;

    /**
     * uuid
     */
    private String uuid;

    /**
     * token
     */
    private String token;

    /**
     * 来源服务
     */
    private String service;

    /**
     * 账户
     */
    private String account;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 头像
     */
    private String header;

    /**
     * 磁盘id
     */
    private String disk;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 性别
     */
    private String gender;

    public OnlineUser(User user){
        this.id = user.getId();
        this.uuid = user.getUuid();
        this.service = user.getService();
        this.account = user.getAccount();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.header = user.getHeader();
        this.disk = user.getDisk();
        this.nickname = user.getNickname();
        this.gender = UserGender.getGender(user.getGender());
        this.genderValue = user.getGender();
        this.signature = user.getSignature();
        this.loginIp = user.getLoginIp();
        this.lastLoginTime = user.getLastLoginTime();
        this.onlineTime = user.getOnlineTime();
        this.score = user.getScore();
        this.address = user.getAddress();
    }

    public OnlineUser(User user, String token, List<Role> roles, List<String> permissions) {
        this.id = user.getId();
        this.uuid = user.getUuid();
        this.token = token;
        this.service = user.getService();
        this.account = user.getAccount();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.header = user.getHeader();
        this.disk = user.getDisk();
        this.nickname = user.getNickname();
        this.gender = UserGender.getGender(user.getGender());
        this.genderValue = user.getGender();
        this.signature = user.getSignature();
        this.loginIp = user.getLoginIp();
        this.lastLoginTime = user.getLastLoginTime();
        this.onlineTime = user.getOnlineTime();
        this.score = user.getScore();
        this.address = user.getAddress();
        this.roles = roles;
        this.permissions = permissions;
    }

    /**
     * 个性签名
     */
    private String signature;

    /**
     * 登录ip
     */
    private String loginIp;

    /**
     * 最后登陆时间
     */
    private Date lastLoginTime;

    /**
     * 登录时常,最大12
     */
    private Integer onlineTime;

    /**
     * 用户积分
     */
    private Integer score;

    /**
     * 用户所在城市,默认是未知
     */
    private Integer address;

    /**
     * 用户所在城市名
     */
    private String addressName;

    /**
     * 所有角色信息
     */
    @JsonProperty("roles")
    private List<Role> roles;

    /**
     * 所有权限信息
     */
    @JsonProperty("permissions")
    private List<String> permissions;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDisk() {
        return disk;
    }

    public void setDisk(String disk) {
        this.disk = disk;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getGenderValue() {
        return genderValue;
    }

    public void setGenderValue(Integer genderValue) {
        this.genderValue = genderValue;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(Integer onlineTime) {
        this.onlineTime = onlineTime;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getAddress() {
        return address;
    }

    public void setAddress(Integer address) {
        this.address = address;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * 当前登录用户是否管理员
     *
     * @return 是否管理员
     */
    public boolean isAdmin() {
        return false;
    }


}
