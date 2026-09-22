package com.mydomain.registrationApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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

        // Associando as referências dos botões da tela principal.
        Button btn_config = (Button) findViewById(R.id.btn_config);
        Button btn_exit = (Button) findViewById(R.id.btn_exit);

        // declarando "escutadores" dos botões da tela principal.
        btn_config.setOnClickListener(this);
        btn_exit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // ação para acessar/iniciar uma nova activity, a de configurações.
        if (v.getId() == R.id.btn_config){
            Intent intent = new Intent(this, ConfigActivity.class);
            startActivity(intent);
        }
        // ação que finaliza a atividade principal.
        if (v.getId() == R.id.btn_exit){
            finish();
        }
    }
}
