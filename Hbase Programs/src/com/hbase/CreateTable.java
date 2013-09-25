package com.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;

public class CreateTable {
	public static void main(String[] args) throws IOException {
		Configuration conf= HBaseConfiguration.create();
		conf.addResource(new Path("/home/krishna/hbase-0.94.7/conf/hbase-site.xml"));
		HBaseAdmin admin=new HBaseAdmin(conf);
		//###### List Of Shell Commands
		//'List' Shows all Tables
		//'Status' for cluster health
		//'whoami'
		//describe 'TEST' details
		//disable 'TEST'
		//drop 'TEST'
		//scan 'TEST'
		HTableDescriptor desc = new HTableDescriptor("TEST");
		HColumnDescriptor meta1 = new HColumnDescriptor("cf1".getBytes());
		HColumnDescriptor meta2 = new HColumnDescriptor("cf2".getBytes());
		HColumnDescriptor meta3 = new HColumnDescriptor("cf3".getBytes());
		HColumnDescriptor meta4 = new HColumnDescriptor("cf4".getBytes());
		HColumnDescriptor meta5 = new HColumnDescriptor("cf5".getBytes());
		desc.addFamily(meta1);
		desc.addFamily(meta2);
		desc.addFamily(meta3);
		desc.addFamily(meta4);
		desc.addFamily(meta5);
		admin.createTable(desc);
		
		
		
	}

}
