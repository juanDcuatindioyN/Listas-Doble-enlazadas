/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import uniandes.cupi2.karaoke.mundo.Karaoke;

/**
 * Dialogo que permite agregar un nuevo artista 
 */
@SuppressWarnings({"serial","rawtypes","unchecked"})
public class DialogoAgregarArtista extends JDialog implements ActionListener
{
	//-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------

    /**
     * Comando Agregar Canción
     */
    private static final String AGREGAR = "Agregar";
    
	/**
	 * Comando Seleccionar Imagen
	 */
	private final static String SELECCIONAR = "...";
	
	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación
     */
    private InterfazKaraoke principal;
    
	//-----------------------------------------------------------------
    // Atributos de la interfaz
    //-----------------------------------------------------------------

    /**
	 * Combo box con las categorías del karaoke
	 */
	private JComboBox comboCategoria;
	
	/**
     * Campo de texto con la imagen de la receta
     */
    private JTextField txtImagen;

    /**
     * Campo de texto con el nombre de la receta
     */
    private JTextField txtNombre;
	
	/**
	 * Botón Agregar
	 */
	private JButton btnAgregar;
	
	/**
     * Botón Seleccionar Imagen
     */
    private JButton btnSeleccionar;
	
    //  -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor del dialogo
     * @param pVentana Ventana principal
     */
    public DialogoAgregarArtista(InterfazKaraoke pVentana)
    {
    	principal = pVentana;
    	
    	setLayout( new BorderLayout( ) );
        setSize(335, 150);
        setModal(true);
        setLocationRelativeTo(null);
        setTitle("Agregar Artista");
        
        JPanel panelInfo1 = new JPanel();
        panelInfo1.setLayout(new GridLayout(3, 2));
        
        panelInfo1.add(new JLabel(" Categoría: "));
        comboCategoria = new JComboBox(Karaoke.CATEGORIAS);
        panelInfo1.add(comboCategoria);
        
        panelInfo1.add(new JLabel(" Nombre: "));
        txtNombre = new JTextField();
        panelInfo1.add(txtNombre);
        
        JPanel aux = new JPanel();
        aux.setLayout(new BorderLayout());
        
        panelInfo1.add(new JLabel(" Imagen: "));
        txtImagen = new JTextField();
        txtImagen.setEditable(false);
        aux.add(txtImagen, BorderLayout.CENTER);
        btnSeleccionar = new JButton(SELECCIONAR);
        btnSeleccionar.setActionCommand(SELECCIONAR);
        btnSeleccionar.addActionListener(this);
        btnSeleccionar.setPreferredSize(new Dimension(50, 0));
        aux.add(btnSeleccionar, BorderLayout.EAST);
        
        panelInfo1.add(aux);        
        
        add(panelInfo1, BorderLayout.CENTER);
        
        btnAgregar = new JButton(AGREGAR);
        btnAgregar.setActionCommand(AGREGAR);
        btnAgregar.addActionListener(this);
                
        add(btnAgregar, BorderLayout.SOUTH);
    }
    
    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
      
    /**
     * Manejo de los eventos de los botones
     * @param pEvento Acción que generó el evento.
     */
	public void actionPerformed(ActionEvent pEvento)
	{
		String comando = pEvento.getActionCommand();
		if(comando.equals(AGREGAR))
		{
			String categoria = (String) comboCategoria.getSelectedItem();
			String nombre = txtNombre.getText();
			String imagen = txtImagen.getText();
			
			if(nombre != null && !nombre.isEmpty() && imagen != null && !imagen.isEmpty())
			{
				principal.agregarArtista(categoria, nombre, imagen);
				this.dispose();
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Por favor ingrese la información completa del artista", "Error", JOptionPane.ERROR_MESSAGE);				
			}
		}
		else if(comando.equals(SELECCIONAR))
		{
			JFileChooser fileChooser = new JFileChooser( "./data/imagenes" );
			if( fileChooser.showOpenDialog( principal ) == JFileChooser.APPROVE_OPTION )
			{
				txtImagen.setText(fileChooser.getSelectedFile( ).getAbsolutePath( ));                
			}			
		}
	}
}