package edu.upenn.cit594.datamanagement;
import java.util.List;

import edu.upenn.cit594.data.Tweet;

public interface Reader<E> {
		
	   List<E> getData();
}
