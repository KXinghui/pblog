package mapper;

import java.util.List;

public interface BaseMapper<T> {
	void insert(T t);

	void delete(Integer id);

	void update(T t);

	T get(Integer id);

	List<T> list();

	Integer count();
}
