package com.eshopweb.service.impl;

import com.lowagie.text.DocumentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-03-27
 */
@Slf4j
@Service
public class PdfService {

    @Value("${path.name.ttf}")
    private String getPathNameTtf;

    @Value("${path.name.image.logo.invoice}")
    private String getNameImageLogoInvoice;

    public static final String TTF_SUFFIX = ".ttf";

    public static final String CODING_CP1250 = "cp1250";

    @Autowired
    private TemplateEngine templateEngine;

    public String createPdf(String templateName, Map<String, Object> map) {
        return getContext(templateName, map);
    }

    public void createPdfSaveToDb(String templateName, Map<String, Object> map) {
        String processedHtml = getContext(templateName, map);

        String fileName = UUID.randomUUID().toString();
        final File outputFile = new File("static/pdf/" + fileName + ".pdf");

        try(FileOutputStream os = new FileOutputStream(outputFile)) {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(processedHtml);
            renderer.layout();
            renderer.createPDF(os, false);
            renderer.finishPDF();
            log.info("PDF created successfully");
        } catch(IOException | DocumentException e) {
            log.error("PDF created unsuccessfully {}", e.getMessage());
        }
    }

    private String getContext(String templateName, Map<String, Object> map) {
        Assert.notNull(templateName, "The templateName can not be null");

        Context ctx = new Context();
        map.forEach(ctx::setVariable);
        return templateEngine.process(templateName, ctx);
    }

    public ByteArrayOutputStream render(String processedHtml) throws DocumentException, IOException {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(processedHtml);

        File[] fonts = new File(getPathNameTtf)
                .listFiles(pathname -> pathname.getPath().endsWith(TTF_SUFFIX));

        log.info("Fonts {}", fonts.toString());

        for (int i = 0; i < Objects.requireNonNull(fonts).length; i++) {
            log.info("Add fonts {}", fonts[i].getPath());
            renderer.getFontResolver().addFont(fonts[i].getPath(), CODING_CP1250, true);
        }

        renderer.layout();
        renderer.createPDF(bos, false);
        renderer.finishPDF();

        return bos;

    }

}
