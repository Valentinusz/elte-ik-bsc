package maxlength;

import java.io.IOException;
import java.util.LinkedHashSet;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MaxLengthCombiner extends Reducer<Text, Text, Text, Text> {
	public void reduce(Text _key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		int max = 0;
		var maxLengths = new LinkedHashSet<String>();
		
		for (var entryValue : values) {
			if (entryValue.getLength() > max) {
				max = entryValue.getLength();
				maxLengths.clear();
			}
			if (entryValue.getLength() == max) {
				maxLengths.add(entryValue.toString());
			}
		}
		
		for(var word : maxLengths) {
			context.write(_key, new Text(word));
		}
	}
}