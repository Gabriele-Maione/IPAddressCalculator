package com.example.ipaddresscalculator;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //ATTRIBUTI
    private TextView textViewIPAddress;     //Indirizzo IP
    private TextView textViewClassIP;       //Classe IP
    private TextView textViewNetworkIP;     //Broadcast IP
    private TextView textViewBroadcastIP;   //Network IP
    private TextView textViewSubnetMask;    //Subnet Mask
    private SpannableString ip;
    private SpannableString classe;
    private SpannableString netIP;
    private SpannableString broIP;
    private SpannableString subMask;
    private InputIPAddress ipAddress;
    private InputSubnetMask subnetMask;
    private NumberPicker numberPicker1;
    private NumberPicker numberPicker2;
    private NumberPicker numberPicker3;
    private NumberPicker numberPicker4;
    private char temp='A';
    //METODI
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numberPicker1= findViewById(R.id.NP1);
        numberPicker2= findViewById(R.id.NP2);
        numberPicker3= findViewById(R.id.NP3);
        numberPicker4= findViewById(R.id.NP4);
        textViewIPAddress= findViewById(R.id.textViewIPAddress);
        textViewClassIP= findViewById(R.id.textViewClassIP);
        textViewNetworkIP= findViewById(R.id.textViewNetworkIp);
        textViewBroadcastIP= findViewById(R.id.textViewBroadcastIP);
        textViewSubnetMask= findViewById(R.id.textViewSubnetMask);
        ipAddress= new InputIPAddress(numberPicker1, numberPicker2, numberPicker3, numberPicker4);
        subnetMask= new InputSubnetMask();
        calcola();
    }
    //Miei metodi
    public void calcola(){
        numberPicker1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                onCLickCalcola();
            }
        });
        numberPicker2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                onCLickCalcola();
            }
        });
        numberPicker3.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                onCLickCalcola();
            }
        });
        numberPicker4.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                onCLickCalcola();
            }
        });
    }
    public void onCLickCalcola(){
        ip= new SpannableString("Indirizzo IP: " + ipAddress.toString());
        ip.setSpan(new ForegroundColorSpan(Color.RED),13, ip.length(), 0);
        textViewIPAddress.setText(ip);
        Dati.setIpAddress( textViewIPAddress.getText().toString());

        classe= new SpannableString("Classe: " + ipAddress.getIPClass());
        classe.setSpan(new ForegroundColorSpan(Color.RED), 7, classe.length(), 0);
        textViewClassIP.setText(classe);
        Dati.setClasse(ipAddress.getIPClass());

        subnetMask.setSubMasDefault();
        subMask=new SpannableString("Subnet Mask: " + subnetMask.toString());
        subMask.setSpan(new ForegroundColorSpan(Color.RED),13, subMask.length(), 0 );
        textViewSubnetMask.setText(subMask);

        netIP= new SpannableString("Network IP: " + ipAddress.getNetworkIp());
        netIP.setSpan(new ForegroundColorSpan(Color.RED), 11, netIP.length(), 0);
        textViewNetworkIP.setText(netIP);

        broIP= new SpannableString("Broadcast IP: " + ipAddress.getBroadcastIP());
        broIP.setSpan(new ForegroundColorSpan(Color.RED), 13 , broIP.length(), 0);
        textViewBroadcastIP.setText(broIP);
    }
    public void onClickCalcola2(View v){
        if(Dati.getClasse()==' '){
            return;
        }
        Intent intent=new Intent(this, Main2Activity.class);
        if(Dati.getClasse()==temp){
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        }
        temp=Dati.getClasse();
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if(Dati.getClasse() != ' ') {
            outState.putInt("NP1", numberPicker1.getValue());
            outState.putInt("NP2", numberPicker2.getValue());
            outState.putInt("NP3", numberPicker3.getValue());
            outState.putInt("NP4", numberPicker4.getValue());
            outState.putString("IP", ip.toString());
            outState.putString("Subnet_Mask", subMask.toString());
            outState.putString("Classe", classe.toString());
            outState.putString("Network_IP", netIP.toString());
            outState.putString("Broadcast_IP", broIP.toString());
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(Dati.getClasse() != ' ') {
            numberPicker1.setValue(savedInstanceState.getInt("NP1"));
            numberPicker2.setValue(savedInstanceState.getInt("NP2"));
            numberPicker3.setValue(savedInstanceState.getInt("NP3"));
            numberPicker4.setValue(savedInstanceState.getInt("NP4"));

            ip = new SpannableString(savedInstanceState.getString("IP"));
            ip.setSpan(new ForegroundColorSpan(Color.RED), 13, ip.length(), 0);
            textViewIPAddress.setText(ip);

            classe = new SpannableString(savedInstanceState.getString("Classe"));
            classe.setSpan(new ForegroundColorSpan(Color.RED), 7, classe.length(), 0);
            textViewClassIP.setText(classe);

            subMask = new SpannableString(savedInstanceState.getString("Subnet_Mask"));
            subMask.setSpan(new ForegroundColorSpan(Color.RED), 13, subMask.length(), 0);
            textViewSubnetMask.setText(subMask);

            netIP = new SpannableString(savedInstanceState.getString("Network_IP"));
            netIP.setSpan(new ForegroundColorSpan(Color.RED), 11, netIP.length(), 0);
            textViewNetworkIP.setText(netIP);

            broIP = new SpannableString(savedInstanceState.getString("Broadcast_IP"));
            broIP.setSpan(new ForegroundColorSpan(Color.RED), 13, broIP.length(), 0);
            textViewBroadcastIP.setText(broIP);
        }
    }
}