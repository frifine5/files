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
 * �����࣬��byte[]����д��ָ��·�����ļ����û����ݱȶ�
 * 
 * @author wcy 2018��3��28�� ����6:03:46
 * @jdk.version 1.8
 */
public class FileUtil {

	/**
	 * ��byte[]д��ָ���ļ�
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
			ps.println(text);// ���ļ���д���ַ���
//			ps.append("http://www.jb51.net");// �����еĻ���������ַ���
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
		dataLength = fis.available();// ��ȡ����
		// System.out.println("dataLength = " + dataLength);
		data = new byte[dataLength];
		fis.read(data, 0, dataLength);
		fis.close();
		return data;
	}
}
