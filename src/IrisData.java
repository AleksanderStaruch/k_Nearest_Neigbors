import java.io.File;
import java.util.*;

public class IrisData {
    private List<Point> list = new ArrayList<>();
    private Set<String> atrybutyDecyzyjne= new TreeSet<>();

    private static void readFile(String path,List<Point> l){
        try{
            File file = new File(path);
            Scanner od = new Scanner(file);
            String linia;
            String[] tmp;
            while(od.hasNext()){
                linia=od.nextLine();
                tmp=linia.split(",");
                double[] tab=new double[tmp.length-1];
                for(int i=0;i<tmp.length-1;i++){
                    tab[i]=Double.parseDouble(tmp[i]);
                }
                l.add(new Point(tab,tmp[tmp.length-1]));
            }
            od.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    /**
     * robisz to uczenie z nadzorem
     * jako dane ntreningowe bierzesz z pliku iris.data.normalized.txt
     * a puzniej sprawdzasz czy dane iris.data.normalized.test.txt sa poprawne
     * a przy sprwadzaniu musisz wybrac ilu najlizszy sasiadow chcesz (tu chodszi o 'k')
     * i gdy znajdziesz tego co sasiada co ma najwiecej polaczeń (z najkrótsza drogą) jego nazwa odpowiedzią
     * trzeba tez sprawdzać czy nie ma przypadku gdy mamy 2 lub wiecej sasiadow z taka sama liczba poloaczen
     *
     */

    public IrisData(String path) {
        try{
            File file = new File(path);
            Scanner od = new Scanner(file);
            String linia;
            String[] tmp;
            while(od.hasNext()){
                linia=od.nextLine();
                tmp=linia.split(",");

                atrybutyDecyzyjne.add(tmp[tmp.length-1]);

                double[] tab=new double[tmp.length-1];
                for(int i=0;i<tmp.length-1;i++){
                    tab[i]=Double.parseDouble(tmp[i]);
                }
                list.add(new Point(tab,tmp[tmp.length-1]));
            }
            od.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void showDataTrening(){
        list.forEach(System.out::println);
        atrybutyDecyzyjne.forEach(System.out::println);
    }

    public void checkDataTestAccuracy(int k,String path){
        List<Point> dataTest=new ArrayList<>();
        readFile(path,dataTest);
        List<AtrybutDecyzyjny> AB= new ArrayList<>();
        int accuracy=0;
        for(String s:atrybutyDecyzyjne){
            AB.add(new AtrybutDecyzyjny(s));
        }

        for(Point p:dataTest){
            String name="";
            List<Len> len= new ArrayList<>();
            for(AtrybutDecyzyjny s:AB){ s.setIlosc(0); }

            for(Point t:list){
                double d=0;
                try { d=Point.length(p,t); }
                catch (Exception e) { e.printStackTrace(); }
                len.add(new Len(t.getName(),d));
            }
            len.sort(Comparator.comparingDouble(Len::getL));
            for(int i=0;i<k;i++){
                System.out.println(len.get(i));
                for(AtrybutDecyzyjny s:AB){
                    if(len.get(i).getName().equals(s.getName())){
                        s.setIlosc(s.getIlosc()+1);
                    }
                }
            }
            int max=-1;
            for(AtrybutDecyzyjny s:AB){
                if(max<s.getIlosc()){
                    max=s.getIlosc();
                    name=s.getName();
                }
            }


            List<String> tmp=new ArrayList<>();
            for(AtrybutDecyzyjny s:AB){
                if(max==s.getIlosc()){
                    tmp.add(s.getName());
                }
            }
            //w przypadku gdy mamy 2 lub wiecej sasiadow z taka sama liczba poloaczen
            boolean stop=false;
            if(tmp.size()!=1){
                for(Len l:len){
                    for (String s:tmp){
                        //wybiera tego sasiada ktory jest najblizej i ma najweicej połaczeń
                        if(l.getName().equals(s)){
                            name=l.getName();
                            stop=true;
                        }
                    }
                    if(stop){
                        break;
                    }
                }
            }


            if(p.getName().equals(name)){
                System.out.println(p+" +");
                accuracy++;
            }else{
                System.out.println(p+" -");
            }
        }
        System.out.println(accuracy);
    }

    public void ownPoint(Point p,int k){
        List<AtrybutDecyzyjny> AB= new ArrayList<>();
        for(String s:atrybutyDecyzyjne){
            AB.add(new AtrybutDecyzyjny(s));
        }

        String name="";
        List<Len> len= new ArrayList<>();
        for(AtrybutDecyzyjny s:AB){ s.setIlosc(0); }

        for(Point t:list){
            double d=0;
            try { d=Point.length(p,t); }
            catch (Exception e) { e.printStackTrace(); }
            len.add(new Len(t.getName(),d));;
        }
        len.sort(Comparator.comparingDouble(Len::getL));
        for(int i=0;i<k;i++){
            System.out.println(len.get(i));
            for(AtrybutDecyzyjny s:AB){
                if(len.get(i).getName().equals(s.getName())){
                    s.setIlosc(s.getIlosc()+1);;
                }
            }
        }
        int max=-1;
        for(AtrybutDecyzyjny s:AB){
            if(max<s.getIlosc()){
                max=s.getIlosc();
                name=s.getName();
            }
        }
        List<String> tmp=new ArrayList<>();
        for(AtrybutDecyzyjny s:AB){
            if(max==s.getIlosc()){
                tmp.add(s.getName());
            }
        }
        //w przypadku gdy mamy 2 lub wiecej sasiadow z taka sama liczba poloaczen
        boolean stop=false;
        if(tmp.size()!=1){
            for(Len l:len){
                for (String s:tmp){
                    //wybiera tego sasiada ktory jest najblizej i ma najweicej połaczeń
                    if(l.getName().equals(s)){
                        name=l.getName();
                        stop=true;;
                    }
                }
                if(stop){
                    break;
                }
            }
        }
        System.out.println(name);
    }




}
