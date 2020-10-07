package org.itkolleg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class InvoiceManager {
    private ArrayList<Invoice> invoices;
    private Invoice selectedInvoice;

    public InvoiceManager(boolean loadDatabaseIntoArrayList){
        this.invoices = new ArrayList<>();
        if(loadDatabaseIntoArrayList)
            loadAllInvoices();
    }
    public InvoiceManager(){
        this.invoices = new ArrayList<>();
    }

    /**
     * Loads the whole SQL Database into invoices (ArrayList of Invoice)
     */
    private void loadAllInvoices(){
        invoices = new ArrayList<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:33060/invoices","root","1234");
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from invoice");
            while(rs.next())
                invoices.add(new Invoice(
                        rs.getInt(1),
                        rs.getDate(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getBoolean(5)
                        ));
            con.close();
        }
        catch(Exception e){ System.out.println(e);}
    }

    private void loadInvoiceByIndex(int index){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:33060/invoices","root","1234");
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM invoice WITH(INDEX(" + index + "))");
            if(rs != null) selectedInvoice = new Invoice(
                        rs.getInt(1),
                        rs.getDate(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getBoolean(5)
                );
            con.close();
        }
        catch(Exception e){ System.out.println(e);}
    }

    /**
     * Insert SQL data into the table invoice
     * @param _description Description of the item
     * @param _value Cost of item
     * @param _paid was it paid?
     */
    public void insertInvoice(String _description, double _value, boolean _paid){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:33060/invoices","root","1234");
            Statement stmt=con.createStatement();
            String command = "INSERT INTO invoice (description, value, paid)" +
                    "VALUE("
                    +"\""+ _description + "\","
                    + _value + ","
                    + _paid + ")";
            stmt.execute(command);
            System.out.println(command);
            con.close();
        }
        catch(Exception e){ System.out.println(e);}
    }
    public void showInvoices(){
        loadAllInvoices();
        for (Invoice i: invoices
             ) {
            i.printInvoice();
        }
        invoices.clear();
    }
}
