package com.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;

import com.sun.java_cup.internal.runtime.Scanner;

public class InsertValues {
	public static void main(String[] args) throws IOException {

		Configuration conf = HBaseConfiguration.create();
		conf.addResource(new Path("/home/krishna/hbase-0.94.7/conf/hbase-site.xml"));
		HBaseAdmin admin = new HBaseAdmin(conf);
		HTable table = new HTable(conf, "TEST");

		Put put = new Put(Bytes.toBytes("rowkey1"));//Row -Key
		put.add(Bytes.toBytes("cf1"), Bytes.toBytes("column7"), Bytes.toBytes("value 1177"));//Column Family-Column-Value
		table.put(put);																		 //Column Families, and Column Qualifiers-Values
		
	
		//Retrieve values from row keys
		byte [] rowKey = Bytes.toBytes("rowkey1");
		 Get get1 = new Get(rowKey);
		//get1.addFamily("cf3".getBytes());
		Result res =table.get(get1);
		System.out.println("###SCan"+table.get(get1));
		
		System.out.println("###########S#######################");
		//SCAN
		Scan s = new Scan();
		ResultScanner scanner = table.getScanner(s);
		for(Result rr= scanner.next(); rr!= null; rr=scanner.next())
		{
			//System.out.println("Row:"+ rr.list());
			System.out.println("Row:"+ rr.list());
			
			
		}
				

	}
}
