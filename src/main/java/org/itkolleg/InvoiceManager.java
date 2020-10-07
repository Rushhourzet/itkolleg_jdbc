package org.itkolleg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class InvoiceManager {
    private ArrayList<Invoice> invoices;

    public InvoiceManager(){
        this.invoices = new ArrayList<>();
    }

    public void LoadInvoices(){
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

    public void insertInvoice(String _description, double _value, boolean _paid){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:33060/invoices","root","1234");
            Statement stmt=con.createStatement();
            stmt.executeQuery("insert into invoice (description, value, paid)" +
                    "VALUE("+ _description + ","
                    + _value + ","
                    + _paid + ")"
            );
            con.close();
        }
        catch(Exception e){ System.out.println(e);}
    }
    public void showInvoices(){
        for (Invoice i: invoices
             ) {
            i.printInvoice();
        }
    }
}
