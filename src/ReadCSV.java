import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * This class is responsible for reading csv file.
 */
public class ReadCSV {
	/**
	 *
	 * @param pathname: path of the file
	 * @return return a list of the elements in the csv file
	 */
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
