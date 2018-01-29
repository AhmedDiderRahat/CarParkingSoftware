/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neub.edu.cse214;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AD-Rahat
 */
public class fileReader {
    
    ArrayList<String> list;
    ArrayList<String> output;
 
    fileReader(String fileName) {
        output = new ArrayList<String>();
        output = dataReadFromFile(fileName);

    }
    
    public ArrayList dataReadFromFile(String fileName)
    {
        FileReader in = null;
        list = new ArrayList<String>();
        try {
            in = new FileReader(fileName);
            BufferedReader br = new BufferedReader(in);
            String line;
            try {
                while ((line = br.readLine()) != null) {
                    //System.out.println(line);   
                    list.add(line);
                }
            } catch (IOException ex) {
                Logger.getLogger(fileReader.class.getName()).log(Level.SEVERE, null, ex);
            }
            in.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(fileReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(fileReader.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(fileReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
       
}
