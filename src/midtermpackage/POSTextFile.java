package midtermpackage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class POSTextFile {
	private static Path filePath = Paths.get("pos.txt");
	
	static List<Toy> readFile() throws IOException{
		try {
			List<String> lines = Files.readAllLines(filePath);
			List<Toy> toys = new ArrayList<>(); 
			for(String line : lines) {
				String[] parts = line.split("\t");
				Toy toy = new Toy();
				toy.setName(parts[0]);
				toy.setCategory(parts[1]);
				toy.setPrice(Double.parseDouble(parts[2]));
				toy.setDescription(parts[3]);
				toys.add(toy);
			}
			return toys;
		} catch(IOException | ArrayIndexOutOfBoundsException ex){
			System.out.println("No toys on the shelf!");
			return new ArrayList<>();
		}
	}
	
	static List<Toy> writeFile() throws IOException{
		
			if ( Files.notExists(filePath) ) {
	            Files.createFile(filePath);
	        }
				
	        // ** Example of adding to the end of a file
	        // Create a list with the user's food in it
	        List<String> linesToAdd = Arrays.asList(country + "\t" + population);
	        // Write those lines to the end of the file
	        Files.write(filePath, linesToAdd, StandardOpenOption.APPEND);
		
	}
	public static void rewritetxtFile(List<Toy> toys) throws IOException {
        
        if ( Files.exists(filePath) ) {
            Files.delete(filePath);
        }
        if ( Files.notExists(filePath) ) {
            Files.createFile(filePath);
        }
        
        // ** Example of adding to the end of a file
        // Create a list with the user's food in it
        List<String> linesToAdd = Arrays.asList(Toy.getName() + "\t" + Toy.getCategory() + "\t" + Toy.);
        // Write those lines to the end of the file
        Files.write(filePath, linesToAdd, StandardOpenOption.APPEND);
        
    }

}
