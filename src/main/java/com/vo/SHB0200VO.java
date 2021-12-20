package com.vo;

import java.nio.ByteBuffer;

public class SHB0200VO extends SHBCommVO{
	
	// 개별 : 수취조회,입금,입금이체
	private byte[] depOrgCd	 	= new byte[8];   // 21.입금기관코드  	
	private byte[] vacctNo  	= new byte[16];  // 22.입금계좌번호 : 가상계좌번호 
	private byte[] vacctNm 	    = new byte[20];  // 23.입금계좌성명 : 가상계좌성명 
	private byte[] wdwOrgCd		= new byte[8];   // 24.출금기관코드  
	private byte[] wdwAcctNo	= new byte[16];  // 25.출금계좌번호 
	private byte[] wdwAcctNm    = new byte[20];  // 26.출금계좌성명 
	private byte[] wdwAcctpw 	= new byte[16];  // 27.출금계좌비밀번호 *로 SET 
	private byte[] secretCard   = new byte[20];  // 28.SECRET카드정보 
	private byte[] tranAmt 	    = new byte[13];  // 29.거래금액 
	private byte[] tranCash		= new byte[13];  // 30.거래금액중현금금액 
	private byte[] tranCheck	= new byte[13];  // 31.거래금액중미결제수표금액 
	private byte[] balanceSign	= new byte[1];   // 32.거래후잔액부호 
	private byte[] balance		= new byte[13];  // 33.거래후잔액 
	private byte[] outbalance	= new byte[13];  // 34.거래후잔액중미결제타점권잔액 
	private byte[] fee  		= new byte[28];  // 35.수수료금액 		
	private byte[] chCd     	= new byte[6];   // 36.어음교환소코드 
	private byte[] referNo      = new byte[20];  // 37.참조번호  
	private byte[] cxCd		    = new byte[4];   // 38.취소사유코드 
	private byte[] acctInf		= new byte[87];  // 39.계정처리정보 
	private byte[] msData		= new byte[60];  // 40.MS TRACK DATA 
	private byte[] filler		= new byte[5];   // 41.필터  
	
	@Override
	public ByteBuffer setTelegram(byte[] inComingData) {
		ByteBuffer bf = super.setTelegram(inComingData);
		
		bf.get(this.depOrgCd);
		bf.get(this.vacctNo);
		bf.get(this.vacctNm);
		bf.get(this.wdwOrgCd);
		bf.get(this.wdwAcctNo);
		bf.get(this.wdwAcctNm);
		bf.get(this.wdwAcctpw);
		bf.get(this.secretCard);
		bf.get(this.tranAmt);
		bf.get(this.tranCash);
		bf.get(this.tranCheck);
		bf.get(this.balanceSign);
		bf.get(this.balance);
		bf.get(this.outbalance);
		bf.get(this.fee);
		bf.get(this.chCd);
		bf.get(this.referNo);
		bf.get(this.cxCd);
		bf.get(this.acctInf);
		bf.get(this.msData);
		bf.get(this.filler);
		
		return bf;
	}
	
	@Override
	public byte[] getTelegram() {
		byte[] telegram = new byte[600];
		ByteBuffer bf = ByteBuffer.wrap(telegram);

		bf.put(super.getTelegram());
		bf.put(this.depOrgCd);
		bf.put(this.vacctNo);
		bf.put(this.vacctNm);
		bf.put(this.wdwOrgCd);
		bf.put(this.wdwAcctNo);
		bf.put(this.wdwAcctNm);
		bf.put(this.wdwAcctpw);
		bf.put(this.secretCard);
		bf.put(this.tranAmt);
		bf.put(this.tranCash);
		bf.put(this.tranCheck);
		bf.put(this.balanceSign);
		bf.put(this.balance);
		bf.put(this.outbalance);
		bf.put(this.fee);
		bf.put(this.chCd);
		bf.put(this.referNo);
		bf.put(this.cxCd);
		bf.put(this.acctInf);
		bf.put(this.msData);
		bf.put(this.filler);
		
		return telegram;
	}
	
	public String getDepOrgCd() {
		return new String(depOrgCd,cs);
	}
	public void setDepOrgCd(String depOrgCd) {
		if(depOrgCd == null) return;
		this.depOrgCd = depOrgCd.getBytes(cs);
	}
	public String getVacctNo() {
		return new String(vacctNo,cs);
	}
	public void setVacctNo(String vacctNo) {
		if(vacctNo == null) return;
		this.vacctNo = fillStr(vacctNo, this.vacctNo.length).getBytes(cs);
	}
	public String getVacctNm() {
		return new String(vacctNm,cs);
	}
	public void setVacctNm(String vacctNm) {
		if(vacctNm == null) return;
		this.vacctNm = fillStr(vacctNm, this.vacctNm.length).getBytes(cs);
	}
	public String getWdwOrgCd() {
		return new String(wdwOrgCd,cs);
	}
	public void setWdwOrgCd(String wdwOrgCd) {
		if(wdwOrgCd == null) return;
		this.wdwOrgCd = wdwOrgCd.getBytes(cs);
	}
	public String getWdwAcctNo() {
		return new String(wdwAcctNo,cs);
	}
	public void setWdwAcctNo(String wdwAcctNo) {
		if(wdwAcctNo == null) return;
		this.wdwAcctNo = fillStr(wdwAcctNo, this.wdwAcctNo.length).getBytes(cs);
	}
	public String getWdwAcctNme() {
		return new String(wdwAcctNm,cs);
	}
	public void setWdwAcctNme(String wdwAcctNm) {
		if(wdwAcctNm == null) return;
		this.wdwAcctNm = fillStr(wdwAcctNm, this.wdwAcctNm.length).getBytes(cs);
	}
	public String getWdwAcctpd() {
		return new String(wdwAcctpw,cs);
	}
	public void setWdwAcctpw(String wdwAcctpw) {
		if(wdwAcctpw == null) return;
		this.wdwAcctpw = fillStr(wdwAcctpw, this.wdwAcctpw.length).getBytes(cs);
	}
	public String getSecretCard() {
		return new String(secretCard,cs);
	}
	public void setSecretCard(String secretCard) {
		if(secretCard == null) return;
		this.secretCard = fillStr(secretCard, this.secretCard.length).getBytes(cs);
	}
	public String getTranAmt() {
		return new String(tranAmt,cs);
	}
	public void setTranAmt(String tranAmt) {
		if(tranAmt == null) return;
		this.tranAmt = fillNum(tranAmt, this.tranAmt.length).getBytes(cs);
	}
	public String getTranCash() {
		return new String(tranCash,cs);
	}
	public void setTranCash(String tranCash) {
		if(tranCash == null) return;
		this.tranCash = fillNum(tranCash, this.tranCash.length).getBytes(cs);
	}
	public String getTranCheck() {
		return new String(tranCheck,cs);
	}
	public void setTranCheck(String tranCheck) {
		if(tranCheck == null) return;
		this.tranCheck = fillNum(tranCheck, this.tranCheck.length).getBytes(cs);
	}
	public String getBalanceSign() {
		return new String(balanceSign,cs);
	}
	public void setBalanceSign(String balanceSign) {
		if(balanceSign == null) return;
		this.balanceSign = balanceSign.getBytes(cs);
	}
	public String  getBalance() {
		return new String(balance,cs);
	}
	public void setBalance(String balance) {
		if(balance == null) return;
		this.balance = fillNum(balance, this.balance.length).getBytes(cs);
	}
	public String getOutbalance() {
		return new String(outbalance,cs);
	}
	public void setOutbalance(String outbalance) {
		if(outbalance == null) return;
		this.outbalance = fillNum(outbalance, this.outbalance.length).getBytes(cs);
	}
	public String getFee() {
		return new String(fee,cs);
	}
	public void setFee(String fee) {
		if(fee == null) return;
		this.fee = fillNum(fee, this.fee.length).getBytes(cs);
	}
	public String getChCd() {
		return new String(chCd,cs);
	}
	public void setChCd(String chCd) {
		if(chCd == null) return;
		this.chCd = fillStr(chCd, this.chCd.length).getBytes(cs);
	}
	public String getReferNo() {
		return new String(referNo,cs);
	}
	public void setReferNo(String referNo) {
		if(referNo == null) return;
		this.referNo = fillStr(referNo, this.referNo.length).getBytes(cs);
	}
	public String getCxCd() {
		return new String(cxCd,cs);
	}
	public void setCxCd(String cxCd) {
		if(cxCd == null) return;
		this.cxCd = fillStr(cxCd, this.cxCd.length).getBytes(cs);
	}
	public String getAcctInf() {
		return new String(acctInf,cs);
	}
	public void setAcctInf(String acctInf) {
		if(acctInf == null) return;
		this.acctInf = fillStr(acctInf, this.acctInf.length).getBytes(cs);
	}
	public String getMsData() {
		return new String(msData,cs);
	}
	public void setMsData(String msData) {
		if(msData == null) return;
		this.msData = fillStr(msData, this.msData.length).getBytes(cs);
	}
	public String getFiller() {
		return new String(filler,cs);
	}
	public void setFiller(String filler) {
		if(filler == null) return;
		this.filler = filler.getBytes(cs);
	}

}
