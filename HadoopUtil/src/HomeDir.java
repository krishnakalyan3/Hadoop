import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HomeDir {
	public static void main(String[] args) throws IOException {
		Configuration configuration = new Configuration();

		configuration.addResource(new Path("/home/krishna/hadoop-1.2.1/conf/core-site.xml"));

		FileSystem fs = FileSystem.get(configuration);

		Path homeDir = fs.getHomeDirectory();

		System.out.println("My HDFS Home Directory is:" + homeDir);

		// HW
		// Delete Files
		// Make Dir
		// Copy from Local to HDFS and vice versa
	}

}
