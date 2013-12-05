import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;





public class JoinDriver {

        /**
         * @param args
         * @throws Exception 
         */
        public static void main(String[] args) throws Exception {

            // Finish indexing unique searches
            Job job = new Job(new Configuration(), "Reduce side join TextPair");
            job.setJarByClass(JoinDriver.class);

            MultipleInputs.addInputPath(job, new Path(args[0]), TextInputFormat.class, NameMapper.class);
            MultipleInputs.addInputPath(job, new Path(args[1]), TextInputFormat.class, LogMapper.class);

            
            job.setReducerClass(JoinReducer.class);
            
            job.setMapOutputKeyClass(TextPair.class);
            job.setMapOutputValueClass(Text.class);
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(Text.class);

            job.setPartitionerClass(JoinPartitioner.class);
            job.setGroupingComparatorClass(JoinComparator.class);
            
            job.setOutputFormatClass(TextOutputFormat.class);

            FileOutputFormat.setOutputPath(job, new Path(args[2]));

            job.waitForCompletion(true);

        }


}