package maxlength;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MaxLengthReducer extends Reducer<Text, Text, Text, Text> {
	public void reduce(Text _key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		int max = 0;
		var maxLengths = new LinkedHashSet<String>();
		
		for (var entryValue : values) {
			if (entryValue.getLength() == max) {
				maxLengths.add(entryValue.toString());
			} else if (entryValue.getLength() > max) {
				max = entryValue.getLength();
				maxLengths.clear();
			}
		}
		
		context.write(_key, new Text(maxLengths.stream().collect(Collectors.joining(" "))));
	}
}