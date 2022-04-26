package fr.fms.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigFile {
	public static Properties getConfig(String fileName) throws IOException {
		FileInputStream propsInput = null;
		Properties prop = null;
		try {
			propsInput = new FileInputStream(fileName);
			prop = new Properties();
			prop.load(propsInput);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			propsInput.close();
		}
		return prop;
	}
}
