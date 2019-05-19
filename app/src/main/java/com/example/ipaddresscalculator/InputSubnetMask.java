package com.example.ipaddresscalculator;

import android.widget.NumberPicker;

public class InputSubnetMask {
    //ATTRIBUTI
    private String subnetMask;
    private String subnetMask2;
    private String subMaskDefaultA="255.0.0.0";
    private String subMaskDefaultB="255.255.0.0";
    private String subMaskDefaultC="255.255.255.0";
    private char classe;
    private String subNetworkIp;
    private String subBroadcastIp;
    private String minimoIp;
    private String massimoIp;
    private NumberPicker NPSottoreti;
    private NumberPicker NPHost;
    private static final int NUM_BIT_CLASS_C=8;
    private static final int NUM_BIT_CLASS_B=16;
    private static final int NUM_BIT_CLASS_A=24;
    private int numeroBit;
    //COSTRUTTORI
    public InputSubnetMask(){}
    public InputSubnetMask(NumberPicker sottoreti, NumberPicker host){
        this.NPSottoreti=sottoreti;
        this.NPHost=host;
        gestisciRangeDefault();
    }
    //METODI
    public String getSubNetworkIp(){
        setSubNetworkIp();
        return subNetworkIp;
    }
    public String getSubBroadcastIp(){
        setSubBroadcastIp();
        return subBroadcastIp;
    }
    public String getMinimoIp(){
        return minimoIp;
    }
    public String getMassimoIp(){
        return massimoIp;
    }
    public void setSubNetworkIp(){
        classe=Dati.getClasse();
        if(classe=='C'){
            int decimale=0;
            String network="00000000";
            String temp=Convertitore.decimaleToBinario(calcolaBitClasseC(), Dati.getSottoreteInserita());
            for(int i=0; i<calcolaBitClasseC(); i++){
                network=network.substring(0, i) + temp.charAt(i) + network.substring(i+1);
            }
            decimale=Convertitore.binarioToDecimale(network);
            subNetworkIp="Sub network IP: "+ Dati.getByte(0) + "." + Dati.getByte(1) + "." + Dati.getByte(2) + "." + decimale;
            minimoIp="Minimo IP: "+ Dati.getByte(0) + "." + Dati.getByte(1) + "." + Dati.getByte(2) + "." + ++decimale;
        }
        else if(classe=='B'){
            int decimale=0;
            int decimale2=0;
            String network="00000000";
            String network2="00000000";
            String temp=Convertitore.decimaleToBinario(calcolaBitClasseB(), Dati.getSottoreteInserita());
            int j=0;
            for(int i=0; i<calcolaBitClasseB(); i++){
                if(i<8){
                    network=network.substring(0, i) + temp.charAt(i) + network.substring(i+1);
                }
                else{
                    network2=network2.substring(0, j) + temp.charAt(i) + network2.substring(j+1);
                    j++;
                }
            }
            decimale=Convertitore.binarioToDecimale(network);
            decimale2=Convertitore.binarioToDecimale(network2);
            subNetworkIp="Sub network IP: "+ Dati.getByte(0) + "." + Dati.getByte(1) + "." + decimale + "." + decimale2;
            minimoIp="Minimo IP: "+ Dati.getByte(0) + "." + Dati.getByte(1) + "." + decimale + "." + ++decimale2;
        }
        else{
            int decimale=0;
            int decimale2=0;
            int decimale3=0;
            String network="00000000";
            String network2="00000000";
            String network3="00000000";
            String temp=Convertitore.decimaleToBinario(calcolaBitClasseA(), Dati.getSottoreteInserita());
            int j=0;
            int c=0;
            for(int i=0; i<calcolaBitClasseA(); i++){
                if(i<8){
                    network=network.substring(0, i) + temp.charAt(i) + network.substring(i+1);
                }
                else if(i<16){
                    network2=network2.substring(0, j) + temp.charAt(i) + network2.substring(j+1);
                    j++;
                }
                else{
                    network3=network3.substring(0, c) + temp.charAt(i) + network3.substring(c+1);
                    c++;
                }
            }
            decimale=Convertitore.binarioToDecimale(network);
            decimale2=Convertitore.binarioToDecimale(network2);
            decimale3=Convertitore.binarioToDecimale(network3);
            subNetworkIp="Sub network IP: "+ Dati.getByte(0) + "." + decimale + "." + decimale2 + "." + decimale3;
            minimoIp="Minimo IP: "+ Dati.getByte(0) + "." + decimale + "." + decimale2 + "." + ++decimale3;
        }
    }
    public void setSubBroadcastIp(){
        classe=Dati.getClasse();
        if(classe=='C'){
            int decimale=0;
            String network="11111111";
            String temp=Convertitore.decimaleToBinario(calcolaBitClasseC(), Dati.getSottoreteInserita());
            for(int i=0; i<calcolaBitClasseC(); i++){
                network=network.substring(0, i) + temp.charAt(i) + network.substring(i+1);
            }
            decimale=Convertitore.binarioToDecimale(network);
            subBroadcastIp="Sub broadcast IP: "+ Dati.getByte(0) + "." + Dati.getByte(1) + "." + Dati.getByte(2) + "." + decimale;
            massimoIp="Massimo IP: "+ Dati.getByte(0) + "." + Dati.getByte(1) + "." + Dati.getByte(2) + "." + --decimale;
        }
        else if(classe=='B'){
            int decimale=0;
            int decimale2=0;
            String network="11111111";
            String network2="11111111";
            String temp=Convertitore.decimaleToBinario(calcolaBitClasseB(), Dati.getSottoreteInserita());
            int j=0;
            for(int i=0; i<calcolaBitClasseB(); i++){
                if(i<8){
                    network=network.substring(0, i) + temp.charAt(i) + network.substring(i+1);
                }
                else{
                    network2=network2.substring(0, j) + temp.charAt(i) + network2.substring(j+1);
                    j++;
                }
            }
            decimale=Convertitore.binarioToDecimale(network);
            decimale2=Convertitore.binarioToDecimale(network2);
            subBroadcastIp="Sub broadcast IP: "+ Dati.getByte(0) + "." + Dati.getByte(1) + "." + decimale + "." + decimale2;
            massimoIp="Massimo IP: "+ Dati.getByte(0) + "." + Dati.getByte(1) + "." + decimale + "." + --decimale2;
        }
        else{
            int decimale=0;
            int decimale2=0;
            int decimale3=0;
            String network="11111111";
            String network2="11111111";
            String network3="11111111";
            String temp=Convertitore.decimaleToBinario(calcolaBitClasseA(), Dati.getSottoreteInserita());
            int j=0;
            int c=0;
            for(int i=0; i<calcolaBitClasseA(); i++){
                if(i<8){
                    network=network.substring(0, i) + temp.charAt(i) + network.substring(i+1);
                }
                else if(i<16){
                    network2=network2.substring(0, j) + temp.charAt(i) + network2.substring(j+1);
                    j++;
                }
                else{
                    network3=network3.substring(0, c) + temp.charAt(i) + network3.substring(c+1);
                    c++;
                }
            }
            decimale=Convertitore.binarioToDecimale(network);
            decimale2=Convertitore.binarioToDecimale(network2);
            decimale3=Convertitore.binarioToDecimale(network3);
            subBroadcastIp="Sub broadcast IP: "+ Dati.getByte(0) + "." + decimale + "." + decimale2 + "." + decimale3;
            massimoIp="Massimo IP: "+ Dati.getByte(0) + "." + decimale + "." + decimale2 + "." + --decimale3;
        }
    }

    public void gestisciRangeDefault(){
        classe=Dati.getClasse();
        if(classe=='C'){
            NPSottoreti.setMinValue(1);
            NPSottoreti.setMaxValue(64);
            NPHost.setMinValue(2);
            NPHost.setMaxValue(254);
        }
        else if(classe=='B'){
            NPSottoreti.setMinValue(1);
            NPSottoreti.setMaxValue(16384);
            NPHost.setMinValue(2);
            NPHost.setMaxValue(65534);
        }
        else{
            NPSottoreti.setMinValue(1);
            NPSottoreti.setMaxValue(4194304);
            NPHost.setMinValue(2);
            NPHost.setMaxValue(16777214);
        }
    }
    public void gestisciRange(){
        classe=Dati.getClasse();
        if(classe=='C'){
            int numBitSottoreti;
            int numBitHost=8;
            numBitSottoreti=calcolaBitClasseC();
            numBitHost=numBitHost-numBitSottoreti;
            NPHost.setMaxValue((int) Math.pow (2, numBitHost)-2);
        }
        else if(classe=='B'){
            int numBitSottoreti;
            int numBitHost=16;
            numBitSottoreti=calcolaBitClasseB();
            numBitHost=numBitHost-numBitSottoreti;
            NPHost.setMaxValue((int) Math.pow (2, numBitHost)-2);
        }
        else{
            int numBitSottoreti;
            int numBitHost=24;
            numBitSottoreti=calcolaBitClasseA();
            numBitHost=numBitHost-numBitSottoreti;
            NPHost.setMaxValue((int) Math.pow (2, numBitHost)-2);
        }
    }

    public void gestisciRangeConHost(){
        classe=Dati.getClasse();
        if(classe=='C'){
            int numBitSottoreti=8;
            int numBitHost=0;
            numBitHost=calcolaBitHostClasseC();
            numBitSottoreti=numBitSottoreti-numBitHost;
            NPSottoreti.setMaxValue((int) Math.pow (2, numBitSottoreti));
        }
        else if(classe=='B'){
            int numBitSottoreti=16;
            int numBitHost=0;
            numBitHost=calcolaBitHostClasseB();
            numBitSottoreti=numBitSottoreti-numBitHost;
            NPSottoreti.setMaxValue((int) Math.pow (2, numBitSottoreti));
        }
        else{
            int numBitSottoreti=24;
            int numBitHost=0;
            numBitHost=calcolaBitHostClasseA();
            numBitSottoreti=numBitSottoreti-numBitHost;
            NPSottoreti.setMaxValue((int) Math.pow (2, numBitSottoreti));
        }
    }
    public String getSubnetMask(){
        setSubnetMask();
        return subnetMask;
    }
    public void setSubnetMask(){
        classe=Dati.getClasse();

        if(classe=='C'){
            String s="00000000";
            int n=0;
            for(int i=0; i<calcolaBitClasseC(); i++){
                s=s.substring(0, i) + '1' + s.substring(i+1);
            }
            n=Convertitore.binarioToDecimale(s);
            subnetMask=Dati.getSubMask(0) + "." + Dati.getSubMask(1) + "." + Dati.getSubMask(2) + "." + n;
        }
        else if(classe=='B'){
            String s="00000000";
            String s2="00000000";
            int n=0;
            int n2=0;
            int j=0;
            for(int i=0; i<calcolaBitClasseB(); i++){
                if(i<8){
                    s=s.substring(0, i) + '1' + s.substring(i+1);
                }
                else{
                    s2=s2.substring(0, j) + '1' + s2.substring(j+1);
                    j++;
                }
            }
            n=Convertitore.binarioToDecimale(s);
            n2=Convertitore.binarioToDecimale(s2);
            subnetMask=Dati.getSubMask(0) + "." + Dati.getSubMask(1) + "." + n + "." + n2;
        }
        else{
            String s="00000000";
            String s2="00000000";
            String s3="00000000";
            int n=0;
            int n2=0;
            int n3=0;
            int j=0;
            int c=0;
            for(int i=0; i<calcolaBitClasseA(); i++){
                if(i<8){
                    s=s.substring(0, i) + '1' + s.substring(i+1);
                }
                else if(i<16){
                    s2=s2.substring(0, j) + '1' + s2.substring(j+1);
                    j++;
                }
                else{
                    s3=s3.substring(0, c) + '1' + s3.substring(c+1);
                    c++;
                }
            }
            n=Convertitore.binarioToDecimale(s);
            n2=Convertitore.binarioToDecimale(s2);
            n3=Convertitore.binarioToDecimale(s3);
            subnetMask=Dati.getSubMask(0) + "." + n + "." + n2 + "." + n3;
        }
    }

    public int calcolaBitHostClasseC(){
        int i;
        int host= Dati.getHost();
        for(i=0; i<NUM_BIT_CLASS_C; i++){
            if(Math.pow(2,i)-2>=host){
                break;
            }
        }
        return i;
    }

    public int calcolaBitClasseC(){
        int i;
        int sottoreti= Dati.getSottoreti();
        for(i=0; i<NUM_BIT_CLASS_C; i++){
            if(Math.pow(2,i)>=sottoreti){
                break;
            }
        }
        return i;
    }
    public int calcolaBitClasseB(){
        int i;
        int sottoreti= Dati.getSottoreti();
        for(i=0; i<NUM_BIT_CLASS_B; i++){
            if(Math.pow(2,i)>=sottoreti){
                break;
            }
        }
        return i;
    }

    public int calcolaBitHostClasseB(){
        int i;
        int host= Dati.getHost();
        for(i=0; i<NUM_BIT_CLASS_B; i++){
            if(Math.pow(2,i)-2>=host){
                break;
            }
        }
        return i;
    }
    public int calcolaBitClasseA(){
        int i;
        int sottoreti= Dati.getSottoreti();
        for(i=0; i<NUM_BIT_CLASS_A; i++){
            if(Math.pow(2,i)>=sottoreti){
                break;
            }
        }
        return i;
    }

    public int calcolaBitHostClasseA(){
        int i;
        int host= Dati.getHost();
        for(i=0; i<NUM_BIT_CLASS_A; i++){
            if(Math.pow(2,i)-2>=host){
                break;
            }
        }
        return i;
    }
    public String getSubMaskDefalutA(){ return subMaskDefaultA; }
    public String getSubMaskDefalutB(){ return subMaskDefaultB; }
    public String getSubMaskDefalutC(){ return subMaskDefaultC; }
    public void setSubMasDefault(){
        classe=Dati.getClasse();
        if(classe=='A'){
            Dati.setSubMask(255,0);
            subnetMask=subMaskDefaultA;
        }
        else if(classe=='B'){
            Dati.setSubMask(255,0);
            Dati.setSubMask(255,1);
            subnetMask=subMaskDefaultB;
        }
        else{
            Dati.setSubMask(255,0);
            Dati.setSubMask(255,1);
            Dati.setSubMask(255,2);
            subnetMask=subMaskDefaultC;
        }
    }
    public String toString(){
        return subnetMask;
    }

    public void inputSubnetMask(int p){
        char classe=Dati.getClasse();
        if(classe=='C'){
            String s="00000000";
            int n=0;
            for(int i=0; i<p; i++){
                s=s.substring(0, i) + '1' + s.substring(i+1);
            }
            n=Convertitore.binarioToDecimale(s);
            Dati.setSottoreti((int) Math.pow(2,p));
            Dati.setHost((int) Math.pow(2, 8-p) -2);
            subnetMask2=Dati.getSubMask(0) + "." + Dati.getSubMask(1) + "." + Dati.getSubMask(2) + "." + n;
        }
        else if(classe=='B'){
            String s="00000000";
            String s2="00000000";
            int n=0;
            int n2=0;
            int j=0;
            for(int i=0; i<p; i++){
                if(i<8){
                    s=s.substring(0, i) + '1' + s.substring(i+1);
                }
                else{
                    s2=s2.substring(0, j) + '1' + s2.substring(j+1);
                    j++;
                }
            }
            n=Convertitore.binarioToDecimale(s);
            n2=Convertitore.binarioToDecimale(s2);
            Dati.setSottoreti((int) Math.pow(2,p));
            Dati.setHost((int) Math.pow(2, 16-p) -2);
            subnetMask2=Dati.getSubMask(0) + "." + Dati.getSubMask(1) + "." + n + "." + n2;
        }
        else{
            String s="00000000";
            String s2="00000000";
            String s3="00000000";
            int n=0;
            int n2=0;
            int n3=0;
            int j=0;
            int c=0;
            for(int i=0; i<p; i++){
                if(i<8){
                    s=s.substring(0, i) + '1' + s.substring(i+1);
                }
                else if(i<16){
                    s2=s2.substring(0, j) + '1' + s2.substring(j+1);
                    j++;
                }
                else{
                    s3=s3.substring(0, c) + '1' + s3.substring(c+1);
                    c++;
                }
            }
            n=Convertitore.binarioToDecimale(s);
            n2=Convertitore.binarioToDecimale(s2);
            n3=Convertitore.binarioToDecimale(s3);
            Dati.setSottoreti((int) Math.pow(2,p));
            Dati.setHost((int) Math.pow(2, 24-p) -2);
            subnetMask2=Dati.getSubMask(0) + "." + n + "." + n2 + "." + n3;
        }
    }

    public String getSubnetMask2(int p){
        inputSubnetMask(p);
        return subnetMask2;
    }

    public int getNumeroBit(){
        char classe=Dati.getClasse();
        if(classe=='C'){
            return calcolaBitClasseC();
        }
        else if(classe=='B'){
            return calcolaBitClasseB();
        }
        else{
            return calcolaBitClasseA();
        }
    }
}