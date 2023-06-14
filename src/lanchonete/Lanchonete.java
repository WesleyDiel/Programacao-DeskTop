package lanchonete;

import apoio.ConexaoBD;
import javax.swing.JOptionPane;
import telas.FrmLogin;

public class Lanchonete {
    public static void main(String[] args) {
        try {
            ConexaoBD.getInstance().getConnection();
            FrmLogin janela = new FrmLogin();
            janela.setVisible(true);
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Erro de conexão com o banco de dados!\nPor favor entre em contato com o suporte.");
        }
    }

}
