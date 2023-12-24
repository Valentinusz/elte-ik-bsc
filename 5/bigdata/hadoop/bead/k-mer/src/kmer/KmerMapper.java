package kmer;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class KmerMapper extends Mapper<LongWritable, Text, Text, IntWritable> {	
	public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {
		Configuration conf = context.getConfiguration();
		
		String requiredNucleotid = conf.get("requiredNucleotid");
		
		final int k = conf.getInt("k", 3);
		
		final IntWritable one = new IntWritable(1);
		
		String sequence = ivalue.toString();
		
		for (int i = 0; i < sequence.length() - (k - 1); ++i) {
			String kmer = sequence.substring(i, i + k);
			
			if (kmer.contains(requiredNucleotid)) {
				context.write(new Text(kmer), one);
			}
		}
	}
}