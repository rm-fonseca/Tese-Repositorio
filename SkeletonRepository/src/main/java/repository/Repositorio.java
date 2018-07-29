package repository;

import java.util.List;
import plataforma.modelointerno.Result;

public class Repositorio implements RepositoryAbstract {

	@Override
	public List<Result> SearchByBox(int latitudeFrom, int latitudeTo, int longitudeFrom, int longitudeTo,
			boolean ignoreExtraProperties) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Result> SearchByTerm(String term, boolean ignoreExtraProperties) throws Exception {
		throw new UnsupportedOperationException();
	}

}
