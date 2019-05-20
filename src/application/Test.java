package application;

import java.util.ArrayList;
import java.util.List;

import controller.model.CSVReaderChaine;
import controller.model.CSVReaderElement;
import controller.model.CSVReaderStockage;
import controller.model.Chaine;
import controller.model.CodeQTE;
import controller.model.Element;
import controller.model.Stockage;

public class Test {

	public static void main(String[] args) {
		CSVReaderChaine a = new CSVReaderChaine();
		
		List<Chaine> l = a.toImport();
		
//		for(Chaine uneChaine : l) {
//			System.out.println(uneChaine.getCode());
//			System.out.println(uneChaine.getNom());
//			System.out.println(uneChaine.getTemps());
//			System.out.println(uneChaine.getPersonnelsNonQualifies());
//			System.out.println(uneChaine.getPersonnelsQualifies());
//			System.out.println("Entreés : ");
//			for(CodeQTE unCode : uneChaine.getListeEntree()) {
//				System.out.println(unCode.getCode());
//				System.out.println(unCode.getQuantite());
//			}
//		}
		
		CSVReaderStockage s = new CSVReaderStockage();
		List<Stockage> aa= s.toImport();
		
		for(Stockage unStockage : aa) {
			System.out.println(unStockage.getCode());
			System.out.println(unStockage.getNom());
			System.out.println(unStockage.getCapacite());
			System.out.println(unStockage.getQuantiteDisponible());
		}
		
		
		

	}

}
