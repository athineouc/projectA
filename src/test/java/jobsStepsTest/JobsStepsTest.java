package jobsStepsTest;

import batchTestConfiguration.BatchTestConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import projectA.batchConfiguration.CustomBatchConfiguration;

import static filesNameConstants.FilesNameConstants.SMALL_FILE;
import static org.junit.Assert.assertEquals;
import static projectA.constants.Constants.STEP_BUILDER;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes= {CustomBatchConfiguration.class, BatchTestConfiguration.class} )
public class JobsStepsTest {

    @Autowired
    JobLauncherTestUtils jobLauncherTestUtils;

    public static final String  PARAM_NAME = "fileName";


    @Test
    public void test_job_Execution() throws Exception {

        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        jobParametersBuilder.addString(PARAM_NAME, SMALL_FILE);

        JobExecution execution = jobLauncherTestUtils.launchJob(jobParametersBuilder.toJobParameters());

        assertEquals(ExitStatus.COMPLETED, execution.getExitStatus());
    }

    @Test
    public void test_step_Execution() throws Exception {

        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        jobParametersBuilder.addString(PARAM_NAME, SMALL_FILE);

        JobExecution execution = jobLauncherTestUtils.launchStep(STEP_BUILDER,jobParametersBuilder.toJobParameters());

        assertEquals(ExitStatus.COMPLETED, execution.getExitStatus());
    }

}
