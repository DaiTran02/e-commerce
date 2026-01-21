package ecommerce.core.user.common;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public abstract class AbstractCrudService<T,ID> {
	protected abstract JpaRepository<T, ID> getRepository();
	
	public T create(T entity) {
		return getRepository().save(entity);
	}
	
	public T update(T entity) {
		return getRepository().save(entity);
	}
	
	public T getById(ID id) {
		return getRepository().findById(id).orElse(null);
	}
	
	public List<T> getAll(){
		return getRepository().findAll();
	}
	
	public void delete(ID id) {
		getRepository().deleteById(id);
	}

}
