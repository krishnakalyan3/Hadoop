package com.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

public class DG {
	public static void main(String[] args) throws IOException {
		Configuration config = HBaseConfiguration.create();
		config.addResource(new Path("/home/krishna/hbase-0.94.7/conf/hbase-site.xml"));
		//CreateTabel(config);
		
	}

	private static void CreateTabel(Configuration config) throws IOException {
		// TODO Auto-generated method stub
		HBaseAdmin admin = new HBaseAdmin(config);
		//name of table
		HTableDescriptor htd =new HTableDescriptor("test-from-java");
		//name of column Family
		HColumnDescriptor hcd = new HColumnDescriptor("data");
		
		htd.addFamily(hcd);
		//admin.createTable(htd);
		
		//Insert Values
		HTable table = new HTable(config, "test-from-java");
		for (int i=0 ; i<=15;i++)
		{
			Put put = new Put(Bytes.toBytes("rowkey"+i));
			put.add(Bytes.toBytes("data"), 
					Bytes.toBytes("cq1"),
					Bytes.toBytes("val1"+i));
			table.put(put);
		}
		
		

		
		
	}

}
