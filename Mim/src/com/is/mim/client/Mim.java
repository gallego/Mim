package com.is.mim.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Mim implements EntryPoint, ValueChangeHandler<String> {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	
	//Instancias de las secciones
	private Inicio inicio = new Inicio();
	private Agenda agenda = new Agenda();
	private Correo correo = new Correo();
	private Social social = new Social();

    private String lastToken;

	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		crearEnlacesTopBar();
		inicializaInicio();
	}
	
	/**
	 * Funciones que insertan en el div centerContainer cada secci—n.
	 */
	private void inicializaInicio() {
		if (inicio.getFirstInicio()) {
			RootPanel.get("centerContainer").add(inicio.getSecInicio());
			inicio.setFirstInicio(false);
		}
	}
	
	private void inicializaAgenda() {
		if (agenda.getFirstAgenda()) {
			RootPanel.get("centerContainer").add(agenda.getSecAgenda());
			agenda.setFirstAgenda(false);
		}
	}
	
	private void inicializaCorreo() {
		if (correo.getFirstCorreo()) {
			RootPanel.get("centerContainer").add(correo.getSecCorreo());
			correo.setFirstCorreo(false);
		}
	}
	
	private void inicializaSocial() {
		if (social.getFirstSocial()) {
			RootPanel.get("centerContainer").add(social.getSecSocial());
			social.setFirstSocial(false);
		}
	}
	
	/**
	 * Esta funcion crea los enlaces en el TopBar.
	 * @lastToken almacena el œltimo enlace pulsado para poder hacer su secci—n invisible
	 */
	private void crearEnlacesTopBar() {
		Hyperlink linkInicio = new Hyperlink("Inicio", "inicio");
	    Hyperlink linkAgenda = new Hyperlink("Agenda", "agenda");
	    Hyperlink linkCorreo = new Hyperlink("Correo", "correo");
	    Hyperlink linkSocial = new Hyperlink("Social", "social");
	    
	    RootPanel.get("linkInicio").add(linkInicio);
	    RootPanel.get("linkAgenda").add(linkAgenda);
	    RootPanel.get("linkCorreo").add(linkCorreo);
	    RootPanel.get("linkSocial").add(linkSocial);
	    
	    lastToken = History.getToken();

	    if (lastToken.length() == 0) {
	      History.newItem("inicio");
	    }
	    
	    // Add history listener
	    History.addValueChangeHandler(this);

	    // Now that we've setup our listener, fire the initial history state.
	    History.fireCurrentHistoryState();
		
	}
	
	 /** 
	  * Esta funci—n captura los eventos cuando hacemos click en los enlaces
	  * del TopBar para hacer visible o no cada una de las secciones.
	  */
	 public void onValueChange(ValueChangeEvent<String> event) {
		 	// Comprobamos el lastToken para ver cual fue la secci—n anterior y hacerla invisible
		 	if ((lastToken.equalsIgnoreCase("inicio")) && (!event.getValue().equalsIgnoreCase("inicio"))) {
		 		inicio.setInvisible();
		 	} else if (lastToken.equalsIgnoreCase("agenda")) {
		 		agenda.setInvisible();
		 	} else if (lastToken.equalsIgnoreCase("correo")) {
		 		correo.setInvisible();
		 	} else if (lastToken.equalsIgnoreCase("social")) {
		 		social.setInvisible();
		 	}
		 
		 	// Comprobamos cual es la secci—n actual para crearla si es necesario y hacerla visible
		 	if ((event.getValue().equals("inicio")) && (!lastToken.equalsIgnoreCase("inicio"))) {
		 		inicio.setVisible();
	 		} else if (event.getValue().equalsIgnoreCase("agenda")) {
 				inicializaAgenda();
	 			agenda.setVisible();
		 	} else if (event.getValue().equalsIgnoreCase("correo")) {
	 			inicializaCorreo();
		 		correo.setVisible();		 		
		 	} else if (event.getValue().equalsIgnoreCase("social")) {
	 			inicializaSocial();
		 		social.setVisible();		 		
		 	}
		 	
		 	// Actualizamos el lastToken
		 	lastToken = event.getValue();
		  }
	
}
