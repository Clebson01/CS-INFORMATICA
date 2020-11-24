/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.csinformatica.telas;

import java.sql.*;
import br.com.csinformatica.dal.ModuloConexao;
import javax.swing.JOptionPane;
// a linha abaixo importa recursos da biblioteca rs2xml.jar
import net.proteanit.sql.DbUtils;
/**
 *
 * @author Clebson Souza
 */
public class TelaCliente extends javax.swing.JInternalFrame {
Connection conexao=null;
PreparedStatement pst=null;
ResultSet rs=null;
    /**
     * Creates new form TelaCliente
     */
    public TelaCliente() {
        initComponents();
        conexao=ModuloConexao.conector();       
    }
    // método para adicionar cliente
    private void adicionar(){
        String sql ="insert into tbclientes(nomecli,endcli,telefonecli,emailcli) values(?,?,?,?)";
        try {
            pst=conexao.prepareStatement(sql);
            pst.setString(1,txtCliNome.getText());
            pst.setString(2,txtCliEndereco.getText());
            pst.setString(3,txtCliTelefone.getText());
            pst.setString(4,txtCliEmail.getText());
            
            // validação dos campos obrigatórios
            if ((txtCliNome.getText().isEmpty()) ||(txtCliEmail.getText().isEmpty())) {
               JOptionPane.showMessageDialog(null, "Preencha todos os campos OBRIGATÓRIOS!");                
            } else {
            
            // a linha abaixo atualiza a tabela usuário com os dados do formulário
            /// a estrutura abaixo é usada para confirma a inseção dos dados na tabela
            int adicionado = pst.executeUpdate();
            // a linha abaixo serve de apoio ao entendimento da lógica
            // System.out.println(adicionado);
            if (adicionado > 0 ){
                JOptionPane.showMessageDialog(null, " adicionado com sucesso!");
                txtCliNome.setText(null);
                txtCliEndereco.setText(null);
                txtCliTelefone.setText(null);
                txtCliEmail.setText(null);                
            }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    // método para pesquisa clientes pelo nome com filtro
    private  void pesquisar_cliente(){
        String sql="select * from tbclientes where nomecli like ?";
        try {
            pst = conexao.prepareStatement(sql);
            // passando o conteúdo da caixa de pesquisa para o ?
            // atenção ao "%" - continuação da String  sql
            pst.setString(1,txtCliPesquisar.getText() + "%");
            rs=pst.executeQuery();
            // a linha abaixo usa a biblioteca rs2xml.jar para preenchar a tabela
            tblClientes.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    // método para setar os campos do formulário com o conteudo da tabela
    public void setar_campos(){
            int setar = tblClientes.getSelectedRow();
            txtCliId.setText(tblClientes.getModel().getValueAt(setar, 0).toString());
            txtCliNome.setText(tblClientes.getModel().getValueAt(setar, 1).toString());
            txtCliEndereco.setText(tblClientes.getModel().getValueAt(setar, 2).toString());
            txtCliTelefone.setText(tblClientes.getModel().getValueAt(setar, 3).toString());
            txtCliEmail.setText(tblClientes.getModel().getValueAt(setar, 4).toString());
            
            // alinha abaixo desabilita a botão adicionar
            btnAdicionar.setEnabled(false);
    }
    
    // método para alterar dados do clientes
    private void alterar(){
       String sql="update tbclientes set nomecli=?,endcli=?,telefonecli=?,emailcli=? where idcli=?";
        try {
            pst=conexao.prepareStatement(sql);
            pst.setString(1,txtCliNome.getText());
            pst.setString(2,txtCliEndereco.getText());
            pst.setString(3,txtCliTelefone.getText());
            pst.setString(4,txtCliEmail.getText());
            pst.setString(5,txtCliId.getText());
             if ((txtCliNome.getText().isEmpty()) ||(txtCliTelefone.getText().isEmpty())) {
               JOptionPane.showMessageDialog(null, "Preencha todos os campos OBRIGATÓRIOS!");                
            } else {
            
            // a linha abaixo atualiza a tabela cliente com os dados do formulário
            /// a estrutura abaixo é usada para corfinar a alteração dos dados na tabela
            int adicionado = pst.executeUpdate();
            // a linha abaixo serve de apoio ao entendimento da lógica
            // System.out.println(adicionado);
            if (adicionado >0){
                JOptionPane.showMessageDialog(null, "Dados do cliente alterado com sucesso!");
                txtCliNome.setText(null);
                txtCliEndereco.setText(null);
                txtCliTelefone.setText(null);
                txtCliEmail.setText(null);   
                btnAdicionar.setEnabled(true);
            }
        }
             
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
        // método responsálvel pela remoção de cliente
    private void remover(){
    // a estryção abaixo corfirma a remoção de usuário
        int confirma=JOptionPane.showConfirmDialog(null,"Tem certeza que deseja remover este cliente", "Atenção",JOptionPane.YES_NO_OPTION);
        if (confirma==JOptionPane.YES_OPTION){
            String sql="delete from tbclientes where idcli=?";
            try {
                pst=conexao.prepareStatement(sql);
                pst.setString(1,txtCliId.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0){
                    JOptionPane.showMessageDialog(null,"Cliente removido com sucesso ");
                   txtCliNome.setText(null);
                    txtCliEndereco.setText(null);
                    txtCliTelefone.setText(null);
                    txtCliEmail.setText(null);   
                    btnAdicionar.setEnabled(true);
                }
            } catch (Exception e) {
                 JOptionPane.showMessageDialog(null, e);
            }
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnRemover = new javax.swing.JButton();
        btnAdicionar = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        txtCliNome = new javax.swing.JTextField();
        txtCliEndereco = new javax.swing.JTextField();
        txtCliEmail = new javax.swing.JTextField();
        txtCliTelefone = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtCliPesquisar = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txtCliId = new javax.swing.JTextField();

        setBackground(new java.awt.Color(204, 204, 204));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Clientes");
        setPreferredSize(new java.awt.Dimension(810, 560));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("* Nome");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Endereço");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Email");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("* Telefone");

        btnRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/csinformatica/icones/delete.png"))); // NOI18N
        btnRemover.setToolTipText("Remover");
        btnRemover.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRemover.setPreferredSize(new java.awt.Dimension(135, 135));
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/csinformatica/icones/create.png"))); // NOI18N
        btnAdicionar.setToolTipText("Adicionar");
        btnAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdicionar.setPreferredSize(new java.awt.Dimension(135, 135));
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        btnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/csinformatica/icones/update.png"))); // NOI18N
        btnAlterar.setToolTipText("Alterar");
        btnAlterar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAlterar.setPreferredSize(new java.awt.Dimension(135, 135));
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("* Campos obrigatório");

        txtCliPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCliPesquisarKeyReleased(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/csinformatica/icones/Pesquisar.png"))); // NOI18N

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
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
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblClientes);

        jLabel7.setText("Id Cliente");

        txtCliId.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(txtCliTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(26, 26, 26)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(87, 87, 87)
                                    .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtCliEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 583, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(txtCliNome)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtCliId, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addComponent(jScrollPane1)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(txtCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(132, 132, 132)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtCliEmail))))
                .addGap(0, 76, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCliNome, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCliId, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCliEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCliTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCliEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(273, 273, 273))
        );

        setBounds(0, 0, 811, 571);
    }// </editor-fold>//GEN-END:initComponents

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        // chamando o método para remover um cliente
        remover();
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        // chamando o método para alterar clientes
        alterar ();
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        // método para adicionar clientes
        adicionar();
    }//GEN-LAST:event_btnAdicionarActionPerformed
    // o evento abaixo é do tipo "enquanto for digitando"
    private void txtCliPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCliPesquisarKeyReleased
        // chamar o método pesquisar clientes
        pesquisar_cliente();
    }//GEN-LAST:event_txtCliPesquisarKeyReleased
    // evento que será usado para os campos da tabela (clicando com o mouse)
    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        // chamando o método para setar os campos
        setar_campos();
    }//GEN-LAST:event_tblClientesMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtCliEmail;
    private javax.swing.JTextField txtCliEndereco;
    private javax.swing.JTextField txtCliId;
    private javax.swing.JTextField txtCliNome;
    private javax.swing.JTextField txtCliPesquisar;
    private javax.swing.JTextField txtCliTelefone;
    // End of variables declaration//GEN-END:variables
}
