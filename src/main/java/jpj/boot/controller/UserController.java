package jpj.boot.controller;

import jpj.boot.dto.SubmitEnumDto;
import jpj.boot.dto.SubmitUserDto;
import jpj.boot.dto.UserLoginDto;
import jpj.boot.dto.UserResetPasswordDto;
import jpj.boot.entity.Role;
import jpj.boot.entity.TEnum;
import jpj.boot.entity.User;
import jpj.boot.exception.BuisnessException;
import jpj.boot.service.RoleService;
import jpj.boot.service.TEnumService;
import jpj.boot.service.TestLintenerService;
import jpj.boot.service.UserService;
import jpj.boot.util.BeanValidator;
import jpj.boot.util.HttpSessionUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @Author: jingpj
 * @Date：creste in 2018/2/8
 */
//@ComponentScan
@RequestMapping("/user")
@Controller
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Resource
    private UserService userService;
    @Resource
    private TEnumService tEnumService;
    @Resource
    private RoleService roleService;
    @Resource
    private TestLintenerService testLintenerService;

    /**
     * 登录
     *
     * @param request
     * @param dto
     * @return
     */
    @RequestMapping(value = "/login")
    public ModelAndView login(HttpServletRequest request, UserLoginDto dto) {
        ModelAndView mav = new ModelAndView();
        //验证数据
        BeanValidator.validator(dto);
        mav.addObject("userName", dto.getUserName());
        User user = userService.selectByUserName(dto.getUserName());
        //mav.addObject("user", user);
        if (!StringUtils.equals(user.getPassword(), dto.getPassword())) {
            mav.addObject("password", dto.getPassword());
            mav.addObject("errorMsg", "提示错误");
            mav.setViewName("login");
            return mav;
        }
        mav.setViewName("redirect:/index");
        request.getSession().setAttribute(dto.getUserName(), user.getId());
        request.getSession().setAttribute(request.getSession().getId(), dto.getUserName());
        return mav;
    }

    /**
     * 重置密码
     *
     * @param request
     * @param dto
     * @return
     */
    @RequestMapping(value = "/resetSubmit")
    public ModelAndView resetPassword(HttpServletRequest request, UserResetPasswordDto dto) {
        ModelAndView mav = new ModelAndView();
        //验证数据
        if (!BeanValidator.validator(dto, mav)) {
            mav.setViewName("reset");
            return mav;
        }
        mav.addObject("userName", dto.getUserName());
        User user = userService.selectByUserName(dto.getUserName());
        //mav.addObject("user", user);
        if (user == null) {
            mav.addObject("errorMsg", "用户不存在");
            mav.setViewName("redirect:/reset");
            return mav;
        }
        if (!StringUtils.equals(user.getPassword(), dto.getOldPassword())) {
            //mav.addObject("oldPassword", dto.getOldPassword());
            mav.addObject("errorMsg", "原密码错误");
            mav.setViewName("redirect:/reset");
            return mav;
        }
        User updateUser = new User();
        updateUser.setId(user.getId());
        updateUser.setPassword(dto.getNewPassword());
        userService.updateByPrimaryKeySelective(updateUser);
        //清除登录信息
        HttpSessionUtil.clearLoginSession(request.getSession());
        mav.setViewName("redirect:/login");
        return mav;
    }

    @RequestMapping("/list")
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView("user/list");
        TEnum tEnum = tEnumService.selectByCode("userroot");
        if (tEnum != null && tEnum.getType() == 2) {
            mav.addObject("btns", tEnumService.listByPid(tEnum.getId()));
        }
        return mav;
    }

    /**
     * 获取用户列表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/listUser")
    public List<User> listUser() {
        //logger.info("测试log4j2");
        //testLintenerService.doSome("jingpj");
        return userService.listUser();
    }

    @RequestMapping("/add")
    public ModelAndView add(Integer pid) {
        ModelAndView mav = new ModelAndView("user/add");
        User user = userService.selectByPrimaryKey(pid);
        if (user == null) {
            throw new BuisnessException("上级用户不存在");
        }
        mav.addObject("pid", pid);
        mav.addObject("pname", user.getName());
        return mav;
    }

    @ResponseBody
    @RequestMapping("/addUserSubmit")
    public boolean addUserSubmit(HttpServletRequest request, SubmitUserDto dto) {
        logger.info("userId:" + HttpSessionUtil.getUserId(request.getSession()) + "新增用户：" + dto);
        BeanValidator.validator(dto);
        User selUser = userService.selectByUserName(dto.getName());
        if (selUser != null) {
            throw new BuisnessException("用户已存在");
        }
        User insertUser = new User();
        insertUser.setName(dto.getName());
        insertUser.setPassword(dto.getPassword());
        insertUser.setPid(dto.getPid());
        insertUser.setCreateTime(new Date());
        insertUser.setUpdateTime(new Date());
        return userService.insertSelective(insertUser) > 0;
    }

    @RequestMapping("/edit")
    public ModelAndView edit(HttpServletRequest request, Integer id) {
        ModelAndView mav = new ModelAndView("user/edit");
        User user = userService.selectByPrimaryKey(id);
        mav.addObject("user", user);
        return mav;
    }

    @ResponseBody
    @RequestMapping("/editUserSubmit")
    public boolean editUserSubmit(HttpServletRequest request, SubmitUserDto dto) {
        if (dto.getId() == null)
            throw new BuisnessException("用户不存在无法修改！！！");
        User user = userService.selectByPrimaryKey(dto.getId());
        if (user == null) {
            throw new BuisnessException("用户不存在无法修改！！");
        }
        logger.info("userId:" + HttpSessionUtil.getUserId(request.getSession()) + "修改用户原user：" + user + "修改数据为：" + dto);

        BeanValidator.validator(dto);
        User upUser = new User();
        upUser.setName(dto.getName());
        upUser.setPassword(dto.getPassword());
        upUser.setPid(dto.getPid());
        upUser.setId(dto.getId());
        return userService.updateByPrimaryKeySelective(upUser) > 0;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public boolean delete(HttpServletRequest request, Integer id) {
        logger.info("userId:" + HttpSessionUtil.getUserId(request.getSession()) + "新增用户id：" + id);
        return userService.deleteUser(request, id);
    }

    @RequestMapping("/addUserRole")
    public ModelAndView addUserRole(HttpServletRequest request, Integer id) {
        ModelAndView mav = new ModelAndView("user/addUserRole");
        User user = userService.selectByPrimaryKey(id);
        Role role = roleService.selectRoleByUserId(id);
        if(user == null)
            throw new BuisnessException("用户不存在！");
        if(role != null)
            mav.addObject("rolename", role.getName());

        mav.addObject("user", user);
        return mav;
    }

    @ResponseBody
    @RequestMapping("/addUserRoleSubmit")
    public boolean addUserRoleSubmit(HttpServletRequest request, @RequestParam("userId") Integer userId, @RequestParam("roleId") Integer roleId) {
        User user = userService.selectByPrimaryKey(userId);
        if (user == null)
            throw new BuisnessException("用户不存在！");
        Role role = roleService.selectByPrimaryKey(roleId);
        if (role == null)
            throw new BuisnessException("角色不存在！");
        userService.insertUserRole(userId, roleId);
        return true;
    }
}
