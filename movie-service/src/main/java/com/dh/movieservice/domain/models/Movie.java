package com.dh.movieservice.domain.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table ( name = "movies")
public class Movie implements Serializable {

   //serialVersionUID
   /*Eso es porque la clase Applet implementa el interfaz Serializable. Las clases que implementan este interfaz son
   susceptibles de ser serializadas (es decir, las instancias de estas clases pueden guardarse y leerse en/de disco
   -o cualquier otro dispositivo de entrada/salida-).

Simplificando mucho la lógica de serialización, lo que sucede es que los atributos del objeto se convierten en
cadenas de bytes y se guardan en el disco. Para leer un objeto, se leen las cadenas de bytes y se reconstruye el
objeto a partir de ellos.

Imagina que tienes una aplicación que serializa en disco una serie de objetos al cerrarse y los lee en la siguiente
ejecución para mantener los valores que tenían antes. En un momento dado, modificas una de las clases añadiendo un
atributo nuevo. Al ejecutar esta versión de la aplicación por primera vez, intentará leer de disco los objetos que
fueron serializados... pero falta un campo en los objetos de la clase que has modificado (cuando se serializaron el
campo no existia) y tu aplicación "teóricamente" va a leer datos corruptos puesto que "teóricamente" no puede saber
que la clase ha cambiado (veremos que si puede saberlo).

El campo serialVersionUID es el número de versión de la clase. Cuando el objeto es serializado lo primero que hace es
 escribir el serialVersionUID. Si al leer el objeto se dectecta que el valor del serialVersionUID guardado no
 coincide con el actual se lanza una exception InvalidClassException, de modo que el programador puede tratar la
 excepción de manera adecuada (por ejemplo, creando un objeto por defecto para susbtituir al que no puede leerse).

Para que este mecanismo funcione bien, el programador debe proveer el campo private static final long
serialVersionUID en todas las clases que implementen Serializable y en todas las subclases de ellas (este es tu caso)
. El valor es indiferente, pero debes actualizarlo cada vez que modificas tu clase añadiendo o quitando atributos (lo
 más sencillo en incrementarlo en 1). Si el programador no indica este campo la JVM añade uno por su cuenta, sin
 embargo no es demasiado conveniente permitir esto (al cambiar ligeramente el programa o cambiar la JMV podría
 cambiar el valor y darte una desagradable sorpresa)

Realmente esto no es necesario si no vas a serializar los objetos... pero como nunca se sabe, no es mala idea que
proveas este valor y lo mantengas.*/
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue (strategy = GenerationType.IDENTITY)
   private Long id;
   private String name;
   private String genre;
   private String urlStream;

   public Movie () {
      //No-args constructor
   }

   public Movie (Long id, String name, String genre, String urlStream) {
      this.id = id;
      this.name = name;
      this.genre = genre;
      this.urlStream = urlStream;
   }

   public Long getId () {
      return id;
   }

   public void setId (Long id) {
      this.id = id;
   }

   public String getName () {
      return name;
   }

   public void setName (String name) {
      this.name = name;
   }

   public String getGenre () {
      return genre;
   }

   public void setGenre (String genre) {
      this.genre = genre;
   }

   public String getUrlStream () {
      return urlStream;
   }

   public void setUrlStream (String urlStream) {
      this.urlStream = urlStream;
   }

   @Override
   public String toString () {
      return "Movie{" + "id=" + id + ", name='" + name + '\'' + ", genre='" + genre + '\'' + ", urlStream='" + urlStream + '\'' + '}';
   }
}
