package com.youdows.fightting.service;

import java.io.InputStream;

import org.apache.commons.net.ftp.FTPClient;

public interface FtpService {
	/**
	 * 获取FTP客户端对象
	 *
	 * @return
	 */
	public FTPClient getFtpClient();

	/**
	 * 上传文件（文件流）
	 * @param ftpPath
	 * @param is
	 */
	public boolean uploadByIS(String ftpPath,InputStream is);
	
	/**
	 * 从ftp获取文件流
	 * @param fileName
	 * @return
	 */
	public InputStream getFileIS(String ftpPath, String fileName);
	
	/**
	 * 创建文件夹
	 * @param workDir
	 * @param makeDir
	 * @param client
	 * @return
	 */
	public boolean makeDirectory(String workDir,String makeDir,FTPClient client);

}
