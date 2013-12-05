import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class NameMapper extends Mapper<LongWritable, Text, TextPair, Text> {

	private String[] tokens = null;

	@Override
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {

		if (value != null) {
			tokens = value.toString().split("\\s+");
			
			context.write(new TextPair(tokens[0], "0"), new Text(tokens[1]));

		}
	}

}