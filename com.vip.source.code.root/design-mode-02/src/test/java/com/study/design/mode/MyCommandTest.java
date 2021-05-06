package com.study.design.mode;

import com.study.design.mode.annotation.IConvert;
import com.study.design.mode.samples.command.*;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 功能描述: <br>
 *
 * @author zjx
 * @version 1.0.0
 * @date 2021/4/19 15:34
 */
public class MyCommandTest {


    @Test
    public void myTest2() {
        Client.waiter();
    }


    @Test
    public void myTest() {
        LocalDate nowMoth = LocalDate.now();
        LocalDate previousMonth = LocalDate.now().plusMonths(-1);

        System.out.println(LocalDate.now().plusMonths(-1).with(TemporalAdjusters.firstDayOfMonth()).toString());

    }

    @Test
    public void myTest3() {
        List<Customer> list = new ArrayList<>();
        Customer customer = new Customer();
        customer.setId("1");
        customer.setName("a");
        list.add(customer);
        customer = new Customer();
        customer.setId("2");
        customer.setName("b");
        list.add(customer);
        customer = new Customer();
        customer.setId("3");
        customer.setName("c");
        list.add(customer);
        customer = new Customer();
        customer.setId("4");
        customer.setName("d");
        list.add(customer);

        List<String> integers = new ArrayList<>();
        integers.add("1");
        integers.add("4");
        integers.add("5");

        //取差集
//        List<Integer> integerss = integers.stream().filter(integer ->
//                !list.stream().map(Customer::getId).collect(Collectors.toList()).contains(integer))
//                .collect(Collectors.toList());
//        System.out.println(integerss);
//        List<Customer> listw = list.stream().filter(customer1 -> !integers.contains(customer1.getId())).collect(Collectors.toList());
//        System.out.println(listw);
//    }
        //取交集
        List<String> integerss = integers.stream().filter(integer ->
                list.stream().map(Customer::getId).collect(Collectors.toList()).contains(integer))
                .collect(Collectors.toList());
        System.out.println(integerss);
//    List<Customer> listw = list.stream().filter(customer1 -> !integers.contains(customer1.getId())).collect(Collectors.toList());
//        System.out.println(listw);
    }
    @Test
    public void myTest4() {
        IConvert<String,String> convert = Something::startsWith;
        convert.convert("123");
    }

}

class Customer {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


class Something {

    // constructor methods
    Something() {
        System.out.println("Something ");
    }

    Something(String something) {
        System.out.println("Something "+something);
    }

    // static methods
    static String startsWith(String s) {
        System.out.println("startsWith "+s);
        return String.valueOf(s.charAt(0));
    }

//    object methods
    String endWith(String s) {
        System.out.println("endWith "+s);
        return String.valueOf(s.charAt(s.length()-1));
    }

    void endWith() {
        System.out.println("endWith ");
    }
}
