package com.person;

import com.person.Man;
import com.person.Woman;

public class Main {
    public static void main(String[] args) {
        Man elon = new Man("Elon", "Mask", 51);
        Man bred = new Man("Bred", "Pit", 59);
        Man mel = new Man("Mel", "Gibson", 67);
        Woman angelina = new Woman("Angelina", "Jolie", 47);
        Woman sharon  = new Woman("Sharon", "Stone", 65);


        System.out.println(bred.getFirstName()+" "+bred.getLastName()+": "+bred.getAge()+ " age");
        System.out.println(angelina.getFirstName()+" "+angelina.getLastName()+": "+ angelina.getAge()+" age");
        bred.registerPartnership(angelina);
        System.out.println(bred.getFirstName()+" "+bred.getLastName()+" is partner "+ angelina.getFirstName()+" "+angelina.getLastName());

        if(mel.isRetired()){
            System.out.println(mel.getFirstName()+" "+mel.getLastName()+": "+ mel.getAge()+" age, he is retired.");
        }
        if(sharon.isRetired()){
            System.out.println(sharon.getFirstName()+" "+sharon.getLastName()+": "+ sharon.getAge()+" age, she is retired");
            sharon.setLastName("Grinberg");
            System.out.println("Sharon Stone change last name to - "+sharon.getLastName());
            sharon.setPreviousLastName("Stone");
            //sharon.deregisterPartnership(true);
            System.out.println("Sharon Stone return his last name - "+sharon.getLastName());
        }

        elon.setPartner(sharon);
        System.out.println(elon.getFirstName()+" "+elon.getLastName()+" is partner "+ sharon.getFirstName()+" "+sharon.getLastName());

    }
}
