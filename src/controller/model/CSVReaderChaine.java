package controller.model;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import com.opencsv.bean.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CSVReaderChaine implements DataReader {
	//chemin vers le fichier csv
	private static final String SAMPLE_CSV_FILE_PATH = "chaines.csv";

	/**
	 * Permet de lire le fichier CSV : chaines.csv
	 * @return Liste d'objet de type Chaine
	 */
	@Override
	public List<Chaine> toImport(){
		Reader reader;
		try {
			reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));

			CsvToBean<Chaine> csvToBean = new CsvToBeanBuilder<Chaine>(reader)
					.withType(Chaine.class)
					.withIgnoreLeadingWhiteSpace(true)
					.withSeparator(';')
					.build();

			Iterator<Chaine> mesChainesIterator = csvToBean.iterator();

			List<Chaine> list = new ArrayList<Chaine>();

			while (mesChainesIterator.hasNext()) {
				Chaine maChaine = mesChainesIterator.next();
				Chaine uneChaine = new Chaine(maChaine.getCode(), maChaine.getNom(), maChaine.getEntree().trim(), maChaine.getSortie().trim(), maChaine.getTemps(), maChaine.getPersonnelsNonQualifies(), maChaine.getPersonnelsQualifies());
				list.add(uneChaine);
			}

			ObservableList<Chaine> listeChaine = FXCollections.observableArrayList(list);
			
			return listeChaine;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
}
