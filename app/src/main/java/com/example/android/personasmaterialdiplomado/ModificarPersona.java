package com.example.android.personasmaterialdiplomado;

import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class ModificarPersona extends AppCompatActivity {
    private EditText txtCedula;
    private EditText txtNombre;
    private EditText txtApellido;
    Persona p;
    private TextInputLayout cajaCedula;
    private TextInputLayout cajaNombre;
    private TextInputLayout cajaApellido;
    private ArrayList<Integer> fotos;
    private String cedula, nombre, apellido;
    private int  sexo_bun,fotox;
    private Bundle bundle;
    private Intent i;
    private Resources res;
    private Spinner sexo;
    private ArrayAdapter<String> adapter;
    private String[] opc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_persona);
        res = this.getResources();
        txtCedula = (EditText) findViewById(R.id.txtCedula);
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellido = (EditText) findViewById(R.id.txtapellido);
        cajaCedula = (TextInputLayout) findViewById(R.id.cajaCedula);
        cajaNombre = (TextInputLayout) findViewById(R.id.cajaNombre);
        cajaApellido = (TextInputLayout) findViewById(R.id.cajaApellido);
        sexo = (Spinner) findViewById(R.id.cmbSexo);
        opc = res.getStringArray(R.array.sexo);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opc);
        sexo.setAdapter(adapter);
        i= getIntent();
        bundle =i.getBundleExtra("datos");
        cedula =bundle.getString("cedula");
        nombre =bundle.getString("nombre");
        apellido =bundle.getString("apellido");
        sexo_bun =bundle.getInt("sexo");
        fotox =bundle.getInt("foto");
        txtCedula.setText(cedula);
        txtNombre.setText(nombre);
        txtApellido.setText(apellido);
        sexo.setSelection(sexo_bun);
        iniciar_fotos();
    }



    public void iniciar_fotos() {

        fotos = new ArrayList<>();
        fotos.add(R.drawable.images);
        fotos.add(R.drawable.images2);
        fotos.add(R.drawable.images3);
    }

    protected  void Modificar(View V){
        if (validar()) {
          p = new Persona(0, txtCedula.getText().toString(),
                    txtNombre.getText().toString(), txtApellido.getText().toString(), sexo.getSelectedItemPosition());
            Datos.ModificarPersonas(p,cedula);
            Snackbar.make(V, res.getString(R.string.mensaje_modificado), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

            onBackPressed();

        }
    }
    protected  void Cancelar(View V){
        onBackPressed();
    }
    public void onBackPressed() {

        Intent i = new Intent(ModificarPersona.this, DetallePersona.class);
        Bundle b=new Bundle();
        b.putString("cedula",p.getCedula());
        b.putString("nombre",p.getNombre());
        b.putString("apellido",p.getApellido());
        b.putInt("sexo",p.getSexo());
        b.putInt("foto",fotox);


        i.putExtra("datos",b);
        startActivity(i);
    }

    public boolean validar() {
        if (validar_aux(txtCedula,cajaCedula)) return false;
        else if (validar_aux(txtNombre,cajaNombre)) return false;
        else if (validar_aux(txtApellido,cajaApellido)) return false;
        else if(txtCedula.getText().toString().equalsIgnoreCase(cedula)) return true;
        else if (Metodos.existencia_Persna(Datos.obtenerPersonas(),txtCedula.getText().toString())){
            cajaCedula.setError(res.getString(R.string.persona_existente_error));
            cajaCedula.setSelected(true);
            return  false;
        }

        return true;
    }

    public boolean validar_aux(TextView t, TextInputLayout ct) {
        if (t.getText().toString().isEmpty()) {
            t.requestFocus();
            t.setError(res.getString(R.string.no_vacio_error));
            return true;
        }
        return false;
    }
}
