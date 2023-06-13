package com.person;
//import com.person.Man;
public class Woman extends Person{
    private Man partner;
    private String previousLastName;

    public Woman(String firstName, String lastName, int age) {
        super(firstName, lastName, age);
    }

    public Man getPartner() {
        return partner;
    }

    public Man setPartner(Man partner) {
        return this.partner = partner;
    }

    public boolean isRetired() {
        return getAge() >= 60;
    }

    public boolean registerPartnership(Man man) {
        setPreviousLastName(getLastName());
        setLastName(man.getLastName());
        man.setPartner(this);
        return true;
    }

    public String setPreviousLastName(String lastName) {
        this.previousLastName = lastName;
        return this.previousLastName;
    }

    public String deregisterPartnership(boolean returnToPreviousLastName) {
        if (getPartner() != null && returnToPreviousLastName) {
            setLastName(this.previousLastName);
            partner.setPartner(null);
            setPartner(null);
            return null;
        }else{
            //setLastName(this.partner.getLastName());
            return setLastName(this.partner.getLastName());
        }
    }

}
