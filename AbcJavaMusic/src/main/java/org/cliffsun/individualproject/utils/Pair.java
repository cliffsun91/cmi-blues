package org.cliffsun.individualproject.utils;

import org.cliffsun.individualproject.chord.Chord;
import org.cliffsun.individualproject.duration.Duration;
import org.cliffsun.individualproject.keys.Scale;

public class Pair<L, R> {

	private final L left;
	private final R right;

	public Pair(L left, R right) {
		this.left = left;
		this.right = right;
	}

	public L getLeft() {
		return left;
	}

	public R getRight() {
		return right;
	}

	@Override
	public int hashCode() {
		return left.hashCode() ^ right.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (!(o instanceof Pair))
			return false;
		@SuppressWarnings("unchecked")
		Pair<L, R> pairo = (Pair<L, R>) o;
		return this.left.equals(pairo.getLeft())
				&& this.right.equals(pairo.getRight());
	}

	public static Pair<Scale, Duration> compPair(Scale scale, Duration duration) {
		return new Pair<Scale, Duration>(scale, duration);
	}

	public static Pair<Chord, Duration> compPair(Chord chord, Duration duration) {
		return new Pair<Chord, Duration>(chord, duration);
	}

}