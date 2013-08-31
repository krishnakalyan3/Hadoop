import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class AvgWCLengthReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
	
	@Override
	public void reduce(Text key,Iterable<IntWritable> values,Context context) throws IOException, InterruptedException
	{
		int sum = 0;
		int count = 0;
		for(IntWritable val:values)
		{
			sum = sum + val.get();
			count++;
		}
		int avgLength = sum/count;
		context.write(key, new IntWritable(avgLength));
	}

}
