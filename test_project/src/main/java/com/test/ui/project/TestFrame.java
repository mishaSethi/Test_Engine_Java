/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.ui.project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import com.test.model.dao.LoginInfoDAO;
import com.test.model.dao.ResultInfoDAO;
import com.test.model.dao.TestQuestionDAO;
import com.test.model.to.LoginInfoTO;
import com.test.model.to.ResultInfoTO;
import com.test.model.to.TestQuestionTO;

/**
 *
 * @author Grapess2
 */
public class TestFrame extends javax.swing.JFrame implements Runnable {

    private List<TestQuestionTO> questions;
    private List<String> answers;
    private int qno;
    private boolean startflag;
    private int time;
    private List<JButton> btns;
    private boolean[] viewed;
    private int testid;
    private String username;

    public TestFrame(int testid, String username) {
        this();
        this.testid = testid;
        this.username = username;
        questions = new TestQuestionDAO().getAllRecordbytestid(testid);
        Collections.shuffle(questions);
        if (questions != null && questions.size() > 0) {
            answers = new ArrayList<>();
            btns = new ArrayList<>();
            int rows = questions.size() / 3 + 1;
            GridLayout layout = new GridLayout(rows, 3, 3, 3);
            buttonPanel.setLayout(layout);
            viewed = new boolean[questions.size()];
            for (int i = 1; i <= questions.size(); i++) {
                answers.add("");
                JButton btn = new JButton();
                btn.setText(String.valueOf(i));
                btn.setBackground(Color.yellow);
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() instanceof JButton) {
                            JButton bt = (JButton) e.getSource();
                            int index = Integer.parseInt(bt.getText()) - 1;
                            processQuestion(qno);
                            displayQuestion(index);
                        }

                    }
                });
                btns.add(btn);
                buttonPanel.add(btn);
            }
            time = (questions.size() - 1) * 60;
            startflag = true;
            new Thread(this).start();
            displayQuestion(qno);
        }
    }

    public TestFrame() {
        //setUndecorated(false);
        initComponents();
        Dimension size = getToolkit().getScreenSize();
        setSize(size);

    }

    public void run() {
        while (startflag) {
            time--;
            int minute = time / 60;
            int second = time % 60;
            String timetext = (minute <= 9 ? ("0" + minute) : minute) + ":";
            timetext += (second <= 9 ? ("0" + second) : second);
            jlTime.setText(timetext);
            if (time == 0) {
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
            }
        }
        if (time == 0) {
            processQuestion(qno);
            prepareResult();
            dispose();
        }
    }

    private void prepareResult() {
        if (questions != null && questions.size() > 0) {
            int totalquestion, totalattempt, totalright, obtainedmarks, totalmarks;
            totalquestion = questions.size();
            totalattempt = totalright = obtainedmarks = totalmarks = 0;
            for (int i = 0; i < totalquestion; i++) {
                TestQuestionTO qi = questions.get(i);
                totalmarks += qi.getMarks();
                String answer = answers.get(i);
                if (!answer.isEmpty()) {
                    totalattempt++;
                    if (answer.equals(qi.getAnswer())) {
                        totalright++;
                        obtainedmarks += qi.getMarks();
                    }
                }
            }
            String result = "<h1> Total Question : " + totalquestion + "<br/>";
            result += " Total Marks : " + totalmarks + "<br/>";
            result += " Obtained Marks : " + obtainedmarks + "<br/>";
            result += " Total Attempt : " + totalattempt + "<br/>";
            result += " Total Right : " + totalright + "</h1>";
            ResultInfoTO rit = new ResultInfoTO();
            rit.setUsername(username);
            rit.setAttempquestion(totalattempt);
            rit.setTestid(testid);
            rit.setTotalquestion(totalquestion);
            rit.setObtainedmarks(obtainedmarks);
            rit.setRightquestion(totalright);
            ResultInfoDAO action = new ResultInfoDAO();
            if (action.insertRecord(rit)) {
                LoginInfoTO lit = new LoginInfoDAO().checkUser(username);
                if (lit != null) {
                    if (lit.getEmailid() != null) {
                        System.out.println(lit.getEmailid());

                        JOptionPane.showMessageDialog(this, "For Result Contact TO Test Administrator");

                    } else {
                        JOptionPane.showMessageDialog(this, "For Result Contact TO Test Administrator");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "For Result Contact TO Test Administrator");
                }

            } else {
                JOptionPane.showMessageDialog(this, "For Result Contact TO Test Administrator");
            }
        }
    }

    private void displayQuestion(int index) {
        if (questions != null && index < questions.size()) {
            bgSelection.clearSelection();
            changeColor(index);
            TestQuestionTO question = questions.get(index);
            jlQuestion.setText(question.getQuestiontext());
            jrbOptionA.setText(question.getOptiona());
            jrbOptionB.setText(question.getOptionb());
            jrbOptionC.setText(question.getOptionc());
            jrbOptionD.setText(question.getOptiond());
            String answer = answers.get(index);
            if (answer.equals("a")) {
                jrbOptionA.setSelected(true);
            } else if (answer.equals("b")) {
                jrbOptionB.setSelected(true);
            } else if (answer.equals("c")) {
                jrbOptionC.setSelected(true);
            } else if (answer.equals("d")) {
                jrbOptionD.setSelected(true);
            }
        }
    }

    private void changeColor(int index) {
        if (questions != null && index < questions.size()) {
            viewed[index] = true;
            btns.get(index).setBackground(Color.gray);
            if (!answers.get(index).isEmpty()) {
                btns.get(index).setBackground(Color.green);
            }
        }
    }

    private void processQuestion(int index) {
        if (questions != null && index < questions.size()) {
            String answer = "";
            if (jrbOptionA.isSelected()) {
                answer = "a";
            } else if (jrbOptionB.isSelected()) {
                answer = "b";
            } else if (jrbOptionC.isSelected()) {
                answer = "c";
            } else if (jrbOptionD.isSelected()) {
                answer = "d";
            }
            answers.set(index, answer);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgSelection = new javax.swing.ButtonGroup();
        jrbOptionA = new javax.swing.JRadioButton();
        jrbOptionC = new javax.swing.JRadioButton();
        jrbOptionB = new javax.swing.JRadioButton();
        jrbOptionD = new javax.swing.JRadioButton();
        btnPrevious = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnEndTest = new javax.swing.JButton();
        jlTime = new javax.swing.JLabel();
        buttonPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jlQuestion = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        bgSelection.add(jrbOptionA);
        jrbOptionA.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        jrbOptionA.setText("jRadioButton1");

        bgSelection.add(jrbOptionC);
        jrbOptionC.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        jrbOptionC.setText("jRadioButton1");

        bgSelection.add(jrbOptionB);
        jrbOptionB.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        jrbOptionB.setText("jRadioButton1");

        bgSelection.add(jrbOptionD);
        jrbOptionD.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        jrbOptionD.setText("jRadioButton1");

        btnPrevious.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        btnPrevious.setText("Previous");
        btnPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviousActionPerformed(evt);
            }
        });

        btnNext.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        btnNext.setText("Next");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnEndTest.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        btnEndTest.setText("End Test");
        btnEndTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEndTestActionPerformed(evt);
            }
        });

        jlTime.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jlTime.setText("Time");

        javax.swing.GroupLayout buttonPanelLayout = new javax.swing.GroupLayout(buttonPanel);
        buttonPanel.setLayout(buttonPanelLayout);
        buttonPanelLayout.setHorizontalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        buttonPanelLayout.setVerticalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 344, Short.MAX_VALUE)
        );

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jlQuestion.setEditable(false);
        jlQuestion.setColumns(20);
        jlQuestion.setFont(new java.awt.Font("Monospaced", 1, 30)); // NOI18N
        jlQuestion.setRows(5);
        jScrollPane1.setViewportView(jlQuestion);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jrbOptionD)
                            .addComponent(jrbOptionB)
                            .addComponent(jrbOptionC)
                            .addComponent(jrbOptionA)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addComponent(btnPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(78, 78, 78)
                                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEndTest, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 885, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(71, 71, 71)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jlTime, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                    .addComponent(buttonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlTime, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(239, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jrbOptionA)
                        .addGap(46, 46, 46)
                        .addComponent(jrbOptionB)
                        .addGap(44, 44, 44)
                        .addComponent(jrbOptionC)
                        .addGap(31, 31, 31)
                        .addComponent(jrbOptionD)
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEndTest, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEndTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEndTestActionPerformed
        int result = JOptionPane.showConfirmDialog(this, "Are you Sure TO End Test?", "Message From System", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            startflag = false;
            processQuestion(qno);
            changeColor(qno);
            prepareResult();
            System.exit(0);
        }
    }//GEN-LAST:event_btnEndTestActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        if (questions != null && qno < questions.size() - 1) {
            processQuestion(qno);
            changeColor(qno);
            qno++;
            displayQuestion(qno);
        } else {
            JOptionPane.showMessageDialog(this, "You are at Last Question");
        }
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousActionPerformed
        if (questions != null && qno > 0) {
            processQuestion(qno);
            changeColor(qno);
            qno--;
            displayQuestion(qno);
        } else {
            JOptionPane.showMessageDialog(this, "You are at First Question");
        }
    }//GEN-LAST:event_btnPreviousActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        startflag = false;
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(TestFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TestFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TestFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TestFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TestFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgSelection;
    private javax.swing.JButton btnEndTest;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrevious;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jlQuestion;
    private javax.swing.JLabel jlTime;
    private javax.swing.JRadioButton jrbOptionA;
    private javax.swing.JRadioButton jrbOptionB;
    private javax.swing.JRadioButton jrbOptionC;
    private javax.swing.JRadioButton jrbOptionD;
    // End of variables declaration//GEN-END:variables
}
