/*
 *	File : LibraryWebpageApplication.java
 *	Name : Shin Dae Hong
 *	Data : 2024 / 01 / 01
 *	objective : Class LibraryWebpageApplication
 *			Main BackEnd Server Application Start file
 *
 *	Modified
 *	=============================================================
 *		1	|	01/01	|	First Write
 *		2	|	01/01	|	Add Logger
 *	=============================================================
 */


package LibraryWebpage_Demo.LibraryWebpage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalTime;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaAuditing
public class LibraryWebpageApplication {

	// Logger Initialize
	private static final Logger logger =
			(Logger) LoggerFactory.getLogger(LibraryWebpageApplication.class);

	// WebPage Running Main Function
	public static void main(String[] args) {
		SpringApplication.run(LibraryWebpageApplication.class, args);
		logger.info("SpringBoot-based LibrarySystem Server was just started : "+LocalTime.now());
		logger.info("URL : http://localhost:8080");
	}

}
