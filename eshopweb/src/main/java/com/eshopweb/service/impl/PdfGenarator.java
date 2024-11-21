package com.eshopweb.service.impl;

import com.lowagie.text.DocumentException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.IWebContext;
import org.thymeleaf.spring4.context.SpringWebContext;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@Slf4j
@Service
public class PdfGenarator {

	private final TemplateEngine templateEngine;
	private final ApplicationContext context;
    private final ServletContext servletContext;

	@Value("${path.name.ttf}")
	private String getPathNameTtf;

	@Value("${path.name.image.logo.invoice}")
	private String getNameImageLogoInvoice;

	public static final String urlBase = "http://localhost:8080";

	public static final String PATH_NAME_TTF = "static/ttf";

	public static final String TTF_SUFFIX = ".ttf";

	public static final String CODING_CP1250 = "cp1250";

	public ByteArrayOutputStream createPdf(final String templateName, final Map map, final HttpServletRequest request, final HttpServletResponse response)
			throws DocumentException {

		IWebContext ctx = new SpringWebContext(request, response, servletContext, LocaleContextHolder.getLocale(), map, context);

		String processedHtml = templateEngine.process(templateName, ctx);

		try(ByteArrayOutputStream bos = new ByteArrayOutputStream()) {

			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocumentFromString(processedHtml, urlBase);

			ClassPathResource classPathResource = new ClassPathResource(PATH_NAME_TTF);
			File file = classPathResource.getFile();

			File[] fonts = new File(file.getPath())
					.listFiles(pathname -> pathname.getPath().endsWith(TTF_SUFFIX));

			for (int i = 0; i < Objects.requireNonNull(fonts).length; i++) {
				renderer.getFontResolver().addFont(fonts[i].getPath(), CODING_CP1250, true);
			}

			renderer.layout();
			renderer.createPDF(bos, false);
			renderer.finishPDF();
			return bos;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return  null;
	}
}
