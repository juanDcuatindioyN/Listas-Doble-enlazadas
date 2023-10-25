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

package uniandes.cupi2.karaoke.mundo;

/**
 * Representa una canción del karaoke
 */
public class Cancion {
	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Nombre de la canción
	 */
	private String nombre;

	/**
	 * Duración en segundos de la canción
	 */
	private int duracion;

	/**
	 * Letra de la canción
	 */
	private String letra;

	/**
	 * Dificultad de la canción
	 */
	private int dificultad;

	/**
	 * Ruta del archivo con la canción
	 */
	private String ruta;

	/**
	 * La siguiente canción de la lista.
	 */
	private Cancion siguiente;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Crea una Canción. Los atributos son inicializados con las parámetros
	 * recibidos
	 * 
	 * @param pNombre Nombre de la canción. pNombre != null y pNombre != ""
	 * @param pDuracion Duración en segundos de la canción. pDuracion > 0
	 * @param pLetra Letra de la canción. pLetra != null y pLetra != ""
	 * @param pDificultad Dificultad de la canción. pDificultad >= 1 y pDificultad <= 10
	 * @param pRuta Ruta del archivo con la canción. pRuta != null y pRuta != ""
	 */
	public Cancion(String pNombre, int pDuracion, String pLetra, int pDificultad, String pRuta) {
		nombre = pNombre;
		duracion = pDuracion;
		letra = pLetra;
		dificultad = pDificultad;
		ruta = pRuta;

		siguiente = null;

	}

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Retorna el nombre de la canción
	 * @return Nombre de la canción
	 */
	public String darNombre() {
		return nombre;
	}

	/**
	 * Retorna la duración en segundos de la canción
	 * @return Duración de la canción
	 */
	public int darDuracion() {
		return duracion;
	}

	/**
	 * Retorna la letra de la canción
	 * @return Letra de la canción
	 */
	public String darLetra() {
		return letra;
	}

	/**
	 * Retorna la dificultad de la canción
	 * @return Dificultad de la canción
	 */
	public int darDificultad() {
		return dificultad;
	}

	/**
	 * Retorna la ruta de archivo con la canción
	 * @return Ruta del archivo
	 */
	public String darRuta() {
		return ruta;
	}

	/**
	 * Retorna la siguiente canción de la lista.
	 * @return Siguiente canción.
	 */
	public Cancion darSiguiente() {

		return siguiente;
	}

	/**
	 * Cambia la siguiente canción de la lista.
	 * @param pCancion Canción a asignar como siguiente.
	 */
	public void cambiarSiguiente(Cancion pCancion) {

		siguiente = pCancion;
	}

	/**
	 * Devuelve la representación en String de la canción
	 * @return La representación en String de la canción: nombre.
	 */
	public String toString() {
		return nombre;
	}
}