package com.dong.dubbo.quickStart;

import com.dong.dubbo.ServiceAPI;

/**
 * @ClassName QuickStartServiceImpl
 * @Description 接口实现类
 * @Author LXD
 * @Date 2018/12/24 14:13
 **/
public class QuickStartServiceImpl implements ServiceAPI{
    @Override
    public String sendMsg(String message) {
        return "provider-send-message="+message;
    }
}
