

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class TRMapper extends Mapper<LongWritable, Text, Text, IntWritable>{

	@Override
	public void map(LongWritable inputKey,Text inputVal,Context context) throws IOException,InterruptedException
	{
		String line = inputVal.toString();
		String[] splits = line.split("\\W+");
		for(String outputKey:splits)
		{
			if(outputKey.length() > 0 )
			context.write(new Text(outputKey), new IntWritable(1));
		}


	}

}