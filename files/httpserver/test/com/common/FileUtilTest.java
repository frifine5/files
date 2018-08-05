package com.common;


import java.io.IOException;

import org.junit.Test;

public class FileUtilTest {

	@Test
	public void testWriteStringToFile() throws IOException {
		
		String t = new String(FileUtil.fromDATfile("C:\\Users\\kingcrab\\Desktop\\p.bin"), "UTF-8");

		String text = "select a from table where a.id='c';";
//		String auserData = "MIICQQIBAQIBajBIoAMCAQEGCCqBHIFFAYI2oQ4EDOezu+e7n+agh+ivhgQHUEtHXzAwMRcNMTgwNDI5MTYwMDAwWqIPFw0xODA4MTkxNjAwMDBaMIIB7TCCAdUCAQIWGGh0dHA6Ly8xOTIuMTY4LjkuMjU6ODEyMwIBajAiGA8yMDE4MDMyOTE2MDAwMFoYDzIwMTkwMzI5MTYwMDAwWjCCATYwggEyBggqgRzPVQGCLgSCASRNSUhZQkFkUVMwZGZNREF4QTBVQUFBRUFBQ0V0czNyM05mNlc2RFNuUVVRcU9id1lPcTBqSnRacTc2YnVtRXlmT2NUQWxIMVplY0FDK0RBSy9TNXVsdTg3UFRrY1FEL3Jyckxrek9kYlZrNk8wRVFEZ1lVQUFBRUFBQXpOTVFadzVoOTFHRDlpZ1hycmhBdTVCRDJ3b3VOaVFCYkQ0NlJmbnBJK2I2THhJMkg2OE5Sa0dIeDNRTmM2Tyt2VDJCN2N5R25WL2FMa0VscE13ZGdZc1UxNnplYTR2Z1pSN0NCY05qczlBVm5oWWpVV2ZubkswL0YzTDBVa1VMRGtBV2VETFBZUmZMMnRKNzZGSkNYNk5pUHNWZ0V2QWhNVmhEU2dqU25FBggqgRyBRQGCNjBLoAMCAQEGCCqBHIFFAYI2oREED+S4gOWPt+S4u+WvhumSpQQHUEtHXzAwMRcNMTgwNDI5MTYwMDAwWqIPFw0xODA0MjkxNjAwMDBaMAsGCSqBHM9VAYIuAQMFAHRlc3Q=";
		
		text = "insert into a(id, emk, smk, time) values('id', '"+t+"', '', '');";
		
		String path = "aa.sql";
		
		FileUtil.WriteStringToFile(text, path);
		
	}

}
