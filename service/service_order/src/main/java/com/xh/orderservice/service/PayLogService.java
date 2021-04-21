package com.xh.orderservice.service;

import com.xh.orderservice.entity.PayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author Yaher
 * @since 2021-04-18
 */
public interface PayLogService extends IService<PayLog> {

    Map<String, String> createNative(String orderNo);

    Map<String, String> queryPayStatus(String orderNo);

    void updateOrderStatus(Map<String, String> map);
}
