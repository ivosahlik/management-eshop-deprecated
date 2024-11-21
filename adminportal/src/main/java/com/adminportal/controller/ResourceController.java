package com.adminportal.controller;

import com.adminportal.service.CategoryService;
import com.adminportal.service.CountriesService;
import com.adminportal.service.DhlTransportService;
import com.adminportal.service.ProductService;
import com.adminportal.service.SettingsService;
import com.adminportal.service.SubCategoryService;
import com.adminportal.service.TariffZoneService;
import com.adminportal.service.TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
@RestController
@RequiredArgsConstructor
public class ResourceController {

	public static final String DELETE_SUCCESS = "delete success";

	private final ProductService productService;
	private final CategoryService categoryService;
	private final SubCategoryService subCategoryService;
	private final TypeService typeService;
	private final SettingsService settingsService;
	private final TariffZoneService tariffZoneService;
	private final DhlTransportService dhlTransportService;
	private final CountriesService countriesService;

	private static final int NUMBER_SUBST = 8;

	private static final int NUMBER_SUBST_ZONE = 12;

	private static final int NUMBER_SUBST_TRANSPORT = 17;

	private static final String SELECTED_ZONE= "selectedZone";

	private static final String SELECTED_TRANSPORT= "selectedTransport";

	@PostMapping("/product/removeList")
	public String removeProductsList(@RequestBody List<String> productIdList){

		productIdList.forEach(id -> productService.removeOne(getLongParse(id)));

		return DELETE_SUCCESS;
	}

	@PostMapping("/category/removeList")
	public String removeCategoriesList(@RequestBody List<String> productIdList){

		productIdList.forEach(id -> categoryService.removeCategory(getLongParse(id)));

		return DELETE_SUCCESS;
	}

	@PostMapping("/subCategory/removeList")
	public String removeSubCategoriesList(@RequestBody List<String> productIdList){

		productIdList.forEach(id -> subCategoryService.removeCategory(getLongParse(id)));

		return DELETE_SUCCESS;
	}

	@PostMapping("/type/removeList")
	public String removeTypeList(@RequestBody List<String> productIdList){

		productIdList.forEach(id -> typeService.removeType(getLongParse(id)));

		return DELETE_SUCCESS;
	}

	@PostMapping("/settings/removeList")
	public String removeSettingsList(@RequestBody List<String> productIdList){

		productIdList.forEach(id -> settingsService.remove(getLongParse(id)));

		return DELETE_SUCCESS;
	}

	@PostMapping("/transport/removeList")
	public String removeTransportList(@RequestBody List<String> transportIdList){

		boolean selectorZone = transportIdList.stream().anyMatch(zone -> zone.startsWith(SELECTED_ZONE));
		boolean selectorTransport = transportIdList.stream().anyMatch(transport -> transport.startsWith(SELECTED_TRANSPORT));

		if (selectorZone) {
			transportIdList.forEach(id -> tariffZoneService.removeTariffZone(getIntegerParse(id,NUMBER_SUBST_ZONE)));
		} else if (selectorTransport) {
			transportIdList.forEach(id -> dhlTransportService.removeTransport(getIntegerParse(id,NUMBER_SUBST_TRANSPORT)));
		}

		return DELETE_SUCCESS;
	}

	private Long getLongParse(String id) {
		return Long.parseLong(id.substring(ResourceController.NUMBER_SUBST));
	}

	private Integer getIntegerParse(String id, int num) {
		return Integer.parseInt(id.substring(num));
	}

	@PostMapping("/countries/removeList")
	public String removeCountriesList(@RequestBody List<String> countriesIdList){

		countriesIdList.forEach(id -> countriesService.remove(getIntegerParse(id,NUMBER_SUBST)));

		return DELETE_SUCCESS;
	}


}
