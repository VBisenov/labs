package humanResources.io;

import humanResources.EmployeeGroup;

public interface FileSource extends Source<EmployeeGroup> {
    /*
    Создайте интерфейс FileSource, расширяющий интерфейс Source<Order> описывающий еще
2 метода:
- public void setPath(String path) – изменяющий путь к файлу, в который
записывается состояние объекта
- public String getPath) – возвращающий путь к файлу, в который записывается
состояние объекта
     */
    public void setPath(String path);
    public String getPath();
}
