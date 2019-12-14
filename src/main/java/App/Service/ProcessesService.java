package App.Service;

import App.Dao.ProcessesDAO;
import App.Model.Processes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ProcessesService {

    @Autowired
    private ProcessesDAO processesDAO;

    public Collection<Processes> getAllProcesses(){
        return processesDAO.getAllProcesses();
    }
}
