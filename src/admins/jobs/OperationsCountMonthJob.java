package admins.jobs;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import admins.web.service.IPayTotalService;

/**
 * 运营月统计
 * @author LuZhiYong
 * @Date 2012-6-12
 */
public class OperationsCountMonthJob extends QuartzJobBean{


	private IPayTotalService payTotalService;
	
	public void setPayTotalService(IPayTotalService payTotalService) {
			this.payTotalService = payTotalService;
		}
     
     @Override
     protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
 		try {
 		    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 		    payTotalService.Gamepay_month(null);
 		    
 		    System.out.println("payTotalService JOB end at " + f.format(new Date()));
 		} catch (Exception e) {
 		    System.out.println(e);
 		}
    }
	
}
