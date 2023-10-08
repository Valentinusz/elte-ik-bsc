package pi;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BooleanWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class PiEstimationDriver {
	public static void main(String[] args) throws Exception {
		System.setProperty("hadoop.tmp.dir", "c:\\BigData\\tmp\\hadoop-xx");
		System.setProperty("hadoop.home.dir", "c:\\BigData\\hadoop-3.3.6");

		Configuration conf = new Configuration();
		
		// pontok száma
		conf.setInt("numOfPoints", 1000000);

		Job job = Job.getInstance(conf, "pi");
		job.setJarByClass(PiEstimationDriver.class);
		job.setMapperClass(PiEstimationMapper.class);

		job.setReducerClass(PiEstimationReducer.class);
		
		// több féle kulcsunk van, de csak egy reducer taskot szeretnénk futtatni
		job.setNumReduceTasks(1);

		job.setMapOutputValueClass(LongWritable.class);
		job.setOutputKeyClass(BooleanWritable.class);
		job.setOutputValueClass(DoubleWritable.class);

		FileInputFormat.setInputPaths(job, new Path("input.txt"));
		FileOutputFormat.setOutputPath(job, new Path("out"));

		if (!job.waitForCompletion(true)) return;
	}
}
