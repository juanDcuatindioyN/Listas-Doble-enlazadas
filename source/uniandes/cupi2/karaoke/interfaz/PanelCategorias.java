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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.karaoke.mundo.Karaoke;

/**
 * Panel con la informaci�n de las categor�as
 */
@SuppressWarnings("serial")
public class PanelCategorias extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Representa la acci�n de cambio de categor�a
     */
    private final static String CAMBIO_CATEGORIA = "cambio";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicaci�n
     */
    private InterfazKaraoke principal;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Combo box con las categor�as del karaoke
     */
    private JComboBox<String> comboCategoria;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea el panel con la informaci�n de las categor�as
     * @param pVentana Ventana principal de la aplicaci�n
     */
    public PanelCategorias( InterfazKaraoke pVentana )
    {
        principal = pVentana;

        setBorder( new CompoundBorder( new TitledBorder( " Categor�as: " ), new EmptyBorder( 0, 5, 0, 10 ) ) );
        setLayout( new BorderLayout( ) );

        comboCategoria = new JComboBox<String>( Karaoke.CATEGORIAS );

        comboCategoria.setActionCommand( CAMBIO_CATEGORIA );
        comboCategoria.addActionListener( this );

        add( comboCategoria, BorderLayout.CENTER );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna la categor�a seleccionada
     * @return Categor�a seleccionada
     */
    public String darCategoriaSeleccionada( )
    {
        String categoria = ( String )comboCategoria.getSelectedItem( );
        return categoria;
    }

    /**
     * Manejo de los eventos de los botones
     * @param pEvento Acci�n que gener� el evento.
     */
    public void actionPerformed( ActionEvent pEvento )
    {
        String comando = pEvento.getActionCommand( );
        if( comando.equals( CAMBIO_CATEGORIA ) )
        {
            String categoria = ( String )comboCategoria.getSelectedItem( );
            principal.actualizarArtistas( categoria );
        }
    }

}
