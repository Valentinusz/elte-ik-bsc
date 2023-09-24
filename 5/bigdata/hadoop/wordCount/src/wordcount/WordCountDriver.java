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
		// HDFS itt tárolja az ideiglenes fájlokat
		System.setProperty("hadoop.tmp.dir", "c:\\BigData\\tmp\\hadoop-xx");
		
		// könyvtár ahol a Hadoop telepítve van
		System.setProperty("hadoop.home.dir", "c:\\BigData\\hadoop-3.3.6");
		
		Configuration conf = new Configuration();
		
		// job neve (igazából nem számít)
		Job job = Job.getInstance(conf, "WordCount"); 
		
		job.setJarByClass(wordcount.WordCountDriver.class);
		
		// mapper osztály megadása
		job.setMapperClass(wordcount.WordCountMapper.class);
		
		// reducer osztály megadása
		job.setReducerClass(wordcount.WordCountReducer.class);
		
		// kimeneti kulcs érték pár típusának megadása
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		// be és kimeneti könyvtárak megadása
		FileInputFormat.setInputPaths(job, new Path("input")); // bementi mappa
		FileOutputFormat.setOutputPath(job, new Path("out")); // kimeneti mappa

		// job futtatása
		if (!job.waitForCompletion(true)) return;
	}
}
