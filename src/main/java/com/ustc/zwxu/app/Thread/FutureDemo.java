package com.ustc.zwxu.app.Thread;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.springframework.util.Assert;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class FutureDemo {
	
	protected ThreadPoolTaskExecutor threadPoolTaskExecutor;
	
	private Map<String,Asset> assetMap;

	public ThreadPoolTaskExecutor getThreadPoolTaskExecutor() {
		return threadPoolTaskExecutor;
	}


	public void setThreadPoolTaskExecutor(ThreadPoolTaskExecutor threadPoolTaskExecutor) {
		this.threadPoolTaskExecutor = threadPoolTaskExecutor;
	}



	public Map<String, Asset> getAssetMap() {
		return assetMap;
	}



	public void setAssetMap(Map<String, Asset> assetMap) {
		this.assetMap = assetMap;
	}
	
	public int queryAll()
	{
		Integer total = 0;
		Map<String,Integer> resultMap = new HashMap<String,Integer>();
		Map<String,Future<Integer>> futureMap = new HashMap<String,Future<Integer>>();
		for(final String key:assetMap.keySet())
		{
			/*threadPoolTaskExecutor.execute(new Runnable(){

				public void run() {
					// TODO Auto-generated method stub
					
				}
				
			});*/
			Future<Integer> future = threadPoolTaskExecutor.submit(new Callable<Integer>(){

				public Integer call() throws Exception {
					Asset asset = assetMap.get(key);
					return asset.query();
				}
				
			});
			futureMap.put(key, future);
		}
		
		for(final String key:assetMap.keySet())
		{
			try {
				resultMap.put(key, futureMap.get(key).get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for(final String key:resultMap.keySet())
		{
			Integer amount = resultMap.get(key);
			total+=amount;
		}
		return total;
	}

	public void query(String memberId)
	{
		//Assert.notNull(memberId, "memberId is null");
		try
		{
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext app =new ClassPathXmlApplicationContext("classpath*:spring/*.xml");  
		FutureDemo processor = (FutureDemo)app.getBean("demo");
		
	
		
		processor.query(null);
	}

}


