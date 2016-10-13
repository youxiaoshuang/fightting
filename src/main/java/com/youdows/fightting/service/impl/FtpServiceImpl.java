package com.youdows.fightting.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

import com.youdows.fightting.dao.UserEntityMapper;
import com.youdows.fightting.entity.UserEntity;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.youdows.fightting.service.FtpService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FtpServiceImpl implements FtpService {
	@Value("#{sys['ftpHost']}")
	public String ftpHost;
	@Value("#{sys['ftpPort']}")
	public int ftpPort;
	@Value("#{sys['ftpUserName']}")
	public String ftpUserName;
	@Value("#{sys['ftpPassword']}")
	public String ftpPassword;
	@Value("#{sys['imageRootPath']}")
	public String rootPath;
	private Logger logger = Logger.getLogger(this.getClass());
	/**
	 * Description: 向FTP服务器上传文件
	 * 
	 * @author youxiaoshuang
	 * @param url
	 *            FTP服务器hostname
	 * @param port
	 *            FTP服务器端口
	 * @param username
	 *            FTP登录账号
	 * @param password
	 *            FTP登录密码
	 * @param path
	 *            FTP服务器保存目录
	 * @param filename
	 *            上传到FTP服务器上的文件名
	 * @param input
	 *            输入流
	 * @return 成功返回true，否则返回false
	 */
	public FTPClient getFtpClient() {
		FTPClient ftpClient = null;

		try {
			ftpClient = new FTPClient();
			ftpClient.connect(ftpHost, ftpPort);// 连接FTP服务器
			ftpClient.login(ftpUserName, ftpPassword);// 登陆FTP服务器
			if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
				logger.info("未连接到FTP，用户名或密码错误。");
				ftpClient.disconnect();
			} else {
				logger.info("FTP连接成功。");
			}
		} catch (SocketException e) {
			e.printStackTrace();
			logger.info("FTP的IP地址可能错误，请正确配置。");
		} catch (IOException e) {
			e.printStackTrace();
			logger.info("FTP的端口错误,请正确配置。");
		}
		return ftpClient;
	}

	/**
	 * 本地上传文件到FTP服务器
	 * 
	 * @param ftpPath
	 *            远程文件路径FTP
	 * @throws IOException
	 */
	public boolean uploadByIS(String ftpPath,InputStream is) {
		FTPClient ftpClient = null;
		boolean flag = true;
		logger.info("开始上传文件到FTP.");
		try {
			ftpClient = this.getFtpClient();
			// 设置PassiveMode传输
			ftpClient.enterLocalPassiveMode();
			// 设置以二进制流的方式传输
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			// 对远程目录的处理
			String remoteFileName = ftpPath;
			//创建文件夹
			String path = this.makeDirForDate(ftpPath, ftpClient);
			
			
//			if (ftpPath.contains("/")) {
//				remoteFileName = ftpPath
//						.substring(ftpPath.lastIndexOf("/") + 1);
//			}
//			File f = new File(writeTempFielPath);
//			InputStream in = new FileInputStream(f);
			if(!ftpClient.storeFile(path, is)){
				flag =  false;
				logger.info("上传文件" + path + "到FTP失败!");
			}else{
				logger.info("上传文件" + path + "到FTP成功!");
			}
			is.close();
//			f.delete();
		} catch (Exception e) {
			e.printStackTrace();
			flag =  false;
		} finally {
			try {
				if (ftpClient.isConnected()) {
					ftpClient.disconnect();
					logger.info("写入完成，断开连接");
				}
			} catch (IOException e) {
				e.printStackTrace();
				flag =  false;
			}
		}
		return flag;
	}

	/**
	 * 去ftp服务器的下载
	 * 
	 * @param ftpUserName
	 * @param ftpPassword
	 * @param ftpPath
	 * @param FTPServer
	 * @return
	 */
	public InputStream getFileIS(String ftpPath,String fileName) {
		InputStream in = null;
		FTPClient ftpClient = null;
		logger.info("开始读取绝对路径" + ftpPath+fileName + "文件!");
		try {
			ftpClient = this.getFtpClient();
			// ftpClient.setControlEncoding("UTF-8"); // 中文支持
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			// ftpClient.enterLocalPassiveMode();
			ftpClient.changeWorkingDirectory(ftpPath+fileName.substring(0, fileName.lastIndexOf("/")));
			in = ftpClient.retrieveFileStream(fileName.substring(fileName.lastIndexOf("/")+1));
			logger.error("获取" + fileName + "文件成功！");
		} catch (FileNotFoundException e) {
			logger.error("没有找到" + fileName + "文件");
			logger.error("下载配置文件失败，请联系管理员.");
			e.printStackTrace();
			return null;
		} catch (SocketException e) {
			logger.error("连接FTP失败.");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("文件读取错误。");
			e.printStackTrace();
			return null;
		}
		return in;
	}

	/**
	 * 把配置文件先写到本地的一个文件中取
	 * 
	 * @param ftpPath
	 * @param str
	 * @return
	 */
	public boolean write(String fileName, String fileContext,
			String writeTempFielPath) {
		try {
			logger.info("开始写配置文件");
			File f = new File(writeTempFielPath + "/" + fileName);
			if (!f.exists()) {
				if (!f.createNewFile()) {
					logger.info("文件不存在，创建失败!");
				}
			}
			BufferedWriter bw = new BufferedWriter(new FileWriter(f, true));
			bw.write(fileContext.replaceAll("\n", "\r\n"));
			bw.flush();
			bw.close();
			return true;
		} catch (Exception e) {
			logger.error("写文件没有成功");
			e.printStackTrace();
			return false;
		}
	}

	public static void main(String[] args) {
//		new FtpService().upload("/public/a.xml", "admin", "admin",
//				"172.16.71.157", 21, "E:\\Postman.rar");
		
		
		String a = "/1/2/3.jpg";
	}

	public boolean makeDirectory(String workDir, String makeDir,
			FTPClient client) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			client.changeWorkingDirectory(workDir);
			flag = client.makeDirectory(workDir);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	public String makeDirForDate(String filePath,FTPClient client){
		String workDir = filePath.substring(0, filePath.lastIndexOf("/")+1);
		String[] dirs = workDir.split("/");
		String dirPath = rootPath;
		for (String dir : dirs) {
			dirPath+="/"+dir;
			try {
				if(client.makeDirectory(dirPath)){
					logger.error("创建文件夹"+dirPath+"成功！");
				}else{
					logger.error("创建文件夹"+dirPath+"失败！");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}        
		return rootPath+filePath;
		
	}


}
