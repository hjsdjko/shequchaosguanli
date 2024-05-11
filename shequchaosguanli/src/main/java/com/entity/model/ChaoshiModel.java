package com.entity.model;

import com.entity.ChaoshiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 超市管理员
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class ChaoshiModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 账户
     */
    private String username;


    /**
     * 密码
     */
    private String password;


    /**
     * 超市管理姓名
     */
    private String chaoshiName;


    /**
     * 联系方式
     */
    private String chaoshiPhone;


    /**
     * 身份证号
     */
    private String chaoshiIdNumber;


    /**
     * 性别
     */
    private Integer sexTypes;


    /**
     * 假删
     */
    private Integer chaoshiDelete;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：账户
	 */
    public String getUsername() {
        return username;
    }


    /**
	 * 设置：账户
	 */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 获取：密码
	 */
    public String getPassword() {
        return password;
    }


    /**
	 * 设置：密码
	 */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 获取：超市管理姓名
	 */
    public String getChaoshiName() {
        return chaoshiName;
    }


    /**
	 * 设置：超市管理姓名
	 */
    public void setChaoshiName(String chaoshiName) {
        this.chaoshiName = chaoshiName;
    }
    /**
	 * 获取：联系方式
	 */
    public String getChaoshiPhone() {
        return chaoshiPhone;
    }


    /**
	 * 设置：联系方式
	 */
    public void setChaoshiPhone(String chaoshiPhone) {
        this.chaoshiPhone = chaoshiPhone;
    }
    /**
	 * 获取：身份证号
	 */
    public String getChaoshiIdNumber() {
        return chaoshiIdNumber;
    }


    /**
	 * 设置：身份证号
	 */
    public void setChaoshiIdNumber(String chaoshiIdNumber) {
        this.chaoshiIdNumber = chaoshiIdNumber;
    }
    /**
	 * 获取：性别
	 */
    public Integer getSexTypes() {
        return sexTypes;
    }


    /**
	 * 设置：性别
	 */
    public void setSexTypes(Integer sexTypes) {
        this.sexTypes = sexTypes;
    }
    /**
	 * 获取：假删
	 */
    public Integer getChaoshiDelete() {
        return chaoshiDelete;
    }


    /**
	 * 设置：假删
	 */
    public void setChaoshiDelete(Integer chaoshiDelete) {
        this.chaoshiDelete = chaoshiDelete;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
