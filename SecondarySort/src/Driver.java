import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;


public class Driver extends Configured implements Tool {

	public static void main(String[] args) throws Exception {
		ToolRunner.run(new Driver(), args);
	}
	
	@Override
	public int run(String[] arg0) throws Exception {
		// TODO Auto-generated method stub
		Configuration config = new Configuration();
		config.addResource(new Path("/home/krishna/hadoop-1.0.3/conf/core-site.xml"));
		
		Job job=new Job(config,"Secondary Sort");
		job.setJarByClass(Driver.class);
		job.setMapperClass(SSMapper.class);
		
		job.setMapOutputKeyClass(Employee.class);
		job.setMapOutputValueClass(IntWritable.class);
		//job.setNumReduceTasks(0);
		job.setPartitionerClass(SSPartitioner.class);
		
		FileInputFormat.addInputPath(job, new Path(arg0[0]));
		FileOutputFormat.setOutputPath(job, new Path(arg0[1]));

		job.waitForCompletion(true);
		
		return 0;
	}

}
