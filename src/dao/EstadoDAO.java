package dao;

import apoio.ConexaoBD;
import apoio.IDAO_T;
import entidade.Estado;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class EstadoDAO implements IDAO_T<Estado> {

    ResultSet resultadoQ = null;

    @Override
    public String salvar(Estado o) {

        try {
            String sql = ""
                    + "INSERT INTO estados (descricao, uf) VALUES ("
                    + "('" + o.getDescricao() + "'),"
                    + "'" + o.getUf() + "' "
                    + ")";

            System.out.println("sql: " + sql);

            ConexaoBD.executeUpdate(sql);

            return null;
        } catch (SQLException e) {
            System.out.println("Erro inserir estado = " + e);
            return e.toString();
        }

    }

//    @Override
//    public String atualizar(Estado o) {
//        try {
//            String sql = ""
//                    + "UPDATE estados "
//                    + "SET "
//                    + "('" + o.getDescricao() + "'),"
//                    + " = '" + o.getUf() + "' ";
//
//            System.out.println("sql: " + sql);
//
//            ConexaoBD.executeUpdate(sql);
//
//            return null;
//        } catch (SQLException e) {
//            System.out.println("Erro atualizar estado = " + e);
//            return e.toString();
//        }
//    }
////
//    @Override
//    public String excluir(int id) {
//        try {
//            String sql = ""
//                    + "DELETE FROM estados "
//                    + "WHERE id = " + id + "";
//
//            System.out.println("sql: " + sql);
//
//            ConexaoBD.executeUpdate(sql);
//
//            return null;
//        } catch (SQLException e) {
//            System.out.println("Erro excluir/inativar estado = " + e);
//            return e.toString();
//        }
//    }
//
//    @Override
//    public ArrayList<Estado> consultarTodos() {
//        ArrayList<Estado> estad = new ArrayList<>();
//
//        try {
//            String sql = ""
//                    + "SELECT * FROM estados ";
//
//            resultadoQ = ConexaoBD.executeQuery(sql);
//
//            while (resultadoQ.next()) {
//                Estado estado = new Estado();
//
//                estado.setId(resultadoQ.getInt("id"));
//                estado.setUf(resultadoQ.getString("UF"));
//                estado.setDescricao(resultadoQ.getString("descrição"));
//                //usuario.setSituacao(resultadoQ.getString("situacao").charAt(0));
//
//                estad.add(estado);
//            }
//        } catch (SQLException e) {
//            System.out.println("Erro consultar todos estados = " + e);
//        }
//
//        return estad;
//    }
//
//    @Override
//    public ArrayList<Estado> consultar(String criterio) {
//        ArrayList<Estado> estado = new ArrayList<>();
//
//        try {
//            String sql = ""
//                    + "SELECT * FROM estados "
//                    + "WHERE  "
//                    + "descrição ILIKE '%" + criterio + "%' "
//                    + "ORDER BY descrição";
//
//            resultadoQ = ConexaoBD.executeQuery(sql);
//
//            while (resultadoQ.next()) {
//                Estado estad = new Estado();
//
//                estad.setId(resultadoQ.getInt("id"));
//                estad.setUf(resultadoQ.getString("UF"));
//                estad.setDescricao(resultadoQ.getString("descrição"));
//                //usuario.setSituacao(resultadoQ.getString("situacao").charAt(0));
//
//                estado.add(estad);
//            }
//        } catch (SQLException e) {
//            System.out.println("Erro consultar todos estados = " + e);
//        }
//
//        return estado;
//    }
//
    @Override
    public Estado consultarId(int id) {
        Estado es = null;

        try {
            String sql = ""
                    + "SELECT * FROM estados "
                    + "WHERE  "
                    + "id = " + id;

            System.out.println("sql: " + sql);

            resultadoQ = ConexaoBD.executeQuery(sql);

            if (resultadoQ.next()) {
                es = new Estado();

                es.setId(id);
                es.setUf(resultadoQ.getString("uf"));
                es.setDescricao(resultadoQ.getString("descricao"));
               // u.setSituacao(resultadoQ.getString("situacao").charAt(0));
            }

        } catch (SQLException e) {
            System.out.println("Erro consultar estado = " + e);
        }
        return es;
    }
//
//   
//
//    /**
//     * Consulta no banco de dados e popula uma tabela
//     *
//     * @param tabela: nome da tabela a ser populada
//     * @param criterio: criterio de busca no banco
//     */
    public void popularTabela(JTable tabela, String criterio) {
        // dados da tabela
        Object[][] dadosTabela = null;
        int colunas = 3;

        // cabecalho da tabela
        Object[] cabecalho = new Object[colunas];
        cabecalho[0] = "Codigo";
        cabecalho[1] = "Descricao";
        cabecalho[2] = "UF";

        // cria matriz de acordo com nº de registros da tabela
        try {
            String sql = ""
                    + "SELECT count(*) FROM estados "
                    + "WHERE  "
                    + "descricao ILIKE '%" + criterio + "%'";

            resultadoQ = ConexaoBD.executeQuery(sql);

            resultadoQ.next();

            dadosTabela = new Object[resultadoQ.getInt(1)][colunas];

        } catch (Exception e) {
            System.out.println("Erro ao consultar estado: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            String sql = ""
                    + "SELECT id, descricao, uf FROM estados "
                    + "WHERE  "
                    + "descricao ILIKE '%" + criterio + "%' "
                    + "ORDER BY descricao";

            resultadoQ = ConexaoBD.executeQuery(sql);

            while (resultadoQ.next()) {

                dadosTabela[lin][0] = resultadoQ.getInt("id");
                dadosTabela[lin][1] = resultadoQ.getString("descricao");
                dadosTabela[lin][2] = resultadoQ.getString("UF");

                // caso a coluna precise exibir uma imagem
//                if (resultadoQ.getBoolean("Situacao")) {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_ativo.png"));
//                } else {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_inativo.png"));
//                }
                lin++;
            }
        } catch (SQLException e) {
            System.out.println("problemas para popular tabela...");
            System.err.println(e);
        }

        // configuracoes adicionais no componente tabela
        tabela.setModel(new DefaultTableModel(dadosTabela, cabecalho) {
            @Override
            // quando retorno for FALSE, a tabela nao é editavel
            public boolean isCellEditable(int row, int column) {
                return false;
                /*  
                 if (column == 3) {  // apenas a coluna 3 sera editavel
                 return true;
                 } else {
                 return false;
                 }
                 */
            }

            // alteracao no metodo que determina a coluna em que o objeto ImageIcon devera aparecer
            @Override
            public Class getColumnClass(int column) {

                if (column == 2) {
//                    return ImageIcon.class;
                }
                return Object.class;
            }
        });

        // permite seleção de apenas uma linha da tabela
        tabela.setSelectionMode(0);

        // redimensiona as colunas de uma tabela
        TableColumn column = null;
        for (int i = 0; i < tabela.getColumnCount(); i++) {
            column = tabela.getColumnModel().getColumn(i);
            switch (i) {
                case 0:
                    column.setPreferredWidth(17);
                    break;
                case 1:
                    column.setPreferredWidth(140);
                    break;
//                case 2:
//                    column.setPreferredWidth(14);
//                    break;
            }
        }
        // renderizacao das linhas da tabela = mudar a cor
//        jTable1.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
//
//            @Override
//            public Component getTableCellRendererComponent(JTable table, Object value,
//                    boolean isSelected, boolean hasFocus, int row, int column) {
//                super.getTableCellRendererComponent(table, value, isSelected,
//                        hasFocus, row, column);
//                if (row % 2 == 0) {
//                    setBackground(Color.GREEN);
//                } else {
//                    setBackground(Color.LIGHT_GRAY);
//                }
//                return this;
//            }
//        });
    }

   

    @Override
    public String excluir(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Estado> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Estado> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String atualizar(Estado o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

 

}
