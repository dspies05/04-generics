package ohm.softa.a04;

import java.util.function.Function;

public interface SimpleList<T> extends Iterable<T> {


	void addDefault();
	/**
	 * Add a given object to the back of the list.
	 */
	void add(T o);

	/**
	 * @return current size of the list
	 */
	int size();

	/**
	 * Generate a new list using the given filter instance.
	 * @return a new, filtered list
	 */
	@SuppressWarnings("unchecked")
	default SimpleList<T> filter(SimpleFilter<T> filter){
		SimpleList<T> result ;
		try {
			result = (SimpleList<T>) getClass().getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			result = new SimpleListImpl<>();
		}
		for(T o : this){
			if(filter.include(o)){
				result.add(o);
			}
		}
		return result;
	}
	default <R> SimpleList<R> map(Function<T, R> transform){
		SimpleList<R> result ;
		try {
			result = (SimpleList<R>) getClass().getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			result = new SimpleListImpl<>();
		}
		for(T item : this){
			result.add((R) transform.apply(item));
		}
		return result;
	}
}
