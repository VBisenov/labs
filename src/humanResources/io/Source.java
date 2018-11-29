package humanResources.io;

public interface Source<T> {
    public void load(T t);
    public void store(T t);
    void delete(T t);
    void create(T t);
}
