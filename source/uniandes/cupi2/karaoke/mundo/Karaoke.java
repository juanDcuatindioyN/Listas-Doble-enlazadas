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

package uniandes.cupi2.karaoke.mundo;

import java.util.ArrayList;

/**
 * Representa un Karaoke
 */
public class Karaoke
{
	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Arreglo de constantes necesarios para el manejo de las categor�as del karaoke.
	 */
	public static final String[] CATEGORIAS = new String[] {"Rock","Pop","Reggae","Tropical","Electr�nica"};

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Primer artista de la lista
	 */
	public Artista primerArtista;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Constructor del karaoke. <br>
	 */
	public Karaoke( )
	{
		primerArtista = null;

	}

	// -----------------------------------------------------------------
	// M�todos
	// -----------------------------------------------------------------

	/**
	 * Agrega un artista a la categor�a del karaoke.<br>
	 * El artista se agrega en la posici�n que le corresponda de tal manera que la lista quede ordenada por nombre de manera ascendente.<br>
	 * <b> post: </b> Se ha agregado un nuevo artista del karaoke
	 * @param pCategoria Nombre de la categor�a. pCategoria pertenece a Karaoke.CATEGORIAS
	 * @param pNombre Nombre del artista. pNombre != null y pNombre != ""
	 * @param pImagen Ruta del archivo con la imagen del artista. pImagen != null y pImagen != ""
	 * @throws Exception Si ya existe un artista con el mismo nombre.
	 */
	public void agregarArtista( String pCategoria, String pNombre, String pImagen ) throws Exception
	{
		if(buscarArtista(pNombre)!=null)
			throw new Exception("Ya existe un artista con el nombre "+pNombre);

		Artista nuevoArtista = new Artista(pCategoria, pNombre, pImagen);

		if(primerArtista==null) {

			primerArtista=nuevoArtista;
		}
		else if(primerArtista.darNombre().compareTo(nuevoArtista.darNombre())>0) {

			nuevoArtista.cambiarSiguiente(primerArtista);
			primerArtista.cambiarAnterior(nuevoArtista);
			primerArtista=nuevoArtista;
		}

		else
		{
			Artista osArtista = primerArtista;

			while(osArtista.darSiguiente()!=null && osArtista.darSiguiente().darNombre().compareTo(nuevoArtista.darNombre())<0) 
			{
				osArtista = osArtista.darSiguiente();
			}

			if(osArtista.darSiguiente()==null)
			{
				osArtista.cambiarSiguiente(nuevoArtista);
				nuevoArtista.cambiarAnterior(osArtista);	
			}

			else 
			{	   			
				nuevoArtista.cambiarAnterior(osArtista);
				nuevoArtista.cambiarSiguiente(osArtista.darSiguiente());
				osArtista.darSiguiente().cambiarAnterior(nuevoArtista);
				osArtista.cambiarSiguiente(nuevoArtista);    		
			}
		}

	}

	/**
	 * Busca un artista con el nombre.
	 * @param pNombre Nombre del artista. pNombre != null y pNombre != ""
	 * @return El artista con el nombre dado. Si no existe un artista con ese nombre se retorna null
	 */
	public Artista buscarArtista( String pNombre )
	{
		Artista osArtista = primerArtista;
		while(osArtista!=null && !osArtista.darNombre().equals(pNombre))
		{
			osArtista = osArtista.darSiguiente();
		}
		return osArtista;
	}

	/**
	 * Agrega una nueva canci�n a un artista del karaoke <br>
	 * <b> pre: </b> El artista existe.<br>
	 * <b> post: </b> Se ha agregado una nueva canci�n al un artista dado
	 * @param pArtista Nombre del artista int�rprete de la canci�n. pArtista != null y pArtista != ""
	 * @param pNombre Nombre de la canci�n. pNombre != null y pNombre != ""
	 * @param pDuracion Duraci�n en segundos de la canci�n. pDuracion > 0
	 * @param pLetra Letra de la canci�n. pLetra != null y pLetra != ""
	 * @param pDificultad Dificultad de la canci�n. pDificultad >= 1 y pDificultad <= 10
	 * @param pRuta Ruta del archivo con la canci�n. pRuta != null y pRuta != ""
	 * @throws Exception Si el artista ya tiene una canci�n con ese nombre.
	 */
	public void agregarCancion( String pArtista, String pNombre, int pDuracion, String pLetra, int pDificultad, String pRuta ) throws Exception
	{
		buscarArtista(pArtista).agregarCancion(pNombre, pDuracion, pLetra, pDificultad, pRuta);
	}

	/**
	 * Elimina al artista con nombre dado del karaoke.<br>
	 * <b>pre:</b>El artista existe.<br>
	 * <b>post</b>Se elimin� el artista del karaoke.
	 * @param pNombre Nombre del artista. pNombre !=null && pNombre!="".
	 */
	public void eliminarArtista( String pNombre )
	{
		if(primerArtista.darNombre().equals(pNombre)) {
			primerArtista = primerArtista.darSiguiente();
			primerArtista.cambiarAnterior(null);
		}

		else {

			Artista auxiliar = primerArtista;
			while(!auxiliar.darNombre().equals(pNombre)) {

				auxiliar = auxiliar.darSiguiente();
			}

			auxiliar.darAnterior().cambiarSiguiente(auxiliar.darSiguiente());

			if(auxiliar.darSiguiente()!=null)
			{
				auxiliar.darSiguiente().cambiarAnterior(auxiliar.darAnterior());
			}
			auxiliar = null;

		}
	}

	/**
	 * Elimina la canci�n con el nombre dado del artista con nombre dado.<br>
	 * <b>pre: </b>Tanto el artista como la canci�n existen.<br>
	 * <b>post:</b> Se elimin� la canci�n de la lista de canciones del artista.
	 * @param pNombreArtista Nombre del artista. pNombreArtista!=null && pNombreArtista!="".
	 * @param pNombreCancion Nombre de la canci�n a eliminar. pNombreCancion != null && pNombreCancion != "".
	 */
	public void eliminarCancion( String pNombreArtista, String pNombreCancion )
	{
		buscarArtista(pNombreArtista).eliminarCancion(pNombreCancion);
	}

	/**
	 * Retorna una lista con los artistas que pertenecen a una categor�a.
	 * @param pCategoria Categor�a de la cual se quieren los artistas. pCategoria pertenece a Karaoke.CATEGORIAS.
	 * @return Lista con los artistas de la categor�a dada.
	 */
	public ArrayList<Artista> darArtistasCategoria( String pCategoria )
	{
		Artista auxiliar = primerArtista;
		ArrayList<Artista>artistasCategoria = new ArrayList<Artista>();
		while(auxiliar!=null)
		{
			if(auxiliar.darCategoria().equals(pCategoria))
				artistasCategoria.add(auxiliar);

			auxiliar = auxiliar.darSiguiente();
		}
		return artistasCategoria;
	}

	/**
	 * Retorna la lista de canciones de un artista con el nombre recibido por par�metro.<br>
	 * <b> pre: </b> El artista existe.
	 * @param pNombre Nombre del artista. pNombre != null y pNombre != ""
	 * @return La lista de canciones del artista
	 */
	public ArrayList<Cancion> darCancionesArtista( String pNombre )
	{
		return buscarArtista(pNombre).darCanciones();
	}

	/**
	 * Busca la canci�n con mayor dificultad. Si existen varias canciones con la misma dificultad retorna la primera canci�n encontrada.
	 * @return La canci�n con mayor dificultad. Si ning�n artista tiene canciones se retorna null
	 */
	public Cancion darCancionMasDificil( )
	{
		Cancion osCancion = null;
		int dificultad = 0;
		for(Artista actual = primerArtista; actual!=null; actual=actual.darSiguiente()) {

			Cancion temporal = actual.darCancionMasDificil();
			if(temporal!=null && temporal.darDificultad()>dificultad)
			{
				osCancion = temporal;
				dificultad = temporal.darDificultad();
			}
		}

		return osCancion;

	}

	/**
	 * Busca la canci�n con menor dificultad. Si existen varias canciones con la misma dificultad retorna la primera canci�n encontrada.
	 * @return La canci�n con menor dificultad. Si ning�n artista tiene canciones se retorna null
	 */
	public Cancion darCancionMasFacil( )
	{
		Cancion osCancion = null;
		int dificultad = 11;
		for(Artista actual = primerArtista; actual!=null; actual=actual.darSiguiente()) {

			Cancion temporal = actual.darCancionMasFacil();
			if(temporal!=null && temporal.darDificultad()<dificultad)
			{
				osCancion = temporal;
				dificultad = temporal.darDificultad();
			}
		}

		return osCancion;
	}

	/**
	 * Busca la canci�n con mayor duraci�n. Si existen varias canciones con la misma duraci�n retorna la primera canci�n encontrada.
	 * @return La canci�n con mayor duraci�n. Si ning�n artista tiene canciones se retorna null
	 */
	public Cancion darCancionMasLarga( )
	{
		Cancion larga = null;
		int duracion = 0;
		for(Artista actual = primerArtista; actual!=null; actual=actual.darSiguiente()) {

			Cancion temporal = actual.darCancionMasLarga();
			if(temporal!=null && temporal.darDuracion()>duracion)
			{
				larga = temporal;
				duracion = temporal.darDuracion();
			}
		}
		return larga;
	}

	/**
	 * Busca la canci�n con menor duraci�n. Si existen varias canciones con la misma duraci�n retorna la primera canci�n encontrada.
	 * @return La canci�n con menor duraci�n. Si ning�n artista tiene canciones se retorna null
	 */
	public Cancion darCancionMasCorta( )
	{
		Cancion corta = null;
		int duracion = 999999;
		for(Artista actual = primerArtista; actual!=null; actual=actual.darSiguiente()) {

			Cancion temporal = actual.darCancionMasCorta();
			if(temporal!=null && temporal.darDuracion()<duracion)
			{
				corta = temporal;
				duracion = temporal.darDuracion();
			}
		}
		return corta;
	}

	/**
	 * Busca el artista con mayor n�mero de canciones. Si existen varios artistas con el mismo n�mero de canciones retorna el primer artista encontrado.
	 * @return El artista de alguna categor�a con mayor n�mero de canciones. Si no hay artistas se retorna null
	 */
	public Artista darArtistaMasCanciones( )
	{
		int cantCanciones = 0;
		Artista osArtista =null;
		Artista actual = primerArtista;
		while(actual!=null)
		{
			if(actual.darCanciones().size()>cantCanciones)
			{
				osArtista = actual;
				cantCanciones = osArtista.darCanciones().size();
			}
			actual = actual.darSiguiente();
		}
		return osArtista;
	}

	/**
	 * Retorna una lista con todas las canciones de la categor�a con el nombre dado.<br>
	 * <b> pre: </b> La categor�a existe.
	 * @param pNombre Nombre de la categor�a. pertenece a Karaoke.CATEGORIAS
	 * @return Lista con todas las canciones de una categor�a
	 */
	public ArrayList<Cancion> darCancionesCategoria( String pNombre )
	{
		ArrayList<Cancion>cancionesCategoria = new ArrayList<Cancion>();

		for(Artista actual = primerArtista; actual!=null; actual= actual.darSiguiente()) {

			if(actual.darCategoria().equals(pNombre))
			{
				cancionesCategoria.addAll(actual.darCanciones());
			}
		}

		return cancionesCategoria;
	}
        
        /**
         * Calcula y devuelve la cantidad de canciones de artistad de Rock o Pop
         * @return Cantidad de canciones de artistas de Rock o Pop
         * @throws Exception - Cuando no hay artistas
         */
        public int darCatidadCancionesRockPop( ) throws Exception{
            
            //crear la variable para contar las canciones 
            int contador = 0;
            
            //Preguntar si la lista esta vacioa, si es así lanzas la excepcion 
            
             if(primerArtista==null){
                 throw new Exception ("Error: no existen artistas: (");
             }
             
             //Realizar el primer recorrido para saber si el artista es Rock o Pop
             
             for(Artista actualArtista = primerArtista; actualArtista !=null; actualArtista= actualArtista.darSiguiente()){
                 if (actualArtista.darCategoria().equals(CATEGORIAS[0]) || actualArtista.darCategoria().equals(CATEGORIAS[1])){
                     //devuelve lka primear cancion de un artista
                     Cancion actualCancion = actualArtista.darPrimeraCancion();
                     
                     //Recorrido para contar las canciones de rock o pop
                     while(actualCancion !=null){
                         
                         //Cuenta las canciones
                         contador++;
                         
                         //Incremento para pasar de cancion en cancion
                         actualCancion= actualCancion.darSiguiente();
                     }
                 }
                     
             }
           
                    
            
            
            // retornar la cantidad de canciones encontradas
            return contador;
        }

	// -----------------------------------------------------------------
	// Puntos de Extensi�n
	// -----------------------------------------------------------------

	/**
	 * M�todo para la extensi�n 1
	 * @return respuesta1
	 */
	public String metodo1(  )
	{
		          try {
                int resultado = darCatidadCancionesRockPop();
                return "La cantidad de canciones rock y pop son: "+resultado;
            } catch (Exception e) {
                return e.getMessage();
            }
	}

	/**
	 * M�todo para la extensi�n2
	 * @return respuesta2
	 */
	public String metodo2( )
	{
		return "Respuesta 2";
	}

}