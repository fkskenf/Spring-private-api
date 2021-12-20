package com.vo;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class SHBCommVO {
	
	// 전문 공통부  
	private byte []  length			= new byte[4];   // 1.LENGTH (공통 부 + 개별 부)
	private byte []  systemId   	= new byte[3];   // 2.시스템ID : "VAS" 사용 
	private byte []  trOrgCd        = new byte[8];   // 3.전문송수신기관코드
	private byte []  trFlag         = new byte[1];   // 4.송수신 FLAG
	private byte []  msgType  		= new byte[4];   // 5.전문구분코드(MSG TYPE)
	private byte []  jobType  		= new byte[4];   // 6.거래구분코드
	private byte []  orgCd          = new byte[8];   // 7.취급기관코드, 입금기관코드                                                                                     
	private byte []  txnNo          = new byte[7];   // 8.거래일련번호 
	private byte []  trSendDt       = new byte[14];  // 9.전문전송일시 YYYYMMDD+HHMMSS
	private byte []  frsTrnDt       = new byte[14];  // 10.거래개시일시 : 취급기관에서 최초 거래가 발생한 일시  
	private byte []  resCd          = new byte[4];   // 11.응답코드
	private byte []  userArea1		= new byte[15];  // 12 USER WORK AREA 1 응답시 copy 해서 보낸다 
	private byte []  userArea2		= new byte[15];  // 13 USER WORK AREA 2 응답시 copy 해서 보낸다 
	private byte []  deadline       = new byte[1];   // 14.마감전후구분,호스트시스템의 마감전후 상태 0:마감 전, 1:마감 후 
	private byte []  occurType      = new byte[1];   // 15.발생구분 1:자행,2:타행,3:제휴기관,0:기타 
	private byte []  mediaType		= new byte[1];   // 16.매체구분 
	private byte []  openOrgCd		= new byte[8];   // 17.개설기관코드 현재 '99999999' 또는 실제개설기관코드가 SET되고 있음 
	private byte []  termInfo  	    = new byte[25];	 // 18.취급단말정보
	private byte []  resMsg			= new byte[50];  // 19.응답 MESSAGE 장애 시 셋팅되는 처리결과 메시지 
	private byte []  netSeq			= new byte[13];  // 20.공동망 고유번호  

	// DB 관리 정보 
	private String sts;        // DB flag  
	private String bankcd;     // 
	private String vacctcno;   // 가상계좌 고객사 cno 또는 company_cno
	private String vacctsano;  // 가상계좌 고객사 사업자번호
	private String clientsano; // 고객사 거래처 사업자번호 
	private String vacctty;    // 가상계좌 타입 
	private   long histid = 0; // hist id  
	private    int dbcnt = 0; 

	protected final Charset cs = Charset.forName("ksc5601");
	
	public SHBCommVO() {}

	public byte[] getTelegram() {
		byte[] telegram = new byte[200];
		ByteBuffer bf = ByteBuffer.wrap(telegram);
		
		bf.put(this.length);  
		bf.put(this.systemId);  
		bf.put(this.trOrgCd);  
		bf.put(this.trFlag);
		bf.put(this.msgType);
		bf.put(this.jobType);
		bf.put(this.orgCd);
		bf.put(this.txnNo);
		bf.put(this.trSendDt);
		bf.put(this.frsTrnDt);
		bf.put(this.resCd);
		bf.put(this.userArea1);
		bf.put(this.userArea2);
		bf.put(this.deadline);
		bf.put(this.occurType);
		bf.put(this.mediaType);
		bf.put(this.openOrgCd);
		bf.put(this.termInfo);
		bf.put(this.resMsg);
		bf.put(this.netSeq);
		
		return telegram;
	}
	
	public ByteBuffer setTelegram(byte[] inComingData) {
		ByteBuffer bf = ByteBuffer.wrap(inComingData);
		
		bf.get(this.length);  
		bf.get(this.systemId);  
		bf.get(this.trOrgCd);  
		bf.get(this.trFlag);
		bf.get(this.msgType);
		bf.get(this.jobType);
		bf.get(this.orgCd);
		bf.get(this.txnNo);
		bf.get(this.trSendDt);
		bf.get(this.frsTrnDt);
		bf.get(this.resCd);
		bf.get(this.userArea1);
		bf.get(this.userArea2);
		bf.get(this.deadline);
		bf.get(this.occurType);
		bf.get(this.mediaType);
		bf.get(this.openOrgCd);
		bf.get(this.termInfo);
		bf.get(this.resMsg);
		bf.get(this.netSeq);
		
		return bf;
	}

	public String fillStr(String inputString, int length) {
   		
   		if(inputString == null) inputString ="";
   		
   	    if (inputString.length() >= length) {
 	        return inputString;
 	    }
 	    StringBuilder sb = new StringBuilder();
 	    sb.append(inputString);
 	    while (sb.length() < length - inputString.length()) {
 	        sb.append(' ');
 	    }
 	    return sb.toString();
 	}
	
	public String fillNum(String inputString, int length) {
    	
		if(inputString == null) inputString ="";
    	 
 	    if (inputString.length() >= length) {
 	        return inputString;
 	    }
 	    StringBuilder sb = new StringBuilder();
 	    while (sb.length() < length - inputString.length()) {
 	        sb.append('0');
 	    }
 	    sb.append(inputString);
 	    return sb.toString();
 	}
	
	public String getLength() {
		return new String(length,cs);
	}

	public void setLength(String length) {
		if(length == null) return;
		this.length = length.getBytes(cs);
	}

	public String getSystemId() {
		return new String(systemId,cs);
	}

	public void setSystemId(String systemId) {
		if(systemId == null) return;
		this.systemId = systemId.getBytes(cs);
	}

	public String getTrOrgCd() {
		return new String(trOrgCd,cs);
	}

	public void setTrOrgCd(String trOrgCd) {
		if(trOrgCd == null) return;
		this.trOrgCd = trOrgCd.getBytes(cs);
	}

	public String getTrFlag() {
		return new String(trFlag,cs);
	}

	public void setTrFlag(String trFlag) {
		if(trFlag == null) return;
		this.trFlag = trFlag.getBytes(cs);
	}

	public String getMsgType() {
		return new String(msgType,cs);
	}

	public void setMsgType(String msgType) {
		if(msgType == null) return;
		this.msgType = msgType.getBytes(cs);
	}

	public String getJobType() {
		return new String(jobType,cs);
	}

	public void setJobType(String jobType) {
		if(jobType == null) return;
		this.jobType = jobType.getBytes(cs);
	}

	public String getOrgCd() {
		return new String(orgCd,cs);
	}

	public void setOrgCd(String orgCd) {
		if(orgCd == null) return;
		this.orgCd = orgCd.getBytes(cs);
	}

	public String getTxnNo() {
		return new String(txnNo,cs);
	}

	public void setTxnNo(String txnNo) {
		if(txnNo == null) return;
		this.txnNo = txnNo.getBytes(cs);
	}

	public String getTrSendDt() {
		return new String(trSendDt,cs);
	}

	public void setTrSendDt(String trSendDt) {
		if(trSendDt == null) return;
		this.trSendDt = trSendDt.getBytes(cs);
	}

	public String getFrsTrnDt() {
		return new String(frsTrnDt,cs);
	}

	public void setFrsTrnDt(String frsTrnDt) {
		if(frsTrnDt == null) return;
		this.frsTrnDt = frsTrnDt.getBytes(cs);
	}

	public String getResCd() {
		return new String(resCd,cs);
	}

	public void setResCd(String resCd) {
		if(resCd == null) return;
		this.resCd = resCd.getBytes(cs);
	}

	public String getUserArea1() {
		return new String(userArea1,cs);
	}

	public void setUserArea1(String userArea1) {
		if(userArea1 == null) return;
		this.userArea1 = userArea1.getBytes(cs);
	}

	public String getUserArea2() {
		return new String(userArea2,cs);
	}

	public void setUserArea2(String userArea2) {
		if(userArea2 == null) return;
		this.userArea2 = userArea2.getBytes(cs);
	}

	public String getDeadline() {
		return new String(deadline,cs);
	}

	public void setDeadline(String deadline) {
		if(deadline == null) return;
		this.deadline = deadline.getBytes(cs);
	}

	public String getOccurType() {
		return new String(occurType,cs);
	}

	public void setOccurType(String occurType) {
		if(occurType == null) return;
		this.occurType = occurType.getBytes(cs);
	}

	public String getMediaType() {
		return new String(mediaType,cs);
	}

	public void setMediaType(String mediaType) {
		if(mediaType == null) return;
		this.mediaType = mediaType.getBytes(cs);
	}

	public String getOpenOrgCd() {
		return new String(openOrgCd,cs);
	}

	public void setOpenOrgCd(String openOrgCd) {
		if(openOrgCd == null) return;
		this.openOrgCd = openOrgCd.getBytes(cs);
	}

	public String getTermInfo() {
		return new String(termInfo,cs);
	}

	public void setTermInfo(String termInfo) {
		if(termInfo == null) return;
		this.termInfo = termInfo.getBytes(cs);
	}

	public String getResMsg() {
		return new String(resMsg,cs);
	}

	public void setResMsg(String resMsg) {
		if(resMsg == null) return;
		this.resMsg = fillStr(resMsg, this.resMsg.length).getBytes(cs);
	}

	public String getNetSeq() {
		return new String(netSeq,cs);
	}

	public void setNetSeq(String netSeq) {
		if(netSeq == null) return;
		this.netSeq = netSeq.getBytes(cs);
	}

	public String getSts() {
		return sts;
	}

	public void setSts(String sts) {
		if(sts == null) return;
		this.sts = sts;
	}

	public String getVacctcno() {
		return vacctcno;
	}

	public void setVacctcno(String vacctcno) {
		if(vacctcno == null) return;
		this.vacctcno = vacctcno;
	}

	public String getVacctsano() {
		return vacctsano;
	}

	public void setVacctsano(String vacctsano) {
		if(vacctsano == null) return;
		this.vacctsano = vacctsano;
	}

	public String getClientsano() {
		return clientsano;
	}

	public void setClientsano(String clientsano) {
		if(clientsano == null) return;
		this.clientsano = clientsano;
	}

	public String getVacctty() {
		return vacctty;
	}

	public void setVacctty(String vacctty) {
		if(vacctty == null) return;
		this.vacctty = vacctty;
	}
	
	public long getHistid() {
		return histid;
	}

	public void setHistid(long histid) {
		this.histid = histid;
	}

	public int getDbcnt() {
		return dbcnt;
	}

	public void setDbcnt(int dbcnt) {
		this.dbcnt = dbcnt;
	}

	public String getBankcd() {
		return bankcd;
	}

	public void setBankcd(String bankcd) {
		if(bankcd == null) return;
		this.bankcd = bankcd;
	}


}


