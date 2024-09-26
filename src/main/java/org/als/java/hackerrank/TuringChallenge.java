package org.als.java.hackerrank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TuringChallenge {

    public void test() {
        List l = new ArrayList();
        l.add("1");
        l.add("2");
        l.add("3");

        String s = "Hola Mundo";

        String[] ss = s.split("");
        for( String subs : ss ){
            p("Splited: " + subs);
        }

        Integer i = 12;

        Map<Integer, String> m = new HashMap<>();
        m.put(1,"a");
        m.put(2,"b");
        m.put(3,"c");

        p(l);
        p(s);
        p(i);
        p(m);
    }


    public static void main( String[] args ){
        TuringChallenge turing = new TuringChallenge();

        turing.test();
    }

    public void p(String o ){
        System.out.println( o );
    }
    public void p(int o ){
        System.out.println( o );
    }
    public void p(List o ){
        System.out.println( o );
    }
    public void p(Map o ){
        System.out.println( o );
    }
}
