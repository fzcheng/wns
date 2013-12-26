package admins.jobs;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class AllLevelCountJob extends QuartzJobBean{

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		
	}

//    private OperationsCountService operationsCountService;
//    
//    public void setOperationsCountService(OperationsCountService operationsCountService) {
//        this.operationsCountService = operationsCountService;
//    }
//    
//    @Override
//    protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
//    	System.out.println("run AllLevelCountJob begin at:"+new Date().toString());
//    	try{
//    		operationsCountService.allLevelCount();
//    	}catch(Exception e){
//    		e.printStackTrace();
//    	}
//    	System.out.println("run AllLevelCountJob end:"+new Date().toString());
//    }
}
