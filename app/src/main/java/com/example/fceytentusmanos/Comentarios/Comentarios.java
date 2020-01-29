package com.example.fceytentusmanos.Comentarios;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.fceytentusmanos.R;
import com.google.api.services.gmail.Gmail;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Comentarios extends Fragment {

    private ComentariosViewModel mViewModel;

    public static Comentarios newInstance() {
        return new Comentarios();
    }
    Button btnenviar;

    Session session=null;
    ProgressDialog pdialog = null;
    Context context = null;
    EditText mensajeET, correoUsuarioET, asuntoET, contraseñaUsuarioET;
    String mailUsuario, asunto, mensaje, contraseña;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.comentarios_fragment, container, false);
        btnenviar=(Button) view.findViewById(R.id.btnenviar);
        mensajeET=(EditText) view.findViewById(R.id.et_Mensaje);
        btnenviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarEmail();
            }
        });
        context=this.getContext();
        correoUsuarioET = (EditText) view.findViewById(R.id.et_CorreoUsuario);
        asuntoET = (EditText) view.findViewById(R.id.et_Asunto);
        contraseñaUsuarioET = (EditText) view.findViewById(R.id.et_Contrasenia);
        return view;
    }


    //Este metodo solo permite enviar mensajes desde correos gmail
    //this method only works with GMail
    public void enviarEmail() {
        pdialog = ProgressDialog.show(context, "", "Enviando correo...",true);

        //Policy configurations
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        asunto = asuntoET.getText().toString();
        mailUsuario = correoUsuarioET.getText().toString();
        mensaje = mensajeET.getText().toString();
        contraseña = contraseñaUsuarioET.getText().toString();
        //Connection to GMail's SMTP server
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.starttls.enable", "true") ;
        prop.put("mail.smtp.auth", "true") ;

        try {
            session = Session.getDefaultInstance(prop,
                    new javax.mail.Authenticator() {
                        //Authenticating the password
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(mailUsuario, contraseña);
                        }
                    });

            if(session != null){
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(mailUsuario));
                message.setSubject(asunto);
                //Destination mail
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("santi.tagliavini@Gmail.com"));
                message.setContent(mensaje, "text/html; charset= utf-8");
                Transport.send(message);
                Toast.makeText(context,"¡El correo ha sido enviado a: santi.tagliavini@Gmail.com!",Toast.LENGTH_LONG).show();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        pdialog.hide();
        Toast.makeText(context,"¡El correo ha sido enviado a: santi.tagliavini@Gmail.com!",Toast.LENGTH_LONG).show();
    }
    /*public void enviarEmail(){
        String[] TO={"santi.tagliavini@gmail.com"};
        Intent email=new Intent(Intent.ACTION_SEND);
        email.setData(Uri.parse("mailto:"));
        email.setType("text/plain");
        email.putExtra(Intent.EXTRA_EMAIL,TO);
        //email.putExtra(Intent.EXTRA_CC,);
        email.putExtra(Intent.EXTRA_SUBJECT,"Consultas FCE");
        email.putExtra(Intent.EXTRA_TEXT,mensaje.getText());
        startActivity(Intent.createChooser(email,"Send Email"));
    }
    */



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ComentariosViewModel.class);
        // TODO: Use the ViewModel
    }

}
