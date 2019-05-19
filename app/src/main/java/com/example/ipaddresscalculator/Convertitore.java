package com.example.ipaddresscalculator;

public class Convertitore {
    //METODI
    public static String decimaleToBinario(int decimale){
        String binario="";
        String temp="";
        for(int i=0; i<8; i++){
            if(decimale==0){
                if(binario.length()<8){
                    temp=binario;
                    binario="0"+temp;
                }
            }
            else if(decimale%2==0){
                temp=binario;
                binario="0"+temp;
            }
            else{
                temp=binario;
                binario="1"+temp;
            }
            decimale=decimale/2;
        }
        return binario;
    }
    public static String decimaleToBinario(int n,int decimale){
        String binario="";
        String temp="";
        for(int i=0; i<n; i++){
            if(decimale%2==0){
                temp=binario;
                binario="0"+temp;
            }
            else{
                temp=binario;
                binario="1"+temp;
            }
            decimale=decimale/2;
        }
        return binario;
    }
    public static int binarioToDecimale(String binario){
        int decimale=0;
        int j=0;
        for(int i=7; i>=0; i--){
            if(binario.charAt(i)=='1'){
                decimale+= (int) Math.pow(2,j);
            }
            j++;
        }
        return decimale;
    }
}