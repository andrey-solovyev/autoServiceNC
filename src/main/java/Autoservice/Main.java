package Autoservice;

import Autoservice.Logics.Logic;
import com.sun.nio.sctp.SctpStandardSocketOptions;


public class Main {

    public static void main(String[] args) {
        Logic logic=new Logic(2,8,4);
        logic.startApplication();
	// write your code here
    }
}
