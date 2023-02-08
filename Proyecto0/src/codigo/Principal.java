package codigo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java_cup.internal_error;

public class Principal {

	public static void main(String[] args) throws internal_error, IOException, Exception {
		String ruta = "./src/codigo/Lexer.flex";
		String ruta2 = "./src/codigo/LexerCup.flex";
		String[] rutaS = {"-parser","sintax","./src/codigo/LexerCup.flex"};
		generar(ruta,ruta2,rutaS);
	}
	
	public static void generar(String ruta,String ruta2, String[] rutaS) throws internal_error, IOException, Exception {
		
		File archivo;
		archivo = new File(ruta);
		JFlex.Main.generate(archivo);
		
		archivo = new File(ruta2);
		JFlex.Main.generate(archivo);
		
		java_cup.Main.main(rutaS);
		
		Path rutaSym = Paths.get("./src/codigo/Sintax.java");
		if(Files.exists(rutaSym)) {
			Files.delete(rutaSym);
			
		}
		
		Path rutaSin = Paths.get("./src/codigo/Sintax.java");
		if(Files.exists(rutaSin)) {
			Files.delete(rutaSin);
			
		}
		
		Files.move(Paths.get("./Sintax.java"), Paths.get("./src/codigo/Sintax.java"));
		Files.move(Paths.get("./Sintax.java"), Paths.get("./src/codigo/Sintax.java"));
	}
	
}
