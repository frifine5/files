package com.main;

import java.io.IOException;
import com.util.AsnUtil;

public class Asn2Sql {
	
	public static void main(String[] args) {
		System.out.println("ʹ��˵���������ra�������ļ����뽫�ļ�����Ϊdata.bin\n"
				+ "�����õ�sql�ļ�����rootMKID+ʱ���������sql�ļ�\n\n");
		try {
			AsnUtil.parse2sql_158();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("\n\n*************�뽫��������ļ�data.bin�ŵ���ǰĿ¼��Ȼ������ִ�б�����*************");
		} catch (java.lang.IllegalArgumentException|java.lang.ClassCastException e ) {
			e.printStackTrace();
			System.out.println("\n\n*************��������ļ�data.bin������Э��Ҫ�󣬵��½���ʧ�ܣ�"
					+ "��ȷ������������ȷ��Ȼ������ִ�б�����*************");

		}
	}

}
