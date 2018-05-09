package com.lzw.lemoniot.controllor;

import com.lzw.lemoniot.common.utils.R;
import com.lzw.lemoniot.e.LoginType;
import com.lzw.lemoniot.exception.LemonException;
import com.lzw.lemoniot.modal.Device;
import com.lzw.lemoniot.modal.LoginModal;
import com.lzw.lemoniot.modal.User;
import com.lzw.lemoniot.service.AsyncTaskService;
import com.lzw.lemoniot.service.DeviceService;
import com.lzw.lemoniot.service.UserService;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lzw
 * @date 2018/4/4 16:05
 **/
@RestController
@CrossOrigin
@RequestMapping(value = "/user")
public class UserControllor {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private UserService userService;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private AsyncTaskService asyncTaskService;

    /**
     * 登陆操作
     *
     * @param loginModal type 登陆类型
     *                   visit 授权之前用户要访问的地址
     *                   account 账户授权码(code)
     * @return 用户信息
     */
    @PostMapping(value = "/login")
    public R login(@RequestBody LoginModal loginModal) {
        switch (loginModal.getType()) {
            case LoginType.WECHAT:
                try {
                    //获取微信access_token和用户openid(需要用户手动同意)
                    WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(loginModal.getAccount());
                    //根据上一步信息获取用户资料
                    WxMpUser wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, "zh_CN");
                    User user = new User();
                    user.setName(wxMpUser.getNickname());
                    user.setOpenId(wxMpUser.getOpenId());
                    //异步入库
                    asyncTaskService.asyncSaveUser(user);
                    return R.ok().put("user", wxMpUser);
                } catch (WxErrorException e) {
                    throw new LemonException(e, e.getError().getErrorMsg(), String.valueOf(e.getError().getErrorCode()));
                }
            case LoginType.NORMAL:
                break;
            default:
                break;
        }
        return null;
    }

    /**
     * 获取所有设备
     * @param userId 用户id
     * @return
     */
    @GetMapping(value = "/{userId}/devices")
    public R devices(@PathVariable String userId){
        List<Device> devices = deviceService.getDevices(userId);
        return R.ok().put("devices", devices);
    }


}
