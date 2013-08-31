import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;


public class WCoMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	private Text key = new Text();
	private IntWritable one = new IntWritable(1);


	public void map(LongWritable inputKey,Text inputVal,Context context) throws IOException, InterruptedException{


		String line = inputVal.toString();
		String[] splits = line.trim().split("\\W+");
		//here we are not considering if the lines consist of single word only

		for(int i=0;i<splits.length-1;i++)
		{
			
			key.set(splits[i] +":"+splits[i+1]);
			
			System.out.println(key);
			context.write(key, one);
		}



	}

}
