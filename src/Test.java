//import java.util.Arrays;
//import java.util.StringTokenizer;
//
///**
// * Created by Roman Maximov on 16.10.2017
// */
//public class Test {
//    //Конвертируем String в char и char в String на java
//    public static void main(String[] args){
//        String a = "a.b";//ресурс
//        String str = "a.b.c";//передаю
//        boolean h = false;
//        int count = 0;
//        StringTokenizer st = new StringTokenizer(str, "\\.");
//        StringTokenizer at = new StringTokenizer(a, "\\.");
//        String[] strin = new String[st.countTokens()];
//        String[] atrin = new String[at.countTokens()];
//        if (strin.length < atrin.length ) {System.exit(4);}
//        for (int i = 0; i < atrin.length; i++) {
//            atrin[i] = (String) at.nextToken();
//        }
//        for (int j = 0; j < strin.length; j++) {
//                strin[j] = (String) st.nextToken();
//            }
//        for (int i = 0;i<atrin.length;i++)
//        {
//            if (atrin[i].equals(strin[i])) {
//                h=true;
//                System.out.println(strin[i]);
//                System.out.println(atrin[i]);
//            } else {h=false;count++;}
//            if (count >=1) {
//                System.exit(4);
//            }
//        }
//              //  System.out.println(atrin[j]);
//
////                if (atrin[j].equals(strin[i]))
////                {
////                    h = true;
////                } else {h=false;
////                count++;}
////              if (count >= 1) {System.exit(4);}
//
//        }}
//
//
//
//
////выводим результат
//
//
