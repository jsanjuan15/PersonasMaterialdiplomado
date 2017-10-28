package com.example.android.personasmaterialdiplomado;

import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CrearPersonas extends AppCompatActivity {
    private EditText txtCedula;
    private EditText txtNombre;
    private EditText txtApellido;

    private TextInputLayout cajaCedula;
    private TextInputLayout cajaNombre;
    private TextInputLayout cajaApellido;
    private ArrayList<Integer> fotos;
    private Resources res;
    private Spinner sexo;
    private ArrayAdapter<String> adapter;
    private String[] opc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_personas);
        txtCedula = (EditText) findViewById(R.id.txtCedula);
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellido = (EditText) findViewById(R.id.txtapellido);


        res = this.getResources();
        cajaCedula = (TextInputLayout) findViewById(R.id.cajaCedula);
        cajaNombre = (TextInputLayout) findViewById(R.id.cajaNombre);
        cajaApellido = (TextInputLayout) findViewById(R.id.cajaApellido);
        sexo = (Spinner) findViewById(R.id.cmbSexo);
        opc = res.getStringArray(R.array.sexo);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opc);
        sexo.setAdapter(adapter);

        limpiar();
        iniciar_fotos();
    }

    public void limpiar(View V) {
        limpiar();
    }

    public void iniciar_fotos() {

        fotos = new ArrayList<>();
        fotos.add(R.drawable.images);
        fotos.add(R.drawable.images2);
        fotos.add(R.drawable.images3);
    }

    public void guardar(View V) {

        if (validar()) {
            Persona p = new Persona(Metodos.fotoAleatoria(fotos), txtCedula.getText().toString(),
                    txtNombre.getText().toString(), txtApellido.getText().toString(), sexo.getSelectedItemPosition());
            p.guardar();
            Snackbar.make(V, res.getString(R.string.mensaje_guardado), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

            limpiar();

        }
    }

    public void limpiar() {
        txtCedula.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        sexo.setSelection(0);
        txtNombre.requestFocus();
    }

    public void onBackPressed() {
        finish();
        Intent i = new Intent(CrearPersonas.this, Principal.class);
        startActivity(i);
    }

    public boolean validar() {
        if (validar_aux(txtCedula,cajaCedula)) return false;
        else if (validar_aux(txtNombre,cajaNombre)) return false;
        else if (validar_aux(txtApellido,cajaApellido)) return false;
        else if (Metodos.existencia_Persna(Datos.obtenerPersonas(),txtCedula.getText().toString())){
           cajaCedula.setError(res.getString(R.string.persona_existente_error));
            cajaCedula.setSelected(true);
            return  false;
        }

        return true;
    }

    public boolean validar_aux(TextView t,TextInputLayout ct) {
        if (t.getText().toString().isEmpty()) {
            t.requestFocus();
            t.setError(res.getString(R.string.no_vacio_error));
            return true;
        }
      return false;
    }

}