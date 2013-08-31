import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Mapper;


public class AvgWCLengthMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	
	@Override
	public void map(LongWritable key,Text value,Context context) throws IOException, InterruptedException
	{
		String inputString = value.toString();
		String[] splits = inputString.split("\\W+");
		
		for(String str : splits)
		{
			if(str.length()>0)
			{
			String ch = str.substring(0,1);
			int length = str.length();
			context.write(new Text(ch), new IntWritable(length));
			//InputSplit is = (InputSplit) context.getInputSplit();
			
			}
		}
		
		
	}

}
