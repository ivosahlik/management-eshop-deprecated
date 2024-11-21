package com.adminportal.controller;

import com.adminportal.domain.VideoBanner;
import com.adminportal.domain.VideoStreams;
import com.adminportal.service.VideoBannerService;
import com.adminportal.service.VideoStreamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-10-18
 */
@RequestMapping("/video")
@RequiredArgsConstructor
@Controller
public class VideoController {

    private final VideoBannerService videoBannerService;
    private final VideoStreamService videoStreamService;

    @GetMapping("/list")
    public String getVideoList(Model model) {
        model.addAttribute("videoBannerList", videoBannerService.getVideoBanner());
        return "banners/video/list";
    }

    @GetMapping("/banner/edit/{id}")
    public String getVideoBannerById(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("videoBanner", videoBannerService.getVideoBannerById(id));
        return "banners/video/edit";
    }

    @GetMapping("/banner/add")
    public String getVideoAdd(Model model) {
        VideoBanner videoBanner = new VideoBanner();
        model.addAttribute("videoBanner", videoBanner);
        return "banners/video/add";
    }

    @PostMapping("/banner/add")
    public String postVideoAdd(@ModelAttribute("videoBanner") VideoBanner videoBanner,
                               Principal principal) {

        if (videoBanner.getId() != null) {
            videoBanner.setId(videoBanner.getId());
        }

        videoBanner.setCreated(LocalDateTime.now());
        videoBanner.setUserCreated(principal.getName());
        videoBannerService.save(videoBanner);

        return "redirect:/video/list";
    }

    @GetMapping("/stream/add")
    public String getVideoStreamAdd(@RequestParam(value = "bannerId") Integer bannerId,
                                    Model model) {
        VideoStreams videoStreams = new VideoStreams();
        videoStreams.setVideo_banner_id(bannerId);
        model.addAttribute("videoStreams", videoStreams);
        return "banners/video/streamAdd";
    }


    @PostMapping("/stream/add")
    public String postVideoStreamAdd(@ModelAttribute("videoStreams") VideoStreams videoStreams) {

        videoStreamService.save(videoStreams);

        return "redirect:/video/list";
    }

    @GetMapping("/stream/list")
    public String getVideoStreamList(Model model) {
        List<VideoStreams> videoStreamsList = videoStreamService.getVideoStreams();
        model.addAttribute("videoStreamsList", videoStreamsList);
        return "banners/video/streamList";
    }

    @GetMapping("/stream/edit")
    public String editStreamById(@RequestParam(value = "id") Integer id, Model model) {
        VideoStreams videoStreams = videoStreamService.findById(id);
        model.addAttribute("videoStreams", videoStreams);
        return "banners/video/streamEdit";
    }

    @PostMapping("/stream/edit")
    public String updateStreamById(@ModelAttribute("videoStreams") VideoStreams videoStreams) {
        videoStreamService.save(videoStreams);
        return "redirect:/video/stream/list";
    }

    @PostMapping("/stream/remove")
    public String videoStreamRemoveById(@RequestParam(value = "id") Integer id) {
        videoStreamService.deleteById(id);
        return "redirect:/video/stream/list";
    }

    @PostMapping("/remove")
    public String videoRemoveById(@RequestParam(value = "id") Integer id) {
        videoBannerService.deleteById(id);
        return "redirect:/video/list";
    }
}
