package App.Controller;

import App.Entity.Processes;
import App.Service.ProcessesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class Controller {

    @Autowired
    private ProcessesService processesService;

    public Collection<Processes> getAllProcesses() {
        return processesService.getAllProcesses();
    }

    {

    }

    @RequestMapping("/")
    @ResponseBody
    public String welcome() {
        return "<html><body><h1>Hi there</h1></body></html>";
    }

    @RequestMapping("/doit")
    @ResponseBody
    public List<String> hello() {

        List<String> processList = new ArrayList<>();

        try {

            Process process = Runtime.getRuntime().exec("tasklist.exe");
            Scanner scanner = new Scanner(new InputStreamReader(process.getInputStream()));
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
                processList.add(scanner.nextLine());
            }
            scanner.close();
        } catch (Exception err) {
            System.out.println("No next line");
        }

        return processListFormatter(processList);
    }

    List<String> processListFormatter(List<String> list) {

        int space = 0;
        int middle = 0;

        List<String> newList = new ArrayList<>();

        //check start
        if (list.get(1).contains("   ")) {
            space = list.get(1).indexOf("   ");
        } else if (list.get(1).contains("   ")) {
            space = list.get(1).indexOf("   ");
        }

        if (space == 0) {
            System.out.println("No Services or Console found, reload processes!");
        }

        //check end of PID
        if (list.get(1).contains(" Services")) {
            middle = list.get(1).indexOf(" Services");
        } else if (list.get(1).contains(" Console")) {
            middle = list.get(1).indexOf(" Console");
        }

        if (middle == 0) {
            System.out.println("No Services or Console found, reload processes!");
        }
        for (int i = 1; i < list.size(); i++) {

            int PID = 0;
            String regexPID = "[0-9]+";

            String firstPart = (list.get(i).substring(space, middle));
            String processFinder = (list.get(i).substring(0, space).trim());

            Pattern pattern = Pattern.compile(regexPID);
            Matcher matcher = pattern.matcher(firstPart);

            while (matcher.find()) {
                PID = Integer.valueOf(matcher.group());
            }
            newList.add(processFinder + "|" + PID);
        }
        return newList;
    }
}






