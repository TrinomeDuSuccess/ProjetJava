package controller.model;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CSVReaderStockage implements DataReader {
	// chemin vers le csv
	private static final String SAMPLE_CSV_FILE_PATH = "stockage.csv";

	/**
	 * Permet de lire le fichier CSV : stockage.csv
	 * 
	 * @return Liste d'objet de type Stockage
	 */
	@Override
	public List<Stockage> toImport() {
		Reader reader;
		try {
			reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));

			CsvToBean<Stockage> csvToBean = new CsvToBeanBuilder<Stockage>(reader).withType(Stockage.class)
					.withIgnoreLeadingWhiteSpace(true).withSeparator(';').build();

			Iterator<Stockage> myStockageIterator = csvToBean.iterator();

			List<Stockage> list = new ArrayList<Stockage>();

			while (myStockageIterator.hasNext()) {
				Stockage myStockage = myStockageIterator.next();
				Stockage unStockage = new Stockage(myStockage.getCode(), myStockage.getNom(), myStockage.getCapacite(),
						myStockage.getQuantiteDisponible());
				list.add(unStockage);
			}

			ObservableList<Stockage> liste = FXCollections.observableArrayList(list);

			return liste;

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
