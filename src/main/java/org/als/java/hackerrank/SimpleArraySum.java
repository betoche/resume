package org.als.java.hackerrank;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class SimpleArraySum implements Comparable {

    public static int simpleArraySum(List<Integer> ar) {
        return ar.stream().reduce(0, Integer::sum);
    }

    public static void methodTest(){
        Collection<String> col = new LinkedList<>();
        col.add("1");
        col.add("2");
        col.add("3");
        col.forEach(System.out::println);
        Set s = new TreeSet();
        s.add(1);
        System.out.println(s);

        double x = 22.9;
List l = new ArrayList();
List l2 = new ArrayList(l);

        System.out.println( Math.round(x) );

        Thread t = new Thread(() -> {System.out.println("hola");});
        t.start();
        t.start();
        p(t.getState().toString());
    }

    public static void p( String s ){
        System.out.println(s);
    }



    public static void main(char[] args) throws IOException {

    }
    public static void main(String[] args) throws IOException {
        SimpleArraySum.methodTest();
        /*
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        //int arCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> ar = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int result = SimpleArraySum.simpleArraySum(ar);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();

         */
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
