package wordcount;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

// Típusparaméterek:
// 1. bemeneti pár kulcsának típusa
// 2. bemeneti pár értékének típusa
// 3. kimeneti pár kulcsának típusa
// 4. kimeneti pár értékének típusa
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	// hatékonyság miatt használjuk újra a változókat
	private Text key = new Text("");
	private IntWritable value = new IntWritable(1); 
	// nem a megszokott Java típusokat használja
	
	// ikey típusa = osztály 1. típusparamétere
	// ivalue típusa = osztály 2. típusparamétere
	// ivalue tartalmazza bemeneti értéket (esetünkben egy fájl egy sora)
	public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {
		String[] words = ivalue.toString().split(" ");
		// felbontjuk a stringet szóközök mentén
		for (String word : words) {
			key.set(word); // kulcs legyen a szó
			context.write(key, value); // kulcs érték pár létrehozása és továbbadása
		}
	}
}
