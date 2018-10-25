package com.uooguo.newretail.cloud.login.service.service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author wangqiang
 * @since 2018-09-11
 */
public interface LoginService {

    Map<String, Object> login(HttpServletRequest request, String userName, String passWord);

}
