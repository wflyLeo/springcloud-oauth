package com.uooguo.newretail.cloud.framework.kit;

import lombok.SneakyThrows;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * @author jiangskui
 * @date 2018/9/11  16:13
 * @description 请求解析
 */
public class WebKit {

    /**
     * @return 获得当前请求
     */
    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    /**
     * @return 获得当前Body
     */
    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

    public static ServletRequestAttributes getRequestAttributes() {
        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }

    public static String getRequestUrl() {
        val request = getRequest();
        val query = request.getQueryString();
        return request.getRequestURI() + (Objects.isNull(query) ? "" : "?" + query);
    }


    public static void redirect(String url) {
        val response = getResponse();
        response.addHeader("Location", url);
        response.setStatus(302);
    }

    /**
     * 获得参数MAP
     *
     * @param args
     *         如果有 必传2个参数 第一个为原始编码 第二个为输出编码
     * @return 参数MAP
     */
    public static Map<String, String> getParamMap(String... args) {
        Map<String, String> map = new HashMap<>();
        getRequest().getParameterMap().forEach((s, arr) -> {
            val value = StringUtils.join(arr, ",");
            if (args.length > 1) {
                map.put(s, convertEncode(value, args[0], args[1]));
            } else {
                map.put(s, value);
            }
        });
        return map;
    }

    @SneakyThrows
    public static String convertEncode(String value, String input, String output) {
        return new String(value.getBytes(input), output);
    }

    public static Map<String, String> getParamMapToUTF8() {
        return getParamMap("ISO-8859-1", "UTF-8");
    }

    /**
     * @return 获取客户端IP
     */
    public static String getClientIP() {
        HttpServletRequest req = getRequest();
        String ip = Optional
                .ofNullable(req.getHeader("X-Real-IP"))
                .orElse(req.getHeader("X-Forwarded-For"));
        return Optional
                .ofNullable(ip)
                .orElse(req.getRemoteAddr()).split(",")[0];
    }
}
