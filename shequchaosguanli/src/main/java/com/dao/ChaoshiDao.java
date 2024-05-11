package com.dao;

import com.entity.ChaoshiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.ChaoshiView;

/**
 * 超市管理员 Dao 接口
 *
 * @author 
 */
public interface ChaoshiDao extends BaseMapper<ChaoshiEntity> {

   List<ChaoshiView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
