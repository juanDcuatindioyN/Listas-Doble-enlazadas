/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_karaoke
 * Autor: Equipo Cupi2  2018-2
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */


package uniandes.cupi2.karaoke.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.karaoke.mundo.Artista;
import uniandes.cupi2.karaoke.mundo.Karaoke;

/**
 * Dialogo que permite agregar una nueva canci�n
 */
@SuppressWarnings({"serial","rawtypes","unchecked"})
public class DialogoAgregarCancion extends JDialog implements ActionListener
{
	//-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------

    /**
     * Comando Agregar Canci�n
     */
    private static final String AGREGAR = "Agregar";
    
	/**
	 * Comando Seleccionar Imagen
	 */
	private final static String SELECCIONAR = "...";
	
	/**
	 * Representa la acci�n de cambio de categor�a
	 */
	private final static String CAMBIO_CATEGORIA = "Cambio categor�a";
		
	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Ventana principal de la aplicaci�n
     */
    private InterfazKaraoke principal;
    
	//-----------------------------------------------------------------
    // Atributos de la interfaz
    //-----------------------------------------------------------------

    /**
	 * Combo box con las categor�as del karaoke
	 */
	private JComboBox comboCategoria;
	
	/**
	 * Combo box con los artistas de una categor�a
	 */
	private JComboBox comboArtistas;
	
	/**
	 * Campo de texto con el nombre de la canci�n
	 */
	private JTextField txtNombre;
	
	/**
	 * Campo de texto con la duraci�n de la canci�n
	 */
	private JTextField txtDuracion;
	
	/**
	 * Campo de texto con la dificultad de la canci�n
	 */
	private JTextField txtDificultad;
	
	/**
	 * �rea de texto con la letra de la canci�n
	 */
	private JTextArea txtLetra;
	
	/**
	 * Campo de texto con la ruta del archivo con la canci�n
	 */
	private JTextField txtRuta;
	
	/**
	 * Bot�n Agregar
	 */
	private JButton btnAgregar;
	
	/**
     * Bot�n Seleccionar Imagen
     */
    private JButton btnSeleccionar;
	
    //  -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor del dialogo
     * @param pVentana Ventana principal
     */
    public DialogoAgregarCancion(InterfazKaraoke pVentana)
    {
    	principal = pVentana;
    	
    	setLayout( new BorderLayout( ) );
        setSize(335, 400);
        setModal(true);
        setLocationRelativeTo(pVentana);
        setTitle("Agregar Canci�n");
        
        JPanel panelInfo1 = new JPanel();
        panelInfo1.setLayout(new GridLayout(6, 2));
        
        panelInfo1.add(new JLabel(" Categor�a: "));
        comboCategoria = new JComboBox(Karaoke.CATEGORIAS);
        comboCategoria.setActionCommand(CAMBIO_CATEGORIA);
        comboCategoria.addActionListener(this);
        panelInfo1.add(comboCategoria);

        panelInfo1.add(new JLabel(" Artista: "));
        comboArtistas = new JComboBox();
        panelInfo1.add(comboArtistas);
        
        panelInfo1.add(new JLabel(" Nombre: "));
        txtNombre = new JTextField();
        panelInfo1.add(txtNombre);  
        
        panelInfo1.add(new JLabel(" Duraci�n (seg): "));
        txtDuracion = new JTextField();
        panelInfo1.add(txtDuracion);
        
        panelInfo1.add(new JLabel(" Dificultad: "));
        txtDificultad = new JTextField();
        panelInfo1.add(txtDificultad);

        JPanel aux = new JPanel();
        aux.setLayout(new BorderLayout());
        
        panelInfo1.add(new JLabel(" Archivo: "));
        txtRuta = new JTextField();
        txtRuta.setEditable(false);
        aux.add(txtRuta, BorderLayout.CENTER);
        btnSeleccionar = new JButton(SELECCIONAR);
        btnSeleccionar.setActionCommand(SELECCIONAR);
        btnSeleccionar.addActionListener(this);
        btnSeleccionar.setPreferredSize(new Dimension(50, 0));
        aux.add(btnSeleccionar, BorderLayout.EAST);
        
        panelInfo1.add(aux);        
        
        add(panelInfo1, BorderLayout.NORTH);
        
        JPanel panelInfo2 = new JPanel();
        panelInfo2.setLayout(new BorderLayout());
        
        txtLetra = new JTextArea();
        txtLetra.setLineWrap(true);
        
        JScrollPane scrollLetra = new JScrollPane( txtLetra );
        scrollLetra.setPreferredSize(new Dimension(0, 80));
        scrollLetra.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollLetra.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollLetra.setBorder(new TitledBorder(" Letra: "));
        panelInfo2.add(scrollLetra, BorderLayout.CENTER);
        
        add(panelInfo2, BorderLayout.CENTER);
        
        btnAgregar = new JButton(AGREGAR);
        btnAgregar.setActionCommand(AGREGAR);
        btnAgregar.addActionListener(this);
                
        add(btnAgregar, BorderLayout.SOUTH);
        
        comboCategoria.setSelectedIndex(0);
    }
    
    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------
      
    /**
     * Manejo de los eventos de los botones
     * @param pEvento Acci�n que gener� el evento.
     */
	public void actionPerformed(ActionEvent pEvento)
	{
		String comando = pEvento.getActionCommand();
		if(comando.equals(AGREGAR))
		{
			String artista = (String) comboArtistas.getSelectedItem();
			String nombre = txtNombre.getText();
			String dificultadS = txtDificultad.getText();
			String duracionS = txtDuracion.getText();
			String ruta = txtRuta.getText();
			String letra = txtLetra.getText();
			
			if(nombre != null && !nombre.isEmpty() && dificultadS != null && !dificultadS.isEmpty() && 
					duracionS != null && !duracionS.isEmpty() && ruta != null && !ruta.isEmpty() && 
					letra != null && !letra.isEmpty() && artista != null)
			{
				try
				{
					int dificultad = Integer.parseInt(dificultadS);
					int duracion = Integer.parseInt(duracionS);
					
					if(duracion > 0)
					{
						if(dificultad > 0 && dificultad <= 10)
						{
							principal.agregarCancion( artista, nombre, duracion, letra, dificultad, ruta);
							this.dispose();
						}
						else
						{
							JOptionPane.showMessageDialog(this, "La dificultad debe estar entre 1 y 10", "Error", JOptionPane.ERROR_MESSAGE);													
						}
					}
					else
					{
						JOptionPane.showMessageDialog(this, "La duraci�n debe ser mayor a cero", "Error", JOptionPane.ERROR_MESSAGE);						
					}
				}
				catch (NumberFormatException ex) 
				{
					JOptionPane.showMessageDialog(this, "La duraci�n y la dificultad de la canci�n deben ser valores num�ricos", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Por favor ingrese la informaci�n completa de la canci�n", "Error", JOptionPane.ERROR_MESSAGE);				
			}
		}
		else if(comando.equals(SELECCIONAR))
		{
			JFileChooser fileChooser = new JFileChooser( "./data/canciones" );
			if( fileChooser.showOpenDialog( principal ) == JFileChooser.APPROVE_OPTION )
			{
				txtRuta.setText(fileChooser.getSelectedFile( ).getAbsolutePath( ));                
			}			
		}
		else if(comando.equals(CAMBIO_CATEGORIA))
    	{
    		String categoria = (String) comboCategoria.getSelectedItem();
    		
    		comboArtistas.removeAllItems();
    		ArrayList artistas = principal.darArtistasCategoria( categoria );
    		for (int i = 0; i < artistas.size(); i++) 
    		{
				Artista a = (Artista) artistas.get(i);
				comboArtistas.addItem(a.darNombre());
			}
    	}
	}
}