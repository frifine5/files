package com.main;

import java.io.IOException;
import com.util.AsnUtil;

public class Asn2Sql {
	
	public static void main(String[] args) {
		System.out.println("使用说明：入参是ra导出的文件，请将文件名改为data.bin\n"
				+ "解析得到sql文件，以rootMKID+时间戳命名的sql文件\n\n");
		try {
			AsnUtil.parse2sql_158();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("\n\n*************请将你的数据文件data.bin放到当前目录，然后重新执行本程序*************");
		} catch (java.lang.IllegalArgumentException|java.lang.ClassCastException e ) {
			e.printStackTrace();
			System.out.println("\n\n*************你的数据文件data.bin不符合协议要求，导致解析失败；"
					+ "请确认数据内容正确，然后重新执行本程序*************");

		}
	}

}
