package admins.jobs;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 消费统计
 * @author LuZhiYong
 * @Date 2012-6-12
 */
public class OperationsCountConsumeJob extends QuartzJobBean {

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		
	}

//	private OperationsCountService operationsCountService;
//	
//	public void setOperationsCountService(OperationsCountService operationsCountService) {
//		this.operationsCountService = operationsCountService;
//	}
//	
//	@Override
//	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
//		try {
//			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			operationsCountService.OperationsCountConsume();
//			System.out.println("OperationsCountConsume JOB end at " + f.format(System.currentTimeMillis())); 
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
