package wordcount;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

// shuffling lépésben az azonos kulcs érték párok egy reducerhez kerülnek
// Típusparaméterek:
// 1. bemeneti pár kulcs típus
// 2. bemeneti pár érték típus
// 3. kimeneti pár kulcsának típusa
// 4. kimeneti pár értékének típusa
public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	// _key a közös kulcs, values gyűjteményben az értékek
	// _key típusa az osztály 1. típusparamétere
	// values típusparamétere az osztály 2. típusparamétere
	public void reduce(Text _key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
		int count = 0;
		
		for (IntWritable entryValue : values) {
			count += entryValue.get(); // így a reducer combinerként is használható
		}
		
		context.write(_key, new IntWritable(count)); // pár továbbadása
	}
}
