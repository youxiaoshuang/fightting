package com.youdows.fightting.controller;

import com.youdows.fightting.service.WechatService;
import com.youdows.fightting.util.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/weixin/signature")
/**
 * 微信接口验证
 *
 * @author manson
 * @date 2013-05-20
 */
public class WechatController {
    @Autowired
    private WechatService wechatService;
    private static final Logger logger = LoggerFactory.getLogger(WechatController.class);

    /**
     * 确认请求来自微信服务器
     *
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.GET)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");

        PrintWriter out = response.getWriter();
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        logger.info(signature + "+" + timestamp + "+" + nonce + "+" + echostr);
        String isTrue = "F";
        if (MessageUtil.checkSignature(signature, timestamp, nonce)) {
            out.print(echostr);
            isTrue = "T";
        }
        logger.info(isTrue);

        out.close();
        out = null;
    }

    /**
     * 处理微信服务器发来的消息
     */
    @RequestMapping(method = RequestMethod.POST)
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        logger.info("WechatController started: ");
        // 调用核心业务类接收消息、处理消息
        String respMessage = wechatService.processRequest(request);
        logger.info("WechatController doPost: " + respMessage);
        // 响应消息
        PrintWriter out = response.getWriter();
        out.print(respMessage);
        out.close();
    }
    
}