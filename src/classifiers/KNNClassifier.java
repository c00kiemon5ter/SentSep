package classifiers;

import core.Categorizable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import similarity.Similarity;

/**
 * 
 * @param <T>
 */
public class KNNClassifier<T extends Categorizable> implements Classifier<T> {

	private final int k;
	private List<T> trainset;

	public KNNClassifier(int k) {
		if (k % 2 != 0) {
			this.k = k;
		} else {
			throw new IllegalArgumentException(String.format("==> ERROR: k-factor should be an odd integer: %d", k));
		}
	}

	public int getK() {
		return k;
	}

	@Override
	public void train(List<T> trainset) {
		this.trainset = new ArrayList<T>(trainset);
	}

	@Override
	public List<T> classify(List<T> tests, Similarity<T> similarity) {
		List<T> classified = new ArrayList<T>(tests.size());
		for (T test : tests) {
			classified.add(classify(test, similarity));
		}
		return classified;
	}

	@Override
	public T classify(T test, Similarity<T> similarity) {
		Map<T, Double> neighborhood = new HashMap<T, Double>(trainset.size());
		for (T train : trainset) {
			neighborhood.put(train, similarity.distance(train, test));
		}
		List<Entry<T, Double>> neighbors = sortByDistance(neighborhood);
		Collections.reverse(neighbors);

		int categcnt = 0;
		for (int idx = 0; idx < this.k && idx < neighbors.size(); idx++) {
			if (neighbors.get(idx).getKey().getCategory()) {
				++categcnt;
			}
		}
		test.setCategory(this.k - categcnt < categcnt);
		return test;
	}

	private List<Entry<T, Double>> sortByDistance(Map<T, Double> neighborhood) {
		List<Entry<T, Double>> sortedlist = new ArrayList<Entry<T, Double>>(neighborhood.entrySet());
		Collections.sort(sortedlist, new Comparator<Entry<T, Double>>() {

			@Override
			public int compare(Entry<T, Double> a, Entry<T, Double> b) {
				return a.getValue().compareTo(b.getValue());
			}
		});
		return sortedlist;
	}
}
