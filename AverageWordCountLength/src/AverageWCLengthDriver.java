import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class AverageWCLengthDriver {
	
	public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException
	{
		Configuration config = new Configuration();
		config.addResource("/usr/local/hadoop/conf/core-site.xml");
		
		Job job = new Job(config,"Avg word Count lenght");
		job.setJarByClass(AverageWCLengthDriver.class);
		
		job.setMapperClass(AvgWCLengthMapper.class);
		job.setReducerClass(AvgWCLengthReducer.class);

		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		FileInputFormat.addInputPath(job,new Path("hdfs://192.168.240.129:54310/user/training/hadoop"));
		FileOutputFormat.setOutputPath(job, new Path("hdfs://192.168.240.129:54310/user/training/op1111"));
		
		
		 job.waitForCompletion(true);
		
		
		
		
	}

}
