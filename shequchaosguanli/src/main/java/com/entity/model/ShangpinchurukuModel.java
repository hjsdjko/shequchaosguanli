package com.entity.model;

import com.entity.ShangpinchurukuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 商品信息
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class ShangpinchurukuModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 商品名称
     */
    private Integer shangpinId;


    /**
     * 出入库时间
     */
    private Integer shangpinchurukuNumber;


    /**
     * 类型
     */
    private Integer shangpinchurukuTypes;


    /**
     * 创建时间  show1 show2 photoShow
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
	 * 获取：商品名称
	 */
    public Integer getShangpinId() {
        return shangpinId;
    }


    /**
	 * 设置：商品名称
	 */
    public void setShangpinId(Integer shangpinId) {
        this.shangpinId = shangpinId;
    }
    /**
	 * 获取：出入库时间
	 */
    public Integer getShangpinchurukuNumber() {
        return shangpinchurukuNumber;
    }


    /**
	 * 设置：出入库时间
	 */
    public void setShangpinchurukuNumber(Integer shangpinchurukuNumber) {
        this.shangpinchurukuNumber = shangpinchurukuNumber;
    }
    /**
	 * 获取：类型
	 */
    public Integer getShangpinchurukuTypes() {
        return shangpinchurukuTypes;
    }


    /**
	 * 设置：类型
	 */
    public void setShangpinchurukuTypes(Integer shangpinchurukuTypes) {
        this.shangpinchurukuTypes = shangpinchurukuTypes;
    }
    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
