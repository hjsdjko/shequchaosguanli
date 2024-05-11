












package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 超市管理员
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/chaoshi")
public class ChaoshiController {
    private static final Logger logger = LoggerFactory.getLogger(ChaoshiController.class);

    @Autowired
    private ChaoshiService chaoshiService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service

    @Autowired
    private YonghuService yonghuService;


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isEmpty(role))
            return R.error(511,"权限为空");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        else if("超市管理员".equals(role))
            params.put("chaoshiId",request.getSession().getAttribute("userId"));
        params.put("chaoshiDeleteStart",1);params.put("chaoshiDeleteEnd",1);
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = chaoshiService.queryPage(params);

        //字典表数据转换
        List<ChaoshiView> list =(List<ChaoshiView>)page.getList();
        for(ChaoshiView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ChaoshiEntity chaoshi = chaoshiService.selectById(id);
        if(chaoshi !=null){
            //entity转view
            ChaoshiView view = new ChaoshiView();
            BeanUtils.copyProperties( chaoshi , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody ChaoshiEntity chaoshi, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,chaoshi:{}",this.getClass().getName(),chaoshi.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isEmpty(role))
            return R.error(511,"权限为空");

        Wrapper<ChaoshiEntity> queryWrapper = new EntityWrapper<ChaoshiEntity>()
            .eq("username", chaoshi.getUsername())
            .or()
            .eq("chaoshi_phone", chaoshi.getChaoshiPhone())
            .or()
            .eq("chaoshi_id_number", chaoshi.getChaoshiIdNumber())
            .andNew()
            .eq("chaoshi_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ChaoshiEntity chaoshiEntity = chaoshiService.selectOne(queryWrapper);
        if(chaoshiEntity==null){
            chaoshi.setChaoshiDelete(1);
            chaoshi.setCreateTime(new Date());
            chaoshi.setPassword("123456");
            chaoshiService.insert(chaoshi);
            return R.ok();
        }else {
            return R.error(511,"账户或者联系方式或者身份证号已经被使用");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ChaoshiEntity chaoshi, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,chaoshi:{}",this.getClass().getName(),chaoshi.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(StringUtil.isEmpty(role))
//            return R.error(511,"权限为空");
        //根据字段查询是否有相同数据
        Wrapper<ChaoshiEntity> queryWrapper = new EntityWrapper<ChaoshiEntity>()
            .notIn("id",chaoshi.getId())
            .andNew()
            .eq("username", chaoshi.getUsername())
            .or()
            .eq("chaoshi_phone", chaoshi.getChaoshiPhone())
            .or()
            .eq("chaoshi_id_number", chaoshi.getChaoshiIdNumber())
            .andNew()
            .eq("chaoshi_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ChaoshiEntity chaoshiEntity = chaoshiService.selectOne(queryWrapper);
        if(chaoshiEntity==null){
            //  String role = String.valueOf(request.getSession().getAttribute("role"));
            //  if("".equals(role)){
            //      chaoshi.set
            //  }
            chaoshiService.updateById(chaoshi);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"账户或者联系方式或者身份证号已经被使用");
        }
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        ArrayList<ChaoshiEntity> list = new ArrayList<>();
        for(Integer id:ids){
            ChaoshiEntity chaoshiEntity = new ChaoshiEntity();
            chaoshiEntity.setId(id);
            chaoshiEntity.setChaoshiDelete(2);
            list.add(chaoshiEntity);
        }
        if(list != null && list.size() >0){
            chaoshiService.updateBatchById(list);
        }
        return R.ok();
    }

    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<ChaoshiEntity> chaoshiList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            ChaoshiEntity chaoshiEntity = new ChaoshiEntity();
//                            chaoshiEntity.setUsername(data.get(0));                    //账户 要改的
//                            //chaoshiEntity.setPassword("123456");//密码
//                            chaoshiEntity.setChaoshiName(data.get(0));                    //超市管理姓名 要改的
//                            chaoshiEntity.setChaoshiPhone(data.get(0));                    //联系方式 要改的
//                            chaoshiEntity.setChaoshiIdNumber(data.get(0));                    //身份证号 要改的
//                            chaoshiEntity.setSexTypes(Integer.valueOf(data.get(0)));   //性别 要改的
//                            chaoshiEntity.setChaoshiDelete(1);//逻辑删除字段
//                            chaoshiEntity.setCreateTime(date);//时间
                            chaoshiList.add(chaoshiEntity);


                            //把要查询是否重复的字段放入map中
                                //账户
                                if(seachFields.containsKey("username")){
                                    List<String> username = seachFields.get("username");
                                    username.add(data.get(0));//要改的
                                }else{
                                    List<String> username = new ArrayList<>();
                                    username.add(data.get(0));//要改的
                                    seachFields.put("username",username);
                                }
                                //联系方式
                                if(seachFields.containsKey("chaoshiPhone")){
                                    List<String> chaoshiPhone = seachFields.get("chaoshiPhone");
                                    chaoshiPhone.add(data.get(0));//要改的
                                }else{
                                    List<String> chaoshiPhone = new ArrayList<>();
                                    chaoshiPhone.add(data.get(0));//要改的
                                    seachFields.put("chaoshiPhone",chaoshiPhone);
                                }
                                //身份证号
                                if(seachFields.containsKey("chaoshiIdNumber")){
                                    List<String> chaoshiIdNumber = seachFields.get("chaoshiIdNumber");
                                    chaoshiIdNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> chaoshiIdNumber = new ArrayList<>();
                                    chaoshiIdNumber.add(data.get(0));//要改的
                                    seachFields.put("chaoshiIdNumber",chaoshiIdNumber);
                                }
                        }

                        //查询是否重复
                         //账户
                        List<ChaoshiEntity> chaoshiEntities_username = chaoshiService.selectList(new EntityWrapper<ChaoshiEntity>().in("username", seachFields.get("username")).eq("chaoshi_delete", 1));
                        if(chaoshiEntities_username.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(ChaoshiEntity s:chaoshiEntities_username){
                                repeatFields.add(s.getUsername());
                            }
                            return R.error(511,"数据库的该表中的 [账户] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //联系方式
                        List<ChaoshiEntity> chaoshiEntities_chaoshiPhone = chaoshiService.selectList(new EntityWrapper<ChaoshiEntity>().in("chaoshi_phone", seachFields.get("chaoshiPhone")).eq("chaoshi_delete", 1));
                        if(chaoshiEntities_chaoshiPhone.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(ChaoshiEntity s:chaoshiEntities_chaoshiPhone){
                                repeatFields.add(s.getChaoshiPhone());
                            }
                            return R.error(511,"数据库的该表中的 [联系方式] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //身份证号
                        List<ChaoshiEntity> chaoshiEntities_chaoshiIdNumber = chaoshiService.selectList(new EntityWrapper<ChaoshiEntity>().in("chaoshi_id_number", seachFields.get("chaoshiIdNumber")).eq("chaoshi_delete", 1));
                        if(chaoshiEntities_chaoshiIdNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(ChaoshiEntity s:chaoshiEntities_chaoshiIdNumber){
                                repeatFields.add(s.getChaoshiIdNumber());
                            }
                            return R.error(511,"数据库的该表中的 [身份证号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        chaoshiService.insertBatch(chaoshiList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }


    /**
    * 登录
    */
    @IgnoreAuth
    @RequestMapping(value = "/login")
    public R login(String username, String password, String captcha, HttpServletRequest request) {
        ChaoshiEntity chaoshi = chaoshiService.selectOne(new EntityWrapper<ChaoshiEntity>().eq("username", username));
        if(chaoshi==null || !chaoshi.getPassword().equals(password))
            return R.error("账号或密码不正确");
        else if(chaoshi.getChaoshiDelete() != 1)
            return R.error("账户已被删除");
        //  // 获取监听器中的字典表
        // ServletContext servletContext = ContextLoader.getCurrentWebApplicationContext().getServletContext();
        // Map<String, Map<Integer, String>> dictionaryMap= (Map<String, Map<Integer, String>>) servletContext.getAttribute("dictionaryMap");
        // Map<Integer, String> role_types = dictionaryMap.get("role_types");
        // role_types.get(.getRoleTypes());
        String token = tokenService.generateToken(chaoshi.getId(),username, "chaoshi", "超市管理员");
        R r = R.ok();
        r.put("token", token);
        r.put("role","超市管理员");
        r.put("username",chaoshi.getChaoshiName());
        r.put("tableName","chaoshi");
        r.put("userId",chaoshi.getId());
        return r;
    }

    /**
    * 注册
    */
    @IgnoreAuth
    @PostMapping(value = "/register")
    public R register(@RequestBody ChaoshiEntity chaoshi){
//    	ValidatorUtils.validateEntity(user);
        Wrapper<ChaoshiEntity> queryWrapper = new EntityWrapper<ChaoshiEntity>()
            .eq("username", chaoshi.getUsername())
            .or()
            .eq("chaoshi_phone", chaoshi.getChaoshiPhone())
            .or()
            .eq("chaoshi_id_number", chaoshi.getChaoshiIdNumber())
            .andNew()
            .eq("chaoshi_delete", 1)
            ;
        ChaoshiEntity chaoshiEntity = chaoshiService.selectOne(queryWrapper);
        if(chaoshiEntity != null)
            return R.error("账户或者联系方式或者身份证号已经被使用");
        chaoshi.setChaoshiDelete(1);
        chaoshi.setCreateTime(new Date());
        chaoshiService.insert(chaoshi);
        return R.ok();
    }

    /**
     * 重置密码
     */
    @GetMapping(value = "/resetPassword")
    public R resetPassword(Integer  id){
        ChaoshiEntity chaoshi = new ChaoshiEntity();
        chaoshi.setPassword("123456");
        chaoshi.setId(id);
        chaoshiService.updateById(chaoshi);
        return R.ok();
    }


    /**
     * 忘记密码
     */
    @IgnoreAuth
    @RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request) {
        ChaoshiEntity chaoshi = chaoshiService.selectOne(new EntityWrapper<ChaoshiEntity>().eq("username", username));
        if(chaoshi!=null){
            chaoshi.setPassword("123456");
            boolean b = chaoshiService.updateById(chaoshi);
            if(!b){
               return R.error();
            }
        }else{
           return R.error("账号不存在");
        }
        return R.ok();
    }


    /**
    * 获取用户的session用户信息
    */
    @RequestMapping("/session")
    public R getCurrChaoshi(HttpServletRequest request){
        Integer id = (Integer)request.getSession().getAttribute("userId");
        ChaoshiEntity chaoshi = chaoshiService.selectById(id);
        if(chaoshi !=null){
            //entity转view
            ChaoshiView view = new ChaoshiView();
            BeanUtils.copyProperties( chaoshi , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }
    }


    /**
    * 退出
    */
    @GetMapping(value = "logout")
    public R logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return R.ok("退出成功");
    }




    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        // 没有指定排序字段就默认id倒序
        if(StringUtil.isEmpty(String.valueOf(params.get("orderBy")))){
            params.put("orderBy","id");
        }
        PageUtils page = chaoshiService.queryPage(params);

        //字典表数据转换
        List<ChaoshiView> list =(List<ChaoshiView>)page.getList();
        for(ChaoshiView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ChaoshiEntity chaoshi = chaoshiService.selectById(id);
            if(chaoshi !=null){


                //entity转view
                ChaoshiView view = new ChaoshiView();
                BeanUtils.copyProperties( chaoshi , view );//把实体数据重构到view中

                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody ChaoshiEntity chaoshi, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,chaoshi:{}",this.getClass().getName(),chaoshi.toString());
        Wrapper<ChaoshiEntity> queryWrapper = new EntityWrapper<ChaoshiEntity>()
            .eq("username", chaoshi.getUsername())
            .or()
            .eq("chaoshi_phone", chaoshi.getChaoshiPhone())
            .or()
            .eq("chaoshi_id_number", chaoshi.getChaoshiIdNumber())
            .andNew()
            .eq("chaoshi_delete", 1)
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ChaoshiEntity chaoshiEntity = chaoshiService.selectOne(queryWrapper);
        if(chaoshiEntity==null){
            chaoshi.setChaoshiDelete(1);
            chaoshi.setCreateTime(new Date());
        chaoshi.setPassword("123456");
        //  String role = String.valueOf(request.getSession().getAttribute("role"));
        //  if("".equals(role)){
        //      chaoshi.set
        //  }
        chaoshiService.insert(chaoshi);
            return R.ok();
        }else {
            return R.error(511,"账户或者联系方式或者身份证号已经被使用");
        }
    }


}
