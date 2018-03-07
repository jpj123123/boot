package jpj.boot.controller;

import jpj.boot.dto.AddRoleEnumSubmitDto;
import jpj.boot.dto.EnumDto;
import jpj.boot.dto.RoleSubmitDto;
import jpj.boot.entity.Role;
import jpj.boot.entity.TEnum;
import jpj.boot.exception.BuisnessException;
import jpj.boot.service.RoleService;
import jpj.boot.service.TEnumService;
import jpj.boot.util.BeanValidator;
import jpj.boot.validator.Validator;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

/**
 * @Author: jingpj
 * @Date：creste in 2018/3/5
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    @Resource
    private RoleService roleService;

    @Resource
    private TEnumService tEnumService;

    /**
     * 跳转角色列表页面
     * @param request
     * @return
     */
    @RequestMapping("/list")
    public ModelAndView list(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/role/list");
        TEnum tEnum = tEnumService.selectByCode("roleroot");
        if (tEnum != null && tEnum.getType() == 2) {
            mav.addObject("btns", tEnumService.listByPid(tEnum.getId()));
        }
        List<Role> roleList = roleService.listAll();
        mav.addObject("roles", roleList);
        return mav;
    }

    /**
     * 获取角色列表
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/listAll")
    public List<Role> listAll(HttpServletRequest request) {
        List<Role> roleList = roleService.listAll();
        return roleList;
    }

    /**
     * 跳转添加角色页面
     * @param request
     * @param pid
     * @return
     */
    @RequestMapping("/add")
    public ModelAndView add(HttpServletRequest request,Integer pid) {
        ModelAndView mav = new ModelAndView("/role/add");
        if(pid == null || pid ==0){
            throw new BuisnessException("无选中节点！");
        }
        Role role = roleService.selectByPrimaryKey(pid);
        if(role == null){
            throw new BuisnessException("节点错误！");
        }
        mav.addObject("pid", pid);
        mav.addObject("roleName", role.getName());
        return mav;
    }

    /**
     * 添加角色提交
     * @param request
     * @param dto
     * @return
     */
    @ResponseBody
    @RequestMapping("/addRoleSubmit")
    public boolean addRoleSubmit(HttpServletRequest request, RoleSubmitDto dto) {
        BeanValidator.validator(dto);
        if (dto.getPid() == 0)
            throw new BuisnessException("无上级id");
        Role role = new Role();
        role.setPid(dto.getPid());
        role.setName(dto.getName());
        return roleService.insert(role) > 0;
    }

    /**
     * 删除角色
     * @param request
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/delete")
    public boolean delete(HttpServletRequest request, Integer id) {
        if (id == null || id == 0) {
            throw new BuisnessException("根节点无法删除！");
        }
        return roleService.deleteByPrimaryKey(id) > 0;
    }
    /**
     * 跳转添加角色页面
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/addRoleEnum")
    public ModelAndView addRoleEnum(HttpServletRequest request,Integer id) {
        ModelAndView mav = new ModelAndView("/role/addRoleEnum");
        if(id == null || id == 0){
            throw new BuisnessException("无选中节点！");
        }
        Role role = roleService.selectByPrimaryKey(id);
        if(role == null){
            throw new BuisnessException("节点错误！");
        }
        mav.addObject("pid", role.getPid());
        mav.addObject("id", id);
        return mav;
    }

    /**
     * 添加关联
     * @param request
     * @param roleId
     * @param enumIds
     * @return
     */
    @ResponseBody
    @RequestMapping("/addRoleEnumSubmit")
    public boolean addRoleEnumSubmit(HttpServletRequest request, @RequestParam("roleId") Integer roleId, @RequestParam("enumIds") Integer[] enumIds) {
        return roleService.addRoleEnumSubmit(roleId,enumIds);
    }
}
