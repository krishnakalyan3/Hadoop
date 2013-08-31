import java.io.IOException;

import org.apache.hadoop.io.*;

import org.apache.hadoop.mapreduce.Reducer;


public class WCoReducer extends Reducer<Text,IntWritable,Text, IntWritable> {
	private IntWritable finalSum = new IntWritable();

	public void reduce(Text key,Iterable<IntWritable> listOfValues,Context context) throws IOException,InterruptedException
	{
		int sum=0;
		for(IntWritable val:listOfValues)
		{
			sum = sum + val.get();
		}
		finalSum.set(sum);
		context.write(key,finalSum);


	}
}
