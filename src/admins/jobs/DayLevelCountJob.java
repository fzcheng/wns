package admins.jobs;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class DayLevelCountJob  extends QuartzJobBean{

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		
	}

//    private OperationsCountService operationsCountService;
//    
//    public void setOperationsCountService(
//    	OperationsCountService operationsCountService) {
//        this.operationsCountService = operationsCountService;
//    }
//    
//    @Override
//    protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
//    	try{
//    		operationsCountService.dayLevelCount();
//    	}catch(Exception e){
//    		e.printStackTrace();
//    	};
//    	System.out.println("run DayLevelCountJob end :"+new Date().toString());
//    }
}
