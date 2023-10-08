package temp;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

public class TemperatureWritable implements WritableComparable<TemperatureWritable> {
	private Text hour;
	private DoubleWritable temperature;
	
	// kell üres konstruktor
	public TemperatureWritable() {
		this.hour = new Text("");
		this.temperature = new DoubleWritable(Double.MIN_VALUE);
	}
	
	public TemperatureWritable(String hour, double temperature) {
		this.hour = new Text(hour);
		this.temperature = new DoubleWritable(temperature);
	}
	
	public Text getHour() {
		return this.hour;
	}
	
	public DoubleWritable getTemperature() {
		return this.temperature;
	}
	
	public void setHour(Text hour) {
		this.hour = hour;
	}
	
	public void setTemperature(DoubleWritable temperature) {
		this.temperature = temperature;
	}

	public void readFields(DataInput in) throws IOException {
		this.hour.readFields(in);
		this.temperature.readFields(in);
	}
	
	public void write(DataOutput out) throws IOException {
		this.hour.write(out);
		this.temperature.write(out);
	}

	public int compareTo(TemperatureWritable o) {
		return this.temperature.compareTo(o.temperature);
	}
	
	@Override
	public String toString() {
		return this.hour + ": " + this.temperature + "C";
	}
}