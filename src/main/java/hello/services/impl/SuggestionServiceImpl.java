package hello.services.impl;

import hello.domain.Categoria;
import hello.domain.Sugerencia;
import hello.services.SuggestionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pelay on 29/03/2017.
 */
@Service
public class SuggestionServiceImpl implements SuggestionService {


    @Override
    public List<Sugerencia> findAll() {
        //TODO
        return null;
    }

    @Override
    public List<Sugerencia> findByCat(Categoria cat) {
        return null;
    }
}
