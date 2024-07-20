import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try {
            Path path = Paths.get("end.log");
            Path path1 = Paths.get("start.log");
            Path path2 = Paths.get("abbreviations.txt");
            String end = Files.readString(path);
            String start = Files.readString(path1);
            String abbreviation = Files.readString(path2);
            Map<String,String> m1 ;
            List<String> endList = new ArrayList<>();
            List<String> startList = new ArrayList<>();
            endList = StringUtil.stringtoArray(end);
            startList = StringUtil.stringtoArray(start);
            TreeMap<String, LocalTime> endMap = new TreeMap<>();
            TreeMap<String, LocalTime> startMap = new TreeMap<>();
            endMap = StringUtil.listToMap(endList);
            startMap = StringUtil.listToMap(startList);
            Map<String,String> diff = new TreeMap<>();
            diff = StringUtil.mapstoOne(endMap,startMap);
            System.out.println(diff);
            List<Map.Entry<String,String>> list = new ArrayList<>(diff.entrySet());
            list.sort(Map.Entry.comparingByValue());
            Map<String,String> diffLink = new LinkedHashMap<>();
            for (Map.Entry<String, String> entry:list){
                diffLink.put(entry.getKey(),entry.getValue());
            }
            System.out.println(diffLink);
            Map<String,String> abbreviationsCar ;
            Map<String,String> abbreviationsNames ;
            abbreviationsCar = StringUtil.abbreviationsCar(abbreviation);
            abbreviationsNames = StringUtil.abbreviationsName(abbreviation);
            Iterator<Map.Entry<String,String>> lastcycle = diffLink.entrySet().iterator();
            int i = 1;
            while (lastcycle.hasNext()){
                Map.Entry<String,String> entry = lastcycle.next();
                System.out.println(i+"."+abbreviationsCar.get(entry.getKey())+" |"+abbreviationsNames.get(entry.getKey())+" |"+entry.getValue());
                if (i==15) {
                    System.out.println("--------------------------------------------------");
                }
                i++;
            }

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}