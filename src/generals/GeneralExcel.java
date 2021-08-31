/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generals;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import views.jprincipal;
/**
 *
 * @author Programador 1
 */
public class GeneralExcel {

    private static final HSSFWorkbook book = new HSSFWorkbook();

    public static void GeneraExcel(String path, ArrayList<DefaultTableModel> list,
            String nameFile) {

        String document = path + nameFile + ".xls";
        File file = new File(document);
        //------------------------------------------
        list.forEach((item) -> {

            HSSFSheet sheet = book.createSheet();
            HSSFCell cell;
            HSSFRow row;

            crearEncabezado(item, sheet);

            for (int i = 0; i < item.getRowCount(); i++) {
                row = sheet.createRow(i + 1);
                for (int j = 0; j < item.getColumnCount(); j++) {
                    if (item.getValueAt(i, j) != null) {
                        cell = row.createCell(j);
                        cell.setCellValue(new HSSFRichTextString(item.getValueAt(i, j).toString()));
                    }
                }
                try {

                    try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                        book.write(fileOutputStream);
                        fileOutputStream.close();
                    }

                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        });
        //---------------
        try {
            Desktop.getDesktop().open(file);
        } catch (IOException ex) {
            Logger.getLogger(GeneralExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void crearEncabezado(DefaultTableModel item, HSSFSheet sheet) {
        HSSFRow row;
        HSSFCell cell;
        row = sheet.createRow(0);
        for (int j = 0; j < item.getColumnCount(); j++) {
            cell = row.createCell(j);
            cell.setCellValue(new HSSFRichTextString(item.getColumnName(j)));
        }
    }

    public static void saveFile(jprincipal viewPrincipal, ArrayList<DefaultTableModel> list, String nameFile) {
        JFileChooser saveFile = new JFileChooser();

        if (saveFile.showSaveDialog(viewPrincipal) == JFileChooser.APPROVE_OPTION) {
            GeneraExcel(saveFile.getSelectedFile().getAbsolutePath(), list, nameFile);
        }

    }

}
