package com.pao.project.etapa1.model;

public class Pet {
    private final String id;
    private String name;
    private String specie;

    public Pet(String id, String name, String specie){
        this.id = id;
        this.name = name;
        this.specie = specie;
    }

    public double finalPrice(double price, double discount){
        return price * discount;
    }
    public String getPetId(){return id;}
}



















//// Should pet be type ? and need subclasses eg. Cat, Dog, Bird?
//public class Pet {
//    private String id;
//    private String slotid;
//    private String datetime;
//    private String duration;
//    private String servicetype;
//    private String staff;
//    private String status;
//    private String clientid;
//    private String clientname;
//    private String phone;
//    private String email;
//    private String petId;
//    private String petName;
//    private String petage;
//    private String medicalhistory;
//    private String microchip;
//    private String paymentmethod;
//    private String reason;
//    private String birthDate;
//    private String type; // i have previously learned if i don't have String type, i can do sth
//
//    // Owner
//    private Owner owner;
//
//
//
//    // constructor
//    public Pet(String petName){ this.petName = petName;}
//
//    // methods
//    public boolean isNew(){return false;}
//    public String getPet(String petId, String petName, boolean ignoreNew){return null ;} // return three things
//    public String getId(){return petId;}
//    public String getPetName(){return petName;}
//    public boolean ignoreNew(){return false;}
//
//    public void setBirthDate(String birthDate){this.birthDate = birthDate;}
//    public String getBirthDate(){return this.birthDate;}
//    public PetType getType() {
//        return this.type;
//    }
//
//    public void setType(PetType type) {
//        this.type = type;
//    }
//
//    public Owner getOwner() {
//        return this.owner;
//    }
//
//    protected void setOwner(Owner owner) {
//        this.owner = owner;
//    }
//
//    protected Set<Visit> getVisitsInternal() {
//        if (this.visits == null) {
//            this.visits = new HashSet<>();
//        }
//        return this.visits;
//    }
//
//    protected void setVisitsInternal(Set<Visit> visits) {
//        this.visits = visits;
//    }
//
//    public List<Visit> getVisits() {
//        List<Visit> sortedVisits = new ArrayList<>(getVisitsInternal());
//        sortedVisits.sort(Comparator.comparing(Visit::getDate).reversed());
//        return Collections.unmodifiableList(sortedVisits);
//    }
//
//    public void addVisit(Visit visit) {
//        getVisitsInternal().add(visit);
//        visit.setPet(this);
//    }
//
//    // Consultation
//    private Set<Consultation> consultations; // not sure about this
//
//}
