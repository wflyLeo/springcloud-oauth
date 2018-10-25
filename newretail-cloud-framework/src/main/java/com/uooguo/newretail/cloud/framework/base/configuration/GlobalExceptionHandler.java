package com.uooguo.newretail.cloud.framework.base.configuration;

import com.alibaba.fastjson.JSONObject;
import com.uooguo.newretail.cloud.framework.core.constant.ErrorStatus;
import com.uooguo.newretail.cloud.framework.core.exception.ServiceException;
import com.uooguo.newretail.cloud.framework.core.protocol.Result;
import feign.FeignException;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 全局 验证 异常 处理
 *
 * @author Tiangel
 * @date 2017-12-20
 */
@ControllerAdvice(annotations = {RestController.class, Controller.class})
public class GlobalExceptionHandler {

    @Value("${spring.profiles.active:dev}")
    public String activeProfile;

    private static final String DEV_MODE = "dev";
    private static final String TEST_MODE = "test";

    private Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private String getMessage(Exception exception, ErrorStatus status) {
        if (activeProfile.equalsIgnoreCase(DEV_MODE) || activeProfile.equalsIgnoreCase(TEST_MODE)) {
            return exception.getMessage() != null ? exception.getMessage() : status.getMessage();
        } else {
            return status.getMessage();
        }
    }

    /**
     * 构建返回错误
     *
     * @param status
     * @param exception
     * @param data
     * @return
     */
    private Result failure(ErrorStatus status, Exception exception, Object data) {
        String meesage = null;
        if (exception != null) {
            meesage = this.getMessage(exception, status);
        }
        return Result.buildFailure(status.value(), meesage, data);
    }

    /**
     * 构建返回错误
     *
     * @param status
     * @param data
     * @return
     */
    private Result failure(ErrorStatus status, Object data) {
        return failure(status, null, data);
    }

    /**
     * 构建返回错误
     *
     * @param status
     * @param exception
     * @return
     */
    private Result failure(ErrorStatus status, Exception exception) {
        return failure(status, exception, null);
    }

    /**
     * 构建返回错误
     *
     * @param status
     * @return
     */
    private Result failure(ErrorStatus status) {
        return Result.buildFailure(status);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ConstraintViolationException.class)
    public Result handleValidationException(ConstraintViolationException e) {
        log.error(ErrorStatus.ILLEGAL_DATA.getMessage() + ":" + e.getMessage());
        List<Map<String, Object>> fields = new ArrayList<>();
        for (ConstraintViolation<?> cv : e.getConstraintViolations()) {
            String fieldName = ((PathImpl) cv.getPropertyPath()).getLeafNode().asString();
            String message = cv.getMessage();
            Map<String, Object> field = new HashMap<>();
            field.put("field", fieldName);
            field.put("message", message);
            fields.add(field);
        }
        return failure(ErrorStatus.ILLEGAL_DATA, fields);
    }

    @ResponseBody
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result handleBindException(BindException e) {
        log.error(ErrorStatus.ILLEGAL_DATA.getMessage() + ":" + e.getMessage());
        List<Map<String, Object>> fields = new ArrayList<>();
        List<String> msg = new ArrayList<>();
        for (FieldError error : e.getFieldErrors()) {
            Map<String, Object> field = new HashMap<>();
            field.put("field", error.getField());
            field.put("message", error.getDefaultMessage());
            msg.add(error.getDefaultMessage());
            fields.add(field);
        }
        return Result.buildFailure(ErrorStatus.ILLEGAL_DATA.value(), String.join(",", msg), fields);
    }

    @ResponseBody
    @ExceptionHandler(MultipartException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result handleMultipartException() {
        return failure(ErrorStatus.MULTIPART_TOO_LARGE);
    }

    @ResponseBody
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result handleIllegalArgumentException(IllegalArgumentException e) {
        log.error(ErrorStatus.ILLEGAL_ARGUMENT.getMessage() + ":" + e.getMessage());
        return failure(ErrorStatus.ILLEGAL_ARGUMENT, e);
    }

    @ResponseBody
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result handleMissingServletRequestParameterException(
            MissingServletRequestParameterException e) {
        log.error(ErrorStatus.MISSING_ARGUMENT.getMessage() + ":" + e.getMessage());
        return failure(ErrorStatus.MISSING_ARGUMENT, e);
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result handleMethodArgumentTypeMismatchExceptionException(
            MethodArgumentTypeMismatchException e) {

        log.error(ErrorStatus.ILLEGAL_ARGUMENT_TYPE.getMessage() + ":" + e.getMessage());
        return failure(ErrorStatus.ILLEGAL_ARGUMENT_TYPE, e);
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result handleException(Exception e) {
        e.printStackTrace();
        log.error(ErrorStatus.INTERNAL_SERVER_ERROR.getMessage() + ":" + e.getMessage());
        return failure(ErrorStatus.INTERNAL_SERVER_ERROR, e);
    }

    @ResponseBody
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public Result handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {

        log.error(ErrorStatus.METHOD_NOT_ALLOWED.getMessage() + ":" + e.getMessage());
        return failure(ErrorStatus.METHOD_NOT_ALLOWED, e);
    }

    @ResponseBody
    @ExceptionHandler(ServiceException.class)
//    @ResponseStatus(HttpStatus.OK)
    public Result handleServiceException(HttpServletResponse response, ServiceException e) {
        log.error(ErrorStatus.SERVICE_EXCEPTION.getMessage() + ":" + e.getMessage());
        if (e.getCode() == null) {
            return failure(ErrorStatus.SERVICE_EXCEPTION, e);
        } else {
            if (e.getCode() == 306) {
                response.setStatus(306);
            } else {
                response.setStatus(HttpStatus.OK.value());
            }
            return Result.buildFailure(e.getCode(), e.getMessage());
        }
    }

    @ResponseBody
    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result handleIllegalStateException(IllegalStateException e) {
        log.warn("exception", e);
        return failure(ErrorStatus.ILLEGAL_STATE, e);
    }

    /**
     * feignClient调用异常，将服务的异常和http状态码解析
     *
     * @param exception
     * @return
     */
    @ResponseBody
    @ExceptionHandler({FeignException.class})
    public Result feignException(FeignException exception) {
        int httpStatus = exception.status();
        if (httpStatus >= 500) {
            log.error("feignClient调用异常", exception);
        }
        Result result = new Result();
        String msg = exception.getMessage();
        if (!StringUtils.isEmpty(msg)) {
            int index = msg.indexOf("\n");
            if (index > 0) {
                String string = msg.substring(index);
                if (!StringUtils.isEmpty(string)) {
                    JSONObject json = JSONObject.parseObject(string.trim());
                    result.setMsg((String) json.get("error_description"));
                }
            }
        }
        result.setCode(ErrorStatus.SERVICE_EXCEPTION.value());
        result.setSuccess(false);
        return result;
    }
}
