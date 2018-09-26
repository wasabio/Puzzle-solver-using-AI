package miniProject1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TxtWriter {
	
	public static void Generate(String fileName, String content) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
			
			out.write(content);
			out.newLine();

            out.close();
        } catch (IOException e) {
        	System.out.println(e);
        }
		
	}

}
