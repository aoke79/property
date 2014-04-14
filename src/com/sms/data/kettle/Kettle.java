package com.sms.data.kettle;


import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.logging.LogWriter;
import org.pentaho.di.core.util.EnvUtil;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobEntryLoader;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.repository.Repository;
import org.pentaho.di.repository.RepositoryDirectory;
import org.pentaho.di.repository.RepositoryMeta;
import org.pentaho.di.repository.UserInfo;
import org.pentaho.di.trans.StepLoader;

public class Kettle {

	private String dbname = KettleConstants.DBNAME;
	private String ip = KettleConstants.IP;
	private String user = KettleConstants.USER;
	private String password = KettleConstants.PASSWORD;

	/**
	 * 调用 kettle 的 JOB
	 * @param jobname
	 * @throws KettleException
	 */
	public void executeJobs(String jobname) throws KettleException {

		// kettle初始化
		EnvUtil.environmentInit();
		JobEntryLoader.init();
		StepLoader.init();

		// kettle日志
		LogWriter log = LogWriter.getInstance("TransTest.log", true,
				LogWriter.LOG_LEVEL_DETAILED);

		// kettle用户
		UserInfo userInfo = new UserInfo();
		userInfo.setLogin("admin");
		userInfo.setPassword("admin");

		// 数据库连接元对象
		DatabaseMeta connection = new DatabaseMeta(dbname, "Oracle", "Native",
				ip, dbname, "1521", user, password);

		// 资源库元对象
		RepositoryMeta repinfo = new RepositoryMeta();
		repinfo.setConnection(connection);

		// 资源库
		Repository rep = new Repository(log, repinfo, userInfo);

		// 连接资源库
		rep.connect("");

		// 资源库目录对象
		RepositoryDirectory dir = new RepositoryDirectory(rep);

		// 步骤加载对象
		StepLoader steploader = StepLoader.getInstance();

		// job元对象
		JobMeta jobmeta = new JobMeta(log, rep, jobname, dir);

		// 创建job
		Job job = new Job(log, steploader, rep, jobmeta);

		// 执行job
		job.execute();

		// 等待job执行结束
		job.waitUntilFinished();

	}

	

	public static void main(String[] args) {
		System.out.println("~~~~~~~~~start----------------------------");

		try {
			new Kettle().executeJobs("qualification_t_trainingtype");
		} catch (KettleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("~~~~~~~~~end------------------------------");
	}

}
