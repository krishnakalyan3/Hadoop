
import java.io.IOException;

import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;
import org.apache.hadoop.util.bloom.Key;





public class JoinReducer extends  Reducer<TextPair, Text, Text, Text> {

        private Text NAME = new Text();
    
        @Override
        protected void reduce(TextPair key, Iterable<Text> values, Context context)
                        throws IOException, InterruptedException {

                Iterator<Text> it = values.iterator();

                NAME.set(it.next());
                
              
                
                for(;it.hasNext();){
                        context.write(NAME, it.next());
                }
                
        }

        
    
}