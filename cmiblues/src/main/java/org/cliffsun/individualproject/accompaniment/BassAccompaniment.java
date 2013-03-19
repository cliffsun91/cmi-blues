package org.cliffsun.individualproject.accompaniment;

import java.util.List;

import org.cliffsun.individualproject.duration.Duration;
import org.cliffsun.individualproject.keys.Scale;
import org.cliffsun.individualproject.score.BassClefScoreLine;
import org.cliffsun.individualproject.utils.Pair;

public interface BassAccompaniment {

	BassClefScoreLine getScoreLine() throws Exception;
	List<Pair<Scale, Duration>> getForm();
}
