package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 商品信息
 *
 * @author 
 * @email
 */
@TableName("shangpinchuruku")
public class ShangpinchurukuEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public ShangpinchurukuEntity() {

	}

	public ShangpinchurukuEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")

    private Integer id;


    /**
     * 商品名称
     */
    @TableField(value = "shangpin_id")

    private Integer shangpinId;


    /**
     * 出入库时间
     */
    @TableField(value = "shangpinchuruku_number")

    private Integer shangpinchurukuNumber;


    /**
     * 类型
     */
    @TableField(value = "shangpinchuruku_types")

    private Integer shangpinchurukuTypes;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "create_time",fill = FieldFill.INSERT)

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
	 * 设置：商品名称
	 */
    public Integer getShangpinId() {
        return shangpinId;
    }


    /**
	 * 获取：商品名称
	 */

    public void setShangpinId(Integer shangpinId) {
        this.shangpinId = shangpinId;
    }
    /**
	 * 设置：出入库时间
	 */
    public Integer getShangpinchurukuNumber() {
        return shangpinchurukuNumber;
    }


    /**
	 * 获取：出入库时间
	 */

    public void setShangpinchurukuNumber(Integer shangpinchurukuNumber) {
        this.shangpinchurukuNumber = shangpinchurukuNumber;
    }
    /**
	 * 设置：类型
	 */
    public Integer getShangpinchurukuTypes() {
        return shangpinchurukuTypes;
    }


    /**
	 * 获取：类型
	 */

    public void setShangpinchurukuTypes(Integer shangpinchurukuTypes) {
        this.shangpinchurukuTypes = shangpinchurukuTypes;
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

    @Override
    public String toString() {
        return "Shangpinchuruku{" +
            "id=" + id +
            ", shangpinId=" + shangpinId +
            ", shangpinchurukuNumber=" + shangpinchurukuNumber +
            ", shangpinchurukuTypes=" + shangpinchurukuTypes +
            ", createTime=" + createTime +
        "}";
    }
}
