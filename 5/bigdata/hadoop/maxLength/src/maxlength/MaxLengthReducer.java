package maxlength;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MaxLengthReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	public void reduce(Text _key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
		int max = 0;
		
		for (IntWritable entryValue : values) {
			if (entryValue.get() > max) {
				max = entryValue.get();
			}
		}
		
		context.write(_key, new IntWritable(max));
	}
}