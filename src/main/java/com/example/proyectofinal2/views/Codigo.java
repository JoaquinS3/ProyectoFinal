package com.example.proyectofinal2.views;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Codigo extends Stage implements EventHandler<KeyEvent> {

    private VBox vBox;
    private ToolBar tlbMenu;
    private TextArea txt1, txt2;
    private FileChooser flcArchivo;
    private Scene escena;
    private Button btnAbrir, btnTraducir, btnBorrar;
    private Image imgAbrir;
    private ImageView imvAbrir;
    private HBox hBox;
    private File file;

    String[] morse = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..",
            ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..",
            "-----", ".----", "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.",
            ".-.-.","  ", " ", ".-.-.-", "--..--", "---...", ".----.", "-....-", "-..-.", "-.--.","-.--.-","-...-",
            "\n","\t" };

    String[] alfanum = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "+", " ","", ".", ",", ":", "'", "-", "/","(",")","=",
            "\n","\t"};


    ArrayList<Character> tam = new ArrayList<Character>();
    ArrayList<String> escritura = new ArrayList<String>();

    private char[] tamaño;
    private String[] escrituraTxt;

    public Codigo(){
        CrearUI();
        this.setTitle("Práctica 3: Codificador Código Morse");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        hBox = new HBox();
        vBox = new VBox();
        tlbMenu = new ToolBar();
        file = new File("src/main/java/com/example/proyectofinal2/Imagenes/Archivo.jpg");
        imgAbrir = new Image(file.toURI().toString());
        imvAbrir = new ImageView(imgAbrir);
        imvAbrir.setFitHeight(25);
        imvAbrir.setFitWidth(25);




        btnAbrir = new Button("Abrir Archivo");
        btnAbrir.setGraphic(imvAbrir);
        btnAbrir.setOnAction(actionEvent -> {
            //aquì va el bloque de còdigo
            flcArchivo = new FileChooser();
            flcArchivo.setTitle("Selecciona Archivo");
            flcArchivo.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Archivos de texto", "*.txt"));
            File archivo =  flcArchivo.showOpenDialog(this);
            InputStream doc = null;
            try {
                doc = new FileInputStream(String.valueOf(archivo));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Scanner lee = new Scanner(doc);
            while (lee.hasNextLine()){
                txt1.appendText(lee.nextLine());
                txt1.appendText("\n");
            }
        });
        tlbMenu.getItems().addAll(btnAbrir);
        //...
        txt1 = new TextArea();
        txt1.setPromptText("");
        txt1.setOnKeyPressed(this);

        txt2 = new TextArea();
        txt2.setEditable(false);

        btnTraducir = new Button("Traducir");
        btnTraducir.setOnAction(actionEvent ->{
            TraduceEscrito();
        });

        btnBorrar = new Button("Borrar");
        btnBorrar.setOnAction(actionEvent->{
            txt1.setText("");
            txt2.setText("");
            tam.clear();
            escritura.clear();

        });

        hBox.getChildren().addAll(btnTraducir,btnBorrar);
        hBox.setSpacing(5);
        hBox.setPadding(new Insets(5));
        hBox.setAlignment(Pos.CENTER);

        vBox.getChildren().addAll(tlbMenu, txt1, txt2,hBox);
        vBox.setSpacing(5);
        vBox.setPadding(new Insets(5));
        escena = new Scene(vBox,500,300);



    }

    private void TraduceEscrito() {

        char[] tamaño = new char[txt1.getLength()];
        String[] escrituraTxt = new String[tamaño.length];

        txt2.setText("");

        for (int i = 0; i <= txt1.getLength()-1; i++) {
            tamaño[i] = txt1.getText().charAt(i);
        }

        for (int i = 0; i <= tamaño.length - 1 ; i++) {
            for (int j = 0; j < morse.length; j++) {
                if (String.valueOf(tamaño[i]).equals(alfanum[j])) {
                    txt2.appendText(morse[j]+" ");

                }

            }
        }
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        Traduce(keyEvent);

    }

    private void Traduce(KeyEvent keyEvent) {
        String guarda  = "";

        String compa = keyEvent.getCode().toString().toLowerCase();
        String cadena = "";


        if (compa.length()<=1) {
            tam.add(compa.charAt(compa.length()-1));

            switch (String.valueOf(tam.get(tam.size()-1))){
                case "a":
                    if (keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add(".- ");
                        txt2.appendText(escritura.get(escritura.size() - 1));
                    }
                    break;
                case "b":
                    if (keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add("-... ");
                        txt2.appendText(escritura.get(escritura.size() - 1));
                    }
                    break;
                case "c":
                    if (keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add("-.-. ");
                        txt2.appendText(escritura.get(escritura.size() - 1));
                    }
                    break;
                case "d":
                    if (keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add("-.. ");
                        txt2.appendText(escritura.get(escritura.size() - 1));
                    }
                    break;
                case "e":
                    if (keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add(". ");
                        txt2.appendText(escritura.get(escritura.size() - 1));
                    }
                    break;
                case "f":
                    if (keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add("..-. ");
                        txt2.appendText(escritura.get(escritura.size() - 1));
                    }
                    break;
                case "g":
                    if (keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add("--. ");
                        txt2.appendText(escritura.get(escritura.size() - 1));
                    }
                    break;
                case "h":
                    if (keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add(".... ");
                        txt2.appendText(escritura.get(escritura.size() - 1));
                    }
                    break;
                case "i":
                    if (keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add(".. ");
                        txt2.appendText(escritura.get(escritura.size() - 1));
                    }
                    break;
                case"j":
                    if (keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add(".--- ");
                        txt2.appendText(escritura.get(escritura.size() - 1));
                    }
                    break;
                case "k":
                    if (keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add("-.- ");
                        txt2.appendText(escritura.get(escritura.size() - 1));
                    }
                    break;
                case "l":
                    if (keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add(".-.. ");
                        txt2.appendText(escritura.get(escritura.size() - 1));
                    }
                    break;
                case "m":
                    if (keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add("-- ");
                        txt2.appendText(escritura.get(escritura.size() - 1));
                    }
                    break;
                case "n":
                    if (keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add("-. ");
                        txt2.appendText(escritura.get(escritura.size() - 1));
                    }
                    break;
                case "o":
                    if (keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add("--- ");
                        txt2.appendText(escritura.get(escritura.size() - 1));
                    }
                    break;
                case "p":
                    if (keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add(".--. ");
                        txt2.appendText(escritura.get(escritura.size() - 1));
                    }
                    break;
                case "q":

                    if (keyEvent.isAltDown()|| keyEvent.isMetaDown() || keyEvent.isControlDown()){

                    }else {
                        escritura.add("--.- ");
                        txt2.appendText(escritura.get(escritura.size()-1));
                    }
                    break;
                case "r":
                    if (keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add(".-. ");
                        txt2.appendText(escritura.get(escritura.size() - 1));
                    }
                    break;
                case "s":
                    if (keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add("... ");
                        txt2.appendText(escritura.get(escritura.size() - 1));
                    }
                    break;
                case "t":
                    if (keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add("- ");
                        txt2.appendText(escritura.get(escritura.size() - 1));
                    }
                    break;
                case "u":
                    if (keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add("..- ");
                        txt2.appendText(escritura.get(escritura.size() - 1));
                    }
                    break;
                case "v":
                    if (keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add("...- ");
                        txt2.appendText(escritura.get(escritura.size() - 1));
                    }
                    break;
                case "w":
                    if (keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add(".-- ");
                        txt2.appendText(escritura.get(escritura.size() - 1));
                    }
                    break;
                case "x":
                    if (keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add("-..- ");
                        txt2.appendText(escritura.get(escritura.size() - 1));
                    }
                    break;
                case "y":
                    if (keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add("-.-- ");
                        txt2.appendText(escritura.get(escritura.size() - 1));
                    }
                    break;
                case "z":
                    if (keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add("--.. ");
                        txt2.appendText(escritura.get(escritura.size() - 1));
                    }
                    break;

            }
        }else{
            switch (compa){
                case "back_space":
                    if (keyEvent.isControlDown()){
                        //Se ignora la clase porque se borra en la parte de arriba
                    }else{
                        if (!escritura.isEmpty()) {
                            guarda = "";
                            escritura.remove(escritura.size()-1);
                            Iterator<String> imprime = escritura.iterator();
                            while (imprime.hasNext()){
                                guarda = guarda  + imprime.next();

                            }
                            txt2.setText(guarda);
                        }
                    }
                    break;
                case "space":
                    escritura.add("  ");
                    txt2.appendText(escritura.get(escritura.size()-1));
                    break;
                case "enter":
                    escritura.add("\n");
                    txt2.appendText(escritura.get(escritura.size()-1));
                    break;
                case "digit0":
                    if (keyEvent.isShiftDown() || keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add("----- ");
                        txt2.appendText(escritura.get(escritura.size()-1));
                    }

                    break;
                case "digit1":
                    if (keyEvent.isShiftDown() || keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add(".---- ");
                        txt2.appendText(escritura.get(escritura.size()-1));
                    }
                    break;
                case "digit2":
                    if (keyEvent.isShiftDown() || keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add("..--- ");
                        txt2.appendText(escritura.get(escritura.size()-1));
                    }

                    break;
                case "digit3":
                    if (keyEvent.isShiftDown() || keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add("...-- ");
                        txt2.appendText(escritura.get(escritura.size()-1));
                    }
                    break;
                case "digit4":
                    if (keyEvent.isShiftDown() || keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add("....- ");
                        txt2.appendText(escritura.get(escritura.size()-1));
                    }
                    break;
                case"digit5":
                    if (keyEvent.isShiftDown() || keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add("..... ");
                        txt2.appendText(escritura.get(escritura.size()-1));
                    }
                    break;
                case"digit6":
                    if (keyEvent.isShiftDown() || keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add("-.... ");
                        txt2.appendText(escritura.get(escritura.size()-1));
                    }
                    break;
                case"digit7":
                    if (keyEvent.isShiftDown() || keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add("--... ");
                        txt2.appendText(escritura.get(escritura.size()-1));
                    }
                    break;
                case "digit8":
                    if (keyEvent.isShiftDown() || keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add("---.. ");
                        txt2.appendText(escritura.get(escritura.size()-1));
                    }
                    break;
                case "digit9":
                    if (keyEvent.isShiftDown() || keyEvent.isAltDown() || keyEvent.isControlDown() || keyEvent.isMetaDown()) {

                    }else {
                        escritura.add("----. ");
                        txt2.appendText(escritura.get(escritura.size()-1));
                    }
                    break;
                case "tab":
                    escritura.add("\t");
                    txt2.appendText(escritura.get(escritura.size()-1));
                    break;
            }
        }
    }
}
