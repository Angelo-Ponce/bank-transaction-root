package com.neoris.services.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

@RestController
@RequestMapping("/api/v1/utils")
@RequiredArgsConstructor
public class UtilController {

    private LocaleResolver localeResolver;

    private HttpServletRequest httpServletRequest;

    private HttpServletResponse httpServletResponse;

    // Ejemplo de cambiar idioma
    @GetMapping("/locale/{loc}")
    public ResponseEntity<Void> cambiarIdioma(@PathVariable("loc") String loc){
        Locale userLocale = null;

        switch (loc) {
            case "en":
                userLocale = Locale.ENGLISH;
                break;
		/*case "fr":
			userLocale = Locale.FRENCH;
			break;*/
            default:
                userLocale = Locale.ROOT;
                break;
        }

        localeResolver.setLocale(httpServletRequest, httpServletResponse, userLocale);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
