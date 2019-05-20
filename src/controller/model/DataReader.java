package controller.model;

import java.util.*;

public interface DataReader<T> {
	public List<T> toImport();
}
