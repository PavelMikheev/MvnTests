package org.example.util;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class DataProviders {
    @DataProvider
    public static Iterator<Object[]> dataProviderFirst() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                DataProviders.class
                        .getResourceAsStream("/dataFirst.data")));

        List<Object[]> userData = new ArrayList<Object[]>();
        String line = in.readLine();
        while (line != null) {
            userData.add(line.split(";"));
            line = in.readLine();
        }
        in.close();
        return userData.iterator();
    }


    @DataProvider
    public static Iterator<Object[]> dataProviderSecond() {
        List<Object[]> data = new ArrayList();
        data.add(new Object[]{"", "Perfect10)", "Missing email"});
        data.add(new Object[]{"FFEFWF", "Perfect10)", "There isn't an account for this username"});
        data.add(new Object[]{"mikheev65@gma", "Perfect10)", "There isn't an account for this email"});

        return data.iterator();
    }


    @DataProvider
    public Iterator<Object[]> dataProviderThird() {
        List<Object[]> data = new ArrayList();

        for (int i = 0; i < 4; ++i) {
            data.add(new Object[]{this.generateRandomName(), this.generateRandomPassword()});
        }

        return data.iterator();
    }

    private Object generateRandomName() {

        return "demo" + (new Random()).nextInt() + "@gmail.com";
    }

    private Object generateRandomPassword() {

        return "pass" + (new Random()).nextInt();
    }

    @DataProvider
    public Iterator<Object[]> dataProviderCreateList() {
        List<Object[]> data = new ArrayList();

        for (int i = 0; i < 4; ++i) {
            data.add(new Object[]{this.generateRandomListName()});
        }

        return data.iterator();
    }


    public static String generateRandomListName(){
    //private Object generateRandomListName() {

        char[] alphabet =
                "abcdefghijklmnopqrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ1234567890".toCharArray();
                 Random random = new Random();
                 Random gen = new Random();
                int num = 2 + random.nextInt(9 - 2);
                String result = "";
                   for (int i=0; i < num; i++) {
        result = result + alphabet[gen.nextInt(alphabet.length)];

              }
          return result;

       // return new Random().nextInt() + "@gmail.com";
    }




  /* @DataProvider
    public static Iterator<Object[]> dataProviderCreateList() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                DataProviders.class
                        .getResourceAsStream("/createList.data")));

        List<Object[]> userData = new ArrayList<Object[]>();
        String line = in.readLine();
        while (line != null) {
            userData.add(line.split(";"));
            line = in.readLine();
        }
        in.close();
        return userData.iterator();
    } */


}

