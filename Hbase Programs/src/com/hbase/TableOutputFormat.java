package com.hbase;


import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Map.Entry;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.util.*;
import org.apache.hadoop.util.ToolRunner;


public class TableOutputFormat extends Configured implements Tool {
    
    public static class HbaseLoadMapper extends Mapper<LongWritable, Text, ImmutableBytesWritable, Put> {
        
        private long checkpoint = 100;
        private long count = 0;
        
        public void map(LongWritable key, Text line, Context context) throws IOException {
            String[] fields = line.toString().split(" ");
            if(fields.length != 4) {
                return;
            }
      
            // Extract each value
            byte [] row = Bytes.toBytes(fields[0]);
            byte [] family = Bytes.toBytes(fields[1]);
            byte [] qualifier = Bytes.toBytes(fields[2]);
            byte [] value = Bytes.toBytes(fields[3]);
      
            // Create Put
            Put put = new Put(row);
            put.add(family, qualifier, value);
      
            // Uncomment below to disable WAL. This will improve performance but means
            // you will experience data loss in the case of a RegionServer crash.
            // put.setWriteToWAL(false);
      
            try {
                context.write(new ImmutableBytesWritable(row), put);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
      
            // Set status every checkpoint lines
            if(++count % checkpoint == 0) {
                context.setStatus("Emitting Put " + count);
            }
        }
    }
        
    public int run(String[] args) throws Exception {

        Job job  = new Job(getConf());
        
        // Set job class and job name
        job.setJarByClass(TableOutputFormat.class);
        job.setJobName("TableOutputFormat");

        // Set mapper class and reducer class
        job.setMapperClass(HbaseLoadMapper.class);
        job.setNumReduceTasks(0);

        // Hbase specific setup
        Configuration conf = HBaseConfiguration.create();
        conf.addResource(new Path("/Users/vmehra/softwares/hbase-0.90.6-cdh3u6/conf/hbase-site.xml"));
        //Configuration conf = job.getConfiguration();
        TableMapReduceUtil.initTableReducerJob(args[1], null, job);
        //

        // Handle input path
        List<String> other_args = new ArrayList<String>();
        for (int i=0; i < args.length; ++i) {
            other_args.add(args[i]);
        }
        FileInputFormat.setInputPaths(job, new Path(other_args.get(0)));

        // Submit job to server and wait for completion
        job.waitForCompletion(true);
        return 0;
    }

    public static void main(String[] args) throws Exception {
        int res = ToolRunner.run(HBaseConfiguration.create(), new TableOutputFormat(), args);
        System.exit(res);
    }
}

