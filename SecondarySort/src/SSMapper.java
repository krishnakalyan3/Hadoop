import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SSMapper extends Mapper<LongWritable, Text, Employee, IntWritable> {
	Employee emp = new Employee();

	@Override
	public void map(LongWritable key, Text val, Context context)
			throws IOException, InterruptedException {

		String[] values = val.toString().split(",");

		if (values.length == 2) {
			System.out.println(values[0] + "  " + values[1]);
			String name = values[0].trim();
			Integer id = Integer.parseInt(values[1].trim());
			emp.setEmpID(id);
			emp.setEmpName(name);
			context.write(emp, new IntWritable(id));
		}
		

	}

}
