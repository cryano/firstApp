package com.mydomain.registrationApp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ConfigActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "user_config_prefs";
    private static final String KEY_NAME = "key_name";
    private static final String KEY_SEX = "key_sex"; // true = masculino, false = feminino
    private static final String KEY_DIET_HIPOLIPIDICA = "key_diet_hipolipidica";
    private static final String KEY_DIET_HIPOGLICIDICA = "key_diet_hipoglicidica";
    private static final String KEY_DIET_SEM_GLUTEN = "key_diet_sem_gluten";

    private static final String DEFAULT_NAME = "Admin";
    private static final boolean DEFAULT_SEX_MALE = true;

    private EditText editNome;
    private RadioGroup radioGroupSex;
    private RadioButton radioBtnMale;
    private RadioButton radioBtnFemale;
    private CheckBox checkboxHipolipidica;
    private CheckBox checkboxHipoglicidica;
    private CheckBox checkboxSemGluten;

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_config);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editNome = findViewById(R.id.edit_nome);
        radioGroupSex = findViewById(R.id.radio_group_sex);
        radioBtnMale = findViewById(R.id.radio_btn_male);
        radioBtnFemale = findViewById(R.id.radio_btn_female);
        checkboxHipolipidica = findViewById(R.id.checkbox_hipolipidica);
        checkboxHipoglicidica = findViewById(R.id.checkbox_hipoglicidica);
        checkboxSemGluten = findViewById(R.id.checkbox_sem_gluten);

        Button btnConfirm = findViewById(R.id.btn_confirm);
        Button btnCancel = findViewById(R.id.btn_cancel);

        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        carregarConfiguracoes();

        btnConfirm.setOnClickListener(this::onConfirmClick);
        btnCancel.setOnClickListener(v -> finish());
    }

    private void carregarConfiguracoes() {
        String nomeSalvo = prefs.getString(KEY_NAME, DEFAULT_NAME);
        boolean sexoMasculino = prefs.getBoolean(KEY_SEX, DEFAULT_SEX_MALE);
        boolean hipolipidica = prefs.getBoolean(KEY_DIET_HIPOLIPIDICA, false);
        boolean hipoglicidica = prefs.getBoolean(KEY_DIET_HIPOGLICIDICA, false);
        boolean semGluten = prefs.getBoolean(KEY_DIET_SEM_GLUTEN, false);

        editNome.setText(nomeSalvo);

        if (sexoMasculino) {
            radioBtnMale.setChecked(true);
        } else {
            radioBtnFemale.setChecked(true);
        }

        checkboxHipolipidica.setChecked(hipolipidica);
        checkboxHipoglicidica.setChecked(hipoglicidica);
        checkboxSemGluten.setChecked(semGluten);
    }

    private void onConfirmClick(View view) {
        String nome = editNome.getText().toString().trim();
        boolean sexoMasculino = radioGroupSex.getCheckedRadioButtonId() == R.id.radio_btn_male;

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_NAME, nome);
        editor.putBoolean(KEY_SEX, sexoMasculino);
        editor.putBoolean(KEY_DIET_HIPOLIPIDICA, checkboxHipolipidica.isChecked());
        editor.putBoolean(KEY_DIET_HIPOGLICIDICA, checkboxHipoglicidica.isChecked());
        editor.putBoolean(KEY_DIET_SEM_GLUTEN, checkboxSemGluten.isChecked());
        editor.apply();

        finish();
    }
}