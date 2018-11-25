package humanResources.io;

import humanResources.*;

public class ControlledDepartmentManager extends DepartmentsManager {

    protected Source<EmployeeGroup> source;

    public ControlledDepartmentManager(String name) {
        super(name);
    }

    public ControlledDepartmentManager(String name, EmployeeGroup[] groups) {
        super(name, groups);
    }

    // говно блять
}
