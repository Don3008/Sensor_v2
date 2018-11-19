package siemens.sensor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import siemens.sensor.domain.Message;
import siemens.sensor.repo.MessageRepo;

import javax.validation.Valid;

@Controller
@RequestMapping("message")
public class MessageController {

    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private Validator messageValidator;

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("messages", messageRepo.findAll());
        model.addAttribute("title", "Data list");
        return "message/index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String displayAddMessageForm(Model model) {
        model.addAttribute("title", "Add Data");
        model.addAttribute(new Message());
        return "message/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processAddMessageForm( @ModelAttribute @Valid Message message,
                                        BindingResult bindingResult, Model model) {
        messageValidator.validate(message, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Add Data");
            return "message/add";
        }
        model.addAttribute("title", "Add Data");
        messageRepo.save(message);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveMessageForm(Model model) {
        model.addAttribute("messages", messageRepo.findAll());
        model.addAttribute("title", "Remove Data");
        return "message/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveMessageForm(@RequestParam int[] messageIds) {
        for (int messageId : messageIds) {
            messageRepo.delete(messageId);
        }
        return "redirect:";
    }
}
