package com.example.android.housetrinder.Model;

import java.util.ArrayList;

public class HouseExemple {

    private String Adress;
    private String Description;
    private int toilet_nb;
    private int room_nb;
    private boolean hasPool;
    private String imgLink;
    private float price;

    public int getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(int parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    private int parkingSpot;

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    private int area;

    public HouseExemple(){

    }
    HouseExemple(String a,String d,int t,int r,boolean p,String img,float price,int area,int parking){
        setAdress(a);
        setDescription(d);
        setToilet_nb(t);
        setRoom_nb(r);
        setHasPool(p);
        setImgLink(img);
        setPrice(price);
        setArea(area);
        setParkingSpot(parking);
    }

    public ArrayList<HouseExemple> getListExemple(){
        ArrayList<HouseExemple> lista = new ArrayList<HouseExemple>();

            lista.add(new HouseExemple("Kitnet Apartamento - Vila Marina - Rua José Gullo ","Kitnet Contendo 1 Dorms C/ Arm E Piso Frio 1 Sala C/ Piso Frio 1 Coz C/ Piso Frio 1 Wc Social C/ Piso Frio Area De Serviço",
                    1,1,false,"https://www.roca.com.br/images/0008547/01_fachada.jpg",800,90,0));
            lista.add(new HouseExemple("Kitnet Apartamento - Vila Celina - Rua Santos Dumont ","Apartamento Contendo Dois Dormitórios Piso Frio , Wc Piso Frio Azulejos Até O Teto Box Acrílico, Cozinha Piso Frio , Sala Piso Frio. Área Construída 50M²",
                1,2,false,"https://www.roca.com.br/images/0005965/01_fachada.jpg",777,50,0));
            lista.add(new HouseExemple("Kitnet Apartamento - Cidade Jardim - Rua Bernardino Fernandes Nunes","Kitnet Contendo 1 Dorm C/ Piso Frio 1 Wc Social C/ Piso Frio 1 Coz C/ Piso Frio Area De Serviço C/ Piso Frio 1 Garagem Coberta",
                1,1,false,"https://www.roca.com.br/images/0008760/01fachada.jpg",650,30,1));
        lista.add(new HouseExemple("Kitnet Apartamento - Vila Marina - Rua José Gullo ","Kitnet Contendo 1 Dorms C/ Arm E Piso Frio 1 Sala C/ Piso Frio 1 Coz C/ Piso Frio 1 Wc Social C/ Piso Frio Area De Serviço",
                1,1,false,"https://www.roca.com.br/images/0008547/01_fachada.jpg",800,90,0));
        lista.add(new HouseExemple("Kitnet Apartamento - Vila Celina - Rua Santos Dumont ","Apartamento Contendo Dois Dormitórios Piso Frio , Wc Piso Frio Azulejos Até O Teto Box Acrílico, Cozinha Piso Frio , Sala Piso Frio. Área Construída 50M²",
                1,2,false,"https://www.roca.com.br/images/0005965/01_fachada.jpg",777,50,0));
        lista.add(new HouseExemple("Kitnet Apartamento - Cidade Jardim - Rua Bernardino Fernandes Nunes","Kitnet Contendo 1 Dorm C/ Piso Frio 1 Wc Social C/ Piso Frio 1 Coz C/ Piso Frio Area De Serviço C/ Piso Frio 1 Garagem Coberta",
                1,1,false,"https://www.roca.com.br/images/0008760/01fachada.jpg",650,30,1));



        return lista;
    }

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String adress) {
        Adress = adress;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getToilet_nb() {
        return toilet_nb;
    }

    public void setToilet_nb(int toilet_nb) {
        this.toilet_nb = toilet_nb;
    }

    public int getRoom_nb() {
        return room_nb;
    }

    public void setRoom_nb(int room_nb) {
        this.room_nb = room_nb;
    }

    public boolean isHasPool() {
        return hasPool;
    }

    public void setHasPool(boolean hasPool) {
        this.hasPool = hasPool;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
