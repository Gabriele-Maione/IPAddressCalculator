package com.example.ipaddresscalculator;

import android.widget.NumberPicker;

public class InputIPAddress{
    //ATTRIBUTI
    private NumberPicker numPicker1;
    private NumberPicker numPicker2;
    private NumberPicker numPicker3;
    private NumberPicker numPicker4;
    private char classe;
    private String networkIp;
    private String broadcastIP;
    //costruttore
    public InputIPAddress(NumberPicker NP1, NumberPicker NP2, NumberPicker NP3, NumberPicker NP4){
        numPicker1= NP1;
        numPicker2= NP2;
        numPicker3= NP3;
        numPicker4= NP4;
        numPicker1.setMinValue(0);
        numPicker1.setMaxValue(255);
        numPicker2.setMinValue(0);
        numPicker2.setMaxValue(255);
        numPicker3.setMinValue(0);
        numPicker3.setMaxValue(255);
        numPicker4.setMinValue(0);
        numPicker4.setMaxValue(255);
    }
    //METODI
    public String toString(){
        Dati.setByte(numPicker1.getValue(), 0);
        Dati.setByte(numPicker2.getValue(), 1);
        Dati.setByte(numPicker3.getValue(), 2);
        Dati.setByte(numPicker4.getValue(), 3);
        return numPicker1.getValue() + "." + numPicker2.getValue() + "." + numPicker3.getValue() + "." + numPicker4.getValue();
    }
    private void setClasse(){
        String binario=Convertitore.decimaleToBinario(numPicker1.getValue());
        if(binario.charAt(0)=='0'){
            this.classe='A';
        }
        else if((binario.charAt(0)=='1') && (binario.charAt(1)=='0')){
            this.classe='B';
        }
        else{
            this.classe='C';
        }
    }
    public char getIPClass(){
        setClasse();
        return classe;
    }
    public void setNetworkIp(){
        if(classe=='A'){
            this.networkIp=String.valueOf(numPicker1.getValue()) + "." + "0" + "."  + "0" + "." + "0";
        }
        else if(classe=='B'){
            this.networkIp=String.valueOf(numPicker1.getValue()) + "." + String.valueOf(numPicker2.getValue()) + "."  + "0" + "." + "0";
        }
        else{
            this.networkIp=String.valueOf(numPicker1.getValue()) + "." + String.valueOf(numPicker2.getValue()) + "." + String.valueOf(numPicker3.getValue()) + "." + "0";
        }
    }
    public void setBroadcastIP(){
        if(classe=='A'){
            this.broadcastIP=String.valueOf(numPicker1.getValue()) + "." + "255" + "."  + "255" + "." + "255";
        }
        else if(classe=='B'){
            this.broadcastIP=String.valueOf(numPicker1.getValue()) + "." + String.valueOf(numPicker2.getValue()) + "."  + "255" + "." + "255";
        }
        else{
            this.broadcastIP=String.valueOf(numPicker1.getValue()) + "." + String.valueOf(numPicker2.getValue()) + "." + String.valueOf(numPicker3.getValue()) + "." + "255";
        }
    }
    public String getNetworkIp(){
        setNetworkIp();
        return networkIp;
    }
    public String getBroadcastIP(){
        setBroadcastIP();
        return broadcastIP;
    }
}