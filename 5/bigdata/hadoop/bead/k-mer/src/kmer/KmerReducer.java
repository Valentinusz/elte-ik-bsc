package kmer;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Counter;
import org.apache.hadoop.mapreduce.Reducer;

public class KmerReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	private Counter passedCounter;
	
	public void reduce(Text _key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
		Configuration conf = context.getConfiguration();
		final int minimumKMerCount = conf.getInt("minimumKMerCount", 0);
		
		int kmerCount = 0;
		
		this.passedCounter = context.getCounter(KmerCounter.TOTAL_PASSED_KMERS);
		
		for (IntWritable val : values) {
			kmerCount += val.get();
		}
		
		if (kmerCount > minimumKMerCount) {
			context.write(_key, new IntWritable(kmerCount));
			passedCounter.increment(kmerCount);
		}
	}
	
	public void cleanup(Context context) throws IOException, InterruptedException {
		context.write(new Text(String.valueOf(this.passedCounter.getValue())), null);
	}
}
