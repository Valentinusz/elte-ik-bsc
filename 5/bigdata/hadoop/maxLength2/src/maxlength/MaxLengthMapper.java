package maxlength;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MaxLengthMapper extends Mapper<LongWritable, Text, Text, Text> {
	public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {
		for (String word : ivalue.toString().replaceAll("[,.?!:]", " ").split(" ")) {
			context.write(new Text(word.substring(0, 1).toLowerCase()), new Text(word));
		}
	}
}