/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import data.Patient;
import data.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import logicBusiness.CalculateAnthropometry;
import logicBusiness.Login;

/**
 *
 * @author JULIAN C
 */
public class WindowsManager extends javax.swing.JFrame {

    /**
     * Creates new form WindowsManager
     */
    public WindowsManager() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws Exception {
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
            java.util.logging.Logger.getLogger(WindowsManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WindowsManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WindowsManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WindowsManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WindowsManager().setVisible(true);
            }
        });
        
        
        
        // ---------------------------------------------------------------------
        
        char opcion;
        Scanner keyboard = new Scanner(System.in);
        System.out.println("\nANTHROPOMETRIC");
        Login login = new Login();
        List<User> userList = new ArrayList<>();

        while (login.validateLogin() == false) {
            //login.validateLogin();//verificar el usuario y contraseña

            
            if(login.validateLogin() == true){
                System.out.println("\nANTHROPOMETRIC");
            System.out.println("\n1. Pacientes");
            System.out.println("2. Mediciones");
            System.out.println("3. Salir.");
            System.out.print("\nOpcion: ");

            opcion = keyboard.next().charAt(0);
            switch (opcion) {
                case '1':
                    System.out.println("Sección -Pacientes-");
                    break;
                case '2':
                    System.out.println("Sección -Mediciones-");
                    break;
                case '3':
                    login.exit();
                    //System.out.println("Sección -Mediciones-");
                    break;

                default:
                    System.out.println("Opción inválida.");
                    break;
            }
            }
            
        }//End While sesion

        //-------------- Bloque Pruebas -------------------------------------------//
        userList.add(new User("juacastropa", "1234", "juacastropa@unal.edu.co", false));
        userList.add(new Patient("julianscastro", "4321", "julians993@gmail.com", true));
        userList.add(new Patient("jbarrantes", "5678", "jbrrantes@unal.edu.co", false));
        userList.add(new User("drobayo", "9876", "drobayo@unal.edu.co", true));

        System.out.println("\nLista de usuarios por tipo de perfil");
        System.out.println("-----------------------------------");
        for (int i = 0; i <= userList.size() - 1; i++) {
            System.out.println(userList.get(i).toString());
        }

        System.out.print("\nIngrese sexo (1)Femenino (2) Masculino: ");
        String sex = null;

        opcion = keyboard.next().charAt(0);
        opcion = '2';
        switch (opcion) {
            case '1':
                sex = "female";
                System.out.println("Seleccionó -Femenino-");
                break;
            case '2':
                sex = "male";
                System.out.println("Seleccionó -Masculino-");
                break;

            default:
                System.out.println("Opción inválida.");
                break;
        }

        System.out.print("Ingrese edad: ");
        /*keyboard.nextLine();
        String stringAge = keyboard.nextLine();
        int age = Integer.parseInt(stringAge);*/
        int age = 25;
        System.out.println(age);

        CalculateAnthropometry basicMeasures = new CalculateAnthropometry(64, 171, 89, 165.5);
        double skinMass = basicMeasures.calculateSkinMass(sex, age, basicMeasures.getPeso(),
                basicMeasures.getTallaAltura());

        CalculateAnthropometry skinfoldMeasurements = new CalculateAnthropometry(8, 13, 4.5, 12, 6.5, 10, 13, 6, 0);
        double adiposeMass = skinfoldMeasurements.adiposeMassPrediction(basicMeasures.getTallaAltura());

        CalculateAnthropometry perimeterMeasurements = new CalculateAnthropometry(58.8,
                35.8, 29.8, 32, 26.7, 16.3, 96.3, 73.4, 75, 89.5, 50.5, 50.9, 34.5, 22.3);
        double muscleMass = perimeterMeasurements.muscleMassPrediction(basicMeasures.getTallaAltura(),
                skinfoldMeasurements.getPliegueTriceps(), skinfoldMeasurements.getPliegueSubescapular(),
                skinfoldMeasurements.getPliegueAnteriorMuslo(), skinfoldMeasurements.getPliegueMedialPierna());

        CalculateAnthropometry diameterMeasurements = new CalculateAnthropometry(41, 19.4, 25.6, 28.3, 20.6, 7.1, 5.6, 10.2);
        double boneMass = diameterMeasurements.boneMassPrediction(basicMeasures.getTallaAltura(),
                perimeterMeasurements.getPerimetroCabeza());

        double residualMass = diameterMeasurements.residualMassPrediction(basicMeasures.getTallaSentado(),
                perimeterMeasurements.getPerimetroAbdominalCinturaMinimo(), skinfoldMeasurements.getPliegueAbdominal());

        CalculateAnthropometry ca = new CalculateAnthropometry();
        ca.predictionTotalBodyMass(basicMeasures.getPeso(), skinMass, adiposeMass, muscleMass, boneMass, residualMass);

        //-------------- Fin Bloque Pruebas -------------------------------------------//
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
