import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class PMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	Text word = new Text();

	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		String line = value.toString();
		StringTokenizer token = new StringTokenizer(line);

		while (token.hasMoreElements()) {
			word.set(token.nextToken().substring(0, 1));
			switch (word.toString()) {
			case "a":
			case "e":
			case "i":
			case "o":
			case "u":
				context.write(new Text("Vowel"), new IntWritable(1));
				break;
			case ".":
				context.write(new Text("Others"), new IntWritable(1));
				break;
			default:
				context.write(new Text("Consonant"), new IntWritable(1));
				break;

			}

		}

	}

}
