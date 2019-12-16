package App.Controller;

import App.Entity.Processes;
import App.Service.ProcessesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class ProcessController {

//    @Autowired
//    private ProcessesService processesService;
//
//    @RequestMapping()
//    public Collection<Processes> getAllProcesses() {
//        return processesService.getAllProcesses();
//    }
//
//    {
//
//    }
//
//    @RequestMapping("/")
//    @ResponseBody
//    public String welcome() {
//        return "<html><body><h1>Hi there</h1></body></html>";
//    }

    @Value("${initial.message}")
    private String message;

    @GetMapping(value ={"/doit"})
    public List<Processes> hello(Model model) {



        List<String> processList = new ArrayList<>();
//
        List <Processes> pr = new ArrayList<>();

//
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

        pr = processListFormatter(processList);

        model.addAttribute(pr);
        model.addAttribute("message", message);

        return pr;



//        Map<Processes> listOfProcesses = new HashMap();
//
    }

    List<Processes> processListFormatter(List<String> list) {

        int space = 0;
        int middle = 0;

        List<Processes> newList = new ArrayList<>();

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

            newList.add(new Processes(processFinder, PID));
                    //processFinder + "|" + PID);
        }
        return newList;
    }
}






