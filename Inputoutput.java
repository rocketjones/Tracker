package current;

import java.io.*;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.lang.Object;

public class Inputoutput {

	private static int cal = 0;

	public static void main(String[] args) throws IOException {

		// Declare file with name "data.txt"
		File data = new File("data.txt");
		// Create file if doesn't exist
		data.createNewFile();

		Scanner in = new Scanner(data);
		PrintWriter out = new PrintWriter("data.txt");

		// If file is empty, write cal to it
		BufferedReader br = new BufferedReader(new FileReader("data.txt"));
		if (br.readLine() == null) {
			out.println(cal);
			br.close();
		}

		try {
			while (in.hasNextLine()) {
				int i = in.nextInt(); // (does not consume white space)
				cal = i;
				String cat = in.nextLine();
				// System.out.println(cat);
			}
			in.close();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}

	}
}
