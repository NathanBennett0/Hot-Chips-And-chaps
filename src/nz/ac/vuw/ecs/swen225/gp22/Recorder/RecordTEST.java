package recorder.java;
import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class RecordTEST {
	Document doc;
	RecordTEST(File file){
		try {
			doc = new SAXReader().read(file);
		}catch(DocumentException e) {
			throw new IllegalArgumentException("Unsuccessful record");
			
		}
	}

}
