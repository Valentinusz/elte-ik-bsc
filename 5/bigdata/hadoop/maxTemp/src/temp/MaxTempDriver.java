package temp;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class MaxTempDriver {
	public static void main(String[] args) throws Exception {
		System.setProperty("hadoop.tmp.dir", "c:\\BigData\\tmp\\hadoop");
		System.setProperty("hadoop.home.dir", "c:\\BigData\\hadoop-3.3.6");
		
		Configuration conf = new Configuration();
		
		Job job = Job.getInstance(conf, "JobName");
		job.setJarByClass(MaxTempDriver.class);
		job.setMapperClass(MaxTempMapper.class);

		job.setReducerClass(MaxTempReducer.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(TemperatureWritable.class);

		FileInputFormat.setInputPaths(job, new Path("tempBudapestMeteoBlue.csv"));
		FileOutputFormat.setOutputPath(job, new Path("out"));

		if (!job.waitForCompletion(true)) return;
	}
}