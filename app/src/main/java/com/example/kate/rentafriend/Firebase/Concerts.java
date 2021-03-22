package com.example.kate.rentafriend.Firebase;

import com.example.kate.rentafriend.Concert;

import java.util.List;

public class Concerts {

    List<Concert> concertList;

    public List<Concert> getConcertList() {
        return concertList;
    }

    public void setConcertList(List<Concert> concertList) {
        this.concertList = concertList;
    }

    public Concerts(List<Concert> concertList) {
        this.concertList = concertList;
    }

    public Concert findById(String id){
        for(Concert concert : concertList){
            if(concert.getID().equals(id))
                return concert;
        }
        return null;
    }
}
