import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class WCDriver {
	public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {


		long starttime = System.currentTimeMillis();

		//Add these Files to run the job on your Cluster
		Configuration co=new Configuration();
		co.addResource(new Path("/home/krishna/hadoop-1.0.3/conf/core-site.xml"));

		//JOB Configuration
		Job job = new Job(co, "WordCountInFile Job");

		

		job.setJarByClass(WCDriver.class);
		job.setMapperClass(WCMapper.class);
		job.setReducerClass(WCReducer.class);


		// TODO: specify output types
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);

		//Output of Reducer
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		//File to be read
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));


		System.out.println("Job Finished in :"+(System.currentTimeMillis()-starttime)/1000+" sec");
		job.waitForCompletion(true);

	}
}
