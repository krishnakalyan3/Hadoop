import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;


public class SSPartitioner extends Partitioner<Employee, IntWritable> {

	@Override
	public int getPartition(Employee emp, IntWritable arg1, int numOfReducer) {
		// TODO Auto-generated method stub
		return (emp.getEmpName().hashCode() & Integer.MAX_VALUE ) % numOfReducer;
	}

}
