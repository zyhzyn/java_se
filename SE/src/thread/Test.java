package thread;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;

public class Test {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        ArrayList<Integer> l=new ArrayList<>();
        l.add(1);
        l.add(2);
        l.add(3);
        l.add(4);
        l.add(5);
        Iterator<Integer> i=l.iterator();
        while (i.hasNext()){
            Integer in=i.next();
            if (in==2){
                l.remove(in);
            }
        }
    }
}
