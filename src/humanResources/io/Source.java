package humanResources.io;

public interface Source<T> {
    /*
    Создайте интерфейс Source<T>, описывающий 4 метода:
- public void load(T t) - восстановление состояния объекта из некоторого источника
- public void store(T t) – запись состояния объекта в некоторый источник
- delete(T t) – удаляющий информацию о состоянии объекта из некоторого
источника
- create(T t) – создающий новый источник, хранящий состояние объекта
     */
    public void load(T t);
    public void store(T t);
    void delete(T t);
    void create(T t);
}
