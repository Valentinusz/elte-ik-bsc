package temp;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MaxTempMapper extends Mapper<LongWritable, Text, Text, TemperatureWritable> {
	private TemperatureWritable temperature = new TemperatureWritable();
	private Text date = new Text("");

	public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {
		String[] columns = ivalue.toString().split(","); // két oszlopot fogunk kapni
		
		// header sorok figyelmen kívül hagyása
		if(columns[0].startsWith("2020")) {
			String[] timestamp = columns[0].split("T"); // napot és órát T betû választja el
			date.set(timestamp[0]); // kulcs dátum
			
			temperature.setHour(new Text(timestamp[1]));
			temperature.setTemperature(new DoubleWritable(Double.parseDouble(columns[1])));
			context.write(date, temperature);
		}
	}

}
