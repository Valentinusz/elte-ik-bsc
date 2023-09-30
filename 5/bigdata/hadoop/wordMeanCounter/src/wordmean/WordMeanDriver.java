package wordmean;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordMeanDriver {
	public static void main(String[] args) throws Exception {
		System.setProperty("hadoop.tmp.dir", "c:\\BigData\\tmp\\hadoop-xx");
		System.setProperty("hadoop.home.dir", "c:\\BigData\\hadoop-3.3.6");
		
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "wordMean");
		job.setJarByClass(wordmean.WordMeanDriver.class);

		job.setMapperClass(wordmean.WordMeanMapper.class);
		job.setReducerClass(wordmean.WordMeanReducer.class);
		
		// alapértelmezetten a Map output típusai megegyeznek a teljes output típusaival
		// esetünkben ez nem így van ezért explicit meg kell adnunk őket
		job.setMapOutputKeyClass(NullWritable.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		job.setOutputKeyClass(NullWritable.class);
		job.setOutputValueClass(DoubleWritable.class);

		FileInputFormat.setInputPaths(job, new Path("input"));
		FileOutputFormat.setOutputPath(job, new Path("out"));
		
		if (!job.waitForCompletion(true)) return;
	}
}