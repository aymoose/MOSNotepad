package com.mos.servlet;

import com.mos.exception.ParameterException;
import com.mos.exception.SystemException;
import com.mos.po.JSON;
import com.mos.util.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 进行统一的操作
 * 父类不实现方法，子类去实现方法---->抽象方法
 */
public abstract class BaseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        JSON result = new JSON();
        try {
            Object data = process(request, response);
            result.setSuccess(true);
            result.setCode("200");
            result.setMessage("操作成功");
            result.setData(data);
//            response.getWriter().write(JSONUtil.format(result));
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof ParameterException) {
                result.setCode(((ParameterException) e).getCode());
                result.setMessage(e.getMessage());
            } else if (e instanceof SystemException) {
                result.setCode(((SystemException) e).getCode());
                result.setMessage(e.getMessage());
            } else {
                result.setCode("500");
                result.setMessage("服务器错误");
            }
        }
        //无论成功与否我们不仅在后台捕获异常，打印堆栈信息，错误信息，而且再前端也显示错误信息（简单处理）
        response.getWriter().write(JSONUtil.format(result));

    }

    //让子类实现去做具体的业务
    public abstract Object process(HttpServletRequest request, HttpServletResponse response)
            throws Exception;
}
