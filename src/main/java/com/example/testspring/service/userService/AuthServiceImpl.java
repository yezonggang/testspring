package com.example.testspring.service.userService;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.testspring.mapper.userMapper.AuthMapper;
import com.example.testspring.model.userModel.Auth;
import com.example.testspring.service.AuthService;
import com.example.testspring.utils.SecurityUtil;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AuthServiceImpl extends ServiceImpl <AuthMapper, Auth> implements AuthService {
    @Override
    public Auth getByLoginName(String loginName) {
        LambdaQueryWrapper<Auth> queryWrapper = Wrappers.<Auth>lambdaQuery()
                .eq(Auth::getLoginName, loginName);
        List<Auth> ltSysUserAuthList = this.baseMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(ltSysUserAuthList)) {
            return null;
        }
        else if (ltSysUserAuthList.size() > 1) {
            log.error("用户账号下包含多个用户信息，请检查数据！");
        }
        return ltSysUserAuthList.get(0);
    }

    @Override
    public boolean updatePwdByUserId(String userId, String newPwd) {

        String entryptPassword = SecurityUtil.entryptPassword(newPwd);
        LambdaUpdateWrapper<Auth> wrapper = Wrappers.<Auth>lambdaUpdate()
                .eq(Auth::getUserId, userId).set(Auth::getPasswd, entryptPassword);
        return this.baseMapper.update(null, wrapper) > 0;
    }

}
