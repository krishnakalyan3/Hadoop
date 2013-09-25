

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.approvaltests.hadoop.version1.HadoopApprovals;
import org.approvaltests.hadoop.version1.MapperWrapper;
import org.approvaltests.hadoop.version1.SmartMapper;
import org.approvaltests.reporters.DiffReporter;
import org.approvaltests.reporters.UseReporter;

import junit.framework.TestCase;

@UseReporter(DiffReporter.class)
public class HadoopTest extends TestCase {

	public void testMapReduce() throws Exception{
		SmartMapper mapper =new MapperWrapper(new WCMapper(), LongWritable.class, Text.class, Text.class, IntWritable.class);
		HadoopApprovals.verifyMapping(mapper, 0, "Hello 1 a a a a a");
		
		
	}
	
	
	
	
}
