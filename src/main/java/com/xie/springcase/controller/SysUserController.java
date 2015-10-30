package com.xie.springcase.controller;

import com.xie.springcase.hibernate.entity.SysUser;
import com.xie.springcase.service.ISysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by xieqinchao on 15-9-7.
 */
@Controller
public class SysUserController {
    @Resource(name="sysUserService")
    private ISysUserService sysUserService;

    @RequestMapping(value="/login",method=RequestMethod.GET)
    public String loginForm(Model model) {
        model.addAttribute("user", new SysUser());
        return "/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, @Valid SysUser user, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        try {
            if(bindingResult.hasErrors()) {
                return "/login";
            }
            UsernamePasswordToken token = new UsernamePasswordToken(user.getName(),user.getPassword());
                String accountId = token.getPrincipal().toString();
                String password = token.getCredentials().toString();
            SecurityUtils.getSubject().login(token);
                SecurityUtils.getSubject().getSession().setAttribute("password",password);
                accountId = SecurityUtils.getSubject().getPrincipal().toString();
                password = SecurityUtils.getSubject().getSession().getAttribute("password").toString();
//                SecurityUtils.getSubject().isPermitted("");
            return "redirect:/user";
        } catch (AuthenticationException e) {
            redirectAttributes.addFlashAttribute("message","用户名或密码错误！");
            return "redirect:/login";
        }
    }

    @RequestMapping(value="/logout",method=RequestMethod.GET)
    public String logout(RedirectAttributes redirectAttributes ) {
        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息
        SecurityUtils.getSubject().logout();
        redirectAttributes.addFlashAttribute("message", "您已安全退出");
        return "redirect:/login";
    }

    @RequestMapping(value = "/user")
    public String userManage(HttpServletRequest request, HttpServletResponse response, Model model) {
        List<SysUser> userList = sysUserService.findALL();
        request.setAttribute("userList", userList);
        return "/user";
    }
}
