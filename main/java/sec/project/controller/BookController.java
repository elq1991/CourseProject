package sec.project.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Contact;
import sec.project.repository.ContactRepository;

@Controller
public class BookController {

    @Autowired
    private ContactRepository contactRepository;

    @RequestMapping("*")
    public String defaultMapping() {
        return "redirect:/book";
    }
    
    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public String loadForm() {
        return "book";
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public String submitForm(@RequestParam String name, @RequestParam String address) {
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        contactRepository.save(new Contact(user, name, address));
        return "book";
    }
    
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String findContact(Model model, @RequestParam String name) {
        List<Contact> contacts = contactRepository.findByUser(name);//bad code, user input name not checked
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
//        if(name.equals(user)){
//            contacts = contactRepository.findByUser(user);
//        }
        
        if(contacts.isEmpty()){
            contacts = contactRepository.findByName(user, name+"%");
            
        }
        model.addAttribute("searchRes", contacts);
        return "search";
    }

}
