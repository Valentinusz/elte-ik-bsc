package maxlength;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class MaxLengthDriver {
	public static void main(String[] args) throws Exception {
		System.setProperty("hadoop.tmp.dir", "c:\\BigData\\tmp\\hadoop-xx");
		System.setProperty("hadoop.home.dir", "c:\\BigData\\hadoop-3.3.6");
		
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "wordMean");
		job.setJarByClass(maxlength.MaxLengthDriver.class);

		job.setMapperClass(maxlength.MaxLengthMapper.class);
		job.setReducerClass(maxlength.MaxLengthReducer.class);
			
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		FileInputFormat.setInputPaths(job, new Path("input"));
		FileOutputFormat.setOutputPath(job, new Path("out"));
		
		if (!job.waitForCompletion(true)) return;
	}
}