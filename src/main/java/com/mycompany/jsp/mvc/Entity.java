/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jsp.mvc;

/**
 *
 * @author Ludovic
 */
public class Entity {
    
	private String Code;
	private float Taux;

	public Entity(String Code,float Taux) {
		this.Code = Code;
		this.Taux = Taux;
	}

	/**
         * Retourne la lettre sous forme string
	 */
	public String getCode() {
		return Code;
	}

	/**
	 * Retourne le taux sous un float
	 */
	public Float getTaux() {
		return Taux;
	}
}
