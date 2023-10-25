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

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.karaoke.mundo.Karaoke;



/**
 * Panel con operaciones de b�squeda y creaci�n de artistas y canciones
 */
@SuppressWarnings("serial")
public class PanelOpciones extends JPanel implements ActionListener
{
    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------

    /**
     * Comando Agregar Artista
     */
    private static final String AGREGAR_ARTISTA = "Agregar Artista";

    /**
     * Comando Agregar Canci�n
     */
    private static final String AGREGAR_CANCION = "Agregar Canci�n";

    /**
     * Comando Buscar
     */
    private static final String BUSCAR = "Buscar";
    
    /**
     * Comando Todas las Canciones
     */
    private static final String TODAS = "Lista de Canciones";

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Ventana principal de la aplicaci�n
     */
    private InterfazKaraoke principal;

    //-----------------------------------------------------------------
    // Atributos de interfaz
    //-----------------------------------------------------------------

    /**
     * Bot�n Agregar Artista
     */
    private JButton btnAgregarArtista;
    
    /**
     * Bot�n Agregar Canci�n
     */
    private JButton btnAgregarCancion;
    
    /**
     * Bot�n Buscar
     */
    private JButton btnBuscar;
    
    /**
     * Bot�n Todas las Canciones
     */
    private JButton btnTodas;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Constructor del panel
     * @param pVentana Ventana principal
     */
    public PanelOpciones( InterfazKaraoke pVentana )
    {
        principal = pVentana;

        setBorder( new TitledBorder("") );
        setLayout( new GridLayout( 1, 4 ) );

        btnAgregarArtista = new JButton( AGREGAR_ARTISTA );
        btnAgregarArtista.setActionCommand( AGREGAR_ARTISTA );
        btnAgregarArtista.addActionListener( this );
        add(btnAgregarArtista);
        
        btnAgregarCancion = new JButton( AGREGAR_CANCION );
        btnAgregarCancion.setActionCommand( AGREGAR_CANCION );
        btnAgregarCancion.addActionListener( this );
        add(btnAgregarCancion);
        
        btnBuscar = new JButton( BUSCAR );
        btnBuscar.setActionCommand( BUSCAR );
        btnBuscar.addActionListener( this );
        add(btnBuscar);
        
        btnTodas = new JButton( TODAS );
        btnTodas.setActionCommand( TODAS );
        btnTodas.addActionListener( this );
        add(btnTodas);
    }

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones
     * @param pEvento Acci�n que gener� el evento.
     */
    public void actionPerformed( ActionEvent pEvento )
    {
    	String comando = pEvento.getActionCommand();
        if(comando.equals(AGREGAR_ARTISTA))
        {
        	DialogoAgregarArtista dialogo = new DialogoAgregarArtista(principal);
        	dialogo.setVisible(true);
        }
        else if(comando.equals(AGREGAR_CANCION))
        {
        	DialogoAgregarCancion dialogo = new DialogoAgregarCancion(principal);
        	dialogo.setVisible(true);
        }
        else if(comando.equals(BUSCAR))
        {
        	new DialogoBuscar(principal).setVisible(true);
        }
        else if(comando.equals(TODAS))
        {
        	String c = (String)JOptionPane.showInputDialog(null, "Categor�a: ", TODAS, JOptionPane.QUESTION_MESSAGE, null, Karaoke.CATEGORIAS, Karaoke.CATEGORIAS[0]);
            if(c != null)
            {	
            	principal.mostrarCanciones(c);            	
            }
        }
    }

}
