/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generals;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import views.jContacto;
import views.jprincipal;
import views.jMensajeriaEnviar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import views.jEnvioSeleccionado;

/**
 *
 * @author Programador 1
 */
public class FileTxt {

    Thread thread;

    public void openFile(jprincipal viewPrincipal,
            jMensajeriaEnviar viewMensajeria) throws IOException {
        ArrayList<Object[]> listData;
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(".txt"));
        chooser.setDialogTitle("¿Donde esta el archivo que vas a enviar?");
        chooser.setMultiSelectionEnabled(true);

        if (chooser.showOpenDialog(viewPrincipal) == JFileChooser.APPROVE_OPTION) {
            File[] files = chooser.getSelectedFiles();
            for (File file : files) {
                // idDocumento = Integer.parseInt(file.getName().split("-")[0].trim());
                listData = readFileTxt(file);
                addRow(viewMensajeria, listData);
            }
        }
    }

    public void openFile(jprincipal viewPrincipal,
            jEnvioSeleccionado viewMensajeria) throws IOException {
        ArrayList<Object[]> listData;
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(".txt"));
        chooser.setDialogTitle("¿Donde esta el archivo que vas a enviar?");
        chooser.setMultiSelectionEnabled(true);

        if (chooser.showOpenDialog(viewPrincipal) == JFileChooser.APPROVE_OPTION) {
            File[] files = chooser.getSelectedFiles();
            for (File file : files) {
                // idDocumento = Integer.parseInt(file.getName().split("-")[0].trim());
                listData = readFileTxt(file);
                addRow(viewMensajeria, listData);
            }
        }
    }

    public void openFile(jprincipal viewPrincipal,
            jContacto viewContacto) throws IOException {
        ArrayList<Object[]> listData;
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(".txt"));
        chooser.setDialogTitle("¿Donde esta el archivo que vas a enviar?");
        chooser.setMultiSelectionEnabled(true);

        if (chooser.showOpenDialog(viewPrincipal) == JFileChooser.APPROVE_OPTION) {
            File[] files = chooser.getSelectedFiles();
            for (File file : files) {
                listData = readFileTxt(file);
                addRow(viewContacto, listData);
            }
        }
    }

    private void addRow(jMensajeriaEnviar viewMensajeria,
            ArrayList<Object[]> listData) {
        DefaultTableModel modelo = (DefaultTableModel) viewMensajeria.jtEmail.getModel();

        listData.forEach(
                (listData1) -> {
                    modelo.addRow(listData1);
                });
        viewMensajeria.jtEmail.setModel(modelo);
    }

    private void addRow(jContacto viewContacto,
            ArrayList<Object[]> listData) {
        DefaultTableModel modelo = (DefaultTableModel) viewContacto.jTCorreo.getModel();

        listData.forEach(
                (listData1) -> {
                    modelo.addRow(listData1);
                });
        viewContacto.jTCorreo.setModel(modelo);
    }

    private void addRow(jEnvioSeleccionado viewMensajeria,
            ArrayList<Object[]> listData) {
        DefaultTableModel modelo = (DefaultTableModel) viewMensajeria.jtEmail.getModel();

        listData.forEach(
                (listData1) -> {
                    modelo.addRow(listData1);
                });
        viewMensajeria.jtEmail.setModel(modelo);
    }

    public ArrayList<Object[]> readFileTxt(File file) throws IOException {
        ArrayList<Object[]> list = new ArrayList<>();

        FileReader fr;
        BufferedReader br;
        String line;
        Object[] array;
        try {

            fr = new FileReader(file);
            br = new BufferedReader(fr);
            Pattern pattern = Pattern
                    .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
            try {
                while ((line = br.readLine()) != null) {
                    Matcher mather = pattern.matcher(line);
                    if (mather.find() == true) {                        String[] separado = line.split(",");
                        array = new String[separado.length];
                        System.arraycopy(separado, 0, array, 0, separado.length);
                        list.add(array);
                    }
                }

            } catch (IOException ex) {
                Logger.getLogger(FileTxt.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileTxt.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public static String convertCodeToUtf(String cadena) throws UnsupportedEncodingException {
        byte[] utf8 = cadena.getBytes(StandardCharsets.US_ASCII);
        cadena = new String(utf8, StandardCharsets.UTF_8);
        return cadena;
    }

    public void createFileVCF(jContacto viewContacto) {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String ruta = createDirectorio() + '/' + viewContacto.comboServidor.getSelectedItem().toString() + ".vcf";
                    String contenido = "";
                    String plantilla;
                    int count = viewContacto.jTCorreo.getModel().getRowCount();
                    viewContacto.jprogressSistem.setMaximum(viewContacto.jTCorreo.getModel().getRowCount());

                    for (int i = 0; i < viewContacto.jTCorreo.getModel().getRowCount(); i++) {

                        plantilla = "BEGIN:VCARD?VERSION:3.0?"
                                + "N: xx" + i
                                + "?FN: xxx" + i
                                + "?EMAIL;TYPE=INTERNET;TYPE=HOME:" + viewContacto.jTCorreo.getModel().getValueAt(i, 0) + "?END:VCARD?";

                        contenido = contenido + plantilla;

                        viewContacto.jprogressSistem.setValue(i);
                        viewContacto.jLbInform.setText("PROCESANDO:" + i + " / " + count);
                    }

                    File file = new File(ruta);

                    if (!file.exists()) {
                        file.createNewFile();
                    }

                    FileWriter fw = new FileWriter(file);

                    try (BufferedWriter bw = new BufferedWriter(fw)) {
                        contenido = contenido.replace("?", "\n");
                        bw.write(contenido);
                        validator(viewContacto);
                    }
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        });
        thread.start();
    }

    private String createDirectorio() {

        String root = System.getProperty("user.home") + "/desktop/contactoMaxmail";

        File directorio = new File(root);

        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Directorio creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        }

        return root;
    }

    private void validator(jContacto viewContacto) {
        DefaultTableModel model = (DefaultTableModel) viewContacto.jTCorreo.getModel();
        ValidControlsSystem.enabledControls(viewContacto.jLayeredPane1);
        viewContacto.jLbInform.setText("PROGRESO:");
        viewContacto.jprogressSistem.setValue(0);
        model.setRowCount(0);
        viewContacto.comboServidor.setSelectedIndex(0);
        JOptionPane.showMessageDialog(null, "proceso terminado");
    }
}
