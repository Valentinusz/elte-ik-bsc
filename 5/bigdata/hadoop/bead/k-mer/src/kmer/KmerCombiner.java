package kmer;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class KmerCombiner extends Reducer<Text, IntWritable, Text, IntWritable> {
	public void reduce(Text _key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
		int count = 0;
		
		for (IntWritable val : values) {
			count++;
		}
		
		context.write(_key, new IntWritable(count));
	}

}
