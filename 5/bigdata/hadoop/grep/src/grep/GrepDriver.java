package grep;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class GrepDriver {
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "grep");
		job.setJarByClass(GrepDriver.class);
		job.setMapperClass(GrepMapper.class);

		job.setNumReduceTasks(0); // reducer fázis kikapcsolása

		job.setOutputKeyClass(LongWritable.class);
		job.setOutputValueClass(Text.class);

		FileInputFormat.setInputPaths(job, new Path("input"));
		FileOutputFormat.setOutputPath(job, new Path("out"));

		if (!job.waitForCompletion(true)) return;
	}
}