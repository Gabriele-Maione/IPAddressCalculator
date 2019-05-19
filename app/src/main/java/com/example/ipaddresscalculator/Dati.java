package com.example.ipaddresscalculator;

public class Dati {
    //ATTRIBUTI
    public static String ipAddress;
    public static String ipClass;
    public static String subnetMask;
    public static String networkIp;
    public static String broadcastIp;
    public static char classe=' ';
    public static int sottoreti=-1;
    public static int host;
    public static int _byte[]= new int[4];
    public static int subMask[]= new int[4];
    public static int sottoreteInserita;
    //METODI
    //getter
    public static String getIpAddress(){ return ipAddress;}
    public static String getIpClass(){ return ipClass;}
    public static String getSubnetMask(){ return subnetMask;}
    public static String getNetworkIp(){ return networkIp;}
    public static String getBroadcastIp(){ return broadcastIp;}
    public static char getClasse(){ return classe;}
    public static int getSottoreti(){ return sottoreti;}
    public static int getHost(){ return host;}
    public static int getByte(int p){ return _byte[p];}
    public static int getSubMask(int p){ return subMask[p];}
    public static int getSottoreteInserita(){ return sottoreteInserita;}
    //setter
    public static void setIpAddress(String ipAdd){ ipAddress = ipAdd; }
    public static void setIpClass(String ipCla){ ipClass = ipCla;}
    public static void setSubnetMask(String subMask){ subnetMask = subMask;}
    public static void setNetworkIp(String netIp){ networkIp = netIp; }
    public static void setBroadcastIp(String broIp){broadcastIp = broIp;}
    public static void setClasse(char c){ classe=c;}
    public static void setSottoreti(int sottoreti){ Dati.sottoreti = sottoreti; }
    public static void setHost(int host){ Dati.host = host; }
    public static void setByte(int n, int p){
        _byte[p]=n;
    }
    public static void setSubMask(int n, int p){
        subMask[p]=n;
    }
    public static void setSottoreteInserita(int sottoreteInserita) { Dati.sottoreteInserita = sottoreteInserita;}
}