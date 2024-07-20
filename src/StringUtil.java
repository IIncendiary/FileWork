import java.time.Duration;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class StringUtil {
    public  static  List<String> stringtoArray (String string){
        List<String> listofStrings ;
        String[] stringArray = string.split("\\n");
        listofStrings = Arrays.asList(stringArray);
        return listofStrings;
    }
    public  static TreeMap<String, LocalTime> listToMap (List<String> listofStrings){
        Map <String,CharSequence> map;
        map = listofStrings.stream().collect(Collectors.toMap(
                e -> e.substring(0,3),
                e -> e.substring((e.lastIndexOf('_')+1))
        ));
        TreeMap<String, LocalTime> map2 = new TreeMap<>();
        Iterator<Map.Entry<String,CharSequence>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,CharSequence> entry = iterator.next();
            map2.put(entry.getKey(), LocalTime.parse(entry.getValue()));
        }
        return map2;
    }
    public static TreeMap<String,String> mapstoOne (TreeMap<String, LocalTime> end , TreeMap<String, LocalTime> start){
        TreeMap<String,String> map = new TreeMap<>();
        Iterator<Map.Entry<String,LocalTime>> iterator = end.entrySet().iterator();
        Iterator<Map.Entry<String,LocalTime>> iterator1 = start.entrySet().iterator();
        while (iterator.hasNext()&&iterator1.hasNext()){
            Map.Entry<String,LocalTime> entry = iterator1.next();
            Map.Entry<String,LocalTime> entry1 = iterator.next();
            map.put(entry.getKey(),StringUtil.diff(entry1.getValue(),entry.getValue()));
        }
        return map;
    }
    private static String diff(LocalTime start, LocalTime end){
        String diff;
        Duration duration = Duration.between(end, start);
        long minutes = duration.toMinutes()%60;
        long seconds = duration.toSeconds()%60;
        long milliseconds = duration.toMillis()%1000;
        diff = String.format("%02d.%02d.%03d", minutes, seconds, milliseconds);
        return diff;
    }
    public static Map<String, String> abbreviationsName (String string){
        List<String> listofStrings ;
        String[] stringArray = string.split("\\n");
        listofStrings = Arrays.asList(stringArray);
        Map <String,String> map;
                map = listofStrings.stream().collect(Collectors.toMap(
                e -> e.substring(0,3),
                e -> e.substring((e.lastIndexOf('_')+1))
        ));
        return map;}
    public static  Map<String, String> abbreviationsCar (String string){
        List<String> listofStrings ;
        String[] stringArray = string.split("\\n");
        listofStrings = Arrays.asList(stringArray);
        Map <String,String> map;
        map = listofStrings.stream().collect(Collectors.toMap(
                e -> e.substring(0,3),
                e->e.substring((e.indexOf('_')+1),(e.lastIndexOf('_')-1)),
                (existing,replasement) -> replasement
        ));
        return map;}
}
