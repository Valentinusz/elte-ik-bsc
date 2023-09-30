package wordmean;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.TaskCounter;

public class WordMeanReducer extends Reducer<NullWritable, IntWritable, NullWritable, DoubleWritable> {
	public void reduce(NullWritable _key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
		int wordLengthSum = 0;
		
		for (IntWritable entryValue : values) {
			wordLengthSum += entryValue.get();
		}
		
		// Hadoop számlálókat tart nyílván
		// REDUCE_INPUT_RECARDS - a reducer-nek átadott kulcs érték párok száma
		context.write(NullWritable.get(), new DoubleWritable( wordLengthSum / (double) context.getCounter(TaskCounter.REDUCE_INPUT_RECORDS).getValue()));
	}
}