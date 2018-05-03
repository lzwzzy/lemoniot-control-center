package com.lzw.lemoniot.service;

import com.lzw.lemoniot.modal.User;

/**
 * 异步任务类
 *
 * @author lzw
 * @date 2018/4/9 22:45
 **/
public interface AsyncTaskService {
    void asyncSaveUser(User user);
}
