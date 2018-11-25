package humanResources.io;

import humanResources.Department;
import humanResources.Employee;
import humanResources.JobTitlesEnum;

import java.util.Collection;

public class ControlledDepartment extends Department {
    /*
    Создайте класс ControlledDepartment, расширяющий класс Department. Он содержит такие
же конструкторы, что и суперкласс (и просто вызывает их в своих конструкторах).
Добавьте один конструктор, принимающий объект по ссылке Department, и
инициализирующий текущий объект данными из переданного в качестве параметра
заказами.
Он добавляет одно поле
- protected boolean isChanged
, отражающее тот факт, что состояние объекта после создания было изменено. Значение поумолчанию,
разумеется, false.
и один метод
- protected boolean isChanged(), возвращающий непосредственно значение переменной
isChanged.
, а также переопределяет методы, которые так или иначе меняют состояние класса
(добавление, удаление сотрудников, изменение названия) – в этих методах сначала
вызывается реализация метода в суперклассе, а затем изменяется isChanged на true.
     */
    protected boolean isChanged;

    public ControlledDepartment(String name) {
        super(name);
    }

    public ControlledDepartment(String name, int capacity) {
        super(name, capacity);
    }

    public ControlledDepartment(String name, Department department) {
        super(name, department.getEmployees());
    }

    public boolean isChanged() {
        return isChanged;
    }

    @Override
    public void setName(String name) {
        isChanged = true;
        super.setName(name);
    }

    @Override
    public boolean add(Employee employeeToAdd) {
        isChanged = true;
        return super.add(employeeToAdd);
    }

    @Override
    public void add(int index, Employee element) {
        isChanged = true;
        super.add(index, element);
    }

    @Override
    public boolean addAll(Collection<? extends Employee> c) {
        isChanged = true;
        return super.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends Employee> c) {
        isChanged = true;
        return super.addAll(index, c);
    }

    @Override
    public boolean remove(Object o) {
        isChanged = true;
        return super.remove(o);
    }

    @Override
    public boolean remove(String firstName, String secondName) {
        isChanged = true;
        return super.remove(firstName, secondName);
    }

    @Override
    public boolean remove(Employee employee) {
        isChanged = true;
        return super.remove(employee);
    }

    @Override
    public boolean remove(JobTitlesEnum jobTitle) {
        isChanged = true;
        return super.remove(jobTitle);
    }

    @Override
    public Employee remove(int index) {
        isChanged = true;
        return super.remove(index);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        isChanged = true;
        return super.removeAll(c);
    }
}
