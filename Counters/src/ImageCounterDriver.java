import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.KeyValueTextInputFormat;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.RunningJob;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;


public class ImageCounterDriver extends Configured implements Tool{
	
	public static void main(String[] args) throws Exception
	{
		ToolRunner.run(new ImageCounterDriver(), args);
	}

	@Override
	public int run(String[] args) throws Exception {
		Configuration config = getConf();
		JobConf conf = new JobConf(config,ImageCounterDriver.class);
		conf.setJobName("Using Counters");
		
		
		conf.setInputFormat(KeyValueTextInputFormat.class);
		conf.set("key.value.separator.in.input.line"," ");
		
		conf.setMapperClass(ImageCounterMapper.class);
		
		conf.setMapOutputKeyClass(Text.class);
		conf.setMapOutputValueClass(IntWritable.class);
		
		conf.setNumReduceTasks(0);
		FileInputFormat.addInputPath(conf, new Path(args[0]));
		FileOutputFormat.setOutputPath(conf, new Path(args[1]));
		
		RunningJob job = JobClient.runJob(conf);
		
		long jpgCounter = job.getCounters().findCounter("ImageCounter.type", "JPG").getValue();
		long gifCounter = job.getCounters().findCounter("ImageCounter.type","GIF").getValue();
		long otherCounters = job.getCounters().findCounter("ImageCounter.type","OTHERS").getValue();
		
		
		
		return 0;
	}

}
