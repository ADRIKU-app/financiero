package com.krakedev.financiero.entidades;

public class Cuenta {

	private String id;
	private double saldoActual;
	private String tipo;
	private Cliente propietario;

	public Cuenta(String id) {
		this.id = id;
		this.saldoActual = 0;
		this.tipo = "A";
	}

	public String getId() {
		return id;
	}

	public double getSaldoActual() {
		return saldoActual;
	}

	public String getTipo() {
		return tipo;
	}

	public Cliente getPropietario() {
		return propietario;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setSaldoActual(double saldoActual) {
		this.saldoActual = saldoActual;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setPropietario(Cliente propietario) {
		this.propietario = propietario;
	}

	public void imprimir() {

		String mensaje;

		mensaje = "Id: " + id + ", Saldo Actual: " + saldoActual + ", Tipo: " + tipo + ", Cédula: "
				+ propietario.getCedula() + ", Nombre: " + propietario.getNombre() 
				+ ", Apellido: " + propietario.getApellido();

		System.out.println(mensaje);

	}

}
