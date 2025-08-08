/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.ui.admin;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import com.test.model.dao.TestInfoDAO;
import com.test.model.to.TestInfoTO;

/**
 *
 * @author user
 */
public class ViewTestInfo extends javax.swing.JInternalFrame {

   List<TestInfoTO> alltests;
    public ViewTestInfo() {
        initComponents();
        bindTableStudentCourse();
    }

    private void bindTableStudentCourse() {
        alltests = new TestInfoDAO().getAllRecord();
        String[] colnames = {"Username", "Subject Name", "Test Name", "Test Level", "Total Questions"};
        Object[][] records = null;
        if (alltests != null && alltests.size() > 0) {
            records = new Object[alltests.size()][colnames.length];
            int i = 0;
            for (TestInfoTO cit : alltests) {
                records[i] = new Object[]{cit.getUsername(), cit.getSubjectname(), cit.getTestname(), cit.getTestlevel(), cit.getTotalquestion()};
                i++;
            }
        } else {
            records = new Object[1][colnames.length];
            records[0] = new Object[]{"There is No Record", "There is No Record", "There is No Record", "There is No Record", "There is No Record"};
        }
        DefaultTableModel dtm = new DefaultTableModel(records, colnames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabletestinfo.setModel(dtm);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabletestinfo = new javax.swing.JTable();
        btnrefreshtestinfo = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Test Information");

        tabletestinfo.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabletestinfo);

        btnrefreshtestinfo.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btnrefreshtestinfo.setText("Refresh The Table");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnrefreshtestinfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnrefreshtestinfo, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnrefreshtestinfo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabletestinfo;
    // End of variables declaration//GEN-END:variables
}
