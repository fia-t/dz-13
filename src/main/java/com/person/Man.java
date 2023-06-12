package com.person;

public class Man extends Person {
    private Woman partner;

    public Man(String firstName, String lastName, int age) {
        super(firstName, lastName, age);
    }
    public Woman getPartner() {
        return partner;
    }

    public Woman setPartner(Woman partner) {

        return this.partner = partner;
    }

    public boolean isRetired() {
        return getAge() >= 65;
    }

    public boolean registerPartnership(Woman woman) {
        woman.setLastName(getLastName());
        setPartner(woman);
        return true;
    }

    public String deregisterPartnership(boolean returnToPreviousLastName) {
        if (partner != null && returnToPreviousLastName) {
            partner.setLastName(partner.getPreviousLastName());
            setPartner(null);
            return null;
        }else{
            //setLastName(this.partner.seLastName());
            return this.partner.getLastName();
        }
    }
}
