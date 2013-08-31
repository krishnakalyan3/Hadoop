import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;


public class PPartitoner extends Partitioner<Text,IntWritable> {

	@Override
	public int getPartition(Text key, IntWritable val, int reducetask) {
		// TODO Auto-generated method stub
		String type=key.toString();
		int reduce=0;
		if(type.startsWith("V")){
			return reduce=0;
		} 
		if(type.startsWith("C")){
			return reduce=1%reducetask;
		}
		if(type.startsWith("O")){
			return reduce=2%reducetask;
		}
		return reduce; 


	}

}
