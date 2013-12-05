
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

public class TextPair implements WritableComparable<TextPair> {

	public Text first;
	public Text second;

	public Text getFirst() {
		return first;
	}

	public Text getSecond() {
		return second;
	}

	public TextPair() {

		first = new Text();
		second = new Text();
	}

	public TextPair(String first, String second) {

		this(new Text(first), new Text(second));
	}

	public TextPair(Text first, Text second) {
		this.first = first;
		this.second = second;
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		first.readFields(in);
		second.readFields(in);
	}

	@Override
	public void write(DataOutput out) throws IOException {
		first.write(out);
		second.write(out);

	}

	@Override
	public int compareTo(TextPair that) {
		int cmp = first.compareTo(that.first);
		if (cmp == 0) {
			cmp = second.compareTo(that.second);
		}
		return cmp;
	}

	@Override
	public boolean equals(Object obj) {

		if (obj instanceof TextPair) {
			TextPair that = (TextPair) obj;
			return (first.equals(that.first) && second.equals(that.second));
		}

		return false;
	}

/*	@Override
	public int hashCode() {
		return first.hashCode() + 167 * second.hashCode();
	}

	@Override
	public String toString() {
		return first.toString() + "-" + second.toString();
	}*/
}