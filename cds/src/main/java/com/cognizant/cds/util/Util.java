package com.cognizant.cds.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

/**
 * Utility class
 * 
 * @author Raghavendra Hegde
 */

public class Util {

	public static <T> List<T> parseCsv(final Class<T> clazz, final String filePath) throws IOException {
		final CsvMapper mapper = new CsvMapper();
		File file = new ClassPathResource(filePath).getFile();
		CsvSchema schema = mapper.schemaFor(clazz).withHeader().withColumnReordering(true);
		MappingIterator<T> readValues = mapper.readerFor(clazz).with(schema).readValues(file);
		return readValues.readAll();
	}
}
