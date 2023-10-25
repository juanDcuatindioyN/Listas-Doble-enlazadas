package uniandes.cupi2.karaoke.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.karaoke.mundo.Artista;
import uniandes.cupi2.karaoke.mundo.Cancion;

/**
 * Panel con la información de artistas
 */
@SuppressWarnings({"serial","rawtypes","unchecked"})
public class PanelArtistas extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Representa la acción de cambio de artista
     */
    private final static String CAMBIO_ARTISTA = "Cambio artista";

    /**
     * Representa la acción de cambio de canción
     */
    private final static String CAMBIO_CANCION = "Cambio canción";

    /**
     * Representa la acción de eliminar artista.
     */
    private final static String ELIMINAR_ARTISTA = "Eliminar artista";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación
     */
    private InterfazKaraoke principal;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Combo box con los artistas de una categoría
     */
    private JComboBox comboArtistas;

    /**
     * Campo de texto con el nombre del artista
     */
    private JTextField txtNombre;

    /**
     * Etiqueta con la imagen del artista
     */
    private JLabel etiquetaImagen;

    /**
     * Combo box con las canciones de un artista
     */
    private JComboBox comboCanciones;

    /**
     * Botón para eliminar el artista.
     */
    private JButton btnEliminarArtista;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea el panel con la información de artistas
     * @param pVentana Ventana principal de la aplicación. ventana != null
     */
    public PanelArtistas( InterfazKaraoke pVentana )
    {
        principal = pVentana;

        setBorder( new EmptyBorder( 0, 5, 0, 10 ) );
        setLayout( new BorderLayout( ) );
        setPreferredSize( new Dimension( 250, 0 ) );

        JPanel artistas = new JPanel( );
        artistas.setLayout( new BorderLayout( ) );
        artistas.setBorder( new TitledBorder( " Artistas: " ) );

        comboArtistas = new JComboBox( );
        comboArtistas.setActionCommand( CAMBIO_ARTISTA );
        comboArtistas.addActionListener( this );
        artistas.add( comboArtistas, BorderLayout.CENTER );

        add( artistas, BorderLayout.NORTH );

        JPanel artista = new JPanel( );
        artista.setBorder( new TitledBorder( " Artista: " ) );
        artista.setLayout( new BorderLayout( ) );

        txtNombre = new JTextField( );
        txtNombre.setEditable( false );
        txtNombre.setFont( new Font( "Arial", Font.BOLD, 16 ) );
        txtNombre.setForeground( Color.DARK_GRAY );
        txtNombre.setHorizontalAlignment( JTextField.CENTER );
        artista.add( txtNombre, BorderLayout.NORTH );

        etiquetaImagen = new JLabel( );
        etiquetaImagen.setPreferredSize( new Dimension( 0, 100 ) );
        etiquetaImagen.setHorizontalAlignment( JLabel.CENTER );
        artista.add( etiquetaImagen, BorderLayout.CENTER );

        comboCanciones = new JComboBox( );
        comboCanciones.setActionCommand( CAMBIO_CANCION );
        comboCanciones.addActionListener( this );
        JPanel aux = new JPanel( new BorderLayout( ) );
        aux.add( comboCanciones, BorderLayout.CENTER );

        btnEliminarArtista = new JButton( ELIMINAR_ARTISTA );
        btnEliminarArtista.setActionCommand( ELIMINAR_ARTISTA );
        btnEliminarArtista.addActionListener( this );

        aux.add( btnEliminarArtista, BorderLayout.SOUTH );
        artista.add( aux, BorderLayout.SOUTH );

        add( artista, BorderLayout.CENTER );

        actualizarArtista( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Actualiza el combo box de artistas
     * @param pArtistas Lista de artistas. pArtistas != null
     */
    public void actualizarArtistas( ArrayList pArtistas )
    {
        comboArtistas.removeAllItems( );
        for( int i = 0; i < pArtistas.size( ); i++ )
        {
            Artista a = ( Artista )pArtistas.get( i );
            comboArtistas.addItem( a );
        }
    }

    /**
     * Retorna el artista seleccionado.
     * @return Artista seleccionado o null si no hay selección.
     */
    public Artista darArtistaSeleccionado( )
    {
        return ( Artista )comboArtistas.getSelectedItem( );
    }

    /**
     * Retorna la canción seleccionada.
     * @return Artista seleccionado o null si no hay selección.
     */
    public Cancion darCancionSeleccionada( )
    {
        return ( Cancion )comboCanciones.getSelectedItem( );
    }

    /**
     * Actualiza la información del artista seleccionado
     */
    public void actualizarArtista( )
    {
        Artista a = ( Artista )comboArtistas.getSelectedItem( );
        comboCanciones.removeAllItems( );

        comboArtistas.setEnabled( comboArtistas.getItemCount( ) > 0 );
        comboCanciones.setEnabled( false );

        if( a != null )
        {
            txtNombre.setText( a.darNombre( ) );
            etiquetaImagen.setIcon( new ImageIcon( a.darImagen( ) ) );

            ArrayList canciones = principal.darCancionesArtista( a.darNombre( ) );
            comboCanciones.setEnabled( !canciones.isEmpty( ) );
            for( int i = 0; i < canciones.size( ); i++ )
            {
                Cancion c = ( Cancion )canciones.get( i );
                comboCanciones.addItem( c );
            }
            btnEliminarArtista.setEnabled( true );
        }
        else
        {
            txtNombre.setText( "" );
            etiquetaImagen.setIcon( null );
            btnEliminarArtista.setEnabled( false );
        }
    }

    /**
     * Manejo de los eventos de los botones
     * @param pEvento Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent pEvento )
    {
        String comando = pEvento.getActionCommand( );
        if( comando.equals( CAMBIO_ARTISTA ) )
        {
            actualizarArtista( );
        }
        else if( comando.equals( CAMBIO_CANCION ) )
        {
            Cancion c = ( Cancion )comboCanciones.getSelectedItem( );
            principal.actualizarCancion( c );
        }
        else if( comando.equals( ELIMINAR_ARTISTA ) )
        {
            principal.eliminarArtistaSeleccionado( );
        }
    }
}