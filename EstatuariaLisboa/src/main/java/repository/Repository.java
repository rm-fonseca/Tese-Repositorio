package repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import plataforma.modelointerno.LanguageString;
import plataforma.modelointerno.Location;
import plataforma.modelointerno.Point;
import plataforma.modelointerno.Result;
import repository.model.GeoBox;
import repository.model.GeoPoint;

public class Repository implements RepositoryAbstract {

	//This needs to be change if the CSV dir for the Repo changes
    //public static final String CSV_REPO = "/home/rgarcia/storage/workspace/Tese-Repositorio/EstatuariaLisboa/src/main/java/Estatu%C3%A1ria.csv";
	
	public static final String CSV_REPO = "E:/Code/Tese-Repositorio/EstatuariaLisboa/src/main/java/Estatu%C3%A1ria.csv";

	private static String repName = null;

	@Override
	public List<Result> SearchByBox(int latitudeFrom, int latitudeTo, int longitudeFrom, int longitudeTo,
			boolean ignoreExtraProperties) throws Exception {
		List<Result> results = new ArrayList<>();
		GeoBox box = new GeoBox(latitudeTo, latitudeFrom, longitudeTo, longitudeFrom);

		Reader reader = Files.newBufferedReader(Paths.get(CSV_REPO));
		CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
				.withHeader("X","Y","OBJECTID","COD_SIG","IDTIPO","INF_NOME","INF_MORADA","INF_TELEFONE","INF_FAX","INF_EMAIL","INF_SITE","INF_DESCRICAO","INF_FONTE","INF_MUNICIPAL","GlobalID")
				.withIgnoreHeaderCase()
				.withFirstRecordAsHeader()
				.withTrim()
		);

		for(CSVRecord record : csvParser) {
			int latitude = (int)Float.parseFloat(record.get(1));
			int longitude = (int)Float.parseFloat(record.get(0));
			GeoPoint point = new GeoPoint(latitude, longitude);

			if(box.isGeoPointWithinBox(point)) {
				Result result = new Result();
				result.getSourceRepositorie().add(getRepositoryName());
				//Tipo - Estátua
				result.getDcType().add(new LanguageString(){{
					setLanguage("pt");
					getText().add("Estátua");
				}});
				//Nome da Estátua
				result.getDcTitle().add(new LanguageString(){{
					setLanguage("pt");
					getText().add(record.get("INF_NOME"));
				}});
				//Descrição
				result.getDcDescription().add(new LanguageString(){{
					setLanguage("pt");
					getText().add(record.get("INF_DESCRICAO"));
				}});
				result.getLocations().add(new Location(){{
					setId(1);
					getCoordinates().add(new Point(){{
						setLatitude(Float.parseFloat(record.get(1)));
						setLongitude(Float.parseFloat(record.get(0)));
					}});
				}});

				results.add(result);
			}
		}
		return results;
	}

	@Override
	public List<Result> SearchByTerm(String term, boolean ignoreExtraProperties) throws Exception {
		List<Result> results = new ArrayList<>();

		Reader reader = Files.newBufferedReader(Paths.get(CSV_REPO));
		CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
				.withHeader("X","Y","OBJECTID","COD_SIG","IDTIPO","INF_NOME","INF_MORADA","INF_TELEFONE","INF_FAX","INF_EMAIL","INF_SITE","INF_DESCRICAO","INF_FONTE","INF_MUNICIPAL","GlobalID")
				.withIgnoreHeaderCase()
				.withFirstRecordAsHeader()
				.withTrim()
		);

		for(CSVRecord record : csvParser) {
			if(parcialMatch(term, record)) {
				Result result = new Result();
				result.getSourceRepositorie().add(getRepositoryName());
				//Tipo - Estátua
				result.getDcType().add(new LanguageString(){{
					setLanguage("pt");
					getText().add("Estátua");
				}});
				//Nome da Estátua
				result.getDcTitle().add(new LanguageString(){{
					setLanguage("pt");
					getText().add(record.get("INF_NOME"));
				}});
				//Descrição
				result.getDcDescription().add(new LanguageString(){{
					setLanguage("pt");
					getText().add(record.get("INF_DESCRICAO"));
				}});
				result.getLocations().add(new Location(){{
					setId(1);
					getCoordinates().add(new Point(){{
						setLatitude(Float.parseFloat(record.get(1)));
						setLongitude(Float.parseFloat(record.get(0)));
					}});
				}});

				results.add(result);
			}
		}
		return results;
	}

	private boolean parcialMatch(String term, CSVRecord record) {
		String info_name = record.get("INF_NOME");
		String info_descricao = record.get("INF_DESCRICAO");
		boolean parcialMatch = false;
		if(info_name.toLowerCase().contains(term.toLowerCase()) || info_descricao.toLowerCase().contains(term.toLowerCase()) || term.isEmpty())
			parcialMatch = true;

		return parcialMatch;
	}

	private static String getRepositoryName() throws IOException {
		if (repName == null) {
			Properties prop = new Properties();
			InputStream input = null;

			String name = new java.io.File(Repository.class.getProtectionDomain()
					.getCodeSource()
					.getLocation()
					.getPath())
					.getName();

			int pos = name.lastIndexOf(".");
			if (pos > 0) {
				name = name.substring(0, pos);
			}
            //input = new FileInputStream("/home/rgarcia/storage/workspace/Tese-Repositorio/MuseumVictoria/src/main/java/museumvictoria.properties");
			input = new FileInputStream("E:/Code/Tese-Repositorio/EstatuariaLisboa/src/main/java/estatuarialisboa.properties");
			prop.load(input);

			repName = prop.getOrDefault("Name", "").toString();
		}

		return repName;
	}

}
