package com.example.ipaddresscalculator;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.TextView;
import static java.lang.Integer.parseInt;

public class Main2Activity extends AppCompatActivity{
    //ATTRIBUTI
    private InputSubnetMask subnetMask;
    private TextView textViewSubnetMask2;
    private TextView textViewSubNetIp;
    private TextView textViewSubBroIp;
    private TextView textViewMinimoIp;
    private TextView textViewMassimoIp;
    private EditText editTextNumeroSottorete;
    private NumberPicker numberPickerSottoreti;
    private NumberPicker numberPickerHost;
    private TextView textViewNumeroSottorete;
    private Integer numeroSottorete=-1;
    private SpannableString subMask;
    private SpannableString numSottorete;
    private SpannableString subBro;
    private SpannableString subNet;
    private SpannableString minIP;
    private SpannableString maxIP;
    private SeekBar seekBarSubnetMask;
    //METODI
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        numberPickerHost=findViewById(R.id.numberPickerHost);
        numberPickerSottoreti= findViewById(R.id.numberPickerSottoreti);
        subnetMask= new InputSubnetMask(numberPickerSottoreti, numberPickerHost);
        textViewSubNetIp= findViewById(R.id.textViewSubNetIp);
        textViewSubBroIp= findViewById(R.id.textViewSubBroIp);
        textViewMinimoIp= findViewById(R.id.textViewMinimoIp);
        textViewMassimoIp= findViewById(R.id.textViewMassimoIp);
        textViewNumeroSottorete= findViewById(R.id.textViewNumeroSottorete);
        editTextNumeroSottorete= findViewById(R.id.editTextNumeroSottorete);
        textViewSubnetMask2= findViewById(R.id.textViewSubnetMask2);
        textViewMinimoIp.setText("Minimo IP:");
        inputSubMask();
        gestireNumberPickerSottoreti();
        gestireNumberPickerHost();
        calcolaSottorete();
    }
    //Miei metodi
    public void calcolaSottorete(){
        editTextNumeroSottorete.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() != 0){
                    onClickCalcolaSottorete();
                }
            }
        });
    }
    public void onClickCalcolaSottorete(){
        Dati.setSottoreti(numberPickerSottoreti.getValue());
        Integer temp= Integer.valueOf(editTextNumeroSottorete.getText().toString());
        if((temp>= Dati.getSottoreti()) || (temp<0)){
            SpannableString s= new SpannableString("Numero sottorete errato");
            s.setSpan(new ForegroundColorSpan(Color.RED),0, s.length(), 0);
            textViewNumeroSottorete.setText(s);
            textViewSubNetIp.setText("Sub network IP:");
            textViewSubBroIp.setText("Sub broadcast IP:");
            textViewMinimoIp.setText("Minimo IP:");
            textViewMassimoIp.setText("Massimo IP:");
            return;
        }
        String s=editTextNumeroSottorete.getText().toString();
        Dati.setSottoreteInserita(Integer.parseInt(s));

        Dati.setHost(numberPickerHost.getValue());
        subnetMask.gestisciRange();
        numeroSottorete=Dati.getSottoreteInserita();

        subMask= new SpannableString("Subnet Mask: " + subnetMask.getSubnetMask());
        subMask.setSpan(new ForegroundColorSpan(Color.RED),13, subMask.length(), 0);
        textViewSubnetMask2.setText(subMask);

        numSottorete= new SpannableString("Sottorete numero:" + numeroSottorete.toString());
        numSottorete.setSpan(new ForegroundColorSpan(Color.RED),16, numSottorete.length(), 0);
        textViewNumeroSottorete.setText(numSottorete);

        subNet= new SpannableString(subnetMask.getSubNetworkIp());
        subNet.setSpan(new ForegroundColorSpan(Color.RED),15, subNet.length(), 0);
        textViewSubNetIp.setText(subNet);

        subBro= new SpannableString(subnetMask.getSubBroadcastIp());
        subBro.setSpan(new ForegroundColorSpan(Color.RED),17, subBro.length(), 0);
        textViewSubBroIp.setText(subBro);

        minIP= new SpannableString(subnetMask.getMinimoIp());
        minIP.setSpan(new ForegroundColorSpan(Color.RED),10, minIP.length(), 0);
        textViewMinimoIp.setText(minIP);

        maxIP= new SpannableString(subnetMask.getMassimoIp());
        maxIP.setSpan(new ForegroundColorSpan(Color.RED),11, maxIP.length(), 0);
        textViewMassimoIp.setText(maxIP);
    }
    public void onClickSuccessiva(View v){
        Dati.setSottoreti(numberPickerSottoreti.getValue());
        if(numeroSottorete==-1){
            editTextNumeroSottorete.setText("0");
            numeroSottorete=0;
            Dati.setSottoreteInserita(numeroSottorete);
            return;
        }
        if(numeroSottorete==Dati.getSottoreti()-1){
            numeroSottorete=0;
            editTextNumeroSottorete.setText(numeroSottorete.toString());
            Dati.setSottoreteInserita(numeroSottorete);
            return;
        }
        String s=editTextNumeroSottorete.getText().toString();
        numeroSottorete= parseInt(s);
        numeroSottorete++;
        editTextNumeroSottorete.setText(numeroSottorete.toString());
        Dati.setSottoreteInserita(numeroSottorete);
    }
    public void onClickPrecedente(View v){
        Dati.setSottoreti(numberPickerSottoreti.getValue());
        if(numeroSottorete==-1){
            editTextNumeroSottorete.setText("0");
            numeroSottorete=0;
            return;
        }
        if(numeroSottorete==0){
            numeroSottorete=Dati.getSottoreti()-1;
            editTextNumeroSottorete.setText(numeroSottorete.toString());
            Dati.setSottoreteInserita(numeroSottorete);
            return;
        }
        String s=editTextNumeroSottorete.getText().toString();
        numeroSottorete= parseInt(s);
        numeroSottorete--;
        editTextNumeroSottorete.setText(numeroSottorete.toString());
    }

    public void gestireNumberPickerSottoreti(){
        numberPickerSottoreti.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                if(numeroSottorete==-1){
                    editTextNumeroSottorete.setText("0");
                    numeroSottorete=0;
                }
                String s=editTextNumeroSottorete.getText().toString();
                Dati.setSottoreteInserita(Integer.parseInt(s));
                Dati.setSottoreti(numberPickerSottoreti.getValue());
                Dati.setHost(numberPickerHost.getValue());
                subnetMask.gestisciRange();
                numeroSottorete=Dati.getSottoreteInserita();
                subMask= new SpannableString("Subnet Mask: " + subnetMask.getSubnetMask());
                subMask.setSpan(new ForegroundColorSpan(Color.RED),13, subMask.length(), 0);
                textViewSubnetMask2.setText(subMask);
                seekBarSubnetMask.setProgress(subnetMask.getNumeroBit());
            }
        });
    }

    public void gestireNumberPickerHost(){
        numberPickerHost.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                if(numeroSottorete==-1){
                    editTextNumeroSottorete.setText("0");
                    numeroSottorete=0;
                }
                String s=editTextNumeroSottorete.getText().toString();
                Dati.setSottoreteInserita(Integer.parseInt(s));
                Dati.setSottoreti(numberPickerSottoreti.getValue());
                Dati.setHost(numberPickerHost.getValue());
                subnetMask.gestisciRangeConHost();
                numeroSottorete=Dati.getSottoreteInserita();

                subMask= new SpannableString("Subnet Mask: " + subnetMask.getSubnetMask());
                subMask.setSpan(new ForegroundColorSpan(Color.RED),13, subMask.length(), 0);
                textViewSubnetMask2.setText(subMask);
            }
        });
    }

    public void inputSubMask(){
        seekBarSubnetMask= findViewById(R.id.seekBarSubnetMask);
        if(Dati.getClasse()=='A'){
            seekBarSubnetMask.setMax(22);
        }
        else if(Dati.getClasse()=='B'){
            seekBarSubnetMask.setMax(14);
        }
        else{
            seekBarSubnetMask.setMax(6);
        }
        seekBarSubnetMask.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser==true) {
                    if (Dati.getClasse() == 'A') {
                        seekBar.setMax(22);
                        subnetMask.gestisciRangeDefault();
                        subMask = new SpannableString("Subnet Mask: " + subnetMask.getSubnetMask2(progress));
                        subMask.setSpan(new ForegroundColorSpan(Color.RED), 13, subMask.length(), 0);
                        textViewSubnetMask2.setText(subMask);
                        numberPickerSottoreti.setValue(Dati.getSottoreti());
                        numberPickerHost.setValue(Dati.getHost());
                    } else if (Dati.getClasse() == 'B') {
                        seekBar.setMax(14);
                        subnetMask.gestisciRangeDefault();
                        subMask = new SpannableString("Subnet Mask: " + subnetMask.getSubnetMask2(progress));
                        subMask.setSpan(new ForegroundColorSpan(Color.RED), 13, subMask.length(), 0);
                        textViewSubnetMask2.setText(subMask);
                        numberPickerSottoreti.setValue(Dati.getSottoreti());
                        numberPickerHost.setValue(Dati.getHost());
                    } else {
                        seekBar.setMax(6);
                        subnetMask.gestisciRangeDefault();
                        subMask = new SpannableString("Subnet Mask: " + subnetMask.getSubnetMask2(progress));
                        subMask.setSpan(new ForegroundColorSpan(Color.RED), 13, subMask.length(), 0);
                        textViewSubnetMask2.setText(subMask);
                        numberPickerSottoreti.setValue(Dati.getSottoreti());
                        numberPickerHost.setValue(Dati.getHost());
                    }
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home: {
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        }
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (textViewMinimoIp.getText().toString() == "Minimo IP:"){
            outState.putInt("NPSottoreti", numberPickerSottoreti.getValue());
            outState.putInt("NPHost", numberPickerHost.getValue());
            outState.putString("Numero_Sottorete", "0");
            outState.putString("Subnet_Mask2", "provola e mozzarella Mask:");
            outState.putString("numSottorete", "Sottorete numero:");
            outState.putString("Sub_Network_IP", "Sub network IP:");
            outState.putString("Sub_Broadcast_IP","Sub broadcast IP:");
            outState.putString("Minimo_IP", "Minimo IP:");
            outState.putString("Massimo_IP", "Massimo IP:");
        }
        else if(Dati.getSottoreti() != -1) {
            outState.putInt("NPSottoreti", numberPickerSottoreti.getValue());
            outState.putInt("NPHost", numberPickerHost.getValue());
            outState.putString("Numero_Sottorete",editTextNumeroSottorete.getText().toString());
            outState.putString("Subnet_Mask2",subMask.toString());
            outState.putString("numSottorete", numSottorete.toString());
            outState.putString("Sub_Network_IP", subNet.toString());
            outState.putString("Sub_Broadcast_IP", subBro.toString());
            outState.putString("Minimo_IP", minIP.toString());
            outState.putString("Massimo_IP", maxIP.toString());
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(Dati.getSottoreti() != -1) {
            numberPickerSottoreti.setValue(savedInstanceState.getInt("NPSottoreti"));
            numberPickerHost.setValue(savedInstanceState.getInt("NPHost"));

            numeroSottorete=parseInt(savedInstanceState.getString("Numero_Sottorete"));
            editTextNumeroSottorete.setText(savedInstanceState.getString("Numero_Sottorete"));

            subMask= new SpannableString(savedInstanceState.getString("Subnet_Mask2"));
            subMask.setSpan(new ForegroundColorSpan(Color.RED),13, subMask.length(), 0);
            textViewSubnetMask2.setText(subMask);

            numSottorete= new SpannableString(savedInstanceState.getString("numSottorete"));
            numSottorete.setSpan(new ForegroundColorSpan(Color.RED),16, numSottorete.length(), 0);
            textViewNumeroSottorete.setText(numSottorete);

            subNet= new SpannableString(savedInstanceState.getString("Sub_Network_IP"));
            subNet.setSpan(new ForegroundColorSpan(Color.RED),15, subNet.length(), 0);
            textViewSubNetIp.setText(subNet);

            subBro= new SpannableString(savedInstanceState.getString("Sub_Broadcast_IP"));
            subBro.setSpan(new ForegroundColorSpan(Color.RED),17, subBro.length(), 0);
            textViewSubBroIp.setText(subBro);

            minIP= new SpannableString(savedInstanceState.getString("Minimo_IP"));
            minIP.setSpan(new ForegroundColorSpan(Color.RED),10, minIP.length(), 0);
            textViewMinimoIp.setText(minIP);

            maxIP= new SpannableString(savedInstanceState.getString("Massimo_IP"));
            maxIP.setSpan(new ForegroundColorSpan(Color.RED),11, maxIP.length(), 0);
            textViewMassimoIp.setText(maxIP);
        }
    }
}