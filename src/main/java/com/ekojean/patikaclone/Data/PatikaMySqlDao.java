package com.ekojean.patikaclone.Data;

import com.ekojean.patikaclone.Configuration.DBConnector;
import com.ekojean.patikaclone.Entities.Patika;
import com.ekojean.patikaclone.Interfaces.IEntity;
import com.ekojean.patikaclone.Interfaces.IRepositoryDao;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PatikaMySqlDao implements IRepositoryDao<Patika> {
    @Override
    public List<Patika> getList() {
        ArrayList<Patika> patikaList = new ArrayList<>();
        String query = "SELECT * FROM patika";
        Statement statement = null;
        ResultSet resultSet = null;
        Patika patika;

        try {
            statement = DBConnector.getConnect().createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                patika = new Patika(
                        resultSet.getInt("patikarefno"),
                        resultSet.getString("adi"),
                        resultSet.getString("aciklama"),
                        resultSet.getInt("puan"),
                        resultSet.getInt("saat"),
                        resultSet.getString("aktif")
                );

                patikaList.add(patika);
            }

            statement.close();
            resultSet.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return patikaList;
    }

    @Override
    public List<Patika> getFindList(String findText) {
        ArrayList<Patika> patikaList = new ArrayList<>();
        String query = "";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Patika patika;

        try {
            preparedStatement = DBConnector.getConnect().prepareStatement(query);
            preparedStatement.setString(0, "%" + findText + "%");

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                patika = new Patika(
                        resultSet.getInt("patikarefno"),
                        resultSet.getString("adi"),
                        resultSet.getString("aciklama"),
                        resultSet.getInt("puan"),
                        resultSet.getInt("saat"),
                        resultSet.getString("aktif")
                );

                patikaList.add(patika);
            }

            preparedStatement.close();
            resultSet.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return patikaList;
    }

    @Override
    public boolean add(Patika patika) {
        boolean isChecked = false;
        String query = "INSERT INTO patika(adi, aciklama, puan, saat, aktif) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = null;
        int result;

        try {
            preparedStatement = DBConnector.getConnect().prepareStatement(query);
            preparedStatement.setString(0, patika.getAdi());
            preparedStatement.setString(1, patika.getAciklama());
            preparedStatement.setInt(2, patika.getPuan());
            preparedStatement.setInt(3, patika.getSaat());
            preparedStatement.setString(4, patika.getAktif());

            result = preparedStatement.executeUpdate();

            if (result != -1)
                isChecked = true;
            else
                isChecked = false;

            preparedStatement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return isChecked;
    }

    @Override
    public boolean update(Patika patika) {
        boolean isChecked = false;
        String query = "UPDATE patika SET adi = ?, aciklama = ?, puan = ?, saat = ?, aktif = ? WHERE patikarefno = ?";
        PreparedStatement preparedStatement = null;
        int result;

        try {
            preparedStatement = DBConnector.getConnect().prepareStatement(query);
            preparedStatement.setString(0, patika.getAdi());
            preparedStatement.setString(1, patika.getAciklama());
            preparedStatement.setInt(2, patika.getPuan());
            preparedStatement.setInt(3, patika.getSaat());
            preparedStatement.setString(4, patika.getAktif());
            preparedStatement.setInt(5, patika.getPatikarefno());

            result = preparedStatement.executeUpdate();

            if (result != -1)
                isChecked = true;
            else
                isChecked = false;

            preparedStatement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return isChecked;
    }

    @Override
    public boolean delete(Patika patika) {
        boolean isChecked = false;
        String query = "DELETE FROM patika WHERE patikarefno = ?";
        PreparedStatement preparedStatement = null;
        int result;

        try {
            preparedStatement = DBConnector.getConnect().prepareStatement(query);
            preparedStatement.setInt(0, patika.getPatikarefno());

            result = preparedStatement.executeUpdate();

            if (result != -1)
                isChecked = true;
            else
                isChecked = false;

            preparedStatement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return isChecked;
    }
}
