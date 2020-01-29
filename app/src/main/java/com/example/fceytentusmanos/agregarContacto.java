package com.example.fceytentusmanos;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.OperationApplicationException;
import android.content.pm.PackageManager;
import android.provider.ContactsContract;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class agregarContacto extends AppCompatActivity {
    EditText nomContacto;
    EditText numContacto;
    Button btnAgregar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_contacto);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle extras=getIntent().getExtras();
        nomContacto=(EditText)findViewById(R.id.nomContact);
        nomContacto.setText(extras.getString("nombre"));
        numContacto=(EditText)findViewById(R.id.numContact);
        btnAgregar=(Button)findViewById(R.id.btnagregar);
        if(extras.getString("nombre").equals("Ing. Pedro Juvenal Basualdo")){
            numContacto.setText("+543854509560");
        }
        if(extras.getString("nombre").equals("Dr. Ing. Carlos Ramón Juárez")){
            numContacto.setText("+543854509560");
        }
        if(extras.getString("nombre").equals("Dra. María Fernanda Mellano")){
            numContacto.setText("+543854509560");
        }
        if(extras.getString("nombre").equals("Lic. Juan Carlos Coronel Gallardo")){
            numContacto.setText("+543854509560");
        }
        if(extras.getString("nombre").equals("Dra. María José Benac")){
            numContacto.setText("+543854509560");
        }
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                    if(getBaseContext().checkSelfPermission(Manifest.permission.WRITE_CONTACTS)==
                            PackageManager.PERMISSION_DENIED){
                        String[] permissions={Manifest.permission.WRITE_CONTACTS};
                        requestPermissions(permissions,1001);
                    }
                    else{
                        insertarContacto();
                    }
                }
                else {
                    insertarContacto();
                }
            }
        });
    }
    private void insertarContacto(){
        String displayName=nomContacto.getText().toString();
        String number=numContacto.getText().toString();
        ArrayList<ContentProviderOperation> ops = new ArrayList <ContentProviderOperation> ();
        ops.add(ContentProviderOperation.newInsert(
                ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                .build());
        ops.add(ContentProviderOperation.newInsert(
                ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Data.MIMETYPE,
                        ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                .withValue(
                        ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,
                        displayName).build());
        ops.add(ContentProviderOperation.
                    newInsert(ContactsContract.Data.CONTENT_URI)
                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                    .withValue(ContactsContract.Data.MIMETYPE,
                            ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                    .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, number)
                    .withValue(ContactsContract.CommonDataKinds.Phone.TYPE,
                            ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
                    .build());
        try {
            getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
            Toast.makeText(getBaseContext(), "CONTACTO INSERTADO CON EXITO!!!!", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getBaseContext(), "Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
