package com.example.petrolao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText litros;
    private Spinner combustiveis;
    private EditText precoComb;
    private TextView precoFinal;
    private int selected =0;
    private RadioGroup payGroup;
    private double desconto = 0.95;
    private double[] precos = {5.84,5.70,9.60,3.59,6.20};
    private Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CharSequence text = "Transação finalizada!";
        int duration = Toast.LENGTH_SHORT;

        toast = Toast.makeText(this /* MyActivity */, text, duration);

        //variaveis do front
        litros = (EditText) findViewById(R.id.litros);

        combustiveis = (Spinner) findViewById(R.id.combustiveis);

        payGroup = (RadioGroup) findViewById(R.id.payGroup);

        precoComb = (EditText) findViewById(R.id.precoComb);

        precoFinal = (TextView) findViewById(R.id.precoFinal);


        //configuração do spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.array_combustiveis, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        combustiveis.setAdapter(adapter);

        // Cria uma classe interna que implementa a interface OnItemSelectedListener
        class MyListener implements AdapterView.OnItemSelectedListener{

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Executa uma função ao mudar o spinner
                selected = position;
                switch (position){
                    case 0:
                        precoComb.setText(Double.toString(precos[selected]));

                        break;
                    case 1:
                        precoComb.setText(Double.toString(precos[selected]));
                        break;
                    case 2:
                        precoComb.setText(Double.toString(precos[selected]));
                        break;
                    case 3:
                        precoComb.setText(Double.toString(precos[selected]));
                        break;
                    case 4:
                        precoComb.setText(Double.toString(precos[selected]));
                        break;
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
                // Executa a função quando o spinner não for selecionado
                //do nothing
            }
        }

        // Associa o listener ao spinner
        combustiveis.setOnItemSelectedListener(new MyListener());
        precoComb.setText(Double.toString(precos[0]));

        payGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = group.findViewById(checkedId);
                String option = radioButton.getText().toString();


                switch (option){
                    case "pix":
                        desconto = 1.05;
                        break;
                    case "dinheiro":
                        desconto = 0.95;
                        break;
                    case "debito":
                        desconto = 1.05;
                        break;
                    case "credito":
                        desconto = 1.1;
                }
            }
        });

    }


    public void pagamento( View view ){
        //finalização e calculo da transação
        double x, y, result;

        x = Double.valueOf(litros.getText().toString());
        y = Double.valueOf(precoComb.getText().toString());

        precos[selected]=y;

        result = x * y;
        result *= desconto;
        DecimalFormat df = new DecimalFormat("#.##");

        precoFinal.setText("R$:"+Double.toString(result));

        toast.show();
    }

}