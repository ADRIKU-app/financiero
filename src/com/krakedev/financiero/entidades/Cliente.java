package com.krakedev.financiero.entidades;

public class Cliente {

	private String cedula;
	private String nombre;
	private String apellido;
	private Direccion direccionDomicilio;

	public Cliente() {

	}

	public Cliente(String cedula, String nombre, String apellido) {
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccionDomicilio = new Direccion();
	}

	public String getCedula() {
		return cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void imprimir() {

		String mensaje;

		mensaje = "Cédula: " + cedula + ", Nombre: " + nombre + ", Apellido: " + apellido + ", Dirección: "
				+ direccionDomicilio.getCallePrincipal();

		System.out.println(mensaje);

	}

}
