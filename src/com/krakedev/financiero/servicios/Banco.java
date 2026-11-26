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

	public boolean depositar(double monto, Cuenta cuenta) {

		if (monto > 0) {

			double saldo = cuenta.getSaldoActual();

			double nuevoSaldo = saldo + monto;

			cuenta.setSaldoActual(nuevoSaldo);

			return true;

		} else {

			return false;

		}

	}

	public boolean retirar(double monto, Cuenta cuenta) {

		double saldo = cuenta.getSaldoActual();

		if (monto > 0 && monto <= saldo) {

			double nuevoSaldo = saldo - monto;

			cuenta.setSaldoActual(nuevoSaldo);

			return true;

		} else {

			return false;

		}
	}

	public boolean transferir(double monto, Cuenta origen, Cuenta destino) {

		boolean resultadoRetiro = retirar(monto, origen);

		if (resultadoRetiro) {
			
			boolean resultadoDeposito = depositar(monto, destino);

			return resultadoDeposito;

		} else {

			return false;

		}

	}
	
	public boolean cobrarMantenimiento(double monto, Cuenta cuenta) {
		
		//El monto a cobrar debe ser > 0
		//El saldo actual de la cuenta debe ser >= al monto que se intenta cobrar
		//Devolver un true si se efectúa un cobro exitoso
		
		double saldo = cuenta.getSaldoActual();
		
		if(monto > 0 && monto <= saldo) {
			
			double nuevoSaldo = saldo - monto;
			
			cuenta.setSaldoActual(nuevoSaldo);
			
			System.out.println("Cobro exitoso");
			
			return true;
			
		} else {
			
			System.out.println("Cobro fallido");
			
			return false;
		}
		
	}

}
