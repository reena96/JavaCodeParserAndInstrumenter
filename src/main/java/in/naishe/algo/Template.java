package in.naishe.algo;

import java.util.Map;

//Map<String,String> myMap = new HashMap<String,String>();
public class Template {
    public static void instrum(String lineNumber, String statement_type, Object ... args)
    {
        String all_arguments="";
        //String v1, Object variable, String v2, Object variable2, String v3, Object variable3){
        for (int i = 0; i < args.length; ++i) {
            Object argument = args[i];
            all_arguments +=" , "+argument.toString();
        }
        System.out.println("Line Number: "+lineNumber+" Instrumented Statement Type : "+statement_type+"  "+ all_arguments);
    }
}