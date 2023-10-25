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

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

/**
 * Clase que representar el dialogo para las busquedas.
 */
@SuppressWarnings("serial")
public class DialogoBuscar extends JDialog implements ActionListener{

	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Comando Buscar
	 */
	private static final String BUSCAR = "Buscar";

	/**
	 * Comando asociado a la canción más facil
	 */
	private static final String MAS_FACIL = "Canción más facil";

	/**
	 * Comando asociado a la canción más dificil
	 */
	private static final String MAS_DIFICIL = "Canción más dificil";
	/**
	 * Comando asociado a la canción más larga
	 */
	private static final String MAS_LARGA = "Canción más larga";
	/**
	 * Comando asociado a la canción más corta
	 */
	private static final String MAS_CORTA = "Canción más corta";
	/**
	 * Comando asociado a artista con más canciones
	 */
	private static final String MAS_CANCIONES = "Artista con más canciones";

	// -----------------------------------------------------------------
	// Atributos de Interfaz
	// -----------------------------------------------------------------

	/**
	 * Botón Buscar
	 */
	private JButton btnBuscar;

	/**
	 * Radio Button asociado a la canción más facil
	 */
	private JRadioButton rbMasFacil;

	/**
	 * Radio Button asociado a la canción más dificil
	 */
	private JRadioButton rbMasDificil;

	/**
	 * Radio Button asociado a la canción más corta
	 */
	private JRadioButton rbMasCorta;

	/**
	 * Radio Button asociado a la canción más larga
	 */
	private JRadioButton rbMasLarga;

	/**
	 * Radio Button asociado al artista con más canciones.
	 */
	private JRadioButton rbMasCanciones;

	/**
	 * Grupo de botones
	 */
	private ButtonGroup btnGrupoBusqueda;

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Ventana principal de la aplicación.
	 */
	private InterfazKaraoke principal;

	/**
	 * Opción seleccionada.
	 */
	private String opcSeleccionada;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Crea el panel de facturas
	 * @param pPrincipal Instancia del panel contenedor. pPrincipal != null.
	 */
	public DialogoBuscar(InterfazKaraoke pPrincipal) {
		super(pPrincipal, true);

		principal= pPrincipal;
		setTitle( "Buscar" );
		setSize( 220, 250 );
		setLocationRelativeTo( principal );
		opcSeleccionada = null;

		JPanel contenedor = new JPanel(new GridLayout(6, 1));
		contenedor.setBorder(new EmptyBorder(10, 10, 10, 10));

		btnGrupoBusqueda = new ButtonGroup();

		rbMasFacil = new JRadioButton("Canción más Facil");
		rbMasFacil.setActionCommand(MAS_FACIL);
		rbMasFacil.addActionListener(this);

		rbMasDificil = new JRadioButton("Canción más Dificil");
		rbMasDificil.setActionCommand(MAS_DIFICIL);
		rbMasDificil.addActionListener(this);

		rbMasCorta = new JRadioButton("Canción más Corta");
		rbMasCorta.setActionCommand(MAS_CORTA);
		rbMasCorta.addActionListener(this);

		rbMasLarga = new JRadioButton("Canción más Larga");
		rbMasLarga.setActionCommand(MAS_LARGA);
		rbMasLarga.addActionListener(this);

		rbMasCanciones = new JRadioButton("Artista con más canciones");
		rbMasCanciones.setActionCommand(MAS_CANCIONES);
		rbMasCanciones.addActionListener(this);

		btnGrupoBusqueda.add(rbMasFacil);
		btnGrupoBusqueda.add(rbMasDificil);
		btnGrupoBusqueda.add(rbMasCorta);
		btnGrupoBusqueda.add(rbMasLarga);
		btnGrupoBusqueda.add(rbMasCanciones);

		contenedor.add(rbMasFacil);
		contenedor.add(rbMasDificil);
		contenedor.add(rbMasCorta);
		contenedor.add(rbMasLarga);
		contenedor.add(rbMasCanciones);

		rbMasFacil.setSelected(true);

		opcSeleccionada = btnGrupoBusqueda.getSelection().getActionCommand();


		btnBuscar = new JButton(BUSCAR);
		btnBuscar.setActionCommand(BUSCAR);
		btnBuscar.addActionListener(this);
		contenedor.add(btnBuscar);

		add(contenedor);
	}

	//-----------------------------------------------------------------
	// Métodos
	//-----------------------------------------------------------------

	/**
	 * Manejo de los eventos de los botones
	 * @param pEvento Acción que generó el evento.
	 */
	public void actionPerformed( ActionEvent pEvento )
	{

		if(pEvento.getActionCommand().equals(BUSCAR))
		{
			dispose();
			if(opcSeleccionada.equals(MAS_FACIL))
				principal.mostrarCancionMasFacil();

			else if(opcSeleccionada.equals(MAS_DIFICIL))
				principal.mostrarCancionMasDificil();

			else if(opcSeleccionada.equals(MAS_LARGA))
				principal.mostrarCancionMasLarga();

			else if(opcSeleccionada.equals(MAS_CORTA))
				principal.mostrarCancionMasCorta();

			else
				principal.mostrarArtistaMasCanciones();
		}
		else
			opcSeleccionada = btnGrupoBusqueda.getSelection().getActionCommand();
	}

}
