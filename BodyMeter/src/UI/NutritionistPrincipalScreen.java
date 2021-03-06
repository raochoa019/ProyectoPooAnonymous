package UI;

import data.Patient;
import data.User;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import logicBusiness.Login;

/**
 *
 * @author JULIAN C
 */
public class NutritionistPrincipalScreen extends javax.swing.JFrame {

    /**
     * Creates new form NutritionistPrincipalScreen
     */
    private final User users = new User();
    private final Login login = new Login();
    private static String idPatientToSee = "";
    private HashMap<String, String[]> listOfNutritionists = new HashMap<>();
    private HashMap<String, String[]> loginList = new HashMap<>();
    private HashMap<String, String[]> patients = null;
    DefaultTableModel tableModel;

    private String searchText = "";

    public NutritionistPrincipalScreen() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        btnSee.setEnabled(false);
        loadPatient("");

        setIconImage(new ImageIcon(getClass().getResource("/resources/img/icono.jpg")).getImage());

        ((JPanel) getContentPane()).setOpaque(false);
        ImageIcon background = new ImageIcon(this.getClass().getResource("/resources/img/logotipoH.jpg"));

        background = new ImageIcon(background.getImage().getScaledInstance(getWidth() / 4, getHeight() / 8, Image.SCALE_SMOOTH));

        JLabel fondo = new JLabel();
        fondo.setIcon(background);

        jPanelBase.add(fondo, JLayeredPane.FRAME_CONTENT_LAYER);
        fondo.setBounds(550, 12, background.getIconWidth(), background.getIconHeight());

        try {
            users.readDatabase();
            login.readDatabase();
            listOfNutritionists = users.getListOfNutritionistsData();
            loginList = login.getUserLoginList();

            String[] loginData = loginList.get(LoginScreen.getUser());
            String[] userData = listOfNutritionists.get(loginData[0]);

            //System.out.println("INFO USER: " + Arrays.toString(loginData) + Arrays.toString(userData));
            jLabelName.setText(userData[1] + " " + userData[2]);

            //txtLastnames.setText(userData[2]);
        } catch (Exception ex) {
            Logger.getLogger(EditUserProfile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadPatient(String searchId) {
        String[] titles = {"Identificación", "Nombre", "Apellido", "Antropometrías", "Acciones"};
        tableModel = new DefaultTableModel(null, titles);
        Patient patient = new Patient();

        try {
            patient.readDatabase();
            patients = patient.getListOfPatientData();
        } catch (Exception ex) {
            Logger.getLogger(NutritionistPrincipalScreen.class.getName()).log(Level.SEVERE, null, ex);
        }

        String[] registro = new String[5];
        String id = "", name = "", lastname = "", anthropometry, actions;

        if (searchId.equals("") || searchId.equals(" ")) {
            btnSee.setEnabled(false);
            for (Map.Entry<String, String[]> entry : patients.entrySet()) {
                
                String key = entry.getKey();
                String[] patientData = entry.getValue();
                id = patientData[0];
                idPatientToSee = patientData[0];
                setIdPatientToSee(idPatientToSee);
                name = patientData[1];
                lastname = patientData[2];

                registro[0] = id;
                registro[1] = name;
                registro[2] = lastname;
                registro[3] = "Antropometria";
                registro[4] = "Acciones";
                tableModel.addRow(registro);
            }
            this.tblPatients.setModel(tableModel);
        } else {
            if (patients.containsKey(searchId)) {
                jLabelErrorSearchMessage.setText(" ");
                btnSee.setEnabled(true);
                String[] patientData = patients.get(searchId);
                id = patientData[0];
                idPatientToSee = id;
                name = patientData[1];
                lastname = patientData[2];
                registro[0] = id;
                registro[1] = name;
                registro[2] = lastname;
                registro[3] = "Antropometria";
                registro[4] = "Acciones";
                tableModel.addRow(registro);
            } else {
                jLabelErrorSearchMessage.setText("*No existen datos del paciente");
            }
            this.tblPatients.setModel(tableModel);
        }

    }

    public static String getIdPatientToSee() {
        return idPatientToSee;
    }

    public void setIdPatientToSee(String idPatientToSee) {
        this.idPatientToSee = idPatientToSee;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanelBase = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabelUserType = new javax.swing.JLabel();
        jLabelName = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        btnEditUserProfile = new javax.swing.JButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabelVoid = new javax.swing.JLabel();
        jLabelListPatients = new javax.swing.JLabel();
        jLabelVoid2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtSearchId = new javax.swing.JTextField();
        jLabelVoid3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPatients = new javax.swing.JTable();
        btnSearch = new javax.swing.JButton();
        jLabelErrorSearchMessage = new javax.swing.JLabel();
        btnSee = new javax.swing.JButton();
        jPanelSignUpTitle = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelUserType.setBackground(new java.awt.Color(255, 204, 204));
        jLabelUserType.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelUserType.setText("NUTRICIONISTA");
        jLabelUserType.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabelUserType.setOpaque(true);

        jLabelName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelName.setText("Nombre");

        btnLogout.setBackground(new java.awt.Color(255, 204, 204));
        btnLogout.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnLogout.setText("Cerrar Sesión");
        btnLogout.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        btnEditUserProfile.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnEditUserProfile.setText("Editar perfil");
        btnEditUserProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditUserProfileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelUserType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnEditUserProfile, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                            .addComponent(btnLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTabbedPane2))
                        .addGap(0, 23, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabelUserType, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(jLabelName, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEditUserProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelVoid.setBackground(new java.awt.Color(153, 153, 153));
        jLabelVoid.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelVoid.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabelVoid.setOpaque(true);

        jLabelListPatients.setBackground(new java.awt.Color(255, 255, 255));
        jLabelListPatients.setText("Pacientes / Listado");
        jLabelListPatients.setOpaque(true);

        jLabelVoid2.setBackground(new java.awt.Color(153, 153, 153));
        jLabelVoid2.setForeground(new java.awt.Color(255, 255, 255));
        jLabelVoid2.setText("Filtro de pacientes");
        jLabelVoid2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabelVoid2.setOpaque(true);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Identificación: ");

        txtSearchId.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSearchIdMouseClicked(evt);
            }
        });
        txtSearchId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchIdActionPerformed(evt);
            }
        });
        txtSearchId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchIdKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchIdKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSearchIdKeyTyped(evt);
            }
        });

        jLabelVoid3.setBackground(new java.awt.Color(153, 153, 153));
        jLabelVoid3.setForeground(new java.awt.Color(255, 255, 255));
        jLabelVoid3.setText("Listado de pacientes");
        jLabelVoid3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabelVoid3.setOpaque(true);

        tblPatients.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblPatients);

        btnSearch.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnSearch.setText("BUSCAR");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        btnSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnSearchKeyPressed(evt);
            }
        });

        jLabelErrorSearchMessage.setFocusable(false);
        jLabelErrorSearchMessage.setOpaque(true);

        btnSee.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnSee.setText("VER MEDIDAS");
        btnSee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeeActionPerformed(evt);
            }
        });
        btnSee.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnSeeKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelVoid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabelListPatients, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabelVoid2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabelVoid3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(39, 39, 39)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelErrorSearchMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSearchId, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(btnSee)
                        .addGap(32, 32, 32))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabelVoid, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelListPatients, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelVoid2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSearchId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(6, 6, 6)
                        .addComponent(jLabelErrorSearchMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSee)
                            .addComponent(btnSearch))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelVoid3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanelSignUpTitle.setBackground(new java.awt.Color(0, 255, 102));

        jLabel3.setBackground(new java.awt.Color(102, 255, 102));
        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("BODYMETRIC");

        javax.swing.GroupLayout jPanelSignUpTitleLayout = new javax.swing.GroupLayout(jPanelSignUpTitle);
        jPanelSignUpTitle.setLayout(jPanelSignUpTitleLayout);
        jPanelSignUpTitleLayout.setHorizontalGroup(
            jPanelSignUpTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSignUpTitleLayout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(jLabel3)
                .addContainerGap(105, Short.MAX_VALUE))
        );
        jPanelSignUpTitleLayout.setVerticalGroup(
            jPanelSignUpTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSignUpTitleLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanelBaseLayout = new javax.swing.GroupLayout(jPanelBase);
        jPanelBase.setLayout(jPanelBaseLayout);
        jPanelBaseLayout.setHorizontalGroup(
            jPanelBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBaseLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jPanelSignUpTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanelBaseLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 57, Short.MAX_VALUE))
        );
        jPanelBaseLayout.setVerticalGroup(
            jPanelBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBaseLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelSignUpTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(101, 101, 101))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelBase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelBase, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        LoginScreen lgScreen = new LoginScreen();
        lgScreen.setVisible(true);
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void txtSearchIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchIdActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        searchText = txtSearchId.getText().trim().replaceAll(" ", "");
        loadPatient(searchText);
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnSearchKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearchKeyPressed

    private void btnEditUserProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditUserProfileActionPerformed
        EditUserProfile ep = new EditUserProfile();
        ep.setVisible(true);
        ep.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

    }//GEN-LAST:event_btnEditUserProfileActionPerformed

    private void txtSearchIdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSearchIdMouseClicked
        jLabelErrorSearchMessage.setText(" ");
    }//GEN-LAST:event_txtSearchIdMouseClicked

    private void txtSearchIdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchIdKeyPressed
        char validate = (char) evt.getKeyCode();
        if (validate == evt.VK_ENTER) {
            searchText = txtSearchId.getText().trim().replaceAll(" ", "");
            loadPatient(searchText);
        }
    }//GEN-LAST:event_txtSearchIdKeyPressed

    private void txtSearchIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchIdKeyReleased

    }//GEN-LAST:event_txtSearchIdKeyReleased

    private void txtSearchIdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchIdKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchIdKeyTyped

    private void btnSeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeeActionPerformed
        TakeMeassurements tm = new TakeMeassurements();
        tm.setVisible(true);
        tm.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }//GEN-LAST:event_btnSeeActionPerformed

    private void btnSeeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnSeeKeyPressed
        char validate = (char) evt.getKeyCode();
        if (validate == evt.VK_ENTER) {
            searchText = txtSearchId.getText().trim().replaceAll(" ", "");
            loadPatient(searchText);
        }
    }//GEN-LAST:event_btnSeeKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditUserProfile;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSee;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelErrorSearchMessage;
    private javax.swing.JLabel jLabelListPatients;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelUserType;
    private javax.swing.JLabel jLabelVoid;
    private javax.swing.JLabel jLabelVoid2;
    private javax.swing.JLabel jLabelVoid3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelBase;
    private javax.swing.JPanel jPanelSignUpTitle;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable tblPatients;
    private javax.swing.JTextField txtSearchId;
    // End of variables declaration//GEN-END:variables
}
