package com.duzon.lulu.common.telegram;

import java.net.InetAddress;
import java.util.HashMap;

public class TelegramData {
	//헤더부 
	private String stnd_mesg_len;
	private String encd_cmpi_dvcd;		//???
	private String guid;
	private String mesg_dman_dvcd;
	private String mesg_ver_no;
	private String frst_trnm_ipad;
	private String frst_trnm_sys_cd;
	private String trnm_sys_cd;
	private String gnrz_tmot_tktm;
	private String tx_tmot_tktm;
	private String hedr_spr_ctnt;		//not used
	private String otsd_dman_sys_cd;
		
	//공통부 
	private String group_cmp_cd;		//???
	private String srvc_id;				//???
	private String procs_rslt_rcms_srvc_id;	//???
	private String if_id;			//fix KJB6000101
	private String tx_synz_dvcd;	//fix S
	private String hmab_dvcd;		//fix 2
	private String otsd_chnl_inst_cd;
	private String sys_oprt_env_dvcd;
	private String tx_typ_dvcd;			//???
	private String ortr_restr_yn;		//???
	private String ortr_guid;			//???
	
	private String err_cd;
	private String err_sys_cd;
	private String ortr_scn_id;			//???
	private String corp_dprm_no;		//???
	private String csif_iqry_rscd;		//???
	private String com_spr_ctnt;
	
	//데이터 헤더부
	String data_hedr_kncd;
	String data_hedr_len;
	
	//데이터부
	HashMap<String, Object> data;
	
	public TelegramData(String trade_id) throws Exception {
		InetAddress addr = null;
		addr = InetAddress.getLocalHost();
		String ip = addr.getHostAddress();
		
		stnd_mesg_len = "";
		encd_cmpi_dvcd = "0";
		guid = trade_id;
		mesg_dman_dvcd = "S";
		mesg_ver_no = "R10";
		
		frst_trnm_ipad = ip;
		frst_trnm_sys_cd = "ITR";
		trnm_sys_cd = "FEP";
		gnrz_tmot_tktm = "380";
		tx_tmot_tktm = "100";
		otsd_dman_sys_cd = "";
		
		group_cmp_cd = "";
		procs_rslt_rcms_srvc_id = "";
		tx_synz_dvcd = "S";
		hmab_dvcd = "2";
		otsd_chnl_inst_cd = "";
		sys_oprt_env_dvcd = "D";
		tx_typ_dvcd = "0";
		ortr_restr_yn = "N";
		ortr_guid = trade_id;
		
		err_cd = "";
		err_sys_cd = "";
		ortr_scn_id = "";
		corp_dprm_no = "";
		csif_iqry_rscd = "";
		com_spr_ctnt = "";
	}
	
	//전문 만들기 
	public HashMap<String, Object> makeDataMsg() {
		String result = null;
		HashMap<String, Object> STD = new HashMap<>();

		HashMap<String, Object> HEDR = new HashMap<>();
		HashMap<String, Object> COM = new HashMap<>();
		HashMap<String, Object> TX_SRVC_INFO = new HashMap<>();
		HashMap<String, Object> ERR_INFO = new HashMap<>();
		HashMap<String, Object> DATA = new HashMap<>();
		
		if ( data == null || data.size() <= 0 ) {
			return null;
		}
		
		//전문헤더 - 헤더부 
		//HEDR
		HEDR.put("STND_MESG_LEN",    stnd_mesg_len   );
		HEDR.put("ENCD_CMPI_DVCD",   encd_cmpi_dvcd  );
		HEDR.put("GUID",             guid            );
		HEDR.put("MESG_DMAN_DVCD",   mesg_dman_dvcd  );
		HEDR.put("MESG_VER_NO",      mesg_ver_no     );
		HEDR.put("FRST_TRNM_IPAD",   frst_trnm_ipad  );
		HEDR.put("FRST_TRNM_SYS_CD", frst_trnm_sys_cd);
		HEDR.put("TRNM_SYS_CD",      trnm_sys_cd     );
		HEDR.put("GNRZ_TMOT_TKTM",   gnrz_tmot_tktm  );
		HEDR.put("TX_TMOT_TKTM",     tx_tmot_tktm    );
		HEDR.put("OTSD_DMAN_SYS_CD", otsd_dman_sys_cd);
		
		//전문헤더 - 공통부
		//TX_SRVC_INFO
		TX_SRVC_INFO.put("GROUP_CMP_CD",            group_cmp_cd           );
		TX_SRVC_INFO.put("SRVC_ID",                 srvc_id                );
		TX_SRVC_INFO.put("PROCS_RSLT_RCMS_SRVC_ID", procs_rslt_rcms_srvc_id);
		TX_SRVC_INFO.put("IF_ID",                   if_id                  );
		TX_SRVC_INFO.put("TX_SYNZ_DVCD",            tx_synz_dvcd           );
		TX_SRVC_INFO.put("HMAB_DVCD",               hmab_dvcd              );
		TX_SRVC_INFO.put("OTSD_CHNL_INST_CD",       otsd_chnl_inst_cd      );
		TX_SRVC_INFO.put("SYS_OPRT_ENV_DVCD",       sys_oprt_env_dvcd      );
		TX_SRVC_INFO.put("TX_TYP_DVCD",             tx_typ_dvcd            );
		TX_SRVC_INFO.put("ORTR_RESTR_YN",           ortr_restr_yn          );
		TX_SRVC_INFO.put("ORTR_GUID",               ortr_guid              );
		
		//ERR_INFO
		ERR_INFO.put("ERR_CD",         err_cd        );
		ERR_INFO.put("ERR_SYS_CD",     err_sys_cd    );
		ERR_INFO.put("ORTR_SCN_ID",    ortr_scn_id   );
		ERR_INFO.put("CORP_DPRM_NO",   corp_dprm_no  );
		ERR_INFO.put("CSIF_IGRY_RSCD", csif_iqry_rscd);
		ERR_INFO.put("COM_SPR_CTNT",   com_spr_ctnt  );
		
		//전문바디 - 데이타부 
		STD.put("DATA", data);
		
		//메시지 조합
		STD.put("HEDR", HEDR);
		COM.put("TX_SRVC_INFO", TX_SRVC_INFO);
		COM.put("ERR_INFO", ERR_INFO);
		STD.put("COM", COM);
		
		HashMap<String, Object> STDMESSAGE = new HashMap<>();
		STDMESSAGE.put("STDMessage", STD);
		
		return STDMESSAGE;
	}

	//헤더부 
	public String getStndMesgLen() {
		return stnd_mesg_len;
	}
	public void setStndMesgLen(String stnd_mesg_len) {
		this.stnd_mesg_len = stnd_mesg_len;
	}
	public String getEncdCmpiDvcd() {
		return encd_cmpi_dvcd;
	}
	public void setEncdCmpiDvcd(String encd_cmpi_dvcd) {
		this.encd_cmpi_dvcd = encd_cmpi_dvcd;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getMesgDmanDvcd() {
		return mesg_dman_dvcd;
	}
	public void setMesgDmanDvcd(String mesg_dman_dvcd) {
		this.mesg_dman_dvcd = mesg_dman_dvcd;
	}
	public String getMesgVerNo() {
		return mesg_ver_no;
	}
	public void setMesgVerNo(String mesg_ver_no) {
		this.mesg_ver_no = mesg_ver_no;
	}
	public String getFrstTrnmIpad() {
		return frst_trnm_ipad;
	}
	public void setFrstTrnmIpad(String frst_trnm_ipad) {
		this.frst_trnm_ipad = frst_trnm_ipad;
	}
	public String getFrstTrnmSysCd() {
		return frst_trnm_sys_cd;
	}
	public void setFrstTrnmSysCd(String frst_trnm_sys_cd) {
		this.frst_trnm_sys_cd = frst_trnm_sys_cd;
	}
	public String getTrnmSysCd() {
		return trnm_sys_cd;
	}
	public void setTrnmSysCd(String trnm_sys_cd) {
		this.trnm_sys_cd = trnm_sys_cd;
	}
	public String getGnrzTmotTktm() {
		return gnrz_tmot_tktm;
	}
	public void setGnrzTmotTktm(String gnrz_tmot_tktm) {
		this.gnrz_tmot_tktm = gnrz_tmot_tktm;
	}
	public String getTxTmotTktm() {
		return tx_tmot_tktm;
	}
	public void setTxTmotTktm(String tx_tmot_tktm) {
		this.tx_tmot_tktm = tx_tmot_tktm;
	}
	public String getHedrSprCtnt() {
		return hedr_spr_ctnt;
	}
	public void setHedrSprCtnt(String hedr_spr_ctnt) {
		this.hedr_spr_ctnt = hedr_spr_ctnt;
	}
	public String getOtsdDmanSysCd() {
		return otsd_dman_sys_cd;
	}
	public void setOtsdDmanSysCd(String otsd_dman_sys_cd) {
		this.otsd_dman_sys_cd = otsd_dman_sys_cd;
	}
	
	//공통부
	public String getGroupCmpCd() {
		return group_cmp_cd;
	}
	public void setGroupCmdCd(String group_cmp_cd) {
		this.group_cmp_cd = group_cmp_cd;
	}
	public String getSrvcId() {
		return srvc_id;
	}
	public void setSrvcId(String srvc_id) {
		this.srvc_id = srvc_id;
	}
	public String getProcsRsltRcmsSrvcId() {
		return procs_rslt_rcms_srvc_id;
	}
	public void setProcsRsltRcmsSrvcId(String procs_rslt_rcms_srvc_id) {
		this.procs_rslt_rcms_srvc_id = procs_rslt_rcms_srvc_id;
	}
	public String getIfId() {
		return if_id;
	}
	public void setIfId(String if_id) {
		this.if_id = if_id;
	}
	public String gretTxSynzDvcd() {
		return tx_synz_dvcd;
	}
	public void setTxSynzDvcd(String tx_synz_dvcd) {
		this.tx_synz_dvcd = tx_synz_dvcd;
	}
	public String getHmabDvcd() {
		return hmab_dvcd;
	}
	public void setHmabDvcd(String hmab_dvcd) {
		this.hmab_dvcd = hmab_dvcd;
	}
	public String getOtsdChnlInstCd() {
		return otsd_chnl_inst_cd;
	}
	public void setOtsdChnInstCd(String otsd_chnl_inst_cd) {
		this.otsd_chnl_inst_cd = otsd_chnl_inst_cd;
	}
	public String getSysOprtEnvDvcd() {
		return sys_oprt_env_dvcd;
	}
	public void setSysOprtEnvDvcd(String sys_oprt_env_dvcd) {
		this.sys_oprt_env_dvcd = sys_oprt_env_dvcd;
	}
	public String getTxTypDvcd() {
		return tx_typ_dvcd;
	}
	public void setTxTypDvcd(String tx_typ_dvcd) {
		this.tx_typ_dvcd = tx_typ_dvcd;
	}
	public String getOrtrRestrYn() {
		return ortr_restr_yn;
	}
	public void setOrtrRestrYn(String ortr_restr_yn) {
		this.ortr_restr_yn = ortr_restr_yn;
	}
	public String getOrtrGuid() {
		return ortr_guid;
	}
	public void setOrtrGuid(String ortr_guid) {
		this.ortr_guid = ortr_guid;
	}
	public String getErrCd() {
		return err_cd;
	}
	public void setErrCd(String err_cd) {
		this.err_cd = err_cd;
	}
	public String getErrSysCd() {
		return err_sys_cd;
	}
	public void setErrSysCd(String err_sys_cd) {
		this.err_sys_cd = err_sys_cd;
	}
	public String getOrtrScnId() {
		return ortr_scn_id;
	}
	public void setOrtrScnId(String ortr_scn_id) {
		this.ortr_scn_id = ortr_scn_id;
	}
	public String getCorpDprmNo() {
		return corp_dprm_no;
	}
	public void setCorpDprmNo(String corp_dprm_no) {
		this.corp_dprm_no = corp_dprm_no;
	}
	public String getCsifIqryRscd() {
		return csif_iqry_rscd;
	}
	public void setCsifIqryRscd(String csif_iqry_rscd) {
		this.csif_iqry_rscd = csif_iqry_rscd;
	}
	public String getComSprCtnt() {
		return com_spr_ctnt;
	}
	public void setComSprCtnt(String com_spr_ctnt) {
		this.com_spr_ctnt = com_spr_ctnt;
	}
	
	//데이터 헤더부 
	public String getDataHedrKncd() {
		return data_hedr_kncd;
	}
	public void setDataHedrKncd(String data_hedr_kncd) {
		this.data_hedr_kncd = data_hedr_kncd;
	}
	public String getDataHedrLen() {
		return data_hedr_len;
	}
	public void setDataHedrLen(String data_hedr_len) {
		this.data_hedr_len = data_hedr_len;
	}
	
	//데이터부 
	public HashMap<String, Object> getData(){
		return data;
	}
	public void setData(HashMap<String, Object> data) {
		this.data = data;
	}
	
}
