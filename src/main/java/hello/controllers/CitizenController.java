package hello.controllers;

import hello.domain.Categoria;
import hello.domain.Citizen;
import hello.domain.Sugerencia;
import hello.producers.KafkaProducer;
import hello.services.CategoryService;
import hello.services.CitizenService;
import hello.services.SuggestionService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by pelay on 28/03/2017.
 */
@Controller
@Scope("session")
public class CitizenController {

    @Autowired
    private KafkaProducer kafkaProducer;
    private Citizen citizen;


    private CitizenService citizenService;
    private SuggestionService suggestionService;
    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Autowired
    public void setCitizenService(CitizenService citizenService) {
        this.citizenService = citizenService;
    }

    @Autowired
    public void setSuggestionService(SuggestionService suggestionService) {
        this.suggestionService = suggestionService;
    }
/*
    @RequestMapping("/")
    public String landing(Model model) {
        // model.addAttribute("message", new Message());
        return "index";
    }
*/

    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public String getLogin(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {

        Citizen citizen = citizenService.getCitizen(email);

        if (citizen != null) {
            if (DigestUtils.sha512Hex(password).equals(citizen.getContrasena())) {
                session.setAttribute("citizen", citizen);
                List<Sugerencia> listaSugerencias = getSugerencias(null);
                session.setAttribute("listaSugerencias", listaSugerencias);
                session.setAttribute("listaCategorias",categoryService.findAll() );


                return "/user/index";

            }
        }
        model.addAttribute("error", "Your username and password is invalid.");
        return "index";


    }

    @RequestMapping(value = "/cat")
    public String getSugerenciasCat(@RequestParam String idCat, HttpSession session, Model model) {

       if(idCat.equals("all")){
           session.setAttribute("listaSugerencias", suggestionService.findAll());
       }
       else{
           Long id = Long.parseLong(idCat);
           Categoria cat = categoryService.findById(id);
           session.setAttribute("listaSugerencias", suggestionService.findByCat(cat));

       }
       return "/user/index";




    }


    /*
    @RequestMapping("/send")
    public String send(Model model, @ModelAttribute Message message) {
        kafkaProducer.send("exampleTopic", message.getMessage());
        return "redirect:/";
    }
*/

    private List<Sugerencia> getSugerencias(Categoria c) {
        if (c == null) {

            return suggestionService.findAll();
        } else {
            return suggestionService.findByCat(c);

        }
    }


}
