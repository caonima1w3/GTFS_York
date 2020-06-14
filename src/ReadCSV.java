import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class ReadCSV {

	public static ArrayList<String[]> readCSV(String pathname) {
		ArrayList<String[]> list = new ArrayList<String[]>();
		try {
			String path = "./assets/" + pathname;
			BufferedReader reader = new BufferedReader(new FileReader(path));
			reader.readLine();
			String line = null;
			while ((line = reader.readLine()) != null) {
				String item[] = line.split(",");
				list.add(item);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
