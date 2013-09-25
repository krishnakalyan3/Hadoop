package com.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.rest.Main;
import org.apache.hadoop.hbase.util.Bytes;

public class Hbasescan {
	public static void main(String[] args) throws IOException {
		Configuration config = HBaseConfiguration.create();
		config.addResource(new Path("/home/krishna/hbase-0.94.7/conf/hbase-site.xml"));
		byte [] rowKey = Bytes.toBytes("rowkey13");
		Get g=new Get(rowKey);
		HTable table = new HTable(config, "test-from-java");
		Result res=table.get(g);
		System.out.println("result of rowkey is"+res);
		Scan scan=new Scan();
		ResultScanner scanner=table.getScanner(scan);
	
		for(Result sca:scanner){
		System.out.println(sca);	
		}
	}

}
