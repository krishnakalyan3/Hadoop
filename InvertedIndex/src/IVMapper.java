import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;


public class IVMapper extends Mapper<LongWritable, Text, Text, Text> {
	
	
	private Text keyWord = new Text();
	private Text file = new Text();
	
	@Override
	public void map(LongWritable key,Text value,Context context) throws IOException, InterruptedException  {
		// TODO Auto-generated method stub
		String inputString = value.toString();
		String[] splits = inputString.trim().split("\\W+");
		
		Path fileName = ((FileSplit)context.getInputSplit()).getPath();
		file.set(fileName.getName());

		for(String words : splits)
		{
			keyWord.set(words);
			context.write(keyWord,file);

		}
		
	
	}

}
