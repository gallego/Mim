package com.is.mim.client;

import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DatePicker;

public class Agenda {
	//Se declaran los paneles que quiera, est‡ el VerticalPanel de ejemplo
	private VerticalPanel seccionAgenda;
	
    /**
     * Esta variable indica si se ha creado ya la secci—n
     * true significa que no se ha creado
     * false significa que SI se ha creado
     */
	private boolean firstAgenda = true;

	public Agenda() {
		super();
		this.seccionAgenda = new VerticalPanel();
		panelAgenda();
	}
	
	public Agenda(VerticalPanel seccionAgenda) {
		super();
		this.seccionAgenda = seccionAgenda;
	}
	
	public void panelAgenda() {
		DatePicker calendar = new DatePicker();
		seccionAgenda.add(calendar);
	}
	
	public VerticalPanel getSecAgenda() {
		return this.seccionAgenda;
	}
	
	public void setSecAgenda(VerticalPanel seccionAgenda) {
		this.seccionAgenda = seccionAgenda;
	}
	
	public void setFirstAgenda(boolean state) {
		this.firstAgenda = state;
	}
	
	public boolean getFirstAgenda() {
		return this.firstAgenda;
	}
	
	public void setVisible(){
		this.seccionAgenda.setVisible(true);
	}
	
	public void setInvisible() {
		this.seccionAgenda.setVisible(false);		
	}
	
	public void addWdiget(Widget w) {
		this.seccionAgenda.add(w);
	}
}
