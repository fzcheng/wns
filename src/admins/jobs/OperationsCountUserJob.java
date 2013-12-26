package admins.jobs;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 流失用户及流失率统计
 * 
 * @author Administrator
 *
 */
public class OperationsCountUserJob extends QuartzJobBean {

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		
	}

//    private OperationsCountService operationsCountService;
//    
//    public void setOperationsCountService(OperationsCountService operationsCountService) {
//		this.operationsCountService = operationsCountService;
//	}
//    
//    @Override
//    protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
//    	try {
//    		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		    operationsCountService.OperationsCountUser(null);
//		    System.out.println("OperationsCountUserJob JOB end at " + f.format(System.currentTimeMillis())); 
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//    }
}
