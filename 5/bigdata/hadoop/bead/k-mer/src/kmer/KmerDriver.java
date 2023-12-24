package kmer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class KmerDriver {
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		conf.setInt("k", 3);
		conf.setInt("minimumKMerCount", 100);
		conf.set("requiredNucleotid", "T");
		
		Job job = Job.getInstance(conf, "k-mer");
		job.setJarByClass(KmerDriver.class);
		
		job.setMapperClass(KmerMapper.class);
		
		job.setCombinerClass(KmerCombiner.class);

		job.setReducerClass(KmerReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		job.setNumReduceTasks(1);
		
		FileInputFormat.setInputPaths(job, new Path("input"));
		FileOutputFormat.setOutputPath(job, new Path("out"));

		if (!job.waitForCompletion(true)) return;
	}
}