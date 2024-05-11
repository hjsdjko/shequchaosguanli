package com.entity.view;

import com.entity.ShangpinchurukuEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 商品信息
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("shangpinchuruku")
public class ShangpinchurukuView extends ShangpinchurukuEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 类型的值
		*/
		private String shangpinchurukuValue;



		//级联表 shangpin
			/**
			* 商品名称
			*/
			private String shangpinName;
			/**
			* 商品类型
			*/
			private Integer shangpinTypes;
				/**
				* 商品类型的值
				*/
				private String shangpinValue;
			/**
			* 商品照片
			*/
			private String shangpinPhoto;
			/**
			* 计量单位
			*/
			private String shangpinJiliang;
			/**
			* 商品库存
			*/
			private Integer shangpinKucunNumber;
			/**
			* 商品原价
			*/
			private Double shangpinOldMoney;
			/**
			* 现价
			*/
			private Double shangpinNewMoney;
			/**
			* 是否上架
			*/
			private Integer shangxiaTypes;
				/**
				* 是否上架的值
				*/
				private String shangxiaValue;
			/**
			* 逻辑删除
			*/
			private Integer shangpinDelete;
			/**
			* 商品简介
			*/
			private String shangpinContent;

	public ShangpinchurukuView() {

	}

	public ShangpinchurukuView(ShangpinchurukuEntity shangpinchurukuEntity) {
		try {
			BeanUtils.copyProperties(this, shangpinchurukuEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 类型的值
			*/
			public String getShangpinchurukuValue() {
				return shangpinchurukuValue;
			}
			/**
			* 设置： 类型的值
			*/
			public void setShangpinchurukuValue(String shangpinchurukuValue) {
				this.shangpinchurukuValue = shangpinchurukuValue;
			}














				//级联表的get和set shangpin
					/**
					* 获取： 商品名称
					*/
					public String getShangpinName() {
						return shangpinName;
					}
					/**
					* 设置： 商品名称
					*/
					public void setShangpinName(String shangpinName) {
						this.shangpinName = shangpinName;
					}
					/**
					* 获取： 商品类型
					*/
					public Integer getShangpinTypes() {
						return shangpinTypes;
					}
					/**
					* 设置： 商品类型
					*/
					public void setShangpinTypes(Integer shangpinTypes) {
						this.shangpinTypes = shangpinTypes;
					}


						/**
						* 获取： 商品类型的值
						*/
						public String getShangpinValue() {
							return shangpinValue;
						}
						/**
						* 设置： 商品类型的值
						*/
						public void setShangpinValue(String shangpinValue) {
							this.shangpinValue = shangpinValue;
						}
					/**
					* 获取： 商品照片
					*/
					public String getShangpinPhoto() {
						return shangpinPhoto;
					}
					/**
					* 设置： 商品照片
					*/
					public void setShangpinPhoto(String shangpinPhoto) {
						this.shangpinPhoto = shangpinPhoto;
					}
					/**
					* 获取： 计量单位
					*/
					public String getShangpinJiliang() {
						return shangpinJiliang;
					}
					/**
					* 设置： 计量单位
					*/
					public void setShangpinJiliang(String shangpinJiliang) {
						this.shangpinJiliang = shangpinJiliang;
					}
					/**
					* 获取： 商品库存
					*/
					public Integer getShangpinKucunNumber() {
						return shangpinKucunNumber;
					}
					/**
					* 设置： 商品库存
					*/
					public void setShangpinKucunNumber(Integer shangpinKucunNumber) {
						this.shangpinKucunNumber = shangpinKucunNumber;
					}
					/**
					* 获取： 商品原价
					*/
					public Double getShangpinOldMoney() {
						return shangpinOldMoney;
					}
					/**
					* 设置： 商品原价
					*/
					public void setShangpinOldMoney(Double shangpinOldMoney) {
						this.shangpinOldMoney = shangpinOldMoney;
					}
					/**
					* 获取： 现价
					*/
					public Double getShangpinNewMoney() {
						return shangpinNewMoney;
					}
					/**
					* 设置： 现价
					*/
					public void setShangpinNewMoney(Double shangpinNewMoney) {
						this.shangpinNewMoney = shangpinNewMoney;
					}
					/**
					* 获取： 是否上架
					*/
					public Integer getShangxiaTypes() {
						return shangxiaTypes;
					}
					/**
					* 设置： 是否上架
					*/
					public void setShangxiaTypes(Integer shangxiaTypes) {
						this.shangxiaTypes = shangxiaTypes;
					}


						/**
						* 获取： 是否上架的值
						*/
						public String getShangxiaValue() {
							return shangxiaValue;
						}
						/**
						* 设置： 是否上架的值
						*/
						public void setShangxiaValue(String shangxiaValue) {
							this.shangxiaValue = shangxiaValue;
						}
					/**
					* 获取： 逻辑删除
					*/
					public Integer getShangpinDelete() {
						return shangpinDelete;
					}
					/**
					* 设置： 逻辑删除
					*/
					public void setShangpinDelete(Integer shangpinDelete) {
						this.shangpinDelete = shangpinDelete;
					}
					/**
					* 获取： 商品简介
					*/
					public String getShangpinContent() {
						return shangpinContent;
					}
					/**
					* 设置： 商品简介
					*/
					public void setShangpinContent(String shangpinContent) {
						this.shangpinContent = shangpinContent;
					}












}
