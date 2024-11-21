package com.adminportal.controller;

import com.adminportal.domain.CustomSettings;
import com.adminportal.domain.Message;
import com.adminportal.domain.Settings;
import com.adminportal.service.CustomSettingsService;
import com.adminportal.service.MessageService;
import com.adminportal.service.SettingsService;
import com.google.common.base.Strings;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * Intellij Idea
 * Created by ivosahlik on 27/12/2018
 */
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/settings")
@Controller
public class SettingsController {

    private final SettingsService settingsService;
    private final CustomSettingsService customSettingsService;
    private final MessageService messageService;


    @GetMapping("")
    public String settings(Model model) {

        model.addAttribute("currentPage", "settings");

        model.addAttribute("name", "Tom");
        model.addAttribute("formatted", "<b>blue</b>");

        Settings settingsHtml = settingsService.findOne((long) 2);
        if (Objects.nonNull(settingsHtml)) {
            String html = settingsHtml.getHtml();
            model.addAttribute("htmlToInject", html);
        }

        return "settings/manager";
    }

    @GetMapping("/add")
    public String addSettings(Model model) {

        Settings settings = new Settings();
        model.addAttribute("settings", settings);

        return "settings/add";
    }

    @PostMapping("/add")
    public String addSettingsPost(@ModelAttribute("settings") Settings settings,
                                  Principal principal) {

        if (Objects.isNull(settings)) {
            log.info("Settings is empty.");
            return null;
        }

        LocalDateTime localDateTime = LocalDateTime.now();
        String userCreated = principal.getName();

        settings.setCreated(localDateTime);
        settings.setUserCreated(userCreated);
        settings.setUpdated(localDateTime);
        settings.setUserUpdated(userCreated);

        settingsService.save(settings);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String updateSettings(@RequestParam("id") Long id,
                                 Model model) {
        Settings settings = settingsService.findOne(id);
        model.addAttribute("settings", settings);
        model.addAttribute("currentPage", "settings");
        return "settings/update";
    }

    @PostMapping("/update")
    public String updateSettingsPost(@ModelAttribute("settings") Settings settings,
                                     Principal principal) {
        if (Objects.isNull(settings)) {
            return null;
        }

        Settings settingsOld = settingsService.findOne(settings.getId());

        if (Objects.isNull(settingsOld.getCreated()) || Objects.isNull(settingsOld.getUserCreated())) {
            settings.setCreated(LocalDateTime.now());
            settings.setUserCreated(principal.getName());
        } else {
            settings.setCreated(settingsOld.getCreated());
        }

        settings.setUserCreated(settingsOld.getUserCreated());
        settings.setUpdated(LocalDateTime.now());
        settings.setUserUpdated(principal.getName());


        settingsService.save(settings);

        return "redirect:/";
    }

    @GetMapping("/list")
    public String settingsList(Model model) {
        List<Settings> settingsList = settingsService.findAll();
        model.addAttribute("settingsList", settingsList);
        model.addAttribute("currentPage", "settings");

        return "settings/list";
    }

    @PostMapping("/remove")
    public String remove(@ModelAttribute("id") String id, Model model) {
        if (Strings.isNullOrEmpty(id)) {
            return "redirect:/";
        }
        settingsService.remove(Long.parseLong(id.substring(12)));
        model.addAttribute("typeList", settingsService.findAll());

        return "redirect:/settings/list";
    }

    @GetMapping("/info")
    public String settingsInfo(@RequestParam("id") Long id, Model model) {
        Settings settings = settingsService.findOne(id);
        model.addAttribute("settings", settings);
        model.addAttribute("currentPage", "settings");
        return "settings/detail";
    }

    @GetMapping("/footer")
    public String settingsFooter(Model model) {
        List<CustomSettings> customSettingsList = customSettingsService.findAll();
        model.addAttribute("customSettingsList", customSettingsList);
        return "footer/list";
    }

    @GetMapping("/footer/info")
    public String settingsFooterInfo(@RequestParam("id") Integer id, Model model) {
        CustomSettings customSettings = customSettingsService.findOne(id);
        model.addAttribute("customSettings", customSettings);
        return "footer/detail";
    }

    @GetMapping("/footer/add")
    public String settingsFooterAdd(Model model) {
        CustomSettings customSettings = new CustomSettings();
        model.addAttribute("customSettings", customSettings);
        return "footer/add";
    }

    @GetMapping("/footer/update")
    public String settingsFooterUpdate(@RequestParam("id") Integer id, Model model) {
        CustomSettings customSettings = customSettingsService.findOne(id);
        model.addAttribute("customSettings", customSettings);

        addAppToModel(model, customSettings.getApp());

        return "footer/update";
    }

    @PostMapping("/footer/add")
    public String settingsFooterUpdatePost(@ModelAttribute("customSettings") CustomSettings customSettings, Principal principal) {

        if (Objects.isNull(customSettings)) {
            log.info("Custom settings is empty.");
            return null;
        }

        LocalDateTime localDateTime = LocalDateTime.now();
        String userCreated = principal.getName();

        customSettings.setCreated(localDateTime);
        customSettings.setUserCreated(userCreated);
        customSettings.setUpdated(localDateTime);
        customSettings.setUserUpdated(userCreated);

        customSettingsService.save(customSettings);

        return "redirect:/settings/footer";
    }

    @GetMapping("/message")
    public String settingsMessage(Model model) {
        List<Message> messagesList = messageService.messageList();
        model.addAttribute("messagesList", messagesList);
        return "message/list";
    }

    @GetMapping("/message/info")
    public String settingsMessageInfo(@RequestParam("id") Integer id, Model model) {
        Message message = messageService.findOne(id);
        model.addAttribute("message", message);
        return "message/detail";
    }

    @GetMapping("/message/add")
    public String settingsMessageAdd(Model model) {
        Message message = new Message();
        model.addAttribute("message", message);
        return "message/add";
    }


    @GetMapping("/message/update")
    public String settingsMessageUpdate(@RequestParam("id") Integer id, Model model) {
        Message message = messageService.findOne(id);
        model.addAttribute("message", message);

        addAppToModel(model, message.getApp());

        return "message/update";
    }

    private void addAppToModel(Model model, String app) {
        if (app != null) {
            if (app.contains("membrania")) {
                model.addAttribute("membrania", Boolean.TRUE);
            }

            if (app.contains("dcsolutions")) {
                model.addAttribute("dcsolutions", Boolean.TRUE);
            }
        }
    }

    @PostMapping("/message/add")
    public String settingsMessageUpdatePost(@ModelAttribute("message") Message message) {

        if (Objects.isNull(message)) {
            log.info("Message is empty.");
            return null;
        }

        messageService.save(message);

        return "redirect:/settings/message";
    }

}
