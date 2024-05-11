package com.entity.vo;

import com.entity.ShangpinchurukuEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 商品信息
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("shangpinchuruku")
public class ShangpinchurukuVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

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
     * 创建时间  show1 show2 photoShow
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
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
