package jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Aluno;

public class AlunoJDBC {

    Connection con;
    String sql;
    PreparedStatement pst;

    public void salvar(Aluno a) throws SQLException {
        try {
            con = db.getConexao();
            sql = "INSERT INTO aluno (nome, sexo, dt_nasc) VALUES (?,?, ?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, a.getNome());
            pst.setString(2, a.getSexo());
            Date dataSql = Date.valueOf(a.getDt_nasc());
            pst.setDate(3, dataSql);
            pst.executeUpdate();
            System.out.println("\nCadastro do aluno realizado com sucesso!");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            pst.close();
            db.closeConexao();
        }
    }

    public List<Aluno> listar() {
        List<Aluno> alunos = new ArrayList<>();
        try {
            con = db.getConexao();
            sql = "SELECT * FROM aluno";
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setSexo(rs.getString("sexo"));
                aluno.setDt_nasc(rs.getDate("dt_nasc").toLocalDate());
                alunos.add(aluno);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return alunos;
    }

    public void apagar(int id) {
        try {
            con = db.getConexao();
            sql = "DELETE FROM aluno WHERE id = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Aluno excluído com sucesso!");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void alterar(Aluno a) {
        try {
            con = db.getConexao();
            sql = "UPDATE aluno SET nome = ?, sexo = ?, dt_nasc = ? WHERE id = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, a.getNome());
            pst.setString(2, a.getSexo());
            pst.setDate(3, Date.valueOf(a.getDt_nasc()));
            pst.setInt(4, a.getId());
            pst.executeUpdate();
            System.out.println("Dados do aluno atualizados com sucesso!");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
