package com.sonkabin.controller;

import com.sonkabin.entity.Message;
import com.sonkabin.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/msg")
    public String toMsgPage(HttpSession session, Model model){
        Integer id = (Integer)session.getAttribute("id");
        List<Message> messages = messageService.findAllById(id);
        model.addAttribute("messages",messages);
        return "message/list";
    }

    @PutMapping("/msg/{id}")
    public String marked(@PathVariable("id")Integer id){
        messageService.marked(id);
        return "redirect:/msg";
    }

    @DeleteMapping("/msg/{id}")
    public String delete(@PathVariable("id")Integer id){
        messageService.deleteMsg(id);
        return "redirect:/msg";
    }

}
