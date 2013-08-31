import java.io.IOException;

import org.apache.hadoop.mapreduce.Mapper;




import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class LRReducer extends Reducer<Text,IntWritable,Text, IntWritable>{

	@Override
	public void reduce(Text key,Iterable<IntWritable> listOfValues,Context context) throws IOException,InterruptedException
	{
		int sum=0;
		for(IntWritable val:listOfValues)
		{
			sum = sum + val.get();
		}
		context.write(key,new IntWritable(sum));
	}

}