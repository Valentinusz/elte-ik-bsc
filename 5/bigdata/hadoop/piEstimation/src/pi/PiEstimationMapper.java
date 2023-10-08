package pi;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.Random;
import java.util.stream.Stream;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.BooleanWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class PiEstimationMapper extends Mapper<LongWritable, Text, BooleanWritable, LongWritable> {
	public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {
		Configuration conf = context.getConfiguration();
		var numberOfPoints = conf.getInt("numOfPoints", 0);
		
		var random = new Random();
		
		var inDistanceCount = Stream
				.generate(() -> new AbstractMap.SimpleEntry<Double, Double>(random.nextDouble(), random.nextDouble()))
				.limit(numberOfPoints)
				.mapToDouble(point -> Math.sqrt(Math.pow(point.getKey(), 2) + Math.pow(point.getValue(), 2)))
				.filter(distance -> distance <= 1)
				.count();
		
		context.write(new BooleanWritable(false), new LongWritable(numberOfPoints));
		context.write(new BooleanWritable(true), new LongWritable(inDistanceCount));
	}
}