/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package similarity;

import core.Categorizable;

/**
 *
 * @param <T> 
 * @author c00kiemon5ter
 */
public interface Similarity<T extends Categorizable> {

	public double distance(T a, T b);
}
