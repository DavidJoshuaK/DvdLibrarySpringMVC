/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibraryspringmvc;

import com.mycompany.dvdlibraryspringmvc.dao.DvdLibraryDao;
import com.mycompany.dvdlibraryspringmvc.model.Dvd;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author DavidKing
 */
@CrossOrigin
@Controller
public class RESTController {

    private DvdLibraryDao dao;

    @Inject
    public RESTController(DvdLibraryDao dao) {
        this.dao = dao;
    }

    //retrieveDVD
    @RequestMapping(value = "/dvd/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Dvd getDvd(@PathVariable("id") long id) {
        return dao.getDvdById(id);
    }

    //createDVD
    @RequestMapping(value = "/dvd", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Dvd createDvd(@Valid @RequestBody Dvd dvd) {
        return dao.addDvd(dvd);
    }

    //deleteDvd
    @RequestMapping(value = "/dvd/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDvd(@PathVariable("id") long id) {
        dao.removeDvd(id);
    }

    //updateDvd
    @RequestMapping(value = "/dvd/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateDvd(@PathVariable("id") long id, @Valid @RequestBody Dvd dvd)
    throws UpdateIntegrityException {
       if(id != dvd.getDvdId()){
           throw new UpdateIntegrityException("Id on URL must match Dvd Id in submitted data.");
       }
        //dvd.setDvdId(id);
        dao.updateDvd(dvd);
    }

    //getallDvds
    @RequestMapping(value = "/dvds", method = RequestMethod.GET)
    @ResponseBody
    public List<Dvd> getAllDvds() {
        return dao.getAllDvds();
    }
}
