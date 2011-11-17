package com.is.mim.client;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Inicio {
	//Panel horizontal que contiene los resœmenes de Agenda y Correo
	private HorizontalPanel seccionInicio = new HorizontalPanel();
	//Panel vertical que contiene el resumen de Agenda
	private VerticalPanel agendaPanel = new VerticalPanel();
	//Panel vertical que contiene el resumen de Correo
	private VerticalPanel correoPanel = new VerticalPanel();
	
	//Panel que se usa para almacenar el TextBox de a–adir tarea y el bot—n de a–adir
    private HorizontalPanel addAgendaPanel = new HorizontalPanel();
    //Tabla que contiene las tareas
	private FlexTable agendaFlexTable = new FlexTable();
	//TextBox para insertar tareas
	private TextBox addTask = new TextBox();
	//Array en el que almacenamos las tareas insertadas en el resumen (de ejemplo hasta que se cree la base de datos)
    private ArrayList<String> tasks = new ArrayList<String>();
    
    /**
     * Esta variable indica si se ha creado ya la secci—n
     * true significa que no se ha creado
     * false significa que SI se ha creado
     */
	private boolean firstInicio = true;

    
    public Inicio(){
    	panelAgenda();
    	panelCorreo();
    }
    
    
    public HorizontalPanel getSecInicio() {
    	return seccionInicio;
    }
    
    public void setFirstInicio(boolean state) {
    	this.firstInicio = state;
    }
    
    public boolean getFirstInicio() {
    	return this.firstInicio;
    }
    
    public void setVisible() {
    	this.seccionInicio.setVisible(true);
    }
    
    public void setInvisible() {
    	this.seccionInicio.setVisible(false);
    }
	
	private void panelAgenda() {
		HTML divInicioAgenda = new HTML("<div class=\"page-header\">" +
					    "<h2>Agenda <small>Listado de tareas que tienes que hacer hoy</small></h2>" +
				    "</div>", true);
		HTML divFinAgenda = new HTML("<h4>Para a–adir una prioridad: #1 alta, #2 media, #3 baja</h4>" +	    
				"</div>", true);
		
		agendaPanel.add(divInicioAgenda);

	    // Add it to the root panel.
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
	    agendaPanel.add(addAgendaPanel);
	    agendaPanel.add(agendaFlexTable);
	    	    
		// Panel de agenda
		//RootPanel.get("agendaContainer").add(agendaPanel);
	    agendaPanel.add(divFinAgenda);
	    seccionInicio.add(agendaPanel);
		
		
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
		HTML divInicioCorreo = new HTML("<div class=\"page-header\">" +
					    "<h2>Correo <small>Listado de tareas que tienes que hacer hoy</small></h2>" +
				    "</div>", true);
		HTML divFinCorreo = new HTML("</div>", true);

		correoPanel.add(divInicioCorreo);
		correoPanel.add(divFinCorreo);
		seccionInicio.add(correoPanel);
		
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
			
			// Don't add the task if it's already in the table.
			if (tasks.contains(symbol))
			  return;
			
			// Add the task to the table.
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
