package com.common;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

/**
 * 工具类，将byte[]数组写入指定路径的文件，用户数据比对
 * 
 * @author wcy 2018年3月28日 下午6:03:46
 * @jdk.version 1.8
 */
public class FileUtil {

	/**
	 * 将byte[]写到指定文件
	 * 
	 * @param derDate
	 * @param wholeFileName
	 */
	public static void writeArrayData(byte[] derDate, String wholeFileName) {
		try {
			DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(wholeFileName)));
			out.write(derDate);
			// System.out.println(out.size() + " bytes have been written.");
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void WriteStringToFile(String text, String filePath) {
		File file = null;
		PrintStream ps = null;
		try {
			file = new File(filePath);
			if(!file.exists()){
				file.createNewFile();
			}
			ps = new PrintStream(new FileOutputStream(file));
			ps.println(text);// 往文件里写入字符串
//			ps.append("http://www.jb51.net");// 在已有的基础上添加字符串
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(null != ps) ps.close();
			} catch (Exception e2) {
			}
		}
	}

	
	
	public static byte[] fromDATfile(String p) throws IOException {
		byte[] data;
		int dataLength = 0;
		FileInputStream fis = new FileInputStream(p);
		dataLength = fis.available();// 获取长度
		// System.out.println("dataLength = " + dataLength);
		data = new byte[dataLength];
		fis.read(data, 0, dataLength);
		fis.close();
		return data;
	}
}
