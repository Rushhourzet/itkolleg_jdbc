package org.itkolleg;

import java.sql.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        InvoiceManager im = new InvoiceManager(false);
        im.insertInvoice("besteck", 10, true);
        im.showInvoices();
    }
}
