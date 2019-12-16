package App.Entity;

public class Processes {

    public int PID;
    public String name;
//    private String sessionName;
//    private int memUsage;


    public Processes(String name, int PID) {
        this.name = name;
        this.PID = PID;
    }

//    public Processes(int PID, String name, String sessionName, int memUsage) {
//        this.PID = PID;
//        this.name = name;
//        this.sessionName = sessionName;
//        this.memUsage = memUsage;
//    }
}
