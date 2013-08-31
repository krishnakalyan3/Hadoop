import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;


public class ImageCounterMapper extends MapReduceBase implements Mapper<Text, Text, Text, IntWritable>{

	@Override
	public void map(Text inputKey, Text inputVal,
			OutputCollector<Text, IntWritable> output, Reporter reporter)
			throws IOException {
		
		String vals = inputVal.toString();
		
		if(vals.contains(".jpg"))
		{
			reporter.incrCounter(ImageCounter.type.JPG, 1);
			//reporter.getCounter(ImageCounter.type.JPG).increment(1);
		}
		else if(vals.contains(".gif"))
		{
			reporter.incrCounter(ImageCounter.type.GIF, 1);
			//reporter.getCounter(ImageCounter.type.GIF).increment(1);
		}
		else
		{
			reporter.incrCounter(ImageCounter.type.OTHERS, 1);
		}
		
		output.collect(inputKey, new IntWritable(1));
	}

}
