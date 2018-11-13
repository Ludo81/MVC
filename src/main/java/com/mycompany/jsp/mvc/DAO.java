/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jsp.mvc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author Ludovic
 */
public class DAO {
    protected final DataSource myDataSource;

	/**
	 *
	 * @param dataSource la source de données à utiliser
	 */
	public DAO(DataSource dataSource) {
		this.myDataSource = dataSource;
	}
        
        public int deleteDiscount_Code(String Code) throws Exception {

		// Requête de suppression du discount code
		String sql = "DELETE FROM DISCOUNT_CODE AS AUX WHERE DISCOUNT_CODE= ?";
		try (   Connection connection = myDataSource.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql)
                ) {
                        // Définir la valeur du paramètre
			stmt.setString(1, Code);
			
			return stmt.executeUpdate();

		}  catch (SQLException ex) {
			Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
			throw new Exception(ex.getMessage());
		}
	}
        
        
        public int updateDiscount_Code(String code,Float taux) throws Exception {

		// Requête de la mise à jour de la base de données
		String sql = "UPDATE APP.DISCOUNT_CODE SET RATE = ? WHERE DISCOUNT_CODE = ?";
		try (   Connection connection = myDataSource.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql)
                ) {
                        // Définir la valeur du paramètre
			stmt.setFloat(1, taux);
                        stmt.setString(2, code);
			
			return stmt.executeUpdate();

		}  catch (SQLException ex) {
			Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
			throw new Exception(ex.getMessage());
		}
	}
        
        
        public int addDiscount_Code(String Code, float Taux) throws Exception {

		// Requête ajout discount code dans base de données
		String sql = "INSERT INTO DISCOUNT_CODE VALUES (?,?)";
		try (   Connection connection = myDataSource.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql)
                ) {
                        // Définir la valeur du paramètre
			stmt.setString(1, Code);
                        stmt.setFloat(2, Taux);
			
			return stmt.executeUpdate();

		}  catch (SQLException ex) {
			Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
			throw new Exception(ex.getMessage());
		}
	}
        
        
        public List<Entity> ListOfDiscount() throws Exception {
		List<Entity> result = new LinkedList<>();

                // Récupération des discount codes
		String sql = "SELECT * FROM DISCOUNT_CODE";
		try (Connection connection = myDataSource.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql)) {

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) { // Tant qu'il y a des enregistrements
					String Code = rs.getString("DISCOUNT_CODE");
                                        float Taux = rs.getFloat("RATE");
                                        Entity DE = new Entity(Code,Taux);
					result.add(DE);
				}
			}
		}  catch (SQLException ex) {
			Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
			throw new Exception(ex.getMessage());
		}

		return result;

	}
        
       
        
}
