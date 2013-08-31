import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class WCoDriver {
	
	
	public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {


		long starttime = System.currentTimeMillis();

		//Add these Files to run the job on your Cluster
		Configuration co=new Configuration();
		co.addResource(new Path("/home/krishna/hadoop-1.0.3/conf/core-site.xml"));

		//JOB Configuration
		Job job = new Job(co, "Word Co-Occurence");

		

		job.setJarByClass(WCoDriver.class);
		job.setMapperClass(WCoMapper.class);
		job.setReducerClass(WCoReducer.class);


		// TODO: specify output types
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);

		//Output of Reducer
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		//File to be read
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.waitForCompletion(true);
		System.out.println("Job Finished in :"+(System.currentTimeMillis()-starttime)/1000+" sec");

	}

}
