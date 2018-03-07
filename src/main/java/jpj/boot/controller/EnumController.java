package jpj.boot.controller;

import com.alibaba.fastjson.JSON;
import jpj.boot.dto.EnumDto;
import jpj.boot.dto.SubmitEnumDto;
import jpj.boot.entity.Role;
import jpj.boot.entity.TEnum;
import jpj.boot.entity.User;
import jpj.boot.exception.BuisnessException;
import jpj.boot.service.TEnumService;
import jpj.boot.service.UserService;
import jpj.boot.util.BeanValidator;
import jpj.boot.util.HttpSessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: jingpj
 * @Date：creste in 2018/3/2
 */
@Controller
@RequestMapping("/enum")
@Slf4j
public class EnumController {
    @Resource
    private TEnumService tEnumService;
    @Resource
    private UserService userService;

    @ResponseBody
    @RequestMapping("/listEnum")
    public List<EnumDto> listEnum(HttpServletRequest request) {
        Integer pid = 0;
        Integer id = HttpSessionUtil.getUserId(request.getSession());
        Role role = userService.selectRoleByUserId(id);
        if (role == null) {
            return null;
        }
        List<EnumDto> list = tEnumService.listChildByPidAndRoleId(pid, role.getId());
        return list;
    }
    @ResponseBody
    @RequestMapping("/listEnumDtoAll")
    public List<EnumDto> listEnumDtoAll(HttpServletRequest request,Integer roleId) {
        List<EnumDto> list = tEnumService.listEnumDtoAllByPid(0, roleId);
        return list;
    }
    /**
     * 菜单管理
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/listEnumAll")
    public List<TEnum> getEnum(HttpServletRequest request,Integer pid) {
        return tEnumService.listByPid(pid);
    }
    /**
     * 菜单管理
     * @param request
     * @return
     */
    //@ResponseBody
    @RequestMapping("/listEnumTreegrid")
    public ModelAndView getEnum(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("enum/list");
        TEnum tenum = tEnumService.selectByCode("listEnum");
        if(tenum != null && tenum.getType() == 2){
            mav.addObject("btns", tEnumService.listByPid(tenum.getId()));
        }
        mav.addObject("pid", 0);

        return mav;
    }

    @RequestMapping("/addEnum")
    public ModelAndView addEnum(HttpServletRequest request, Integer pid) {

        ModelAndView mav = new ModelAndView();

        mav.addObject("pid", pid);
        if(pid == 0){
            mav.setViewName("/enum/add");
            return mav;
        }
        TEnum tEnum = tEnumService.selectByPrimaryKey(pid);
        if (tEnum == null) {
            mav.setViewName("/error/error");
        } else {
            mav.addObject("pname", tEnum.getName());
            mav.setViewName("/enum/add");
        }
        return mav;
    }
    @RequestMapping("/editEnum")
    public ModelAndView editEnum(HttpServletRequest request, Integer id) {

        ModelAndView mav = new ModelAndView();

        TEnum tEnum = tEnumService.selectByPrimaryKey(id);
        if (tEnum == null) {
            mav.setViewName("/error/error");
        } else {
            mav.addObject("tenum", tEnum);
            mav.setViewName("/enum/edit");
        }
        return mav;
    }
    @ResponseBody
    @RequestMapping("/addEnumSubmit")
    public boolean addEnumSubmit(HttpServletRequest request, SubmitEnumDto dto) {
        BeanValidator.validator(dto);
        log.info("dto:"+dto);

        return tEnumService.addEnumSubmit(dto);
    }
    @ResponseBody
    @RequestMapping("/editEnumSubmit")
    public boolean editEnumSubmit(HttpServletRequest request, SubmitEnumDto dto) {
        BeanValidator.validator(dto);
        if(dto.getId()<=0)
            return false;
        log.info("userId:"+HttpSessionUtil.getUserId(request.getSession())+"\t修改菜单"+dto);
        TEnum tEnum = new TEnum();
        tEnum.setDesc(dto.getDesc());
        tEnum.setName(dto.getName());
        tEnum.setUrl(dto.getUrl());
        tEnum.setId(dto.getId());
        tEnum.setPid(dto.getPid());
        tEnum.setType(dto.getType());
        tEnum.setIcons(dto.getIcons());
        return tEnumService.updateByPrimaryKeySelective(tEnum)>0;
    }
    @ResponseBody
    @RequestMapping("/deleteEnum")
    public boolean deleteEnum(HttpServletRequest request, Integer id) {
        if(id==0)
            return false;
        TEnum tEnum = tEnumService.selectByPrimaryKey(id);
        if(tEnum != null)
            return tEnumService.deleteEnum(id,tEnum.getType());
        return false;
    }

}
