package com.is.mim.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class Correo {
	//Se declaran los paneles que quiera, est‡ el VerticalPanel de ejemplo
	private VerticalPanel seccionCorreo;
	
    /**
     * Esta variable indica si se ha creado ya la secci—n
     * true significa que no se ha creado
     * false significa que SI se ha creado
     */	private boolean firstCorreo = true;

	public Correo() {
		super();
		this.seccionCorreo = new VerticalPanel();
		panelCorreo();
	}
	
	public Correo(VerticalPanel vpanel) {
		super();
		this.seccionCorreo = vpanel;
	}
	
	public void panelCorreo() {
		Button btn = new Button("Correo");
		this.seccionCorreo.add(btn);
	}
	
	public VerticalPanel getSecCorreo() {
		return this.seccionCorreo;
	}
	
	public void setSecCorreo(VerticalPanel vpanel) {
		this.seccionCorreo = vpanel;
	}
	
	public void setFirstCorreo(boolean state) {
		this.firstCorreo = state;
	}
	
	public boolean getFirstCorreo() {
		return this.firstCorreo;
	}
	
	public void setVisible(){
		this.seccionCorreo.setVisible(true);
	}
	
	public void setInvisible() {
		this.seccionCorreo.setVisible(false);		
	}
	
	public void addWdiget(Widget w) {
		this.seccionCorreo.add(w);
	}
}
