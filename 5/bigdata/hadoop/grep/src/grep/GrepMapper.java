package grep;
import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class GrepMapper extends Mapper<LongWritable, Text, NullWritable, Text> {
	public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {
		if (ivalue.toString().contains("sütsz")) {
			// NullWritable a leghatékonyabb, ha olyan kulcs/érték kell, ami nem fontos
			context.write(NullWritable.get(), ivalue);
		}
	}
}