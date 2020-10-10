package com.yingxue.lesson.shiro;

import com.alibaba.fastjson.JSON;
import com.yingxue.lesson.contants.Constant;
import com.yingxue.lesson.exception.BusinessException;
import com.yingxue.lesson.exception.code.BaseResponseCode;
import com.yingxue.lesson.utils.DataResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @ClassName: CustomAccessControlFilter
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
@Slf4j
public class CustomAccessControlFilter extends AccessControlFilter {
    /**
     * 如果返回的是true 就流转到下一个链式调用
     * 返回false 就会流转到 onAccessDenied方法
     * @Author:      小霍
     * @UpdateUser:
     * @Version:     0.0.1
     * @param servletRequest
     * @param servletResponse
     * @param o
     * @return       boolean
     * @throws
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }
    /**
     * 如果返true 就会流转到下一个链式调用
     * false 就是不会流转到下一个链式调用
     * @Author:      小霍
     * @UpdateUser:
     * @Version:     0.0.1
     * @param servletRequest
     * @param servletResponse
     * @return       boolean
     * @throws
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        log.info("request 接口地址：{}",request.getRequestURI());
        log.info("request 接口的请求方式{}",request.getMethod());
        String accessToken=request.getHeader(Constant.ACCESS_TOKEN);
        try {
        if(StringUtils.isEmpty(accessToken)){
            throw new BusinessException(BaseResponseCode.TOKEN_NOT_NULL);
        }
            CustomUsernamePasswordToken customUsernamePasswordToken=new CustomUsernamePasswordToken(accessToken);

            getSubject(servletRequest,servletResponse).login(customUsernamePasswordToken);
        }catch (BusinessException e){
            customResponse(servletResponse,e.getCode(),e.getMsg());
            return false;
        } catch (AuthenticationException e) {
            if(e.getCause() instanceof BusinessException){
                BusinessException exception= (BusinessException) e.getCause();
                customResponse(servletResponse,exception.getCode(),exception.getMsg());
            }else {
                customResponse(servletResponse, BaseResponseCode.TOKEN_ERROR.getCode(), BaseResponseCode.TOKEN_ERROR.getMsg());
            }
            return false;
        }catch (Exception e){
            customResponse(servletResponse, BaseResponseCode.SYSTEM_ERROR.getCode(), BaseResponseCode.SYSTEM_ERROR.getMsg());
            return false;
        }
        return true;
    }
    /**
     * 自定义响应前端
     * @Author:      小霍
     * @UpdateUser:
     * @Version:     0.0.1
     * @param response
     * @param code
     * @param msg
     * @return       void
     * @throws
     */
    private void customResponse(ServletResponse response,int code ,String msg){
        try {
            DataResult result= DataResult.getResult(code,msg);
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setCharacterEncoding("UTF-8");
            String str= JSON.toJSONString(result);
            OutputStream outputStream=response.getOutputStream();
            outputStream.write(str.getBytes("UTF-8"));
            outputStream.flush();
        } catch (IOException e) {
            log.error("customResponse...error:{}",e);
        }

    }
}
