/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan_lks;

import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author HAFIIDH
 */
public class penjualan extends javax.swing.JFrame {

    private DefaultTableModel TabModel;
    Connection conn;
    Statement stm;
    ResultSet rs;
    /**
     * Creates new form penjualan
     */
    public penjualan() {
        initComponents();
        txstok.setVisible(false);
        txsubtotal.setVisible(false);
        SiapIsi(false);
        TombolNormal();
        
        Object header[]={"KODE BUKU","ID SISWA","NAMA SISWA","KELAS","NAMA BUKU","PENGARANG","PENERBIT","TERBIT","HARGA","JUMLAH","SUBTOTAL"};
        TabModel=new DefaultTableModel(null, header);
    }
 public Connection setKoneksi(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://localhost/db_tokobukuv2","root","");
            stm=conn.createStatement();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Koneksi Gagal:"+e);
        }
        return conn;
    }
  private void SiapIsi(boolean a){
        txkodetransaksi.setEnabled(a);
        txkodebuku.setEnabled(a);
        txidsiswa.setEnabled(a);
        txnamasiswa.setEnabled(a);
        txkelas.setEnabled(a);
        txnamabuku.setEnabled(a);
        txpengarang.setEnabled(a);
        txpenerbit.setEnabled(a);
        txtahunterbit.setEnabled(a);
        txharga.setEnabled(a);
        txjumlah.setEnabled(a);
        txsubtotal.setEnabled(a);
        txtotal.setEnabled(a);
        txbayar.setEnabled(a);
        txkembalian.setEnabled(a);
        txstok.setEnabled(a);
        
    }
    
    private void TombolNormal(){
        bttambah.setEnabled(true);
        btpilihsiswa.setEnabled(false);
        btaddchart.setEnabled(false);
        btbayar.setEnabled(false);        
        bttransaksi.setEnabled(false);
        btpilihbuku.setEnabled(false);
        bthapuschart.setEnabled(false);
        
    }
    
    private void bersih(){
        txkodetransaksi.setText("");
        txkodebuku.setText("");
        txidsiswa.setText("");
        txnamasiswa.setText("");
        txkelas.setText("");
        txnamabuku.setText("");
        txpengarang.setText("");
        txpenerbit.setText("");
        txtahunterbit.setText("");
        txharga.setText("");
        txjumlah.setText("");
        txsubtotal.setText("0");
        txtotal.setText("0");
        txbayar.setText("0");
        txkembalian.setText("");
        txstok.setText("");
        
    }
    
    private void kodetransaksi(){
       try{
           setKoneksi();
           String sql="select right(kodetransaksi,2)+1 from tb_penjualan";
           ResultSet rs=stm.executeQuery(sql);
           if(rs.next()){
           rs.last();
           String no=rs.getString(1);
           while (no.length()<3){
               no="0"+no;
               txkodetransaksi.setText("TR"+no);}
       }
           else
           {
                   txkodetransaksi.setText("TR001");
       }
       } catch (Exception e)
       {
    }
    }
    
    
    
    private void bayar(){
        setKoneksi();
        try{
           Date skrg=new Date();
           SimpleDateFormat frm=new SimpleDateFormat("yyyy-MM-dd");
           String tanggal=frm.format(skrg); 
 
            int t = tabelchart.getRowCount();
             for(int i=0;i<t;i++)    
            {
            String kodebuku=tabelchart.getValueAt(i, 0).toString();
            String idsiswa=tabelchart.getValueAt(i, 1).toString();
            String namasiswa=tabelchart.getValueAt(i, 2).toString();
            String kelas=tabelchart.getValueAt(i, 3).toString();
            String namabuku=tabelchart.getValueAt(i, 4).toString();
            String pengarang=tabelchart.getValueAt(i, 5).toString();
            String penerbit=tabelchart.getValueAt(i, 6).toString();
            String terbit=tabelchart.getValueAt(i, 7).toString();
            int harga= Integer.parseInt(tabelchart.getValueAt(i, 8).toString());
            int jml= Integer.parseInt(tabelchart.getValueAt(i, 9).toString());            
            int subtot= Integer.parseInt(tabelchart.getValueAt(i, 10).toString());
         
            String sql ="insert into tb_penjualan values('"+txkodetransaksi.getText()
                    +"','"+kodebuku+"','"
                    +idsiswa+"','"
                    +namasiswa+"','"
                    +kelas+"','"
                    +namabuku+"','"
                    +pengarang+"','"
                    +penerbit+"','"
                    +terbit+"','"
                    +tanggal+"','"
                    +harga+"','"
                    +jml+"','"
                    +subtot+"','"
                    +txtotal.getText()+"','"
                    +txbayar.getText()+"','"
                    +txkembalian.getText()+"')";
            
             stm.executeUpdate(sql);
             
            }         
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "SIMPAN TRANSAKSI PENJUALAN GAGAL");
        }
        
    }
    
    
    public void tabeltransaksi(){
        Object header[]={"KTR","KDB","ID SISWA","NAMA SISWA","KELAS","NAMA BUKU","PENGARANG","PENERBIT","TERBIT","TANGGAL","HARGA","JUMLAH","SUBTOTAL","TOTAL","BAYAR","KEMBALIAN"};
        DefaultTableModel data=new DefaultTableModel(null,header);
        tabeltransaksi.setModel(data);
        setKoneksi();
        String sql="select*from tb_penjualan";
        try {
            ResultSet rs=stm.executeQuery(sql);
            while (rs.next())
            {
                String kolom1=rs.getString(1);
                String kolom2=rs.getString(2);
                String kolom3=rs.getString(3);
                String kolom4=rs.getString(4);
                String kolom5=rs.getString(5);
                String kolom6=rs.getString(6);
                String kolom7=rs.getString(7);
                String kolom8=rs.getString(8);
                String kolom9=rs.getString(9);
                String kolom10=rs.getString(10);
                String kolom11=rs.getString(11);
                String kolom12=rs.getString(12);
                String kolom13=rs.getString(13);
                String kolom14=rs.getString(14);
                String kolom15=rs.getString(15);
                String kolom16=rs.getString(16);
               
                String kolom[]={kolom1,kolom2,kolom3,kolom4,kolom5,kolom6,kolom7,kolom8,kolom9,kolom10,kolom11,kolom12,kolom13,kolom14,kolom15,kolom16};
                data.addRow(kolom);
            }
        } catch (Exception e) {
        }
    }
    
    public void tb_buku(){
        Object header[]={"KODE BUKU","NAMA BUKU","PENGARANG","PENERBIT","TERBIT","HARGA","STOK"};
        DefaultTableModel data=new DefaultTableModel(null,header);
        tb_buku.setModel(data);
        setKoneksi();
        String sql="select*from tb_stokbuku";
        try {
            ResultSet rs=stm.executeQuery(sql);
            while (rs.next())
            {
                String kolom1=rs.getString(1);
                String kolom2=rs.getString(2);
                String kolom3=rs.getString(3);
                String kolom4=rs.getString(4);
                String kolom5=rs.getString(5);
                String kolom6=rs.getString(6);
                String kolom7=rs.getString(7);
                
                String kolom[]={kolom1,kolom2,kolom3,kolom4,kolom5,kolom6,kolom7};
                data.addRow(kolom);
            }
        } catch (Exception e) {
        }
    }
    public void tb_siswa(){
        Object header[]={"ID SISWA","NAMA SISWA","KELAS"};
        DefaultTableModel data=new DefaultTableModel(null,header);
        tb_siswa.setModel(data);
        setKoneksi();
        String sql="select*from tb_dsiswa";
        try {
            ResultSet rs=stm.executeQuery(sql);
            while (rs.next())
            {
                String kolom1=rs.getString(1);
                String kolom2=rs.getString(2);
                String kolom3=rs.getString(3);
               
                
                String kolom[]={kolom1,kolom2,kolom3};
                data.addRow(kolom);
            }
        } catch (Exception e) {
        }
 }
    public void hitungstok(){
        int jumlahbeli=Integer.parseInt(txjumlah.getText());
        int stok=Integer.parseInt(txstok.getText());
        
        int total=jumlahbeli-stok;
        txstok.setText(Integer.toString(total));
    }
    
    public void ambildata() {
        try {
            tabelchart.setModel(TabModel);
                String kolom1 = txkodebuku.getText();
                String kolom2 = txidsiswa.getText();
                String kolom3 = txnamasiswa.getText();
                String kolom4 = txkelas.getText();
                String kolom5 = txnamabuku.getText();
                String kolom6 = txpengarang.getText();
                String kolom7 = txpenerbit.getText();
                String kolom8 = txtahunterbit.getText();
                String kolom9 = txharga.getText();
                String kolom10 = txjumlah.getText();
                String kolom11 = txsubtotal.getText();
                String[] kolom = {kolom1,kolom2,kolom3,kolom4,kolom5,kolom6,kolom7,kolom8,kolom9,kolom10,kolom11};
                TabModel.addRow(kolom);
          }
          catch (Exception ex) {
              JOptionPane.showMessageDialog(null, "Data gagal disimpan");
          }     
    }
    
    public void nota(){
        try {
            String NamaFile = "src/report/BuktiTransaksi.jasper";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            java.sql.Connection setKoneksi = DriverManager.getConnection("jdbc:mysql://localhost/db_tokobukuv2","root","");
            HashMap param = new HashMap();
            param.put("ptrans",txkodetransaksi.getText());
            JasperPrint JPrint = JasperFillManager.fillReport(NamaFile, param, conn);
            JasperViewer.viewReport(JPrint, false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data tidak dapat dicetak!","Cetak Data",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void hapusdatadaritabel() {
        int a = tabelchart.getSelectedRow();
        if(a == -1){
        }TabModel.removeRow(a);
    }
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialogtabeltransaksi = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabeltransaksi = new javax.swing.JTable();
        txpencariantransaksi = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jDialog2 = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tb_buku = new javax.swing.JTable();
        txpencarianbuku = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jDialog3 = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tb_siswa = new javax.swing.JTable();
        txpencariansiswa = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txkodetransaksi = new javax.swing.JTextField();
        txkodebuku = new javax.swing.JTextField();
        txnamabuku = new javax.swing.JTextField();
        txpengarang = new javax.swing.JTextField();
        txpenerbit = new javax.swing.JTextField();
        txtahunterbit = new javax.swing.JTextField();
        txharga = new javax.swing.JTextField();
        txjumlah = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txnamasiswa = new javax.swing.JTextField();
        txkelas = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelchart = new javax.swing.JTable();
        bttambah = new javax.swing.JButton();
        btpilihbuku = new javax.swing.JButton();
        btpilihsiswa = new javax.swing.JButton();
        btaddchart = new javax.swing.JButton();
        bthapuschart = new javax.swing.JButton();
        bttransaksi = new javax.swing.JButton();
        txtotal = new javax.swing.JTextField();
        txbayar = new javax.swing.JTextField();
        txkembalian = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btbayar = new javax.swing.JButton();
        txsubtotal = new javax.swing.JTextField();
        txstok = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txidsiswa = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();

        jDialogtabeltransaksi.setMinimumSize(new java.awt.Dimension(900, 400));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tabeltransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tabeltransaksi);

        txpencariantransaksi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txpencariantransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txpencariantransaksiActionPerformed(evt);
            }
        });

        jLabel14.setText("KETIK KODE TRANSAKSI UNTUK MELAKUKAN PENCARIAN LALU ENTER");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 727, Short.MAX_VALUE)
                            .addComponent(txpencariantransaksi)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(216, 216, 216)
                        .addComponent(jLabel14)))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txpencariantransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout jDialogtabeltransaksiLayout = new javax.swing.GroupLayout(jDialogtabeltransaksi.getContentPane());
        jDialogtabeltransaksi.getContentPane().setLayout(jDialogtabeltransaksiLayout);
        jDialogtabeltransaksiLayout.setHorizontalGroup(
            jDialogtabeltransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(jDialogtabeltransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDialogtabeltransaksiLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jDialogtabeltransaksiLayout.setVerticalGroup(
            jDialogtabeltransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
            .addGroup(jDialogtabeltransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDialogtabeltransaksiLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jDialog2.setMinimumSize(new java.awt.Dimension(900, 400));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        tb_buku.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tb_buku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_bukuMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tb_buku);

        txpencarianbuku.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txpencarianbuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txpencarianbukuActionPerformed(evt);
            }
        });

        jLabel15.setText("KETIK KODE TRANSAKSI UNTUK MELAKUKAN PENCARIAN LALU ENTER");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txpencarianbuku)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(220, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addGap(217, 217, 217))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txpencarianbuku, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDialog2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
            .addGroup(jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDialog2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jDialog3.setMinimumSize(new java.awt.Dimension(900, 400));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        tb_siswa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tb_siswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_siswaMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tb_siswa);

        txpencariansiswa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel16.setText("KETIK NAMA SISWAT UNTUK MELAKUKAN PENCARIAN LALU ENTER");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(txpencariansiswa, javax.swing.GroupLayout.PREFERRED_SIZE, 732, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(222, 222, 222)
                        .addComponent(jLabel16)))
                .addContainerGap(57, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                    .addContainerGap(51, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 734, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(55, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txpencariansiswa, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(290, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                    .addContainerGap(123, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(33, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jDialog3Layout = new javax.swing.GroupLayout(jDialog3.getContentPane());
        jDialog3.getContentPane().setLayout(jDialog3Layout);
        jDialog3Layout.setHorizontalGroup(
            jDialog3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(jDialog3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDialog3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jDialog3Layout.setVerticalGroup(
            jDialog3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
            .addGroup(jDialog3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDialog3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("KODE TRANSAKSI");

        jLabel2.setText("KODE BUKU");

        jLabel3.setText("NAMA BUKU");

        jLabel4.setText("PENGARANG");

        jLabel5.setText("PENERBIT");

        jLabel6.setText("TERBIT");

        jLabel7.setText("HARGA");

        jLabel8.setText("JUMLAH");

        txjumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txjumlahActionPerformed(evt);
            }
        });

        jLabel9.setText("NAMA SISWA");

        jLabel10.setText("KELAS");

        tabelchart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabelchart);

        bttambah.setText("TAMBAH");
        bttambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttambahActionPerformed(evt);
            }
        });

        btpilihbuku.setText("PILIH BUKU");
        btpilihbuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btpilihbukuActionPerformed(evt);
            }
        });

        btpilihsiswa.setText("PILIH SISWA");
        btpilihsiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btpilihsiswaActionPerformed(evt);
            }
        });

        btaddchart.setText("ADD CHART");
        btaddchart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btaddchartActionPerformed(evt);
            }
        });

        bthapuschart.setText("HAPUS CHART");
        bthapuschart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthapuschartActionPerformed(evt);
            }
        });

        bttransaksi.setText("TB TRANSAKSI");
        bttransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttransaksiActionPerformed(evt);
            }
        });

        txbayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txbayarKeyPressed(evt);
            }
        });

        jLabel11.setText("TOTAL");

        jLabel12.setText("BAYAR");

        jLabel13.setText("KEMBALI");

        btbayar.setText("BAYAR");
        btbayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbayarActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel17.setText("KASIR PENJUALAN BUKU");

        jLabel18.setText("ID SISWA");

        txidsiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txidsiswaActionPerformed(evt);
            }
        });

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_close_window_48px.png"))); // NOI18N
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(433, 433, 433)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel19))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1045, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(txtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(txbayar, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(txkembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(88, 88, 88)
                        .addComponent(btbayar, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(bttambah, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel18))
                                .addGap(17, 17, 17)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txkodetransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                                    .addComponent(txkodebuku, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                                    .addComponent(txidsiswa))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txnamasiswa, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                                    .addComponent(txkelas))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btpilihsiswa)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                                .addComponent(btpilihbuku, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btaddchart)
                                .addGap(62, 62, 62)
                                .addComponent(bthapuschart)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bttransaksi))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(16, 16, 16)
                                        .addComponent(txnamabuku, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(txpengarang, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txpenerbit, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel7))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtahunterbit, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel8)))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txjumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txharga, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txsubtotal, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
                            .addComponent(txstok))))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 44, Short.MAX_VALUE)
                        .addComponent(jLabel17)
                        .addGap(31, 31, 31)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(txkodetransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txnamabuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txpenerbit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txharga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txnamasiswa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txsubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txkodebuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(txpengarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtahunterbit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txjumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txkelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txstok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txidsiswa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(71, 71, 71)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bttambah)
                    .addComponent(btpilihbuku)
                    .addComponent(btaddchart)
                    .addComponent(bthapuschart)
                    .addComponent(bttransaksi)
                    .addComponent(btpilihsiswa))
                .addGap(37, 37, 37)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txbayar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txkembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(txtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btbayar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txbayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txbayarKeyPressed
        // TODO add your handling code here:
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            int total=Integer.parseInt(txtotal.getText());
            int bayar=Integer.parseInt(txbayar.getText());
            if(bayar<total){
                JOptionPane.showMessageDialog(null, "Jumlah bayar tidak mencukupi");
                txbayar.requestFocus();
            } else{
                int kembali=bayar-total;
                txkembalian.setText(Integer.toString(kembali));
            }
        }
    }//GEN-LAST:event_txbayarKeyPressed

    private void btbayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbayarActionPerformed
        // TODO add your handling code here:
        if(txkodetransaksi.getText().equals("")
            ||txkodebuku.getText().equals("")
                ||txidsiswa.getText().equals("")
                ||txnamasiswa.getText().equals("")
                ||txkelas.getText().equals("")
            ||txnamabuku.getText().equals("")
                ||txpengarang.getText().equals("")
                ||txpenerbit.getText().equals("")
                ||txtahunterbit.getText().equals("")
            ||txharga.getText().equals("")
            ||txjumlah.getText().equals("")
            ||txsubtotal.getText().equals("")
            ||txtotal.getText().equals("")
            ||txbayar.getText().equals("")
            ||txkembalian.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Lengkapi inputan penjualan barang");
        } else{
            bayar();
            int pesan=JOptionPane.showConfirmDialog(null, "Print Out Nota?","Print",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);

            if(pesan==JOptionPane.YES_OPTION){
                nota();
            }else {
                JOptionPane.showMessageDialog(null, "Simpan Transaksi Berhasil");
            }
            bersih();
            SiapIsi(false);
            TombolNormal();
            TabModel.getDataVector().removeAllElements();
            TabModel.fireTableDataChanged();
            bttambah.setText("TAMBAH");
        }
    }//GEN-LAST:event_btbayarActionPerformed

    private void bttambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttambahActionPerformed
        // TODO add your handling code here:
         if(bttambah.getText().equalsIgnoreCase("TAMBAH")){
            bttambah.setText("REFRESH");
            bersih();
            SiapIsi(true);

            kodetransaksi();
            btaddchart.setEnabled(true);
            btpilihsiswa.setEnabled(true);
            bttransaksi.setEnabled(true);
            bttambah.setEnabled(true);
            btbayar.setEnabled(true);
            btpilihbuku.setEnabled(true);
            bthapuschart.setEnabled(true);

        } else{
            bttambah.setText("TAMBAH");
            bersih();
            SiapIsi(false);
            TombolNormal();
            //tabelinventory;
            TabModel.getDataVector().removeAllElements();
            TabModel.fireTableDataChanged();
        }
    }//GEN-LAST:event_bttambahActionPerformed

    private void btpilihbukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btpilihbukuActionPerformed
        // TODO add your handling code here:
        jDialog2.setLocationRelativeTo(null);
        tb_buku();
        jDialog2.setVisible(true);
    }//GEN-LAST:event_btpilihbukuActionPerformed

    private void btaddchartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btaddchartActionPerformed
        // TODO add your handling code here:
         int harga=Integer.parseInt(txharga.getText());
        int jml=Integer.parseInt(txjumlah.getText());
        int stok=Integer.parseInt(txstok.getText());
        int total=Integer.parseInt(txtotal.getText());

        if(jml>stok){
            JOptionPane.showMessageDialog(null, "Stok barang tidak mencukupi");
        }else{

            int subtot=harga*jml;
            txsubtotal.setText(Integer.toString(subtot));

            int hasilstok=stok-jml;
            txstok.setText(Integer.toString(hasilstok));

            int totbay=total+(harga*jml);
            txtotal.setText(Integer.toString(totbay));

            ambildata();

        }
    }//GEN-LAST:event_btaddchartActionPerformed

    private void bthapuschartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthapuschartActionPerformed
        // TODO add your handling code here:
        int baris = tabelchart.getSelectedRow();
        int jml=Integer.parseInt(tabelchart.getModel().getValueAt(baris, 7).toString());
        int total=Integer.parseInt(txtotal.getText());
        int harga=Integer.parseInt(tabelchart.getModel().getValueAt(baris, 6).toString());

        int totbay=total-(harga*jml);
        txtotal.setText(Integer.toString(totbay));
        hapusdatadaritabel();
    }//GEN-LAST:event_bthapuschartActionPerformed

    private void bttransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttransaksiActionPerformed
        // TODO add your handling code here:
        jDialogtabeltransaksi.setLocationRelativeTo(null);
        tabeltransaksi();
        jDialogtabeltransaksi.setVisible(true);
    }//GEN-LAST:event_bttransaksiActionPerformed

    private void tb_bukuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_bukuMouseClicked
        // TODO add your handling code here:
         int baris = tb_buku.getSelectedRow();
        txkodebuku.setText(tb_buku.getModel().getValueAt(baris, 0).toString());
        txnamabuku.setText(tb_buku.getModel().getValueAt(baris, 1).toString());
        txpengarang.setText(tb_buku.getModel().getValueAt(baris, 2).toString());
        txpenerbit.setText(tb_buku.getModel().getValueAt(baris, 3).toString());
        txtahunterbit.setText(tb_buku.getModel().getValueAt(baris, 4).toString());
        txharga.setText(tb_buku.getModel().getValueAt(baris, 5).toString());
        txstok.setText(tb_buku.getModel().getValueAt(baris, 6).toString());
        //txhargabeli.setText("0");
        //txjumlah.setText("0");
        jDialog2.dispose();
    }//GEN-LAST:event_tb_bukuMouseClicked

    private void txpencarianbukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txpencarianbukuActionPerformed
        // TODO add your handling code here:
          Object header[]={"KODE BUKU","NAMA BUKU","PENGARANG","PENERBIT","TERBIT","HARGA","STOK"};
        DefaultTableModel data=new DefaultTableModel(null,header);
        tb_buku.setModel(data);
        setKoneksi();
        String sql="Select * from tb_stokbuku where kodebuku like '%" + txpencarianbuku.getText() + "%'" + "or namabuku like '%" + txpencarianbuku.getText()+"%'";
        try {
            ResultSet rs=stm.executeQuery(sql);
            while (rs.next())
            {
                String kolom1=rs.getString(1);
                String kolom2=rs.getString(2);
                String kolom3=rs.getString(3);
                String kolom4=rs.getString(4);
                String kolom5=rs.getString(5);
                String kolom6=rs.getString(6);
                String kolom7=rs.getString(7);

                String kolom[]={kolom1,kolom2,kolom3,kolom4,kolom5,kolom6,kolom7};
                data.addRow(kolom);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txpencarianbukuActionPerformed

    private void txpencariantransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txpencariantransaksiActionPerformed
        // TODO add your handling code here:
         Object header[]={"KTR","KDB","NAMA","PENGARANG","PENERBIT","TERBIT","TANGGAL","HARGA","JUMLAH","SUBTOTAL","TOTAL","BAYAR","KEMBALIAN"};
        DefaultTableModel data=new DefaultTableModel(null,header);
        tabeltransaksi.setModel(data);
        setKoneksi();
        String sql="Select * from tb_penjualan where kodetransaksi like '%" + txpencariantransaksi.getText() + "%'" + "or kodebuku like '%" + txpencariantransaksi.getText()+"%'";
        try {
            ResultSet rs=stm.executeQuery(sql);
            while (rs.next())
            {
                String kolom1=rs.getString(1);
                String kolom2=rs.getString(2);
                String kolom3=rs.getString(3);
                String kolom4=rs.getString(4);
                String kolom5=rs.getString(5);
                String kolom6=rs.getString(6);
                String kolom7=rs.getString(7);
                String kolom8=rs.getString(8);
                String kolom9=rs.getString(9);
                String kolom10=rs.getString(10);
                String kolom11=rs.getString(11);
                String kolom12=rs.getString(12);
                String kolom13=rs.getString(13);
                
                String kolom[]={kolom1,kolom2,kolom3,kolom4,kolom5,kolom6,kolom7,kolom8,kolom9,kolom10,kolom11,kolom12,kolom13};
                data.addRow(kolom);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txpencariantransaksiActionPerformed

    private void btpilihsiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btpilihsiswaActionPerformed
        // TODO add your handling code here:
        jDialog3.setLocationRelativeTo(null);
        tb_siswa();
        jDialog3.setVisible(true);
    }//GEN-LAST:event_btpilihsiswaActionPerformed

    private void tb_siswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_siswaMouseClicked
        // TODO add your handling code here:
          int baris = tb_siswa.getSelectedRow();
        txidsiswa.setText(tb_siswa.getModel().getValueAt(baris, 0).toString());
        txnamasiswa.setText(tb_siswa.getModel().getValueAt(baris, 1).toString());
        txkelas.setText(tb_siswa.getModel().getValueAt(baris, 2).toString());
        jDialog3.dispose();
    }//GEN-LAST:event_tb_siswaMouseClicked

    private void txidsiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txidsiswaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txidsiswaActionPerformed

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
        // TODO add your handling code here
        dispose();
    }//GEN-LAST:event_jLabel19MouseClicked

    private void txjumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txjumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txjumlahActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new penjualan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btaddchart;
    private javax.swing.JButton btbayar;
    private javax.swing.JButton bthapuschart;
    private javax.swing.JButton btpilihbuku;
    private javax.swing.JButton btpilihsiswa;
    private javax.swing.JButton bttambah;
    private javax.swing.JButton bttransaksi;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JDialog jDialog3;
    private javax.swing.JDialog jDialogtabeltransaksi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable tabelchart;
    private javax.swing.JTable tabeltransaksi;
    private javax.swing.JTable tb_buku;
    private javax.swing.JTable tb_siswa;
    private javax.swing.JTextField txbayar;
    private javax.swing.JTextField txharga;
    private javax.swing.JTextField txidsiswa;
    private javax.swing.JTextField txjumlah;
    private javax.swing.JTextField txkelas;
    private javax.swing.JTextField txkembalian;
    private javax.swing.JTextField txkodebuku;
    private javax.swing.JTextField txkodetransaksi;
    private javax.swing.JTextField txnamabuku;
    private javax.swing.JTextField txnamasiswa;
    private javax.swing.JTextField txpencarianbuku;
    private javax.swing.JTextField txpencariansiswa;
    private javax.swing.JTextField txpencariantransaksi;
    private javax.swing.JTextField txpenerbit;
    private javax.swing.JTextField txpengarang;
    private javax.swing.JTextField txstok;
    private javax.swing.JTextField txsubtotal;
    private javax.swing.JTextField txtahunterbit;
    private javax.swing.JTextField txtotal;
    // End of variables declaration//GEN-END:variables
}
