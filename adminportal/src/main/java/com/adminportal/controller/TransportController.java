package com.adminportal.controller;

import com.adminportal.domain.DhlTransport;
import com.adminportal.domain.TariffZone;
import com.adminportal.service.CountriesService;
import com.adminportal.service.DhlTransportService;
import com.adminportal.service.TariffZoneService;
import com.google.common.base.Strings;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-04-22
 */
@Slf4j
@RequiredArgsConstructor
@RequestMapping("transport")
@Controller
public class TransportController {

    private static final String REDIRECT_TRANSPORT_LIST = "redirect:/transport/transportList";
    private static final String REDIRECT_TRANSPORT_TARIFF_ZONE_LIST = "redirect:/transport/transportTariffZoneList";

    private final DhlTransportService dhlTransportService;
    private final TariffZoneService tariffZoneService;
    private final CountriesService countriesService;

    @GetMapping("/transportList")
    public String transportList(Model model) {
        model.addAttribute("transportList", dhlTransportService.findAll());

        return "transport/transportList";
    }

    @GetMapping("/transportTariffZoneList")
    public String transportTariffZone(Model model) {
        model.addAttribute("transportTariffZoneList", tariffZoneService.findAll());

        return "transport/transportTariffZoneList";
    }

    @GetMapping("/addTransport")
    public String addTransport(Model model) {
        DhlTransport dhlTransport = new DhlTransport();
        model.addAttribute("dhlTransport", dhlTransport);
        model.addAttribute("weightKg", 100);
        model.addAttribute("currentPage","transport");
        return "transport/transportAdd";
    }

    @PostMapping("/addTransport")
    public String addTransportPost(@ModelAttribute("dhlTransport") DhlTransport dhlTransport) {
        if (Objects.isNull(dhlTransport)) {
            log.info("DhlTransport is empty.");
            return null;
        }

        dhlTransportService.save(dhlTransport);

        return REDIRECT_TRANSPORT_LIST;
    }

    @GetMapping("/addTariffZone")
    public String addTariffZone(Model model) {
        TariffZone tariffZone = new TariffZone();
        model.addAttribute("tariffZone", tariffZone);
        model.addAttribute("tariffZoneNum", 10);
        model.addAttribute("currentPage","transport");
        model.addAttribute("countries", countriesService.findAllByActive());
        return "transport/transportTariffZoneAdd";
    }

    @PostMapping("/addTariffZone")
    public String addTariffZonePost(@ModelAttribute("tariffZone") TariffZone tariffZone) {

        if (Objects.isNull(tariffZone)) {
            log.info("TariffZone is empty.");
            return null;
        }

        tariffZoneService.save(tariffZone);

        return REDIRECT_TRANSPORT_TARIFF_ZONE_LIST;
    }

    @PostMapping("/removeTransport")
    public String removeTransport(@ModelAttribute("id") String id, Model model) {
        if (Strings.isNullOrEmpty(id)) {
            return "redirect:/";
        }
        dhlTransportService.removeTransport(Integer.parseInt(id.substring(10)));
        model.addAttribute("transportList", tariffZoneService.findAll());

        return REDIRECT_TRANSPORT_LIST;
    }

    @PostMapping("/removeZone")
    public String removeZone(@ModelAttribute("id") String id, Model model) {
        if (Strings.isNullOrEmpty(id)) {
            return "redirect:/";
        }
        tariffZoneService.removeTariffZone(Integer.parseInt(id.substring(5)));
        model.addAttribute("transportTariffZoneList", tariffZoneService.findAll());

        return REDIRECT_TRANSPORT_TARIFF_ZONE_LIST;
    }

}
