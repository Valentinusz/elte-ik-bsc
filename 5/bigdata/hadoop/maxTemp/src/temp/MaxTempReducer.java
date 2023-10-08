package temp;

import java.io.IOException;
import java.util.stream.StreamSupport;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MaxTempReducer extends Reducer<Text, TemperatureWritable, Text, TemperatureWritable> {
	public void reduce(Text _key, Iterable<TemperatureWritable> values, Context context) throws IOException, InterruptedException {
	    var max = StreamSupport.stream(values.spliterator(), false)
	    		.map(value -> new TemperatureWritable(value.getHour().toString(), value.getTemperature().get()))
                .max(TemperatureWritable::compareTo)
                .orElse(null);

		context.write(_key, max);
	}
}
