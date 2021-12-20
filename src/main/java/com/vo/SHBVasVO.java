package com.vo;

public class SHBVasVO {
	
	private String bankcd;   	// 은행코드
	private String orgcd;       // 기관코드
	private String vacctty;  	// 계좌 타입 (평생 P /일회용 O)
	private String svccd;    	// 서비스코드 
	private String vacctcno; 	// 가상계좌 고객사 cno
	private String vacctno;  	// 가상계좌 번호 
	private String vacctnm;  	// 가상계좌 예금주 명
	private String prevacctnm;  // 가상계좌 고객사 변경전 예금주명 또는 수취인성명
	private String vacctsano;   // 가상계좌 고객사 사업자번호
	private String clientsano;  // 고객사 거래처 사업자번호 
	private String ints;       	// 이력발생시간 
	private String upts;       	// 이력발생시간 
	private String jobtype;  	// 작업 종류 : I (발급), R (반납), C(예금주명 변경)
    private String status;      // 처리상태 : N (미사용), I (발급), R (반납)  
    private String wdwacctnm;	// 출금계좌성명  
	private String fdate;    	// 시작날짜
	private String tdate;    	// 종료일
	private String rescd;		// 응답코드 
	private String resmsg;		// 응답메시지 
	private String bankst;		// 승인 T, 취소 F
    private   long histid;      // hist_id
	private   long tranamt = 0;	// 거래금액
	private   long fee = 0;     // 수수료 
    private    int cnt = 0;     // 요청갯수 등  

	
	public String getBankcd() {
		return bankcd;
	}
	public void setBankcd(String bankcd) {
		if(bankcd == null) return;
		this.bankcd = bankcd;
	}
	public String getVacctty() {
		return vacctty;
	}
	public void setVacctty(String vacctty) {
		if(vacctty == null) return;
		this.vacctty = vacctty;
	}
	public String getSvccd() {
		return svccd;
	}
	public void setSvccd(String svccd) {
		if(svccd == null) return;
		this.svccd = svccd;
	}
	public String getVacctcno() {
		return vacctcno;
	}
	public void setVacctcno(String vacctcno) {
		if(vacctcno == null) return;
		this.vacctcno = vacctcno;
	}
	public String getVacctno() {
		return vacctno;
	}
	public void setVacctno(String vacctno) {
		if(vacctno == null) return;
		this.vacctno = vacctno;
	}
	public String getVacctnm() {
		return vacctnm;
	}
	public void setVacctnm(String vacctnm) {
		if(vacctnm == null) return;
		this.vacctnm = vacctnm;
	}

	public String getJobtype() {
		return jobtype;
	}
	public void setJobtype(String jobtype) {
		if(jobtype == null) return;
		this.jobtype = jobtype;
	}
	public String getWdwacctnm() {
		return wdwacctnm;
	}
	public void setWdwacctnm(String wdwacctnm) {
		if(wdwacctnm == null) return;
		this.wdwacctnm = wdwacctnm;
	}
	public String getFdate() {
		return fdate;
	}
	public void setFdate(String fdate) {
		if(fdate == null) return;
		this.fdate = fdate;
	}
	public String getTdate() {
		return tdate;
	}
	public void setTdate(String tdate) {
		if(tdate == null) return;
		this.tdate = tdate;
	}
	public long getTranamt() {
		return tranamt;
	}
	public void setTranamt(long tranamt) {
		this.tranamt = tranamt;
	}
	public long getFee() {
		return fee;
	}
	public void setFee(long fee) {
		this.fee = fee;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
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
	public long getHistid() {
		return histid;
	}
	public void setHistid(long histid) {
		this.histid = histid;
	}
	public String getOrgcd() {
		return orgcd;
	}
	public void setOrgcd(String orgcd) {
		if(orgcd == null) return;
		this.orgcd = orgcd;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		if(status == null) return;
		this.status = status;
	}
	public String getPrevacctnm() {
		return prevacctnm;
	}
	public void setPrevacctnm(String prevacctnm) {
		if(prevacctnm == null) return;
		this.prevacctnm = prevacctnm;
	}
	public String getInts() {
		return ints;
	}
	public void setInts(String ints) {
		if(ints == null) return;
		this.ints = ints;
	}
	public String getUpts() {
		return upts;
	}
	public void setUpts(String upts) {
		if(upts == null) return;
		this.upts = upts;
	}
	public String getRescd() {
		return rescd;
	}
	public void setRescd(String rescd) {
		if(rescd == null) return;
		this.rescd = rescd;
	}
	public String getResmsg() {
		return resmsg;
	}
	public void setResmsg(String resmsg) {
		if(resmsg == null) return;
		this.resmsg = resmsg;
	}
	public String getBankst() {
		return bankst;
	}
	public void setBankst(String bankst) {
		if(bankst == null) return;
		this.bankst = bankst;
	}

}
