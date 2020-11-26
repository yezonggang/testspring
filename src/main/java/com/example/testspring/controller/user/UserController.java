package com.example.testspring.controller.user;
import com.example.testspring.model.userModel.*;
import com.example.testspring.req.LightErrorCode;
import com.example.testspring.req.LightUapException;
import com.example.testspring.req.LoginReq;
import com.example.testspring.req.R;
import com.example.testspring.service.userService.AuthServiceImpl;
import com.example.testspring.service.userService.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.testspring.utils.SecurityUtil;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;
import com.example.testspring.utils.BeanConverter;
import com.example.testspring.utils.LightTokenUtil;
import com.example.testspring.model.userModel.LightRoleEntity;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@RestController
@RequestMapping("/user")
@Api("统一认证管理")
@Slf4j

public class UserController {
    @Autowired
    AuthServiceImpl authService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    private HttpSession session;

    @PostMapping("/login")
    @ApiImplicitParam(name = "req", value = "用户登陆信息", dataType = "LoginReq")
    public R login(@RequestBody @NotNull LoginReq req) throws Exception {
        String loginName = req.getLoginName();
        String passwd = req.getPasswd();
        Auth info = authService.getByLoginName(loginName);

        // 验证用户状态
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery().eq(User::getUserId, info.getUserId()).eq(User::getStatus, 0);
        User user = userService.getOne(wrapper);
        Assert.notNull(user, "用户已禁用, 请联系管理员");

        if (SecurityUtil.validatePassword(passwd, info.getPasswd())) {
            // 封装 Token
            String jwtToken = generateByUserInfo(info);
            return R.ok(jwtToken);
        }
        else {
            throw new LightUapException(LightErrorCode.FORBIDDEN);
        }
    }

    private String generateByUserInfo(Auth auth) throws Exception {
        String userId = auth.getUserId();
        User user = userService.getUserAndRolesById(userId);
        if (user == null) {
            throw new LightUapException("用户信息不存在，请联系管理员");
        }
        // 查询用户账户信息
        LightUserEntity userEntity = BeanConverter.convert(user, LightUserEntity.class);
        userEntity.setLoginName(auth.getLoginName());
        /**
         * 返回前台时，只返回当前用户的角色
         */
        List<Role> roles = user.getRoles();
        if (!CollectionUtils.isEmpty(roles) && roles.size() >= 1) {
            List<LightRoleEntity> collect = roles.stream().map(r -> r.setUsers(null)).map(r -> LightRoleEntity.builder()
                    .roleId(r.getRoleId()).roleCode(r.getRoleCode()).roleName(r.getRoleName()).roleType(r.getRoleType())
                    .build()).collect(Collectors.toList());
            userEntity.setRoles(collect);
            // 默认一个个角色为主要角色
            // userEntity.setRole(collect.get(0));
        }
        // 生成 token
        // 去除用户信息中的头像防止token过大
        userEntity.setAvatar("");
        String jwtToken = LightTokenUtil.createJwtDefaultExp(userEntity);
        return jwtToken;
    }

    @ApiOperation("用户刷新认证信息")
    @PostMapping("/refreshToken")
    public R refreshToken() throws Exception {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        // 从session中获取,具体查看 cn.com.xcrj.light.uap.interceptor.TokenInterceptor
        LightUserEntity subject = (LightUserEntity) requestAttributes.getAttribute("USER_INFO", 1);
        if (subject != null) {
            Auth info = authService.getByLoginName(subject.getLoginName());
            // 验证用户状态
            LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery()
                    .eq(User::getUserId, info.getUserId()).eq(User::getStatus, 0);
            User ltSysUser = userService.getOne(wrapper);
            Assert.notNull(ltSysUser, "用户已禁用, 请联系管理员");
            // 封装 Token
            String jwtToken = generateByUserInfo(info);
            return R.ok(jwtToken);
        }
        else {
            throw new LightUapException(LightErrorCode.FORBIDDEN);
        }
    }

    @GetMapping("/info")
    @ApiOperation("获取当前登陆的用户信息")
    public R getUserInfo() {
        LightUserEntity lightUserEntity = (LightUserEntity) session.getAttribute("USER_INFO");
        if (lightUserEntity == null) {
            throw new LightUapException("用户没有登录，请先登录");
        }
        // 由于之前token没有携带头像信息，现在补上
        User ltSysUser = userService.getById(lightUserEntity.getUserId());
        lightUserEntity.setAvatar(ltSysUser.getAvatar());
        return R.ok(lightUserEntity);
    }

    //
    @PostMapping("/logout")
    public R login() {
        return R.ok("退出登录");
    }

}

