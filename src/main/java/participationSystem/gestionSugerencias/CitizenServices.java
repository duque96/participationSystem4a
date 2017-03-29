package participationSystem.gestionSugerencias;



import java.util.List;

import hello.domain.Categoria;
import hello.domain.Comentario;
import hello.domain.Sugerencia;
import hello.util.exception.CitizenException;

public interface CitizenServices extends SuperService {
	
	//Citizen getCitizen(String email);

	void createSugerencia(String nombre, String contenido, Categoria categoria) throws CitizenException;

	void createComentario(String contenido, Sugerencia sugerencia) throws CitizenException;

	List<Categoria> getCategoriasDisponibles() throws CitizenException;

	void createCategoria(String nombre) throws CitizenException;

	void votePositiveComment(Comentario comment) throws CitizenException;

	void voteNegativeComment(Comentario comment) throws CitizenException;

	void votePositiveSugerencia(Sugerencia sug) throws CitizenException;

	void voteNegativeSugerencia(Sugerencia sug) throws CitizenException;


}
