package com.crewrisk.controller;

import com.crewrisk.controller.main.Attributes;
import com.crewrisk.model.Notification;
import com.crewrisk.model.Offers;
import com.crewrisk.model.enums.OfferStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/offers")
public class OffersController extends Attributes {
    @GetMapping
    public String offers(Model model) {
        AddAttributes(model);
        model.addAttribute("offers", offersRepo.findAll());
        return "offers";
    }

    @GetMapping("/{id}/app")
    public String app(@PathVariable Long id) {
        Offers offer = offersRepo.getReferenceById(id);
        offer.setStatus(OfferStatus.APPROVED);
        offersRepo.save(offer);
        notificationRepo.save(new Notification("Ваше предложение " + offer.getOffer() + " одобрена", offer.getUser()));
        return "redirect:/offers";
    }

    @GetMapping("/{id}/reject")
    public String reject(@PathVariable Long id) {
        Offers offer = offersRepo.getReferenceById(id);
        offer.setStatus(OfferStatus.REJECTED);
        offersRepo.save(offer);
        notificationRepo.save(new Notification("Ваше предложение " + offer.getOffer() + " отклонена", offer.getUser()));
        return "redirect:/offers";
    }
}
