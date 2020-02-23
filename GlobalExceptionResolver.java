package com.shsxt;

import com.mysql.fabric.xmlrpc.base.Params;
import com.shsxt.exceptions.NoLoginException;
import com.shsxt.exceptions.ParamsException;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


//@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        /*ModelAndView mv= new ModelAndView();
        mv.setViewName("error");
        mv.addObject("ex",ex);

        if(ex instanceof ParamsException){
            ParamsException pe= (ParamsException) ex;
            mv.setViewName("param-error");
            mv.addObject("ex",pe.getMsg());
        }
        if(ex instanceof NoLoginException){
            NoLoginException ne = (NoLoginException) ex;
            mv.setViewName("no-login-error");
            mv.addObject("ex",ne.getMsg());
        }*/

        HandlerMethod  handlerMethod = (HandlerMethod) handler;
        /**
         * 如何判断方法返回的是 视图 还是  Json???
         */

        String result="{\"code\":300,\"msg\":\"默认错误\"}";
        PrintWriter pw =null;
        try {
            response.setContentType("application/json;charset=utf-8");
            response.setCharacterEncoding("utf-8");
            pw =response.getWriter();
            pw.write(result);
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(null !=pw){
                pw.close();
            }
        }



        return null;
    }
}
