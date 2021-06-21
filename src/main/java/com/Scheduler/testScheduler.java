package com.Scheduler;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class testScheduler extends QuartzJobBean{

	/*프로퍼티에 설정 하고 사용하는게 좋음
	 	# \ud50c\ub7ab\ud3fc\uad00\ub9ac\uc790 FINANCE
		globals.FIN_SCHEDULER_YN=N

		# \ud50c\ub7ab\ud3fc\uad00\ub9ac\uc790 FINANCE \uc2a4\ucf00\uc904\ub7ec IP
		globals.FIN_SCHEDULER_IP=127.0.0.1
	 */
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
	/*
    Finance0203Service finance0203Service = (Finance0203Service) context.getMergedJobDataMap().get("finance0203Service");
    Properties wehagoProperties = (Properties) context.getMergedJobDataMap().get("wehagoProperties");

    String FIN_GROUP_SCHEDULER_YN = wehagoProperties.getProperty("globals.FIN_SCHEDULER_YN");
	String FIN_GROUP_SCHEDULER_IP = wehagoProperties.getProperty("globals.FIN_SCHEDULER_IP");
    
    if (FIN_GROUP_SCHEDULER_YN == null || !"Y".equals(FIN_GROUP_SCHEDULER_YN)) return;

    try {
        if (FIN_GROUP_SCHEDULER_IP == null || getScheduleServerYn(FIN_GROUP_SCHEDULER_IP) == false) {
            logger.debug("###########################################################");
            logger.debug("Finance 스케쥴러가 돌지 않는 서버입니다.");
            logger.debug("###########################################################");
            return;
        }
    } catch (Exception e) {
        logger.debug( "###########################################################" );
        logger.debug( "Finance 스케쥴링을 위한 ip 체크중 에러 : " + e.getMessage() );
        logger.debug( "###########################################################" );
        return;
    }

    try {
        logger.info("========= FinanceWithdrawAgreeStatus 스케쥴러 시작");
        finance0203Service.updateWithdrawAgreeStatus(new HashMap<>());
        logger.info("========= FinanceWithdrawAgreeStatus 스케쥴러 종료");
    } catch (Exception e) {
        String errMsg = "############## [플랫폼관리자]청구수금서비스 스케줄러 error : ";

        if(e.getCause() != null) {
            errMsg = errMsg + e.getCause().getMessage();
        }
        else {
            errMsg = errMsg + e.getMessage();
        }
        logger.error(errMsg);
    }
*/
	}
	 /**
     * 스케쥴 서버 여부
     * @return
     */
    public boolean getScheduleServerYn(String chkIp){
        boolean result = false;
        try{
            Enumeration e = NetworkInterface.getNetworkInterfaces();
            while(e.hasMoreElements()){
                NetworkInterface n = (NetworkInterface) e.nextElement();
                Enumeration ee = n.getInetAddresses();
                while (ee.hasMoreElements()){
                    InetAddress i = (InetAddress) ee.nextElement();
                    if (i.getHostAddress().equals(chkIp)){
                        result = true;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            result = false;
        }
        return result;
    }
}
