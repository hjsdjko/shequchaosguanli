package com.entity.vo;

import com.entity.ChaoshiEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 超市管理员
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("chaoshi")
public class ChaoshiVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 账户
     */

    @TableField(value = "username")
    private String username;


    /**
     * 密码
     */

    @TableField(value = "password")
    private String password;


    /**
     * 超市管理姓名
     */

    @TableField(value = "chaoshi_name")
    private String chaoshiName;


    /**
     * 联系方式
     */

    @TableField(value = "chaoshi_phone")
    private String chaoshiPhone;


    /**
     * 身份证号
     */

    @TableField(value = "chaoshi_id_number")
    private String chaoshiIdNumber;


    /**
     * 性别
     */

    @TableField(value = "sex_types")
    private Integer sexTypes;


    /**
     * 假删
     */

    @TableField(value = "chaoshi_delete")
    private Integer chaoshiDelete;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：账户
	 */
    public String getUsername() {
        return username;
    }


    /**
	 * 获取：账户
	 */

    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 设置：密码
	 */
    public String getPassword() {
        return password;
    }


    /**
	 * 获取：密码
	 */

    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 设置：超市管理姓名
	 */
    public String getChaoshiName() {
        return chaoshiName;
    }


    /**
	 * 获取：超市管理姓名
	 */

    public void setChaoshiName(String chaoshiName) {
        this.chaoshiName = chaoshiName;
    }
    /**
	 * 设置：联系方式
	 */
    public String getChaoshiPhone() {
        return chaoshiPhone;
    }


    /**
	 * 获取：联系方式
	 */

    public void setChaoshiPhone(String chaoshiPhone) {
        this.chaoshiPhone = chaoshiPhone;
    }
    /**
	 * 设置：身份证号
	 */
    public String getChaoshiIdNumber() {
        return chaoshiIdNumber;
    }


    /**
	 * 获取：身份证号
	 */

    public void setChaoshiIdNumber(String chaoshiIdNumber) {
        this.chaoshiIdNumber = chaoshiIdNumber;
    }
    /**
	 * 设置：性别
	 */
    public Integer getSexTypes() {
        return sexTypes;
    }


    /**
	 * 获取：性别
	 */

    public void setSexTypes(Integer sexTypes) {
        this.sexTypes = sexTypes;
    }
    /**
	 * 设置：假删
	 */
    public Integer getChaoshiDelete() {
        return chaoshiDelete;
    }


    /**
	 * 获取：假删
	 */

    public void setChaoshiDelete(Integer chaoshiDelete) {
        this.chaoshiDelete = chaoshiDelete;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
