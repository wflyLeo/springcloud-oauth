package com.uooguo.newretail.cloud.uc.util;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils {
    /**
     * 取得客户端ip地址<br>
     * 如果用户使用了代理，则也只返回其真实IP
     *
     * @param request HttpServletRequest
     * @return
     */
    public static String getClientIp(HttpServletRequest request) {
        // 如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串ip值，客户端真实的ip是：第一个非unknown的有效IP字符串
        String ip = request.getHeader("X-Forwarded-For");// X-Forwarded-For: client1, proxy1, proxy2
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
//        X-Forwarded-For一般格式如下:其中的值通过一个 逗号+空格 把多个IP地址区分开, 最左边(client1)是最原始客户端的IP地址, 代理服务器每成功收到一个请求，就把请求来源IP地址添加到右边。
//        X-Forwarded-For: client1, proxy1, proxy2, proxy3
        int multIpFirstSplitIndex = ip.indexOf(",");
        if (multIpFirstSplitIndex != -1) {
            ip = ip.substring(0, multIpFirstSplitIndex);
        }
        if (ip.length() > 15) {
            // IPv4最长15位
            ip = ip.substring(0, 15);
        }
        return ip;
    }

}
