package com.hbase;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.sql.BatchUpdateException;


public class Hbase {

	/**
	 * @param args
	 * @throws IOException
	 */



	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub	
		Configuration conf= HBaseConfiguration.create();
		//Add New resources
		conf.addResource(new Path("/home/krishna/hbase-0.94.7/conf/hbase-site.xml"));
		HBaseAdmin admin = new HBaseAdmin(conf);

		//#### Creating Tables
		//HTable ht=new HTable(conf1, "Latest");

		/*HTableDescriptor desc = new HTableDescriptor("TEST");
		HColumnDescriptor meta = new HColumnDescriptor("personal".getBytes());
		HColumnDescriptor prefix = new HColumnDescriptor("account".getBytes());
		desc.addFamily(meta);
		desc.addFamily(prefix);
		admin.createTable(desc);*/

		//System.out.println(admin.listTables());
		//admin.disableTable("k");
		//admin.deleteTable("k");

		HTableDescriptor desc = new HTableDescriptor("New1");
		/*HColumnDescriptor meta = new HColumnDescriptor("personal".getBytes());
		HColumnDescriptor prefix = new HColumnDescriptor("account".getBytes());
		HColumnDescriptor name = new HColumnDescriptor("name".getBytes());
		HColumnDescriptor place = new HColumnDescriptor("place".getBytes());
		HColumnDescriptor thing  = new HColumnDescriptor("thing".getBytes());
		HColumnDescriptor animal = new HColumnDescriptor("animal".getBytes());
		desc.addFamily(meta);
		desc.addFamily(name);
		desc.addFamily(prefix);
		desc.addFamily(place);
		desc.addFamily(thing);
		desc.addFamily(animal);
		admin.createTable(desc);*/

		HTable table = new HTable(conf, "New1");
		Put put = new Put(Bytes.toBytes("1"));
		put.add(Bytes.toBytes("personal"), 
				Bytes.toBytes("qual1"),
				Bytes.toBytes("val1"));
		table.put(put);


	}


}
