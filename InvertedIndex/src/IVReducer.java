import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class IVReducer extends Reducer<Text, Text, Text, Text> {
	private Text fileList = new Text();

	public void reduce(Text key,Iterable<Text> values,Context context) throws IOException, InterruptedException{
		boolean first = true;
		StringBuilder toReturn = new StringBuilder();
		Iterator valueItr = values.iterator();
		while (valueItr.hasNext()){
			if (!first)
				toReturn.append(", ");
			first=false;
			toReturn.append(valueItr.next().toString());
		}

		context.write(key, new Text(toReturn.toString()));
	}

}


