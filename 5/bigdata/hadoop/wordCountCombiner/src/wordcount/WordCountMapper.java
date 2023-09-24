package wordcount;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	private Text key = new Text("");
	private IntWritable value = new IntWritable(1); 
	
	public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {
		String[] words = ivalue.toString().split(" ");
		for (String word : words) {
			key.set(word);
			context.write(key, value);
		}
	}
}
