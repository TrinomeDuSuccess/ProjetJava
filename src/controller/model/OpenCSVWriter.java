package controller.model;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class OpenCSVWriter {

	private static final String OBJECT_LIST_SAMPLE = "./listeAchats.csv";

	/**
	 * Permet d'exporter la liste d'achat dans un fichier CSV : listeAchats.csv
	 * 
	 * @param listeAchat
	 * @return booléen si l'export s'est bien effectué ou non
	 * @throws IOException
	 * @throws CsvDataTypeMismatchException
	 * @throws CsvRequiredFieldEmptyException
	 */
	public boolean exportAchat(List<Achat> listeAchat)
			throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		boolean exportOK = false;
		try (Writer writer = Files.newBufferedWriter(Paths.get(OBJECT_LIST_SAMPLE));) {
			StatefulBeanToCsv<Achat> beanToCsv = new StatefulBeanToCsvBuilder(writer)
					.withSeparator(';')
					.build();

			List<Achat> mesAchats = new ArrayList<>();

			for (Achat unAchat : listeAchat) {
				mesAchats.add(new Achat(unAchat.getCode(), unAchat.getQuantite(), unAchat.getPrix(),
						unAchat.getCodeChaine()));
			}

			beanToCsv.write(mesAchats);
			exportOK = true;
		}
		return exportOK;
	}
}