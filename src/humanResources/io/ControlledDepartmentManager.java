package humanResources.io;

import humanResources.*;

import java.util.Collection;

public class ControlledDepartmentManager extends DepartmentsManager {
    /*
    Создайте класс ControlledDepartmentManager, расширяющий класс DepartmentManager. Он
содержит такие же конструкторы, что и суперкласс (и просто вызывает их в своих
конструкторах).
Он добавляет одно protected поле типа Source<EmployeeGroup>, а также гетер и сеттер для
него.
Также необходимо переопределить методы, которые так или иначе меняют состояние класса:
- add(), сначала, на основе группы сотрудников создается экземпляр
ControlledDepartmentManage, затем вызывается метод источника create(), а потом только
реализация метода add() в суперклассе.
- remove(), сначала вызывается метод источника delete(employeeGroup), a затем реализация
метода в суперклассе.
addAll(), removeAll(), retainAll() – аналогично.
     */
    protected Source<EmployeeGroup> source;

    public ControlledDepartmentManager(String name) {
        super(name);
    }

    public ControlledDepartmentManager(String name, EmployeeGroup[] groups) {
        super(name, groups);
    }

    public Source<EmployeeGroup> getSource() {
        return source;
    }

    public void setSource(Source<EmployeeGroup> source) {
        this.source = source;
    }

    public boolean add(EmployeeGroup group){
        /*
        todo
        Разобраться куда пихать этот экземпляр
        */
        EmployeeGroup controlledDepartment = new ControlledDepartment(group.getName(), (Department) group);
        source.create(group);
        super.add(group);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (!(o instanceof EmployeeGroup)) {
            return false;
        }
        source.delete((EmployeeGroup) o);
        return super.remove(o);
    }

    @Override
    public boolean addAll(int index, Collection<? extends EmployeeGroup> c) {

        for (EmployeeGroup group: c){
            source.create(group);
        }
        return super.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (!(c instanceof  EmployeeGroup)){
            return false;
        }
        for (Object o: c){
            source.delete((EmployeeGroup)o);
        }
        return super.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (!(c instanceof  EmployeeGroup)){
            return false;
        }
        for (Object o: c){
            if (!c.contains(o)){
                source.delete((EmployeeGroup) o);
            }
        }
        return super.retainAll(c);
    }


    /*
Он добавляет методы:
store() – проходит по всем группам сотрудников, определяет, изменилась ли та или иная
группа (isChanged), и если изменилась – перезаписывает файл группы вызывая метод
store(employeeGroup) на объекте Source<EmployeeGroup>.
load() – проходит по всем шруппам и восстанавливает их состояние из источника, вызывая
метод load(order).
     */

    void store(){
        for (EmployeeGroup group: this){
            if (((ControlledDepartment) group).isChanged){
                source.store(group);
                ((ControlledDepartment) group).isChanged = false;
            }
        }
    }

    void load(){
        for (EmployeeGroup group: this){
                source.load(group);
        }
    }

}
