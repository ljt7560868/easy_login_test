package com.credithc.easylogin.security;

import com.credithc.easylogin.DTO.LoginUser;
import com.credithc.easylogin.util.ThreadLocalUtil;
import com.credithc.infra.platform.ps.security.cache.IThreadLocalUserInfoService;
import com.credithc.infra.platform.ps.security.dto.CustomUserDetails;
import com.credithc.infra.platform.sdk.dto.CurrentUserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: lvjietao
 * @CreateTime: 2022-11-01 09:06
 * @Description: 登陆用户本地线程相关操作实现类
 */
@Component
@Slf4j
public class ThreadLocalUserInfoServiceImpl implements IThreadLocalUserInfoService {

    @Override
    public void putThreadLocalUserInfo(CustomUserDetails customUserDetails) {
        if (customUserDetails == null) {
            log.error("登陆用户放入本地线程操作异常，customUserDetails为空");
            return;
        }
        ThreadLocalUtil.putUser(prepareLoginUser(customUserDetails));
    }

    private LoginUser prepareLoginUser(CustomUserDetails customUserDetails) {
        LoginUser loginUser = new LoginUser();
        loginUser.setId(customUserDetails.getUserId());
        loginUser.setLoginName(customUserDetails.getUserName());
        CurrentUserDTO currentUserInfo = customUserDetails.getCurrentUserInfo();
        loginUser.setCode(currentUserInfo.getUserCode());
        loginUser.setName(currentUserInfo.getRealName());
        return loginUser;
    }

    @Override
    public CustomUserDetails getThreadLocalUserInfo() {
        LoginUser user = ThreadLocalUtil.getUser();
        if (user == null) {
            log.error("本地线程获取登陆用户信息操作异常，user为空");
            return null;
        }
        return prepareCustomUserDetails(user);
    }

    private CustomUserDetails prepareCustomUserDetails(LoginUser user) {
        CurrentUserDTO currentUserDTO = new CurrentUserDTO();
        currentUserDTO.setUserId(user.getId());
        currentUserDTO.setUserName(user.getLoginName());
        currentUserDTO.setRealName(user.getName());
        currentUserDTO.setUserCode(user.getCode());
        CustomUserDetails userDetails = new CustomUserDetails();
        userDetails.setCurrentUserInfo(currentUserDTO);
        return userDetails;
    }
}
