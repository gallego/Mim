package com.is.mim.client;

import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class Social {
	//Se declaran los paneles que quiera, est‡ el VerticalPanel de ejemplo
	private VerticalPanel seccionSocial;
	
    /**
     * Esta variable indica si se ha creado ya la secci—n
     * true significa que no se ha creado
     * false significa que SI se ha creado
     */	private boolean firstSocial = true;

	public Social() {
		super();
		this.seccionSocial = new VerticalPanel();
		panelSocial();
	}
	
	public Social(VerticalPanel vpanel) {
		super();
		this.seccionSocial = vpanel;
	}
	
	public void panelSocial() {
		TextBox texto = new TextBox();
		texto.setText("Social");
		this.seccionSocial.add(texto);
	}
	
	public VerticalPanel getSecSocial() {
		return this.seccionSocial;
	}
	
	public void setSecSocial(VerticalPanel vpanel) {
		this.seccionSocial = vpanel;
	}
	
	public void setFirstSocial(boolean state) {
		this.firstSocial = state;
	}
	
	public boolean getFirstSocial() {
		return this.firstSocial;
	}
	
	public void setVisible(){
		this.seccionSocial.setVisible(true);
	}
	
	public void setInvisible() {
		this.seccionSocial.setVisible(false);		
	}
	
	public void addWdiget(Widget w) {
		this.seccionSocial.add(w);
	}
}
