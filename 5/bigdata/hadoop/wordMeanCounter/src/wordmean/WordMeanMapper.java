package wordmean;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordMeanMapper extends Mapper<LongWritable, Text, NullWritable, IntWritable> {
	public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {
		for (String word : ivalue.toString().replaceAll("[,.?!:]", " ").split(" ")) {
			// NullWritable singleton, ezért az, hogy nem mentjük ki változóba nem ront a teljesítményen
			context.write(NullWritable.get(), new IntWritable(word.length()));
		}
	}
}