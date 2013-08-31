import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class SSDriver extends Configured implements Tool {

	public static void main(String[] args) throws Exception {
		ToolRunner.run(new SSDriver(), args);
	}

	@Override
	public int run(String[] args) throws Exception {

		Configuration config = new Configuration();
		config.addResource(new Path("/usr/local/hadoop/conf/core-site.xml"));

		Job job = new Job(config, "Distributed Cache");
		job.setJarByClass(SSDriver.class);
		job.setMapperClass(SSMapper.class);

		job.setNumReduceTasks(0);
		job.setMapOutputKeyClass(Employee.class);
		job.setMapOutputValueClass(Text.class);
		
	

		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		job.waitForCompletion(true);

		return 0;
	}

}
