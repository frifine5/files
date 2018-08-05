package com.util;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;

import com.common.FileUtil;

public class AsnUtil {
	
	public static void parse2sql() throws IOException{
		String dataFile = "data.bin";
		String outFileName = "rootMKID"+System.currentTimeMillis()+".sql"; 
		String auserData = new String(FileUtil.fromDATfile(dataFile), "UTF-8");
		
		ASN1Sequence asn = ASN1Sequence.getInstance(Base64.getDecoder().decode(auserData));
		
		String serialNumber = ((ASN1Integer)asn.toArray()[1]).getValue().intValue()+"";
		System.out.printf("(����)����Ψһ��ʶ=%s\n",serialNumber);
//		DEROctetString aus1 = (DEROctetString)((ASN1Sequence)asn.toArray()[2]).toArray()[3];// ���Դ��⣬��ô�����
//		System.out.printf("identityData=%s\n", new String(aus1.getOctets()));
		
		ASN1Sequence ibcpk = (ASN1Sequence)((ASN1Sequence)((ASN1Sequence)asn.toArray()[3]).toArray()[0]).toArray()[4];
		DEROctetString mpk = (DEROctetString)((ASN1Sequence)ibcpk.toArray()[0]).toArray()[1];
//		System.out.printf("mpk=%s\n", new String(mpk.getOctets()));
		
		
		byte[] asnMpk =  mpk.getOctets();
//		System.out.println(asnMpk.length );
//		System.out.println("--------------------------------");
		System.out.println(Base64.getEncoder().encodeToString( asnMpk ));
		ASN1Sequence fab0 = (ASN1Sequence)DERSequence.fromByteArray(asnMpk );
//		System.out.println(fab0);
		ASN1Encodable[] array = fab0.toArray();
		DEROctetString at0 = (DEROctetString) array[0];
		DERBitString at1 = (DERBitString) array[1];
		DERBitString at2 = (DERBitString) array[2];
//		System.out.println("------------������ò���------------");
//		System.out.println(at0);
//		System.out.println(at1);
//		System.out.println(at2);
		
		String kgsId = new String(at0.getOctets());
		String ba64enc = Base64.getEncoder().encodeToString(at1.getBytes());
		String ba64sign = Base64.getEncoder().encodeToString(at2.getBytes());
		
		System.out.println("------------����ʶ �ؼ���Ϣ------------");
		System.out.println(kgsId);
		System.out.println(ba64enc);
		System.out.println(ba64sign);
		
		// -------sql���---
		String insert = "INSERT INTO IBC_PPS_ROOTKEY_TAB(ID, KGSID, ENCMASTER_PUBKEY, SIGNMASTER_PUBKEY, ASNDATA) "
				+ "VALUES('"+serialNumber+"', '"+kgsId+"', '"+ba64enc+"', '"+ba64sign+"', '"+auserData+"');";
		FileUtil.WriteStringToFile(insert, outFileName);
		System.out.println("��ɣ����ε������ļ� ��Ϊ"+outFileName);
	}

	
	public static void parse2sql_158() throws IOException{
		String dataFile = "data.bin";
		String outFileName = "rootMKID"+System.currentTimeMillis()+".sql"; 
		String auserData = new String(FileUtil.fromDATfile(dataFile), "UTF-8");
		
		ASN1Sequence asn = ASN1Sequence.getInstance(Base64.getDecoder().decode(auserData));
		
		String serialNumber = ((ASN1Integer)asn.toArray()[1]).getValue().intValue()+"";
		System.out.printf("(����)����Ψһ��ʶ=%s\n",serialNumber);
//		DEROctetString aus1 = (DEROctetString)((ASN1Sequence)asn.toArray()[2]).toArray()[3];// ���Դ��⣬��ô�����
//		System.out.printf("identityData=%s\n", new String(aus1.getOctets()));
		
		ASN1Sequence ibcpk = (ASN1Sequence)((ASN1Sequence)((ASN1Sequence)asn.toArray()[3]).toArray()[0]).toArray()[4];
		DEROctetString mpk = (DEROctetString)((ASN1Sequence)ibcpk.toArray()[0]).toArray()[1];
//		System.out.printf("mpk=%s\n", new String(mpk.getOctets()));
		
		
		byte[] asnMpk =  mpk.getOctets();
//		System.out.println(asnMpk.length );
//		System.out.println("--------------------------------");
		System.out.println(Base64.getEncoder().encodeToString( asnMpk ));
		ASN1Sequence fab0 = (ASN1Sequence)DERSequence.fromByteArray(asnMpk );
//		System.out.println(fab0);
		ASN1Encodable[] array = fab0.toArray();
		
		System.out.printf("��������������Ϣ��expect 11, actual %s.\n", array.length);
		
		
		DEROctetString at0 = (DEROctetString) array[8];
		DERBitString at1 = (DERBitString) array[9];
		DERBitString at2 = (DERBitString) array[10];
//		System.out.println("------------������ò���------------");
//		System.out.println(at0);
//		System.out.println(at1);
//		System.out.println(at2);
		
		String kgsId = new String(at0.getOctets());
		String ba64enc = Base64.getEncoder().encodeToString(at1.getBytes());
		String ba64sign = Base64.getEncoder().encodeToString(at2.getBytes());
		
		System.out.println("------------����ʶ �ؼ���Ϣ------------");
		System.out.println(kgsId);
		System.out.println(ba64enc);
		System.out.println(ba64sign);
		
		// -------sql���---
		String insert = "INSERT INTO IBC_PPS_ROOTKEY_TAB(ID, KGSID, ENCMASTER_PUBKEY, SIGNMASTER_PUBKEY, ASNDATA) "
				+ "VALUES('"+serialNumber+"', '"+kgsId+"', '"+ba64enc+"', '"+ba64sign+"', '"+auserData+"');";
		FileUtil.WriteStringToFile(insert, outFileName);
		System.out.println("��ɣ����ε������ļ� ��Ϊ"+outFileName);
		
		
		for (ASN1Encodable a : array) {
			System.out.println(a);
		}
	}

}
