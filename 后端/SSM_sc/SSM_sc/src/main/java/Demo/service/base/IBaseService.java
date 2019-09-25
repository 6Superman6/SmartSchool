package Demo.service.base;

import java.util.List;

public interface IBaseService<T> {

    public String insert(T t);

    public String delete(Integer id);
    public String deleteByUUid(String uuid);

    public String update(T t);

    public T selectByUUid(String uuid);
    public T select(Integer id);

    public T selectByUsername(String username);

    public List<T> findAll();

    public int getCountByUUid(String uuid);
    public int getCountById(Integer id);

}