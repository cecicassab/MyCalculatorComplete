package com.cecilia.cassab.mycalculatorcomplete;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Vector;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    Double n1,n2;
    String tela="";
    String op="";
    EditText msg;
    boolean res=false;
    public void monta(View v){
        msg = (EditText) findViewById(R.id.caixa);
        String tag = v.getTag().toString();
        if(!res) {
            tela = tela + tag;
        }
        else{
            tela= tag;
            op="";
            res=false;
        }
        msg.setText(tela);
    }
    public void operator(View v){
        msg = (EditText) findViewById(R.id.caixa);
        if(!tela.isEmpty()) {
            String tag = v.getTag().toString();
            op = tag;
            n1 = Double.parseDouble(tela);
            tela = tela + tag;
            res = false;
        }
        msg.setText(tela);
    }

    public void equal(View v){
        msg = (EditText) findViewById(R.id.caixa);
        if(!tela.isEmpty()) {
            String tag = v.getTag().toString();
            String[] elems = tela.split(Pattern.quote(op));
            n2 = Double.parseDouble(elems[1]);
            Double result = 0.0;
            boolean ok = true;
            switch (op) {
                case "+":
                    result = n1 + n2;
                    break;
                case "-":
                    result = n1 - n2;
                    break;
                case "/":
                    if (n2 != 0)
                        result = n1 / n2;
                    else
                        ok = false;
                    break;
                default:
                    result = n1 * n2;
                    break;
            }
            if (ok)
                tela = result.toString();
            else
                tela = "ERROR";
            res = true;
        }
        msg.setText(tela);
    }

    public void backspace(View v){
        msg = (EditText) findViewById(R.id.caixa);
        if(!tela.isEmpty()) {
            int tam = tela.length();
            tela = tela.substring(0, tam - 1);
        }
        msg.setText(tela);
    }

    public void clear(View v){
        msg = (EditText) findViewById(R.id.caixa);
        tela="";
        msg.setText(tela);
    }

    public void ponto(View v){
        msg = (EditText) findViewById(R.id.caixa);
        String tag = v.getTag().toString();
        if(!tela.isEmpty()){
            if(op.isEmpty() && !tela.contains("."))
                tela = tela+tag;
            else{
                String[] s = tela.split(op);
                if(!s[1].contains("."))
                    tela = tela+tag;
            }
        }
        msg.setText(tela);
    }

}