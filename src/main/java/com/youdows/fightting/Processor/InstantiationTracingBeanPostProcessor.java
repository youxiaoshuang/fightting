package com.youdows.fightting.Processor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 项目启动时执行
 */
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.youdows.fightting.Server.SocketServer;

public class InstantiationTracingBeanPostProcessor implements ApplicationListener<ContextRefreshedEvent>{
    private Logger logger = Logger.getLogger(InstantiationTracingBeanPostProcessor.class);
    @Autowired
    private SocketServer socketServer;
    
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
			//启动开启socket.io服务
		
			if(event.getApplicationContext().getParent()==null){
				timeTask();
			}
		
	}
	
	private void timeTask() {
		Runnable runable = new Runnable() {
			public void run() {
				socketServer.runServer();
				logger.info("为毛上不去啊");
			}
		};
		ScheduledExecutorService service = Executors
				.newSingleThreadScheduledExecutor();
		// 延迟10秒执行
		service.schedule(runable, 10, TimeUnit.SECONDS);
	}

}
