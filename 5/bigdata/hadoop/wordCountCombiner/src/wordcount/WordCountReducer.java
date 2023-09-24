package wordcount;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	public void reduce(Text _key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
		int count = 0;
		
		for (IntWritable entryValue : values) {
			count += entryValue.get(); // így a reducer combinerként is használható
		}
		
		context.write(_key, new IntWritable(count));
	}
}
