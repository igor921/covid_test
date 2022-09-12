package org.computools.covid.output;

//Output data using console
public class ConsoleOutput implements Output{

    @Override
    public void print(Object data) {
        System.out.println(data.toString());
    }
}
