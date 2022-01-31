package com.example.calculadoraiphone;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    // SONIDO DEL CLICK //
    private MediaPlayer clickSound;

    // TEXTVIEW //
    
    private static TextView texto;
    private static TextView txtDebug;

    // CLASE OPERACIONES //

    Operaciones operaciones = new Operaciones();

    // VARIABLES PARA LA APLICACION //

    private static String num1 = null, num2 = null, operacion = null;
    private static boolean existeOperacion = false, esNegativo = false, puedeConcatenar = true;
    private static float ultimoResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // DESACTIVAMOS LA ROTACIÓN
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        clickSound = MediaPlayer.create(this,R.raw.click);

        // INITIALIZE BUTTONS //

        findViewById(R.id.btnAC).setOnClickListener(this);
        findViewById(R.id.btnMasMenos).setOnClickListener(this);
        findViewById(R.id.btnPorcentaje).setOnClickListener(this);
        findViewById(R.id.btnDivision).setOnClickListener(this);
        findViewById(R.id.btn7).setOnClickListener(this);
        findViewById(R.id.btn8).setOnClickListener(this);
        findViewById(R.id.btn9).setOnClickListener(this);
        findViewById(R.id.btnMultiplicacion).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
        findViewById(R.id.btn6).setOnClickListener(this);
        findViewById(R.id.btnResta).setOnClickListener(this);
        findViewById(R.id.btn1).setOnClickListener(this);;
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btnSuma).setOnClickListener(this);
        findViewById(R.id.btn0).setOnClickListener(this);
        findViewById(R.id.btnComa).setOnClickListener(this);
        findViewById(R.id.btnIgual).setOnClickListener(this);
        findViewById(R.id.btnDelete).setOnClickListener(this);

        // INITIALIZE TV //

        texto = findViewById(R.id.texto);
        txtDebug = findViewById(R.id.txtDebug);

        ///// VALORES POR DEFECTO /////

        texto.setText("0");

    }


    @Override
    public void onClick(View v) {

        // SONIDO DE CUANDO PULSA UNA TECLA
        clickSound.start();

        switch (v.getId()) {

            case R.id.btn1:

                    concatenarNumero("1");

                break;

            case R.id.btn2:

                concatenarNumero("2");

                break;

            case R.id.btn3:

                concatenarNumero("3");

                break;

            case R.id.btn4:


                concatenarNumero("4");

                break;

            case R.id.btn5:

                concatenarNumero("5");

                break;

            case R.id.btn6:


                concatenarNumero("6");

                break;

            case R.id.btn7:

                concatenarNumero("7");

                break;

            case R.id.btn8:

                concatenarNumero("8");

                break;

            case R.id.btn9:

                concatenarNumero("9");

                break;

            case R.id.btn0:

                System.out.println(num1);
                // EVITA QUE SE PUEDAN PONER NUMEROS COMO 000007....
                if(!existeOperacion) {

                    if (num1 != null && !num1.equals("")) {

                        if (!num1.equals("0")) {

                            concatenarNumero("0");
                            System.out.println("b");
                        } else {
                            System.out.println("a");
                            setDebugText(false, "No sirve de mucho añadir 0's");
                        }
                    }else{

                            concatenarNumero("0");

                        }

                    }

                if (existeOperacion){

                    if(num2 != null && !num2.equals("")){

                        if(!num2.equals("0")){

                            concatenarNumero("0");

                        }else{

                            setDebugText(false, "No sirve de mucho añadir 0's");
                        }

                    }else{

                        concatenarNumero("0");
                    }


                }
                break;

            case R.id.btnComa:

                // SI NO HAY NINGÚN NUMERO, PARA HACER LA INTERFAZ MEJOR AÑADE UNA ,
                if(num1 == null || num1.equals("")) {

                    num1 = "0.";


                }
                if(num1 != null){

                    if(num1.equals("-")){

                        num1 = "-0.";

                    }

                }

                if((num2 == null || num2.equals("")) && existeOperacion) {

                    num2 = "0.";

                }

                if(num2 != null){

                    if(num2.equals("-")){

                        num2 = "-0.";

                    }

                }

                if(!existeOperacion){

                    // NUM1

                    if(!existeComa(num1)){

                        num1 = num1 + ".";
                    }else{

                        setDebugText(false, "Ya existe una coma");

                    }

                }else{

                    // NUM2

                    if(!existeComa(num2)){

                        num2 = num2 + ".";

                    }else{

                        setDebugText(false, "Ya existe una coma");

                    }

                }

                actualizarPantalla();

                break;

            case R.id.btnMasMenos:

                if (!esNegativo) {

                    if (!existeOperacion) {

                        if (num1 != null && !comprobarNegativo(num1)) {

                            num1 = "-" + num1;
                            esNegativo = true;

                            setDebugText(true, "Numero 1 es ahora NEGATIVO");

                        }

                    } else {

                        if (num2 != null && !comprobarNegativo(num2)) {

                            num2 = "-" + num2;
                            esNegativo = true;


                            setDebugText(true, "Numero 2 es ahora NEGATIVO");
                        }

                    }

                } else {

                    if (!existeOperacion) {

                        num1 = num1.replace("-", "");
                        esNegativo = false;

                        setDebugText(true, "Numero 1 es ahora POSITIVO");

                    } else {

                        num2 = num2.replace("-", "");
                        esNegativo = false;

                        setDebugText(true, "Numero 2 es ahora POSITIVO");
                    }

                }

                // SI NO HA ESCRITO NINGÚN NUMERO
                if (num1 == null) {

                    num1 = "-";
                    esNegativo = true;

                }
                if (num2 == null && existeOperacion) {

                    num2 = "-";
                    esNegativo = true;

                }

                // ACTUALIZAMOS LA PANTALLA
                actualizarPantalla();

                break;

            case R.id.btnPorcentaje:

                if(comprobarNumero(num1)){

                    existeOperacion = true;
                    esNegativo = false;
                    operacion = "%";

                    setDebugText(true, "Operación " + operacion + " lista para ejecutarse");

                }else{

                    setDebugText(false, "Tu número " + num1 + " no es válido");

                }
                actualizarPantalla();

                break;

            case R.id.btnDivision:

                if(comprobarNumero(num1)){

                    existeOperacion = true;
                    esNegativo = false;
                    operacion = "÷";

                    setDebugText(true, "Operación " + operacion + " lista para ejecutarse");

                }else{

                    setDebugText(false, "Tu número " + num1 + " no es válido");

                }
                actualizarPantalla();

                break;

            case R.id.btnMultiplicacion:

                if(comprobarNumero(num1)){

                    existeOperacion = true;
                    esNegativo = false;
                    operacion = "×";

                    setDebugText(true, "Operación " + operacion + " lista para ejecutarse");

                }else{

                    setDebugText(false, "Tu número " + num1 + " no es válido");

                }
                actualizarPantalla();

                break;

            case R.id.btnResta:

                if(comprobarNumero(num1)){

                    existeOperacion = true;
                    esNegativo = false;
                    operacion = "-";

                    setDebugText(true, "Operación " + operacion + " lista para ejecutarse");

                }else{

                    setDebugText(false, "Tu número " + num1 + " no es válido");

                }
                actualizarPantalla();


                break;

            case R.id.btnSuma:

                if(comprobarNumero(num1)){

                    existeOperacion = true;
                    esNegativo = false;
                    operacion = "+";

                    setDebugText(true, "Operación " + operacion + " lista para ejecutarse");

                }else{

                    setDebugText(false, "Tu número " + num1 + " no es válido");

                }
                actualizarPantalla();

                break;

            case R.id.btnAC:

                reestablecerValores();

                break;

            case R.id.btnIgual:

                ComprobarEasterEgg();

                if(comprobarNumero(num2) && existeOperacion) {

                        switch (operacion) {

                            case "+":

                                ultimoResultado = operaciones.suma(Float.valueOf(num1), Float.valueOf(num2));

                                num1 = Float.toString(ultimoResultado);
                                num2 = null;
                                operacion = null;
                                existeOperacion = false;

                                break;

                            case "-":

                                ultimoResultado = operaciones.resta(Float.valueOf(num1), Float.valueOf(num2));

                                num1 = Float.toString(ultimoResultado);
                                num2 = null;
                                operacion = null;
                                existeOperacion = false;

                                break;

                            case "×":

                                ultimoResultado = operaciones.multiplicacion(Float.valueOf(num1), Float.valueOf(num2));

                                num1 = Float.toString(ultimoResultado);
                                num2 = null;
                                operacion = null;
                                existeOperacion = false;

                                break;

                            case "÷":

                                ultimoResultado = operaciones.division(Float.valueOf(num1), Float.valueOf(num2));

                                num1 = Float.toString(ultimoResultado);
                                num2 = null;
                                operacion = null;
                                existeOperacion = false;

                                break;

                            case "%":

                                ultimoResultado = operaciones.porcentaje(Float.valueOf(num1), Float.valueOf(num2));

                                num1 = Float.toString(ultimoResultado);
                                num2 = null;
                                operacion = null;
                                existeOperacion = false;

                                break;

                        }

                        setDebugText(true, "Operación exitosa");

                        // QUITAMOS EL .0 QUE SE PONE AL SER FLOAT SI ES EL UNICO DECIMAL QUE HAY
                        num1 = quitarDecimal(num1);
                        System.out.println("Nuevo valor de número1: " + num1);

                        // EVITAMOS QUE PUEDA ESCRIBIR EN EL NUM1 DE NUEVO
                        puedeConcatenar = false;

                        actualizarPantalla();

                }else{

                    setDebugText(false, "Escribe una operación correcta");

                }

                break;

            case R.id.btnDelete:

                borrar();
                actualizarPantalla();

                break;

        }

    }

    /**
     * MÉTOOD PARA BORRAR EL ÚLTIMO CARACTER
     */
    private void borrar() {


            // NO HAY OPERACIÓN
            if (operacion == null || operacion.equals("")){

                if(num1 != null && !num1.equals("") && !num1.equals("0") && !num1.equals("Infinity")){

                    setDebugText(true, "Elemento " + num1.charAt(num1.length()-1) + " borrado con éxito");
                    num1 = num1.substring(0, num1.length()-1);

                }else{

                    setDebugText(false, "No se puede borrar");

                }

            }else{

                // SI HAY OPERACION
                if(num2 == null || num2.equals("")){

                    // BORRAMOS LA OPERACION
                    operacion = "";
                    existeOperacion = false;
                    setDebugText(true, "Operación borrada con éxito");

                }else{

                    // BORRAMOS EL NUM2
                    setDebugText(true, "Elemento " + num2.charAt(num2.length()-1) + " borrado con éxito");
                    num2 = num2.substring(0, num2.length()-1);

                }

            }

        System.out.println("Numero1 " + num1);
    }

    /**
     * Reestablece todos los valores (Solo se usa en el botón AC)
     */
    public void reestablecerValores() {

        num1 = null;
        num2 = null;
        texto.setText("0");
        existeOperacion = false;
        operacion = null;
        esNegativo = false;
        puedeConcatenar = true;

        setDebugText(true, "Valores reestablecidos");

    }

    /**
     * Método para actualizar el text de manera sencilla
     */
    private void actualizarPantalla(){

        if (num1 != null){
            texto.setText(num1);

            if(operacion != null){
                texto.setText(num1 + operacion);

                if(num2 != null){
                    texto.setText(num1 + operacion + num2);
                }
            }
        }
    }

    /**
     * MÉTODO PARA COMPROBAR QUE EL NÚMERO QUE HAY ANTES DE HACER UNA OPERACIÓN ES VÁLIDO
     * @param numero
     * @return
     */
    private boolean comprobarNumero(String numero){

        boolean esCorrecto = true;

        // SI NO HA PULSADO NINGÚN NUMERO HACEMOS QUE EL NUM1 VALGA 0 COMO APARECE EN LA PANTALLA
        if(num1 == null || num1.equals("")) {

            num1 = "0";
        }

        if(numero != null && !numero.equals(".") && !numero.equals("-") && !numero.equals("-.") && !numero.equals("")) {

            // COMPROBAMOS QUE EL NUMERO NO ESTÁ VACÍO
            if(numero.equals("")){
                esCorrecto = false;
            }

            // COMPROBAMOS QUE NO TIENE SOLO UN - EN LA PRIMERA POSICION
            if(numero.length() > 0){ // PARA QUE NO CASQUE

                if(numero.length() == 1 && numero.charAt(0) == '-'){

                    esCorrecto = false;
                }

            }

            if(Float.parseFloat(numero) == 0. || Float.parseFloat(numero) == 0.0){

                numero = "0";

            }

        }else{

            esCorrecto = false;
        }

        return esCorrecto;

    }

    /**
     * MÉTODO PARA QUE CUANDO SE SUMEN 2+2, AL SER UN FLOAT NO APAREZCA UN 4.0 (QUE QUEDA FEO)
     * @param numero
     * @return
     */
    public String quitarDecimal(String numero){

        if(numero.charAt(numero.length()-1) == '0' && numero.charAt(numero.length()-2) == '.'){

            numero = numero.replace(".0", "");

        }

        return numero;
    }

    /**
     * MÉTODO PARA CONCATENAR UN NÚMERO A LA CADENA
     * @param numero
     */
    public void concatenarNumero(String numero){

        if(!existeOperacion) {

            if(puedeConcatenar){

                if(num1 == null){

                    num1 = numero;

                }else{

                    num1 += numero;

                }

                setDebugText(true, numero + " concatenado");

            }else{

                setDebugText(false, "No se puede modificar un resultado");

            }

        }
        else {

            if(num2 == null){

                num2 = numero;

            }else{

                num2 += numero;

            }

        }

        // ACTUALIZAMOS LA PANTALLA
        actualizarPantalla();

    }


    /**
     * MÉTODO PARA VER SI EXISTE UNA COMA
     * @param numero
     * @return
     */
    public boolean existeComa(String numero){

        boolean existe = false;

        if(numero.indexOf('.') != -1){

            existe = true;

        }else{

            existe = false;

        }

      return existe;

    }

    /**
     * MÉTODO PARA COMPROBAR SI EL NÚMERO ES NEGATIVO
     * @param numero
     * @return
     */
    public boolean comprobarNegativo(String numero){

        boolean existe = false;

        if(numero.indexOf('-') != -1){

            existe = true;

        }else{

            existe = false;

        }

      return existe;
    }

    /**
     * MÉTODO PARA AGILIZAR LOS MENSAJES ARRIBA DE LA CALCULADORA
     * @param correcto
     * @param mensaje
     */
    void setDebugText(boolean correcto,String mensaje){

        if(correcto) {

            txtDebug.setTextColor(Color.GREEN);
        }else{

            txtDebug.setTextColor(Color.RED);
        }
        txtDebug.setText(mensaje);

    }

    /**
     * MÉTODO PARA EL EASTER EGG.
     * HAY QUE PONER 69.69 Y DARLE AL =
     */
    void ComprobarEasterEgg(){

        if(num1.equals("69.69")){

            Intent CambiarVentana = new Intent (this, easterEggController.class);

            startActivity(CambiarVentana);

            finish();

        }

    }

}