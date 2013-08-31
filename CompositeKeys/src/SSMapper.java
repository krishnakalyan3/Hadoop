import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SSMapper extends Mapper<LongWritable, Text, Employee, Text> {

	@Override
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		
		String[] split = value.toString().split(",");
		String first = split[0].trim();
		String last = split[1].trim();
		System.out.println(first+"  "+last);
		Employee emp = new Employee(first, last);
		if (split.length > 0) {
			context.write(emp, new Text(last));
		}
	}

}
