import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;


public class WCDriver extends Configured implements Tool {

	
	public static void main(String[] args) throws Exception {
		ToolRunner.run(new WCDriver(),args);
	}
	
	
	@Override
	public int run(String[] arg) throws Exception {
		
		Configuration config = new Configuration();
		config.addResource(new Path("/home/krishna/hadoop-1.2.1/conf/core-site.xml"));
		
		Job job = new Job(config,"Unit Test");
		job.setJarByClass(WCDriver.class);
		job.setMapperClass(WCMapper.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		
		FileInputFormat.addInputPath(job, new Path(arg[0]));
		FileOutputFormat.setOutputPath(job, new Path(arg[1]));

		job.waitForCompletion(true);



		return 0;
	}

}
