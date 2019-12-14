package App.Dao;

import App.Model.Processes;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ProcessesDAO {

    private static Map<Integer, Processes> processes;

    public Collection<Processes> getAllProcesses() {
        return this.processes.values();
    }


}
