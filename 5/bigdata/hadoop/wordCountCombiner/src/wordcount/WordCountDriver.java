package wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordCountDriver {
	public static void main(String[] args) throws Exception {
		System.setProperty("hadoop.tmp.dir", "c:\\BigData\\tmp\\hadoop-xx");
		System.setProperty("hadoop.home.dir", "c:\\BigData\\hadoop-3.3.6");
		
		Configuration conf = new Configuration();
		
		Job job = Job.getInstance(conf, "WordCount"); 
		
		job.setJarByClass(wordcount.WordCountDriver.class);
		
		job.setMapperClass(wordcount.WordCountMapper.class);
		
		// combiner lényegében lokálisan elvégzi a reducer feladatának egy részét
		// így kevesebb hálózati IO kell -> hatékonyabb
		job.setCombinerClass(wordcount.WordCountReducer.class);
		
		job.setReducerClass(wordcount.WordCountReducer.class);
		
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		FileInputFormat.setInputPaths(job, new Path("input"));
		FileOutputFormat.setOutputPath(job, new Path("out"));

		if (!job.waitForCompletion(true)) return;
	}
}
