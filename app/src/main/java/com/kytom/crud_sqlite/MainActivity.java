package com.kytom.crud_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DataBaseHelper myDb;
    EditText nombre, correo, fecha, txtId;
    Button btnInsert;

    //-------------------LISTAR
    TextView txtlista;
    Button btnlistar;
    //--------------------EDITAR
    Button btnEditar;
    //---------------------ELIMINAR
    Button btnEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DataBaseHelper(this);
        nombre =  findViewById(R.id.name);
        correo =  findViewById(R.id.correo);
        fecha =  findViewById(R.id.fecha);
        btnInsert = findViewById(R.id.boton);
        //------------LISTA
        txtlista = findViewById(R.id.lista);
        btnlistar = findViewById(R.id.btnlistar);
        //------------EDITAR
        btnEditar = findViewById(R.id.Editar);
        txtId = findViewById(R.id.idID);
        //------------EDITAR
        btnEliminar = findViewById(R.id.eliminar);

        //-----------------------------------------//
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickMe();
            }
        });
        //------------
        btnlistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Listar();
            }
        });
        //------------
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Editar();
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Eliminar();
            }
        });
    }
    private void ClickMe() {
        String name = nombre.getText().toString();
        String email = correo.getText().toString();
        String date = fecha.getText().toString();
        Boolean result = myDb.insertData(name, email, date);
        if (result == true) {
            Toast.makeText(this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Data Insertion Failed", Toast.LENGTH_SHORT).show();
        }
    }

    private void Listar() {
        Cursor res = myDb.getAllData();
        StringBuffer stringBuffer = new StringBuffer();
        if(res!=null && res.getCount()>0){
            while (res.moveToNext()){
                stringBuffer.append("Id: "+res.getString(0)+"\n");
                stringBuffer.append("Nombre: "+res.getString(1)+"\n");
                stringBuffer.append("Correro: "+res.getString(2)+"\n");
                stringBuffer.append("Fecha: "+res.getString(3)+"\n"+"\n");
            }
            txtlista.setText(stringBuffer.toString());
            Toast.makeText(this,"Data Retrieved Successfully",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"No Data to Retrieve",Toast.LENGTH_SHORT).show();
        }
    }
    private void Editar(){
        String id = txtId.getText().toString();
        String name = nombre.getText().toString();
        String email = correo.getText().toString();
        String date = fecha.getText().toString();
        Boolean result = myDb.updateData(id,name,email,date);
        if(result==true){
            Toast.makeText(this,"Data Updated Successfully",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"No Rows Affected",Toast.LENGTH_SHORT).show();
        }
    }
    public void Eliminar(){
        String id = txtId.getText().toString();
        int result = myDb.deleteData(id);
        Toast.makeText(this,result+" :Rows Affected",Toast.LENGTH_SHORT).show();
    }
}



