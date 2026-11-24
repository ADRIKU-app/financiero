package com.krakedev.financiero.servicios;

import com.krakedev.financiero.entidades.Cuenta;
import com.krakedev.financiero.entidades.Cliente;

public class Banco {

	private int ultimoCodigo = 1000;

	public Banco() {

	}

	public int getUltimoCodigo() {
		return ultimoCodigo;
	}

	public void setUltimoCodigo(int ultimoCodigo) {
		this.ultimoCodigo = ultimoCodigo;
	}

	public Cuenta crearCuenta(Cliente cliente) {

		// Paso 1: Convertir ultimoCodigo a String.
		String codigoStr = ultimoCodigo + "";

		// Paso 2: Incrementar ultimoCodigo.
		ultimoCodigo++;

		// Paso 3: Crear una instancia de Cuenta.
		Cuenta cuenta = new Cuenta(codigoStr);

		// Paso 4: Asignar el cliente como propietario.
		cuenta.setPropietario(cliente);

		// Paso 5: Retornar la cuenta creada.
		return cuenta;

	}

}
