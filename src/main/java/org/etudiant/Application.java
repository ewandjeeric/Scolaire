package org.etudiant;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.etudiant.dao.EtudiantRepository;
import org.etudiant.entities.Etudiant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws ParseException {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
		
		EtudiantRepository etudiantrepository = ctx.getBean(EtudiantRepository.class);
		
		/*
		 * DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		 * etudiantrepository.save(new Etudiant("Ewandje", "Eric",
		 * df.parse("1997-08-30") )); etudiantrepository.save(new Etudiant("Dikos",
		 * "Elian", df.parse("200-08-30") )); etudiantrepository.save(new
		 * Etudiant("Nguedjang", "Bruno", df.parse("1984-07-30") ));
		 * etudiantrepository.save(new Etudiant("Tchakoute", "Roger",
		 * df.parse("1957-08-10") ));
		 */
	}

}
