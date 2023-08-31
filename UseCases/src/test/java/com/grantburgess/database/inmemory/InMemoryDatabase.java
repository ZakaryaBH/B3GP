package com.grantburgess.database.inmemory;

import com.grantburgess.ports.database.Database;
import com.grantburgess.ports.database.TaskGateway;

public class InMemoryDatabase implements Database {
    private TaskGateway taskGateway = new InMemoryTaskGateway();

    public TaskGateway taskGateway() {
        return taskGateway;
    }
}
