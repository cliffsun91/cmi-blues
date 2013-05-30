package org.cliffsun.individualproject.accompaniment;

import java.util.List;

import org.cliffsun.individualproject.chord.Chord;
import org.cliffsun.individualproject.duration.Duration;
import org.cliffsun.individualproject.score.BassClefScoreLine;
import org.cliffsun.individualproject.utils.Pair;

public interface BassAccompaniment {

	BassClefScoreLine getScoreLine() throws Exception;
	List<List<Pair<Chord, Duration>>> getForm();
	int getNumberOfBars();
}
