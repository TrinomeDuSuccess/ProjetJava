package controller.model;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import com.opencsv.bean.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CSVReaderElement implements DataReader {
	//chemin vers le fichier csv
	private static final String SAMPLE_CSV_FILE_PATH = "elements.csv";

	/**
	 * Permet de lire le fichier CSV : elements.csv
	 * 
	 * @return Liste d'objet de type Element
	 */
	@Override
	public List<Element> toImport() {
		Reader reader;
		try {
			reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));

			CsvToBean<Element> csvToBean = new CsvToBeanBuilder<Element>(reader).withType(Element.class)
					.withIgnoreLeadingWhiteSpace(true).withSeparator(';').build();

			Iterator<Element> monElementIterator = csvToBean.iterator();

			List<Element> list = new ArrayList<Element>();

			while (monElementIterator.hasNext()) {
				Element monElement = monElementIterator.next();
				Element unElement = new Element(monElement.getCode(), monElement.getNom(), monElement.getQuantite(),
						monElement.getUnite(), monElement.getAchat(), monElement.getVente(), monElement.getStockage(),
						monElement.getDemande());
				list.add(unElement);
			}

			ObservableList<Element> listeElement = FXCollections.observableArrayList(list);
			
			return listeElement;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
}
