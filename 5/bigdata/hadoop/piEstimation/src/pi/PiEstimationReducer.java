package pi;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.BooleanWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class PiEstimationReducer extends Reducer<BooleanWritable, LongWritable, NullWritable, DoubleWritable> {
	private double inDistanceCount = 0;
	private double totalCount = 0;
	
	public void reduce(BooleanWritable _key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
		for (var value : values) {
			if (_key.get()) {
				this.inDistanceCount += value.get();
			} else {
				this.totalCount += value.get();
			}
		}
	}
	
	public void cleanup(Context context) throws IOException, InterruptedException {		
		context.write(NullWritable.get(), new DoubleWritable(4.0 * (inDistanceCount / totalCount)));
	}

}
