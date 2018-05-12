package com.lzw.lemoniot.controllor;

import com.lzw.lemoniot.common.utils.R;
import com.lzw.lemoniot.dao.RoomRepository;
import com.lzw.lemoniot.dao.UserRepository;
import com.lzw.lemoniot.e.LoginType;
import com.lzw.lemoniot.exception.LemonException;
import com.lzw.lemoniot.modal.Device;
import com.lzw.lemoniot.modal.LoginModal;
import com.lzw.lemoniot.modal.Room;
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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

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
                    user.setHeadImgUrl(wxMpUser.getHeadImgUrl());
                    Room room = new Room();
                    room.setName("默认房间");
                    room.setType("默认房间类型");
                    room.setRemarks("这是默认房间的备注");
                    //入库
                    if (!userRepository.existsByOpenId(wxMpUser.getOpenId())) {
                        user = userService.saveUser(user);
                    }else {
                        user = userService.findUserByOpenId(wxMpOAuth2AccessToken.getOpenId());
                        user.setHeadImgUrl(wxMpUser.getHeadImgUrl());
                    }
                    if (!roomRepository.existsRoomByUser(user)){
                        room.setUser(user);
                        roomRepository.saveAndFlush(room);
                    }
                    return R.ok().put("user", user);
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
        List<Device> devices = deviceService.getDevices(Long.parseLong(userId));
        return R.ok().put("devices", devices);
    }

    /**
     * 获取所有设备
     * @param userId 用户id
     * @return
     */
    @GetMapping(value = "/{userId}/rooms")
    public R rooms(@PathVariable String userId){
        List<Room> rooms = deviceService.getRooms(Long.parseLong(userId));
        return R.ok().put("rooms", rooms);
    }

    /**
     * 添加房间
     * @param userId 用户id
     * @param room 房间
     * @return
     */
    @PutMapping(value = "/{userId}/addRoom")
    public R addRoom(@PathVariable String userId,
                     @RequestBody Room room){

        return null;
    }



}
