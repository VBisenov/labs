package humanResources.io;

import humanResources.EmployeeGroup;

public class GroupsManagerFileSource implements FileSource {
    /*
Создайте абстрактный класс GrouspManagerFileSource, реализующий интерфейс FileSource.
Содержащий одно поле – путь к каталогу, в котором будут храниться файлы, содержащие
состояния проектов и департаментов. Он реализует только методы setPath() и getPath().
 */
    private String path;

    @Override
    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public void load(EmployeeGroup group) {

    }

    @Override
    public void store(EmployeeGroup group) {

    }

    @Override
    public void delete(EmployeeGroup group) {

    }

    @Override
    public void create(EmployeeGroup group) {

    }
}
