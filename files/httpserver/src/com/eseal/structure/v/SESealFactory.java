package com.eseal.structure.v;

import org.bouncycastle.asn1.*;

import com.common.FileUtil;

import java.io.IOException;
import java.util.Date;

/**
 * WangChengYu for test constructing e-seal
 */
public class SESealFactory {

	public static void main(String[] args) throws IOException {

		SESeal seSeal = new SESeal();
		seSeal.setHeader("ES", 1, "FactoryID");
		System.out.println(seSeal.getHeader());
		System.out.println(seSeal.toASN1Primitive());

		SESESSInfo a = new SESESSInfo();
		a.setCert("cert");
		a.setIdAppAttr("idAppAttr");
		a.setCertDigest("certDigest");

		System.out.println(a.toASN1Primitive());
		FileUtil.writeArrayData(new DERSequence(a.toASN1Primitive()).getEncoded(), "e://test.bin");

	}

}

/**
 * DERSequence esealInfo; ASN1ObjectIdentifier signatureAlgorithm; DERBitString
 * signData;
 */
/*
 * 
 * SESeal::=SEQUENCE{ esealInfo SES_SealInfo�? --印章信息 signatureAlgorithm
 * OBJECT IDENTIFIER --制章人签名算法标�? signData BIT STRING --制章人对印章信息的签名�??
 * } SES_SealInfo::= SEQUENCE{ header SES_Header, --头信�? esID IA5String,
 * --电子印章标识，电子印章数据的唯一标识编码 property SES_ESPropertyInfo, --印章属�?�信�?
 * picture SES_ESPictrueInfo,
 * --电子印章图片数据，机构的电子印章宜采用相关国家管理部门指定的印章印模 extDatas ExtensionDatas
 * OPTIONAL, --自定义数�? esmInfo SES_ESMInfo --制章人信�? }
 * 
 */

class SESeal extends DERSequence {

	DERSequence header;
	DERIA5String esID;
	DERSequence property;
	DERSequence extDatas;
	DERSequence esmInfo;

	public void setHeader(String id, int version, String vid) {
		ASN1EncodableVector asn_Header = new ASN1EncodableVector();
		asn_Header.add(new DERIA5String(id));
		asn_Header.add(new ASN1Integer(version));
		asn_Header.add(new DERIA5String(vid));
		DERSequence sesHeader = new DERSequence(asn_Header);
		this.header = sesHeader;
	}

	public DERSequence getHeader() {
		return header;
	}

	public void setEsID(String esID) {
		this.esID = new DERIA5String(esID);
	}

	public DERIA5String getEsID() {
		return esID;
	}

	public void setProperty(int type, String name, DERSequence essInfoList, Date createDate, Date validStart,
			Date validEnd) {
		ASN1EncodableVector asn_Property = new ASN1EncodableVector();
		asn_Property.add(new ASN1Integer(type));
		asn_Property.add(new DERUTF8String(name));
		asn_Property.add(essInfoList);
		asn_Property.add(new DERGeneralizedTime(createDate));
		asn_Property.add(new DERGeneralizedTime(validStart));
		asn_Property.add(new DERGeneralizedTime(validEnd));
		this.property = new DERSequence(asn_Property);
	}

	public DERSequence getProperty() {
		return property;
	}

	public DERSequence getExtDatas() {
		return extDatas;
	}

	public void setExtDatas(DERSequence extDatas) {
		this.extDatas = extDatas;
	}

	public DERSequence getEsmInfo() {
		return esmInfo;
	}

	public void setEsmInfo(DERSequence esmInfo) {
		this.esmInfo = esmInfo;
	}

	@Override
	public ASN1Primitive toASN1Primitive() {
		ASN1EncodableVector arr = new ASN1EncodableVector();
		arr.add(header);
		arr.add(esID);
		arr.add(property);
		arr.add(extDatas);
		arr.add(esmInfo);
		return new DERSequence(arr).toASN1Primitive();
	}
}

class SESESSInfo extends DERSequence {

	DEROctetString cert;
	DEROctetString idAppAttr;
	DEROctetString certDigest;

	public DEROctetString getCert() {
		return cert;
	}

	public void setCert(String cert) {
		this.cert = new DEROctetString(cert.getBytes());
	}

	public DEROctetString getIdAppAttr() {
		return idAppAttr;
	}

	public void setIdAppAttr(String idAppAttr) {
		this.idAppAttr = new DEROctetString(idAppAttr.getBytes());
	}

	public DEROctetString getCertDigest() {
		return certDigest;
	}

	public void setCertDigest(String certDigest) {
		this.certDigest = new DEROctetString(certDigest.getBytes());
	}

	@Override
	public ASN1Primitive toASN1Primitive() {
		ASN1EncodableVector arr = new ASN1EncodableVector();
		arr.add(cert);
		arr.add(idAppAttr);
		arr.add(certDigest);
		return new DERSequence(arr).toASN1Primitive();
	}
}
