package neub.edu.cse214;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author AD-Rahat
 */
public class LandingPage extends javax.swing.JFrame implements ActionListener {

    ArrayList<information> list;
    public int id = -1, maximumCar = 10;
    public String fileName = "file.txt";

    public LandingPage() {
        initComponents();
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        injButton.addActionListener(this);
        outjButton.addActionListener(this);
        homejMenuItem.addActionListener(this);
        logoutjMenuItem.addActionListener(this);
        developerOptionjMenuItem.addActionListener(this);

        PrintToTable();
    }

    @Override
    public void actionPerformed(ActionEvent button) {
        if (button.getSource() == homejMenuItem) {
            LandingPage ob = new LandingPage();
            this.dispose();
        }

        if (button.getSource() == logoutjMenuItem) {
            LogIn ob = new LogIn();
            this.dispose();
        }

        if (button.getSource() == developerOptionjMenuItem) {
            DeveloperOption ob = new DeveloperOption();
        }
        if (button.getSource() == injButton) {
            fileReader ob = new fileReader(fileName);
            ArrayList<String> arr = new ArrayList<>();
            arr = ob.output;

            int numberOfCar = arr.size();

            if (carNumberjTextField.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Enter the Car Number...!!!", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (numberOfCar == maximumCar) {
                JOptionPane.showMessageDialog(this, "Your Parking Area is Fillup...!!!", "Warning", JOptionPane.WARNING_MESSAGE);
                carNumberjTextField.setText("");
            } else {
                String inputQuery = "";
                int slNo = (numberOfCar + 1);
                String carNumber = carNumberjTextField.getText();
                String dateTime = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
                inputQuery = slNo + " " + carNumber.trim() + " " + dateTime.trim();
                arr.add(inputQuery);
                writeToFile(arr);
            }

            DefaultTableModel model = (DefaultTableModel) carListjTable.getModel();
            model.setRowCount(0);
            PrintToTable();
            carNumberjTextField.setText("");
        }

        if (button.getSource() == outjButton) {
            if (id == -1) {
                JOptionPane.showMessageDialog(this, "Select The car...!!!", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                ArrayList<String> outputList = new ArrayList<>();
                outputList = deleteFromFile(id);
                writeToFile(outputList);
                DefaultTableModel model = (DefaultTableModel) carListjTable.getModel();
                model.setRowCount(0);
                PrintToTable();
                carNumberjTextField.setText("");
                id = -1;
            }
        }
    }

    public class information {

        public String sl_no, car_no, inTime;

        public information(String sl_no, String car_no, String inTime) {
            this.sl_no = sl_no;
            this.car_no = car_no;
            this.inTime = inTime;
        }
    }

    public ArrayList<String> deleteFromFile(int id) {
        fileReader ob = new fileReader((fileName));
        ArrayList<String> inputList = new ArrayList<>();
        ArrayList<String> outputList = new ArrayList<>();
        inputList = ob.output;

        for (int i = 0; i < inputList.size(); i++) {
            if (i < (id - 1)) {
                outputList.add(inputList.get(i));
            } else if (i == (id - 1)) {
                //Nothig
            } else {
                String element = inputList.get(i).toString().trim();
                String[] elements = element.split(" ");
                String tempSl = i + "";
                String tempCarNo = elements[1];
                String tempTime = elements[2];
                String inputQuery = tempSl.trim() + " " + tempCarNo.trim() + " " + tempTime.trim();
                outputList.add(inputQuery);
            }
        }

        return outputList;
    }

    public ArrayList dataRetriveFromTextFile() {
        ArrayList<String> tempOutput = new ArrayList<>();
        fileReader ob = new fileReader(fileName);
        tempOutput = ob.output;
        list = new ArrayList<information>();
        for (int i = 0; i < tempOutput.size(); i++) {
            String sl_no, car_no, inTime;
            String element = tempOutput.get(i) + " ";
            String[] elements = element.split(" ");
            sl_no = elements[0];
            car_no = elements[1];
            inTime = elements[2];
            information info = new information(sl_no, car_no, inTime);
            list.add(info);
        }

        return list;
    }

    public void PrintToTable() {

        DefaultTableModel model = (DefaultTableModel) carListjTable.getModel();
        ArrayList<information> ReciveList = dataRetriveFromTextFile();
        Object rawData[] = new Object[3];

        for (int i = 0; i < ReciveList.size(); i++) {
            rawData[0] = ReciveList.get(i).sl_no;
            rawData[1] = ReciveList.get(i).car_no;
            rawData[2] = ReciveList.get(i).inTime;

            model.addRow(rawData);
        }
    }

    public void writeToFile(ArrayList arr) {
        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "utf-8"));
            for (int i = 0; i < arr.size(); i++) {
                writer.write(arr.get(i).toString().trim());
                writer.write("\n");
            }
        } catch (IOException ex) {
        } finally {
            try {
                writer.close();
            } catch (Exception ex) {/*ignore*/
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        carListjTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        carNumberjTextField = new javax.swing.JTextField();
        injButton = new javax.swing.JButton();
        outjButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        homejMenuItem = new javax.swing.JMenuItem();
        logoutjMenuItem = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        developerOptionjMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximizedBounds(new java.awt.Rectangle(600, 450, 450, 450));
        setMaximumSize(new java.awt.Dimension(600, 450));
        setPreferredSize(new java.awt.Dimension(600, 450));

        carListjTable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        carListjTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SL No.", "Car No.", "In Time"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        carListjTable.setRowHeight(25);
        carListjTable.getTableHeader().setReorderingAllowed(false);
        carListjTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                carListjTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(carListjTable);
        if (carListjTable.getColumnModel().getColumnCount() > 0) {
            carListjTable.getColumnModel().getColumn(0).setResizable(false);
            carListjTable.getColumnModel().getColumn(0).setPreferredWidth(50);
            carListjTable.getColumnModel().getColumn(1).setResizable(false);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Car No :");

        carNumberjTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        injButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        injButton.setForeground(new java.awt.Color(0, 153, 51));
        injButton.setText("IN");

        outjButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        outjButton.setForeground(new java.awt.Color(204, 0, 0));
        outjButton.setText("OUT");
        outjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outjButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(carNumberjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addComponent(injButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(outjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(carNumberjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(injButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(outjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu1.setText("File");

        homejMenuItem.setText("Home");
        homejMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homejMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(homejMenuItem);

        logoutjMenuItem.setText("Logout");
        jMenu1.add(logoutjMenuItem);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Help");

        developerOptionjMenuItem.setText("Developer Option");
        jMenu2.add(developerOptionjMenuItem);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void homejMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homejMenuItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_homejMenuItemActionPerformed

    private void outjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outjButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_outjButtonActionPerformed

    private void carListjTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_carListjTableMouseClicked
        int rowNumber = carListjTable.getSelectedRow();
        TableModel model = carListjTable.getModel();
        carNumberjTextField.setText(model.getValueAt(rowNumber, 1).toString());
        String result = model.getValueAt(rowNumber, 0).toString().trim();
        id = Integer.parseInt(result);
    }//GEN-LAST:event_carListjTableMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable carListjTable;
    private javax.swing.JTextField carNumberjTextField;
    private javax.swing.JMenuItem developerOptionjMenuItem;
    private javax.swing.JMenuItem homejMenuItem;
    private javax.swing.JButton injButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem logoutjMenuItem;
    private javax.swing.JButton outjButton;
    // End of variables declaration//GEN-END:variables
}
