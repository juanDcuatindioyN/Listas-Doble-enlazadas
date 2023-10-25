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
import java.io.File;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.karaoke.mundo.Cancion;

/**
 * Panel con la informaci�n de una canci�n
 */
@SuppressWarnings("serial")
public class PanelCancion extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Representa la opci�n Reproducir
     */
    public final static String REPRODUCIR = "Reproducir";

    /**
     * Representa la opci�n Pausar
     */
    public final static String PAUSAR = "Pausar";

    /**
     * Representar la opci�n Parar
     */
    public final static String PARAR = "Parar";

    /**
     * Representar la opci�n eliminar canci�n
     */
    public final static String ELIMINAR_CANCION = "Eliminar canci�n";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ruta del archivo con la canci�n
     */
    private String ruta;

    /**
     * Secuencia
     */
    private Sequencer seq;

    /**
     * Ventana principal de la aplicaci�n
     */
    private InterfazKaraoke principal;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

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
     * Bot�n Reproducir
     */
    private JButton btnReproducir;

    /**
     * Bot�n Pausar
     */
    private JButton btnPausar;

    /**
     * Bot�n Parar
     */
    private JButton btnParar;

    /**
     * Bot�n eliminar canci�n.
     */
    private JButton btnEliminarCancion;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea el panel con la informaci�n de una canci�n <br>
     * @param pVentana Ventana principal de la aplicaci�n. ventana != null
     */
    public PanelCancion( InterfazKaraoke pVentana )
    {
        principal = pVentana;
        ruta = null;

        setBorder( new CompoundBorder( new TitledBorder( " Canci�n: " ), new EmptyBorder( 0, 0, 0, 5 ) ) );
        setPreferredSize( new Dimension( 250, 0 ) );
        setLayout( new BorderLayout( ) );

        JPanel info = new JPanel( );
        info.setLayout( new BorderLayout( ) );

        JPanel info1 = new JPanel( );
        info1.setLayout( new GridLayout( 3, 1 ) );

        JPanel info2 = new JPanel( );
        info2.setLayout( new GridLayout( 3, 1 ) );

        info1.add( new JLabel( " Nombre: " ) );
        txtNombre = new JTextField( );
        txtNombre.setEditable( false );
        info2.add( txtNombre );

        info1.add( new JLabel( " Duraci�n: " ) );
        txtDuracion = new JTextField( );
        txtDuracion.setEditable( false );
        info2.add( txtDuracion );

        info1.add( new JLabel( " Dificultad: " ) );
        txtDificultad = new JTextField( );
        txtDificultad.setEditable( false );
        info2.add( txtDificultad );

        info.add( info1, BorderLayout.WEST );
        info.add( info2, BorderLayout.CENTER );

        add( info, BorderLayout.NORTH );

        JPanel letra = new JPanel( );
        letra.setLayout( new BorderLayout( ) );

        letra.add( new JLabel( " Letra: " ), BorderLayout.NORTH );

        txtLetra = new JTextArea( );
        txtLetra.setEditable( false );
        JScrollPane scrollLetra = new JScrollPane( txtLetra );
        scrollLetra.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED );
        scrollLetra.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        letra.add( scrollLetra, BorderLayout.CENTER );

        add( letra, BorderLayout.CENTER );

        JPanel botones = new JPanel( );
        botones.setLayout( new GridLayout( 1, 3 ) );
        botones.setPreferredSize( new Dimension( 0, 35 ) );

        btnPausar = new JButton( new ImageIcon( "./data/imagenes/pausar.gif" ) );
        btnPausar.setActionCommand( PAUSAR );
        btnPausar.addActionListener( this );
        botones.add( btnPausar );

        btnReproducir = new JButton( new ImageIcon( "./data/imagenes/reproducir.gif" ) );
        btnReproducir.setActionCommand( REPRODUCIR );
        btnReproducir.addActionListener( this );
        botones.add( btnReproducir );

        btnParar = new JButton( new ImageIcon( "./data/imagenes/parar.gif" ) );
        btnParar.setActionCommand( PARAR );
        btnParar.addActionListener( this );
        botones.add( btnParar );

        JPanel aux = new JPanel( new BorderLayout( ) );
        aux.add( botones, BorderLayout.CENTER );

        btnEliminarCancion = new JButton( ELIMINAR_CANCION );
        btnEliminarCancion.setActionCommand( ELIMINAR_CANCION );
        btnEliminarCancion.addActionListener( this );
        aux.add( btnEliminarCancion, BorderLayout.SOUTH );

        add( aux, BorderLayout.SOUTH );

    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Actualiza la informaci�n de la canci�n
     * @param pCancion Canci�n cuya informaci�n va a ser mostrada
     */
    public void actualizar( Cancion pCancion )
    {
        if( pCancion != null )
        {
            txtDificultad.setText( "" + pCancion.darDificultad( ) );
            txtDuracion.setText( pCancion.darDuracion( ) + " seg." );
            txtLetra.setText( pCancion.darLetra( ) );
            txtLetra.setCaretPosition( 0 );
            txtNombre.setText( pCancion.darNombre( ) );
            ruta = pCancion.darRuta( );
            btnEliminarCancion.setEnabled( true );
            btnParar.setEnabled( true );
            btnPausar.setEnabled( true );
            btnReproducir.setEnabled( true );
        }
        else
        {
            txtDificultad.setText( "" );
            txtDuracion.setText( "" );
            txtLetra.setText( "" );
            txtNombre.setText( "" );
            ruta = null;
            btnEliminarCancion.setEnabled( false );
            btnParar.setEnabled( false );
            btnPausar.setEnabled( false );
            btnReproducir.setEnabled( false );
        }

        if( seq != null )
        {
            seq.stop( );

            seq.close( );
            seq = null;
        }
    }

    /**
     * Manejo de los eventos de los botones
     * @param pEvento Acci�n que gener� el evento.
     */
    public void actionPerformed( ActionEvent pEvento )
    {
        String comando = pEvento.getActionCommand( );
        if( comando.equals( REPRODUCIR ) )
        {
            if( ruta != null )
            {
                try
                {
                    if( seq == null )
                    {
                        File archivoMidi = new File( ruta );
                        Sequence sequence = MidiSystem.getSequence( archivoMidi );
                        seq = MidiSystem.getSequencer( );
                        seq.open( );
                        seq.setSequence( sequence );
                        seq.start( );
                    }
                    else
                    {
                        seq.start( );
                    }
                }
                catch( Exception e1 )
                {
                    JOptionPane.showMessageDialog( this, "No fue posible reproducir la canci�n " + e1.getMessage( ), "Reproducir Canci�n", JOptionPane.ERROR_MESSAGE );
                }
            }
        }
        else if( comando.equals( PARAR ) )
        {
            if( seq != null )
            {
                seq.stop( );

                seq.close( );
                seq = null;
            }
        }
        else if( comando.equals( PAUSAR ) )
        {
            if( seq != null )
            {
                seq.stop( );
            }
        }
        else if( comando.equals( ELIMINAR_CANCION ) )
        {
            principal.eliminarCancionSeleccionada( );
        }
    }
}
