/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibraryspringmvc;

import com.mycompany.dvdlibraryspringmvc.dao.DvdLibraryDao;
import com.mycompany.dvdlibraryspringmvc.model.Dvd;
import java.time.LocalDate;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author DavidKing
 */
@Controller
public class DvdController {

    DvdLibraryDao dao;

    @Inject
    public DvdController(DvdLibraryDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/displayDvdsPage", method = RequestMethod.GET)
    public String displayDvdsPage(Model model) {

        List<Dvd> dvdLibrary = dao.getAllDvds();

        model.addAttribute("currentDvd", dvdLibrary);

        return "dvds";
    }

    @RequestMapping(value = "/createDvd", method = RequestMethod.POST)
    public String createDvd(HttpServletRequest request) {
       
        Dvd dvd = new Dvd();
        dvd.setTitle(request.getParameter("title"));
        dvd.setReleaseYear(Integer.parseInt(request.getParameter("releaseYear")));
        dvd.setDirector(request.getParameter("director"));
        dvd.setRating(request.getParameter("rating"));
        dvd.setNotes(request.getParameter("notes"));

        dao.addDvd(dvd);

        return "redirect:displayDvdsPage";
    }

    @RequestMapping(value = "/displayDvdDetails", method = RequestMethod.GET)
    public String displayDvdDetails(HttpServletRequest request, Model model) {
        String dvdIdParameter = request.getParameter("dvdId");
        int dvdId = Integer.parseInt(dvdIdParameter);

        Dvd dvd = dao.getDvdById(dvdId);

        model.addAttribute("dvd", dvd);

        return "dvdDetails";
    }

    @RequestMapping(value = "/deleteDvd", method = RequestMethod.GET)
    public String deleteDvd(HttpServletRequest request) {
        String dvdIdParameter = request.getParameter("dvdId");
        long dvdId = Long.parseLong(dvdIdParameter);
        dao.removeDvd(dvdId);
        return "redirect:displayDvdsPage";
    }

    @RequestMapping(value = "/displayEditDvdForm", method = RequestMethod.GET)
    public String displayEditDvdForm(HttpServletRequest request, Model model) {
        String dvdIdParameter = request.getParameter("dvdId");
        long dvdId = Long.parseLong(dvdIdParameter);
        Dvd dvd = dao.getDvdById(dvdId);
        model.addAttribute("dvd", dvd);
        return "editDvdForm";
    }

    @RequestMapping(value = "/editDvd", method = RequestMethod.POST)
    public String editDvd(@Valid @ModelAttribute("dvd") Dvd dvd,
            BindingResult result) {

        if (result.hasErrors()) {
            return "editDvdForm";
        }

        dao.updateDvd(dvd);

        return "redirect:displayDvdsPage";
    }
}
