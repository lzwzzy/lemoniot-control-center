package com.lzw.lemoniot.controllor;

import com.lzw.lemoniot.common.utils.R;
import org.springframework.web.bind.annotation.*;

/**
 * RoomController
 *
 * @author lzw
 * @date 2018/5/12 15:28
 **/
@RestController
@RequestMapping(value = "/rooms")
public class RoomController {


    @GetMapping(value = "/{roomId}/devices")
    public R getDevices(@PathVariable String roomId){
        return null;
    }


}
