/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koneksidb;

import net.sf.jasperreports.view.*;
import net.sf.jasperreports.engine.design.*;
import net.sf.jasperreports.engine.*;

import java.io.File;
import javax.swing.JOptionPane;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.util.JRLoader;
/**
 *
 * @author HAFIIDH
 */
public class report {
    public report(){}
    
    public report(String filename, Connection conn){
        try {
            File report = new File(filename);
            JasperReport jreprt = (JasperReport)JRLoader.loadObject(report);
            JasperPrint jprintt = JasperFillManager.fillReport(jreprt,null, conn);
            JasperViewer.viewReport(jprintt,false);
        } catch (JRException e) {
            JOptionPane.showMessageDialog(null, "Gagal Membuka Laporan","Cetak Laporan",JOptionPane.ERROR_MESSAGE);
        }
    
    }

    public Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
