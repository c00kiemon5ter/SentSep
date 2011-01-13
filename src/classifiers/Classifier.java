package classifiers;

import core.Categorizable;
import java.util.List;
import similarity.Similarity;

/**
 *
 * @param <T>
 * @author c00kiemon5ter
 */
public interface Classifier<T extends Categorizable> {

	public void train(List<T> trainset);

	public List<T> classify(List<T> tests, Similarity<T> similarity);

	public T classify(T test, Similarity<T> similarity);
}
