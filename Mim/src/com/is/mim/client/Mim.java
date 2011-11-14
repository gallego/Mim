package com.is.mim.client;

import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.is.mim.shared.FieldVerifier;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Mim implements EntryPoint {
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

	
	// Panel de Agenda
	private VerticalPanel agendaPanel = new VerticalPanel();
    private HorizontalPanel addAgendaPanel = new HorizontalPanel();
	private FlexTable agendaFlexTable = new FlexTable();
	private TextBox addTask = new TextBox();
    private ArrayList<String> tasks = new ArrayList<String>();
    
    // Panel de Correo
    private VerticalPanel correoPanel = new VerticalPanel();
	private FlexTable correoFlexTable = new FlexTable();
    


	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		panelAgenda();		
		panelCorreo();
	}
	
	private void panelAgenda() {
		// Variables del panel de agenda
		addTask.setStyleName("xlarge");
		addTask.setText("Nueva tarea");
		Button sendTask = new Button("Enviar");
		sendTask.setStyleName("btn primary");
			
		
	    // Creamos la tabla que muestra los datos de la agenda.
	    agendaFlexTable.setText(0, 0, "#");
	    agendaFlexTable.setText(0, 1, "Tarea");
	    agendaFlexTable.setText(0, 2, "Prioridad");
	    agendaFlexTable.setText(0, 3, "Borrar");
	    agendaFlexTable.addStyleName("bordered-table zebra-striped");
	    
	    // A–adimos el TextBox de a–adir tarea y el boton al panel horizontal
	    addAgendaPanel.add(addTask);
	    addAgendaPanel.add(sendTask);

	    // A–adimos la tabla y el panel horizontal al panel vertical
	    agendaPanel.add(agendaFlexTable);
	    agendaPanel.add(addAgendaPanel);
	    
		// Panel de agenda
		RootPanel.get("agendaContainer").add(agendaPanel);
		
		// Move cursor focus to the input box.
	    addTask.setFocus(true);
		
		
	    // Listen for mouse events on the Add button.
	    sendTask.addClickHandler(new ClickHandler() {
	      public void onClick(ClickEvent event) {
	        addTasks();
	      }
	    });
		
	    // Listen for keyboard events in the input box.
	    addTask.addKeyPressHandler(new KeyPressHandler() {
	      public void onKeyPress(KeyPressEvent event) {
	        if (event.getCharCode() == KeyCodes.KEY_ENTER) {
	          addTasks();
	        }
	      }
	    });
	}
	
	private void panelCorreo() {
		
	}
	
  /**
   * Add tasks to FlexTable. Executed when the user clicks the sendTask or
   * presses enter in the addTask.
   */
	private void addTasks() {
		final String symbol = addTask.getText().toUpperCase().trim();
		char priority = '0';
		addTask.setFocus(true);
		
		// Obtengo la prioridad
		if (symbol.contains("#")) {
			int index = symbol.indexOf('#') + 1;
			priority = symbol.charAt(index);
			int numPriority = priority - 48;
			if ((numPriority < 1) || (numPriority > 3)) {
				Window.alert("La prioridad debe estar comprendida entre 1 (alta), 2 (media) y 3 (baja),");
				return;
			}
		}
		
		
		addTask.setText("");
		
		// Don't add the stock if it's already in the table.
		if (tasks.contains(symbol))
		  return;
		
		// Add the stock to the table.
		int row = agendaFlexTable.getRowCount();
		tasks.add(symbol);
		agendaFlexTable.setText(row, 0, "" + row); // Nœmero de fila
		agendaFlexTable.setText(row, 1, symbol);   // Tarea
		agendaFlexTable.setText(row, 2, String.valueOf(priority)); // Prioridad
		
		
		// Add a button to remove this stock from the table.
		Button removeStockButton = new Button("x");
		removeStockButton.addStyleDependentName("remove");
		    removeStockButton.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		        int removedIndex = tasks.indexOf(symbol);
		        tasks.remove(removedIndex);
		        agendaFlexTable.removeRow(removedIndex + 1);
		      }
		    });
		    agendaFlexTable.setWidget(row, 3, removeStockButton);
		
	}
	
}
