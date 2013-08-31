import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableUtils;


public class Employee implements WritableComparable<Employee> {

	private String first;
	private String last;

	public Employee(String first, String last) {
		// TODO Auto-generated constructor stub
		this.first=first;
		this.last=last;

	}

	@Override
	public void readFields(DataInput in) throws IOException {
		// TODO Auto-generated method stub
		first=WritableUtils.readString(in);
		last=WritableUtils.readString(in);

	}

	@Override
	public void write(DataOutput out) throws IOException {
		// TODO Auto-generated method stub
		WritableUtils.writeString(out, first);
		WritableUtils.writeString(out, last);
	}

	@Override
	public int compareTo(Employee o) {
		
		int result = first.compareTo(o.first);
		if(0 == result) {
			result = last.compareTo(o.last);
		}
		return result;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public String toString() {
		return (new StringBuilder())
				.append('{')
				.append(first)
				.append(',')
				.append(last)
				.append('}')
				.toString();
	}

}
