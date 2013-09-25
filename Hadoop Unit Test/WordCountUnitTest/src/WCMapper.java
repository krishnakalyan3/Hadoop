import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WCMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	@Override
	public void map(LongWritable key, Text val, Context context)
			throws IOException, InterruptedException {
		String[] words = val.toString().split("\\W+");

		for (String word : words) {

			if (!word.isEmpty() && word.length() > 0) {
				context.write(new Text(word), new IntWritable(1));
			}
		}

	}

}
