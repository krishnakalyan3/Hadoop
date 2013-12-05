import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class LogMapper extends Mapper<LongWritable, Text, TextPair, Text> {

	private String[] tokens = null;

	@Override
	protected void map(LongWritable key, Text value,
			org.apache.hadoop.mapreduce.Mapper.Context context)
			throws IOException, InterruptedException {
		
		if(value!=null){
            tokens = value.toString().split("\\s+") ;
            
            context.write(new TextPair(tokens[0], "1"), new Text(tokens[1]));
                            
    }
		

	}

}
